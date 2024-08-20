package com.example.builder;

import com.example.bean.Constants;
import com.example.bean.FieldInfo;
import com.example.bean.TableInfo;
import com.example.utils.DateUtils;
import com.example.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import java.io.*;
import java.util.Map;

public class BuildMapper {

    // 日志
    public static final Logger logger = LoggerFactory.getLogger(BuildMapper.class);

    // 执行构造
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_MAPPERS);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        // logger.info("---{}---{}", tableInfo.getTableName(), tableInfo.getBeanName());

        String className = tableInfo.getBeanName() + Constants.SUFFIX_MAPPERS;
        File poFile = new File(folder, className + ".java");

        try (OutputStream out = new FileOutputStream(poFile);
             OutputStreamWriter outW = new OutputStreamWriter(out, "utf-8");
             BufferedWriter bw = new BufferedWriter(outW)) {
            // package
            bw.write("package " + Constants.PACKAGE_MAPPERS + ";");
            bw.newLine();
            bw.newLine();
            bw.write("import org.apache.ibatis.annotations.Param;");
            bw.newLine();
            bw.newLine();

            //  comment
            BuildComment.createClassComment(bw, tableInfo.getComment() + "Mapper");
            bw.newLine();
            // get set
            // bw.write("@Getter\n" + "@Setter\n" + "@ToString");
            //bw.newLine();
            bw.write("public interface " + className + "<T, P> extends BaseMapper {");
            bw.newLine();

            // 根据索引创建
            Map<String, List<FieldInfo>> keyIndexMaps = tableInfo.getKeyIndexMap();


            for (Map.Entry<String, List<FieldInfo>> entry : keyIndexMaps.entrySet()) {
                List<FieldInfo> keyFieldInfoList = entry.getValue();
                Integer index = 0;
                StringBuilder methodName = new StringBuilder();
                StringBuilder methodParam = new StringBuilder();

                for (FieldInfo fieldInfo : keyFieldInfoList) {
                    logger.info("索引大小---" + keyFieldInfoList.size());
                    index++;
                    methodName.append(StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName()));
                    if (index < keyFieldInfoList.size()) {
                        methodName.append("And");
                    }

                    methodParam.append("@Param(\"" + fieldInfo.getPropertyName() + "\") " +
                            fieldInfo.getJavaType() + " " + fieldInfo.getPropertyName());
                    if (index < keyFieldInfoList.size()) {
                        methodName.append(", ");
                    }
                }
                bw.newLine();
                BuildComment.createFieldComment(bw, "根据" + methodName + "查询");
                bw.write("\t T selectBy" + methodName + "(" + methodParam + ");");
                bw.newLine();

                bw.newLine();
                BuildComment.createFieldComment(bw, "根据" + methodName + "更新");
                bw.write("\t T updateBy" + methodName + "(@Param(\"bean\") T t, " + methodParam + ");");
                bw.newLine();

                bw.newLine();
                BuildComment.createFieldComment(bw, "根据" + methodName + "删除");
                bw.write("\t T deleteBy" + methodName + "(" + methodParam + ");");
                bw.newLine();
            }

            bw.write("}");
            bw.flush();



        } catch (Exception e) {
            logger.error("创建Mapper失败");
            e.printStackTrace();
        }
    }

}
