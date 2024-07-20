package com.example.builder;
import com.example.utils.*;

import java.sql.Connection;



import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.apache.commons.lang3.ArrayUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.example.bean.*;

import java.util.List;
import java.util.ArrayList;
public class BuildTable {
    private static final Logger logger = LoggerFactory.getLogger(BuildTable.class); 
    private static Connection conn = null;
    private static String SQL_SHOW_TABLE_STATUS = "show table status";
    private static String SQL_SHOW_TABLE_FIELDS = "show full fields from %s";
    private static String SQL_SHOW_TABLE_INDEX = "show index from %s";
    static {
       String dirverName = YmlUtils.getString("driverClassName");// 获取mysql驱动
       String url = YmlUtils.getString("url");
       String username = YmlUtils.getString("username");
       String password = YmlUtils.getString("password");

        try {
    
            Class.forName(dirverName);
            //System.out.println("url:" + url + "\nusername:" + username + "\password:" + password);
            Properties connectionProps = new Properties();
            connectionProps.put("user", username);
            connectionProps.put("password", password);
            // 连接数据库
            conn = (Connection)DriverManager.getConnection(url, connectionProps);

        } catch (Exception e) {
            logger.error("database connect miss ", e);
            
        } 
    }
    // 根据_进行切割 返回给首字母大写
    private static String processField(String field, Boolean upperCaseFirstLetter) {
        StringBuffer sb = new StringBuffer();
        String[] fields = field.split("_");
        sb.append(upperCaseFirstLetter ? StringUtils.upperCaseFirstLetter(fields[0]) : fields[0]);

        for (int i = 1, len = fields.length; i < len; i++) {
            // logger.info("大写的字段是" + fields[i]);
            sb.append(StringUtils.upperCaseFirstLetter(fields[i]));
        }
        // logger.info("处理过的字段" + sb.toString());
        return sb.toString();
    }

    // 获取唯一索引信息
    public static void getKeyIndex(TableInfo tableInfo) {


        try(PreparedStatement ps = conn.prepareStatement(String.format(SQL_SHOW_TABLE_INDEX, tableInfo.getTableName())); ResultSet keyResult = ps.executeQuery()) {


            while (keyResult.next()) {
                Integer nonUnique = keyResult.getInt("non_unique");
                String keyName = keyResult.getString("key_name");
                String columnName = keyResult.getString("column_name");
                if (1 == nonUnique) {
                    continue;
                }
                List<FieldInfo> keyFieldList = tableInfo.getKeyIndexMap().get(keyName);
                if (null == keyFieldList) {
                    keyFieldList = new ArrayList<>();
                    tableInfo.getKeyIndexMap().put(keyName, keyFieldList);
                }

                for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
                    if (fieldInfo.getFieldName().equals(columnName)) {
                        keyFieldList.add(fieldInfo);
                    }
                }

            }

        } catch (Exception e) {
            logger.error("读取索引失败", e);
        }
    }
    // 获取表信息
    public static void getTables() {

        try (PreparedStatement ps = conn.prepareStatement(SQL_SHOW_TABLE_STATUS);
        ResultSet tableResult = ps.executeQuery()) {

            while (tableResult.next()) {
                String tableName = tableResult.getString("name");
                String comment = tableResult.getString("comment");
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(tableName);
                tableInfo.setComment(comment);

                String beanName = tableName;
                if (Constants.IGNORE_TABLE_PREFIX) {
                    beanName = tableName.substring(beanName.indexOf("_") + 1);

                }

                tableInfo.setBeanParamName(beanName + Constants.SUFFIX_BEAN_PARAM);
                getFields(tableInfo);
                getKeyIndex(tableInfo);
                logger.info("{}", JsonUtils.convertObj2Json(tableInfo));
            }
        } catch (Exception e) {
            logger.error("读取表信息失败", e);
        }

    }
    // 获取字段的信息
    public static void getFields(TableInfo tableInfo) {

        List<FieldInfo> fieldInfoList = new ArrayList();

        try (PreparedStatement ps = conn.prepareStatement(String.format(SQL_SHOW_TABLE_FIELDS, tableInfo.getTableName()));
        ResultSet fieldResult = ps.executeQuery()) {
            FieldInfo fieldInfo = new FieldInfo();
            while (fieldResult.next()) {
                String comment = fieldResult.getString("comment");
                String field = fieldResult.getString("field");
                String type = fieldResult.getString("type");
                String extra = fieldResult.getString("extra");

                if (type.indexOf("(") > 0) {
                    type = type.substring(0, type.indexOf("("));
                }

                String propertyName = processField(field, false);



                fieldInfo.setFieldName(field);
                fieldInfo.setComment(comment);
                fieldInfo.setSqlType(type);
                fieldInfo.setIsAutoIncrement("auto_increment".equalsIgnoreCase(extra) ? true : false);
                fieldInfo.setPropertyName(propertyName);
                fieldInfo.setJavaType(processJavaType(type));
                if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, type)) {
                    tableInfo.setHaveDateTime(true);
                } else {
                    tableInfo.setHaveDateTime(false);
                }
                if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, type)) {
                    tableInfo.setHaveDate(true);
                } else {
                    tableInfo.setHaveDateTime(false);
                }
                if (ArrayUtils.contains(Constants.SQL_DECIMAL_TYPE, type)) {
                    tableInfo.setHavaBigDecimal(true);
                } else {
                    tableInfo.setHavaBigDecimal(false);
                }

            }
            fieldInfoList.add(fieldInfo);
            tableInfo.setFieldList(fieldInfoList);
        } catch (Exception e) {
            logger.error("读取字段信息失败", e);
        }


    }
    private static String processJavaType(String type) {
        if (ArrayUtils.contains(Constants.SQL_INTEGER_TYPE, type)) {
            return "Integer";
        } else if (ArrayUtils.contains(Constants.SQL_LONG_TYPE, type)) {
            return "Long";
        } else if (ArrayUtils.contains(Constants.SQL_STRING_TYPE, type)) {
            return "String";
        } else if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, type) || ArrayUtils.contains(Constants.SQL_DATE_TYPES, type)) {
            return "Date";
        } else if (ArrayUtils.contains(Constants.SQL_DECIMAL_TYPE, type)) {
            return "BigDecimal";
        } else {
            throw new RuntimeException("无法识别的类型: " + type);
        }
        
        
    }

}
