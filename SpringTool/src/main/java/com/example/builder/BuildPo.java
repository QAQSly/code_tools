package com.example.builder;

import com.example.bean.Constants;
import com.example.bean.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class BuildPo {
    public static final Logger logger = LoggerFactory.getLogger(BuildPo.class);
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_PO);
        logger.info("{}", Constants.PATH_PO);

        if (!folder.exists()) {
            folder.mkdirs();
            logger.info("{}", "创建PO目录");
        }

        try (OutputStream  out = new OutputStream() {
            @Override
            public void write(int i) throws IOException {

            }
        }) {

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
