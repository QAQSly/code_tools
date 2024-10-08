package com.example.bean;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class FieldInfo {

    // 字段名称
    private String FieldName;
    
    // bean属性名称
    private String propertyName;

    private String sqlType;

    // 字段类型
    private String javaType;

    // 字段备注
    private String comment;

    // 字段是否自增长
    private Boolean isAutoIncrement;

}
