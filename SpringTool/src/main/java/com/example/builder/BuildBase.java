package com.example.builder;

import com.example.bean.Constants;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;

import org.slf4j.Logger;

public class BuildBase {
    public static final Logger logger = LoggerFactory.getLogger(BuildBase.class);
    public static void execute() {
        build("DateUtil", Constants.PATH_UTILS);

    }

    private static void build(String fileName, String outPutPath) {
        File folder = new File(outPutPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File javaFile = new File(outPutPath, fileName + ".java");

        InputStream templateIn = BuildBase.class.getClassLoader().getResourceAsStream("template/" + fileName + ".txt");

        try (OutputStream out = new FileOutputStream(javaFile);
             OutputStreamWriter outW = new OutputStreamWriter(out, "utf-8");
             BufferedWriter bw = new BufferedWriter(outW);
             InputStream in = templateIn;
             InputStreamReader inR = new InputStreamReader(in, "utf-8");
             BufferedReader br = new BufferedReader(inR);) {

            bw.write("package " + Constants.PACKAGE_UTILS + ";");
            bw.newLine();
            String line = null;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            logger.info("success");

        } catch (Exception e) {
            logger.error("生成基础类{}失败", fileName, e);
            e.printStackTrace();
        }
    }
}
