package com.example.builder;

import com.example.bean.Constants;
import com.example.bean.FieldInfo;
import com.example.bean.TableInfo;
import com.example.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BuildMapperXml {
    public static final Logger logger = LoggerFactory.getLogger(BuildMapper.class);

    private static final String BASE_COLUMN_LIST = "base_column_list";
    private static final String BASE_CONDITION_FILED = "base_query_condition";
    private static final String QUERY_CONDITION = "query_condition";

    // 执行构造
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_MAPPER_XMLS);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        // logger.info("---{}---{}", tableInfo.getTableName(), tableInfo.getBeanName());

        String className = tableInfo.getBeanName() + Constants.SUFFIX_MAPPERS;
        File poFile = new File(folder, className + ".xml");

        try (OutputStream out = new FileOutputStream(poFile);
             OutputStreamWriter outW = new OutputStreamWriter(out, "utf-8");
             BufferedWriter bw = new BufferedWriter(outW)) {
            // content
            bw.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            bw.newLine();
            bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org// DTD Mapper 3.0//EN\"");
            bw.newLine();
            bw.write("\t\t" +
                    "\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
            bw.newLine();
            bw.write("<mapper namespace=\"" + Constants.PACKAGE_MAPPERS + "." + className + "\">");
            bw.newLine();

            bw.write("<!--实体映射-->");
            bw.newLine();
            String poClass = Constants.PACKAGE_PO + "." + tableInfo.getBeanName();
            bw.write("\t<resultMap id=\"base_result_map\" type=\"" + poClass  + "\">");
            bw.newLine();

            FieldInfo idField = null;
            // 字段
            Map<String, List<FieldInfo>> keyIndexMap = tableInfo.getKeyIndexMap();
            for (Map.Entry<String, List<FieldInfo>> entry : keyIndexMap.entrySet() ) {
                if ("PRIMARY".equals(entry.getKey())) {
                    List<FieldInfo> fieldInfoLists = entry.getValue();
                    if (fieldInfoLists.size() == 1) {
                        idField = fieldInfoLists.get(0);
                        break;
                    }
                }

            }

            for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
                bw.write("\t\t<!-- " + fieldInfo.getComment() + " -->");
                bw.newLine();
                String key = "";
                if (idField != null && fieldInfo.getPropertyName().equals(idField.getPropertyName())) {
                    key = "id";
                } else {
                    key = "result";
                }
                bw.write("\t\t<" + key + " column=\"" + fieldInfo.getFieldName() + "\" property=\"" + fieldInfo.getPropertyName() +"\"/>");
                bw.newLine();
            }

            bw.write("\t</resultMap>");
            bw.newLine();

            bw.newLine();
            // 通用查询列
            QueryColumnsGenerator(bw, tableInfo);

            bw.newLine();
            // 基础查询你条件
            QueryConditionGenerator(bw, tableInfo);
            bw.newLine();

            //  扩展查询条件
            QueryExtendConditionGenerator(bw, tableInfo);

            bw.write("</mapper>");

            bw.flush();
        } catch (Exception e) {
            logger.error("创建Mapper Xml失败");
            e.printStackTrace();
        }
    }
    /**
     * @description: 通用查询结果列生成方法
     * @param: bw tableInfo
     * @return: void
     * @author Sly
     * @date: 2024/8/31 21:16
     */
    static void QueryColumnsGenerator(BufferedWriter bw, TableInfo tableInfo) throws Exception {
        bw.write("\t<!--通用查询结果列-->");
        bw.newLine();

        bw.write("\t<sql id=\"" + BASE_COLUMN_LIST + "\">");
        bw.newLine();
        StringBuilder columnBuilder = new StringBuilder();
        for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
            columnBuilder.append(fieldInfo.getFieldName()).append(",");
        }
        String columnBuilderStr = columnBuilder.substring(0, columnBuilder.lastIndexOf(","));
        bw.write("\t\t" + columnBuilderStr);
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
    }

    /**
     * @description: 基础查询条件
     * @param: null
     * @return:
     * @author Sly
     * @date: 2024/8/31 21:17
     */
    static void QueryConditionGenerator(BufferedWriter bw, TableInfo tableInfo) throws Exception {
        bw.write("\t<!--基础查询结果列-->");
        bw.newLine();

        bw.write("\t<sql id=\"" + BASE_CONDITION_FILED + "\">");
        bw.newLine();

        for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
            String emptyCondition = "";
            if (ArrayUtils.contains(Constants.SQL_STRING_TYPE, fieldInfo.getSqlType())) {
                emptyCondition = " and query." + fieldInfo.getPropertyName() + " != ''";
            }
            bw.write("\t\t<if test=\"query." + fieldInfo.getPropertyName() + " != null" + emptyCondition  + "\">");
            bw.newLine();
            bw.write("\t\t\tand " + fieldInfo.getFieldName() + " = #{query." + fieldInfo.getPropertyName() + " }");
            bw.newLine();
            bw.write("\t\t</if>");
            bw.newLine();
        }
        bw.write("\t</sql>");
        bw.newLine();
    }
    /**
     * @description: 扩展查询条件生成方法
     * @param: bw
tableInfo
     * @return: void
     * @author Sly
     * @date: 2024/9/1 21:27
     */
    static void QueryExtendConditionGenerator(BufferedWriter bw, TableInfo tableInfo) throws Exception {
        bw.write("\t<!--扩展查询条件-->");
        bw.newLine();

        bw.write("\t<sql id=\"" + QUERY_CONDITION + "\">");
        bw.newLine();

        for (FieldInfo fieldInfo : tableInfo.getFieldExtendList()) {
            String andWhere = "";
            if (ArrayUtils.contains(Constants.SQL_STRING_TYPE, fieldInfo.getSqlType())) {
                andWhere = " and " + fieldInfo.getFieldName() +  " like concat('%', #{query." + fieldInfo.getPropertyName() + "}, '%')";
            } else if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, fieldInfo.getSqlType()) ||
            ArrayUtils.contains(Constants.SQL_DATE_TYPES, fieldInfo.getSqlType())) {
                andWhere = "<![CDATA[ and " +  fieldInfo.getFieldName() + " >= str_to_date(#{query." + fieldInfo.getPropertyName() + "}, %Y-%m-%d) ]]>";
            }
            bw.write("\t\t<if test=\"query." + fieldInfo.getPropertyName() + " != null" + " and query." + fieldInfo.getPropertyName() + " != ''"  + "\">");
            bw.newLine();
            bw.write("\t\t\t" + andWhere);
            bw.newLine();
            bw.write("\t\t</if>");
            bw.newLine();
        }
        bw.write("\t</sql>");
        bw.newLine();
    }


}
