package com.example.bean;

import com.example.utils.YmlUtils;

public class Constants {
    public static Boolean IGNORE_TABLE_PREFIX;

    public static String SUFFIX_BEAN_PARAM;

    public static String PATH_JAVA = "java";

    public static String PATH_RESOURCES = "resources";
    public static String PATH_BASE;

    public static String PATH_PO;

    public static String PATH_UTILS;

    public static String PATH_ENUMS;
    public static String PACKAGE_BASE;
    public static String PACKAGE_PO;

    public static String PACKAGE_UTILS;

    public static String PACKAGE_ENUMS;

    // 需要忽略的属性
    public static String IGNORE_BEAN_TOJSON_FIELD;
    public static String IGNORE_BEAN_TOJSON_EXPRESSION;
    public static String IGNORE_BEAN_TOJSON_CLASS;

    // 日期序列化 反序列化
    public static String BEAN_DATE_FORMAT_EXPRESSION;
    public static String BEAN_DATE_FORMAT_CLASS;

    public static String BEAN_DATE_UNFORMAT_EXPRESSION;
    public static String BEAN_DATE_UNFORMAT_CLASS;

    public static String AUTHOR;
    static {
        // 注解
        IGNORE_BEAN_TOJSON_FIELD = YmlUtils.getString("ignore_bean_toJson_field");
        IGNORE_BEAN_TOJSON_EXPRESSION = YmlUtils.getString("ignore_bean_toJson_expression");
        IGNORE_BEAN_TOJSON_CLASS = YmlUtils.getString("ignore_bean_toJson_class");

        // 日期序列化 反序列化
        BEAN_DATE_FORMAT_EXPRESSION = YmlUtils.getString("bean_date_format_expression");
        BEAN_DATE_FORMAT_CLASS = YmlUtils.getString("bean_date_format_class");

        BEAN_DATE_UNFORMAT_EXPRESSION = YmlUtils.getString("bean_date_unFormat_expression");
        BEAN_DATE_UNFORMAT_CLASS = YmlUtils.getString("bean_date_unFormat_class");


        IGNORE_TABLE_PREFIX = Boolean.valueOf(YmlUtils.getString("ignore.table.prefix"));
        SUFFIX_BEAN_PARAM = YmlUtils.getString("suffix_bean_param");
        PACKAGE_BASE = YmlUtils.getString("package_base");
        PACKAGE_PO = YmlUtils.getString("package_po");
        PACKAGE_PO =  PACKAGE_BASE + "." +  PACKAGE_PO;
        PACKAGE_UTILS = YmlUtils.getString("package_utils");
        PACKAGE_UTILS = PACKAGE_BASE + "." + PACKAGE_UTILS;
        PACKAGE_ENUMS = YmlUtils.getString("package_enums");
        PACKAGE_ENUMS = PACKAGE_BASE + "." + PACKAGE_ENUMS;

        PATH_BASE = YmlUtils.getString("path_base");
        PATH_BASE = PATH_BASE  + PATH_JAVA + "/";
        PATH_PO = PATH_BASE + PACKAGE_PO.replace(".", "/");
        PATH_UTILS = PATH_BASE + PACKAGE_UTILS.replace(".", "/");
        PATH_ENUMS = PATH_BASE + PACKAGE_ENUMS.replace(".", "/");



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
        System.out.println(PATH_ENUMS);
        System.out.println(PACKAGE_ENUMS);
    }

}
