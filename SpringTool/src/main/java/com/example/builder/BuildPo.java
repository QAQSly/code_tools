package com.example.builder;

import com.example.bean.Constants;
import com.example.bean.FieldInfo;
import com.example.bean.TableInfo;
import com.example.utils.DateUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class BuildPo {
    public static final Logger logger = LoggerFactory.getLogger(BuildPo.class);
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_PO);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        // logger.info("---{}---{}", tableInfo.getTableName(), tableInfo.getBeanName());

        File poFile = new File(folder, tableInfo.getBeanName() + ".java");

        try (OutputStream  out = new FileOutputStream(poFile);
             OutputStreamWriter outW = new OutputStreamWriter(out, "utf-8");
             BufferedWriter bw = new BufferedWriter(outW)) {
            // package
            bw.write("package " + Constants.PACKAGE_PO + ";");
            bw.newLine();
            bw.newLine();
            bw.write("import java.io.Serializable;");
            bw.newLine();
            bw.write(Constants.IGNORE_BEAN_TOJSON_CLASS + ";");
            bw.newLine();
            if (tableInfo.getHaveDate() || tableInfo.getHaveDateTime()) {
                bw.write("import java.util.Date;");
                bw.newLine();
                bw.write(Constants.BEAN_DATE_FORMAT_CLASS + ";");
                bw.newLine();
                bw.write(Constants.BEAN_DATE_UNFORMAT_CLASS + ";");
                bw.newLine();
            }
            if (tableInfo.getHavaBigDecimal()) {
                bw.write("import java.math.BigDecimal;");
                bw.newLine();
            }
            bw.write("import lombok.Getter;\n" + "import lombok.Setter;");
            bw.newLine();
            bw.newLine();

            //  comment
            BuildComment.createClassComment(bw, tableInfo.getComment());
            bw.newLine();
            // get set
            bw.write("@Getter\n" + "@Setter");
            bw.newLine();
            bw.write("public class " + tableInfo.getBeanName() + " implements Serializable {");
            bw.newLine();

            for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
                BuildComment.createFieldComment(bw, fieldInfo.getComment());
                if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, fieldInfo.getSqlType())) {
                    bw.write("\t" + String.format(Constants.BEAN_DATE_FORMAT_EXPRESSION, DateUtils.YYYY_MM_DD_HH_MM_SS));
                    bw.newLine();
                    bw.write("\t" + String.format(Constants.BEAN_DATE_UNFORMAT_EXPRESSION, DateUtils.YYYY_MM_DD_HH_MM_SS));
                    bw.newLine();
                }
                if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, fieldInfo.getSqlType())) {
                    bw.write("\t" + String.format(Constants.BEAN_DATE_FORMAT_EXPRESSION, DateUtils.YYYY_MM_DD));
                    bw.newLine();
                    bw.write("\t" + String.format(Constants.BEAN_DATE_UNFORMAT_EXPRESSION, DateUtils.YYYY_MM_DD));
                    bw.newLine();
                }
                logger.info("------filed" + Constants.IGNORE_BEAN_TOJSON_FIELD.split(","));
                logger.info("-----property" + fieldInfo.getPropertyName());
                if (ArrayUtils.contains(Constants.IGNORE_BEAN_TOJSON_FIELD.split(","), fieldInfo.getPropertyName())) {
                    bw.write("\t" + String.format(Constants.IGNORE_BEAN_TOJSON_EXPRESSION));
                    bw.newLine();
                }
                bw.write("\tprivate " + fieldInfo.getJavaType() + " " + fieldInfo.getPropertyName() + ";");
                bw.newLine();
                bw.newLine();
            }

            bw.write("}");
            bw.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
