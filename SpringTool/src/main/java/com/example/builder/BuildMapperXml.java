package com.example.builder;

import com.example.bean.Constants;
import com.example.bean.FieldInfo;
import com.example.bean.TableInfo;
import com.example.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BuildMapperXml {
    public static final Logger logger = LoggerFactory.getLogger(BuildMapper.class);

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
            bw.write("</mapper>");

            bw.flush();
        } catch (Exception e) {
            logger.error("创建Mapper Xml失败");
            e.printStackTrace();
        }
    }
}
