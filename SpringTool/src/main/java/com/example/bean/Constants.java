package com.example.bean;

import com.example.utils.YmlUtils;

public class Constants {
    public static Boolean IGNORE_TABLE_PREFIX;

    public static String SUFFIX_BEAN_PARAM;

    public static String PATH_JAVA = "java";

    public static String PATH_RESOURCES = "resources";
    public static String PATH_BASE;

    public static String PATH_PO;
    public static String PACKAGE_BASE;
    public static String PACKAGE_PO;

    public static String AUTHOR;
    static {
        IGNORE_TABLE_PREFIX = Boolean.valueOf(YmlUtils.getString("ignore.table.prefix"));
        SUFFIX_BEAN_PARAM = YmlUtils.getString("suffix_bean_param");

        PATH_BASE = YmlUtils.getString("path_base");
        PACKAGE_BASE = YmlUtils.getString("package_base");
        PATH_PO = YmlUtils.getString("package_po");
        PATH_BASE = PATH_BASE  + PATH_JAVA + "/" + PACKAGE_BASE.replace(".", "/") + "/";
        PATH_PO = PATH_BASE + PATH_PO.replace(".", "/");
        PACKAGE_PO = YmlUtils.getString("package_po");
        PACKAGE_PO =  PACKAGE_BASE + "." +  PACKAGE_PO;

        AUTHOR = YmlUtils.getString("author_comment");
    }
    public final static String[] SQL_DATE_TIME_TYPES = new String[]{"datetime", "timestamp"};
    public final static String[] SQL_DATE_TYPES = new String[]{"date"};
    public final static String[] SQL_DECIMAL_TYPE = new String[]{"decimal", "double", "float"};
    public final static String[] SQL_STRING_TYPE = new String[]{"char", "varchar", "text", "mediumtext", "longtext"};
    // Integer
    public final static String[] SQL_INTEGER_TYPE = new String[]{"int", "tinyint"};
    // Long
    public final static String[] SQL_LONG_TYPE = new String[]{"bigint"};

    public static void main(String[] args) {
        System.out.println(PACKAGE_PO);
    }

}
