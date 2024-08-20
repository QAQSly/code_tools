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

             bw.flush();
        } catch (Exception e) {
            logger.error("创建Mapper Xml失败");
            e.printStackTrace();
        }
    }
}
