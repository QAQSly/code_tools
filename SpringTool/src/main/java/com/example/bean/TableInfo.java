package com.example.bean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
// import com.example.bean.FieldInfo;


@Getter
@Setter
public class TableInfo {
    // table
    private String tableName;
    // bean
    private String beanName;
    // param
    private String beanParamName;
    // comment 
    private String comment;
    // field
    private List<FieldInfo> fieldList;
    // nique 索引
    private Map<String, List<FieldInfo>> keyIndexMap = new LinkedHashMap();
    // date
    private Boolean haveDate;
    // date time
    private Boolean haveDateTime;
    // decimal
    private Boolean havaBigDecimal;
}
