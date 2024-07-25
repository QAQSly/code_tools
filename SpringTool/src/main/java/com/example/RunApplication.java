package com.example;


import com.example.bean.TableInfo;
import com.example.builder.BuildBase;
import com.example.builder.BuildPo;
import com.example.builder.BuildTable;

import java.util.List;

public class RunApplication {
    public static void main(String[] args) {
        List<TableInfo> tableInfoList  =  BuildTable.getTables();

        for (TableInfo tableInfo : tableInfoList) {
            BuildPo.execute(tableInfo);
        }

        BuildBase.execute();

       
    }
}
