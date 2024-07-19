package com.example.utils;


public class StringUtils {
    // 首字母大写转换
    public static String upperCaseFirstLetter(String field) {
        if (field.isEmpty()) {
            return field;
        }
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }
    // 首字母小写
    public static String lowerCaseFirstLetter(String field) {
        if (field.isEmpty()) {
            return field;
        }
        return field.substring(0, 1).toLowerCase() + field.substring(1);
    }
    
}
