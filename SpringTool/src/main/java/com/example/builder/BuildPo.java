package com.example.builder;

import com.example.bean.Constants;
import com.example.bean.TableInfo;
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

        File poFile = new File(folder, tableInfo.getBeanName() + ".java");

        try (OutputStream  out = new FileOutputStream(poFile);
             OutputStreamWriter outW = new OutputStreamWriter(out, "utf-8");
             BufferedWriter bw = new BufferedWriter(outW)) {
            // package
            bw.write("package " + Constants.PACKAGE_PO + ";");
            bw.newLine();
            bw.write("import java.io.Serializable;");
            bw.newLine();
            bw.write("public class " + tableInfo.getBeanName() + " implements Serializable {");
            bw.newLine();
            bw.write("}");
            bw.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
