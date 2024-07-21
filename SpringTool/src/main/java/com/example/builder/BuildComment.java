package com.example.builder;

import com.example.utils.DateUtils;

import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuildComment {
    public static void createClassComment(BufferedWriter bw, String comment) throws Exception {
        bw.write("/**");
        bw.newLine();
        bw.write(" * @Description: " + comment);
        bw.newLine();
        bw.write(" * @Date: " + DateUtils.format(new Date(), DateUtils.YYYY_MM_DD_SLASH));
        bw.newLine();
        bw.write(" */");
    }

    public static void createFieldComment() {

    }

    public static void createMethodComment() {

    }
}
