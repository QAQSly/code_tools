package com.example.builder;

import com.example.bean.Constants;
import com.example.bean.FieldInfo;
import com.example.bean.TableInfo;
import com.example.utils.DateUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

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

            //  comment
            BuildComment.createClassComment(bw, tableInfo.getComment());
            bw.newLine();
            // get set
            bw.write("@Getter\n" + "@Setter\n" + "@ToString");
            bw.newLine();
            bw.write("public interface " + className + " extends BaseMapper {");
            bw.newLine();

            bw.write("}");
            bw.flush();



        } catch (Exception e) {
            logger.error("创建Mapper失败");
            e.printStackTrace();
        }
    }

}
