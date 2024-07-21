package com.example.builder;

import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuildComment {
    public static void createClassComment(BufferedWriter bw, String comment) throws Exception {
        bw.write("/**");
        bw.newLine();
        bw.write(" *@Description: " + comment);
        bw.newLine();
        bw.write(" *@Date: " + new SimpleDateFormat().format(new Date()));
        bw.newLine();
        bw.write(" */");
    }

    public static void createFieldComment() {

    }

    public static void createMethodComment() {

    }
}
