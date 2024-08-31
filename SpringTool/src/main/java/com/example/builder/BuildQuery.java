package com.example.builder;

import com.example.bean.Constants;
import com.example.bean.FieldInfo;
import com.example.bean.TableInfo;
import com.example.utils.DateUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuildQuery {
    public static final Logger logger = LoggerFactory.getLogger(BuildPo.class);
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_QUERY);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        // logger.info("---{}---{}", tableInfo.getTableName(), tableInfo.getBeanName());
        String className = tableInfo.getBeanName() + Constants.SUFFIX_BEAN_QUERY;

        File poFile = new File(folder, className + ".java");

        try (OutputStream out = new FileOutputStream(poFile);
             OutputStreamWriter outW = new OutputStreamWriter(out, "utf-8");
             BufferedWriter bw = new BufferedWriter(outW)) {
            // package
            bw.write("package " + Constants.PACKAGE_QUERY + ";");
            bw.newLine();
            bw.newLine();

            if (tableInfo.getHaveDate() || tableInfo.getHaveDateTime()) {
                bw.write("import java.util.Date;");
                bw.newLine();
            }
            if (tableInfo.getHavaBigDecimal()) {
                bw.write("import java.math.BigDecimal;");
                bw.newLine();
            }
            bw.write("import lombok.Getter;\n" + "import lombok.Setter;\n" + "import lombok.ToString;\n");
            bw.newLine();
            bw.newLine();

            //  comment
            BuildComment.createClassComment(bw, tableInfo.getComment() + "查询对象");
            bw.newLine();
            // get set
            bw.write("@Getter\n" + "@Setter\n" + "@ToString");
            bw.newLine();
            bw.write("public class " + className + " {");
            bw.newLine();



            List<FieldInfo> extendList = new ArrayList<>();
            for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
                BuildComment.createFieldComment(bw, fieldInfo, false);

                bw.write("\tprivate " + fieldInfo.getJavaType() + " " + fieldInfo.getPropertyName() + ";");
                bw.newLine();
                bw.newLine();

                if (ArrayUtils.contains(Constants.SQL_STRING_TYPE, fieldInfo.getSqlType())) {
                    String propertyName = fieldInfo.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_FUZZY;
                    bw.write("\tprivate " + fieldInfo.getJavaType() + " " + propertyName + ";");
                    bw.newLine();
                    bw.newLine();

                    FieldInfo fuzzyField = new FieldInfo();
                    fuzzyField.setJavaType(fieldInfo.getJavaType());
                    fuzzyField.setPropertyName(propertyName);
                    extendList.add(fuzzyField);
                }

                if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, fieldInfo.getSqlType()) ||
                    ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, fieldInfo.getSqlType())) {
                    String propertyStartName = fieldInfo.getPropertyName() +  Constants.SUFFIX_BEAN_QUERY_START;
                    String propertyEndName = fieldInfo.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_END;
                    bw.write("\tprivate " + fieldInfo.getJavaType() + " " + propertyStartName + ";");
                    bw.newLine();
                    bw.newLine();

                    bw.write("\tprivate " + fieldInfo.getJavaType() + " " + propertyEndName + ";");
                    bw.newLine();
                    bw.newLine();

                    FieldInfo timeStartField = new FieldInfo();
                    timeStartField.setJavaType("String");
                    timeStartField.setPropertyName(propertyStartName);
                    extendList.add(timeStartField);

                    FieldInfo timeEndField = new FieldInfo();
                    timeEndField.setJavaType("String");
                    timeEndField.setPropertyName(propertyEndName);
                    extendList.add(timeEndField);

                }
            }
            List<FieldInfo> fieldInfoList = tableInfo.getFieldList();

            bw.write("}");
            bw.flush();



        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
