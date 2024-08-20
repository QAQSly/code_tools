package com.example;


import com.example.bean.TableInfo;
import com.example.builder.*;
import com.example.enums.DateTimePatternEnum;

import java.util.List;

public class RunApplication {
    public static void main(String[] args) {
        List<TableInfo> tableInfoList  =  BuildTable.getTables();
        BuildBase.execute();
        for (TableInfo tableInfo : tableInfoList) {
            BuildPo.execute(tableInfo);
            BuildQuery.execute(tableInfo);
            BuildMapper.execute(tableInfo);
            BuildMapperXml.execute(tableInfo);
        }
    }
}
