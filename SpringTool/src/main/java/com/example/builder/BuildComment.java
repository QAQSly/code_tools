package com.example.builder;

import com.example.bean.Constants;
import com.example.bean.FieldInfo;
import com.example.utils.DateUtils;
import com.example.utils.YmlUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuildComment {
    public static void createClassComment(BufferedWriter bw, String comment) throws Exception {
        bw.write("/**");
        bw.newLine();
        bw.write(" * @Description: " + comment);
        bw.newLine();
        bw.write(" * @author: " + Constants.AUTHOR);
        bw.newLine();
        bw.write(" * @Date: " + DateUtils.format(new Date(), DateUtils.YYYY_MM_DD_SLASH));
        bw.newLine();
        bw.write(" */");
    }

    public static void createFieldComment(BufferedWriter bw, FieldInfo fieldInfo, Boolean isToString) throws Exception {
        bw.write("\t//" + (fieldInfo.getComment() == null ? "" : fieldInfo.getComment()));
        bw.newLine();
        if (isToString) {
            if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, fieldInfo.getSqlType()) ||
                    ArrayUtils.contains(Constants.SQL_DATE_TYPES, fieldInfo.getSqlType())) {
                bw.write("\t" + String.format("@ToString.Exclude()\n", fieldInfo.getComment()));
                return;
            }
            bw.write("\t" + String.format("@ToString.Include(name = \"%s\")", fieldInfo.getComment()));
            bw.newLine();
        }



    }




    public static void createMethodComment() {

    }
}
