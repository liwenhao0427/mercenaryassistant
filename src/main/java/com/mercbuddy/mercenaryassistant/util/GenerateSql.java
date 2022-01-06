package com.mercbuddy.mercenaryassistant.util;

import com.mercbuddy.mercenaryassistant.entity.GameRecord;
import com.mercbuddy.mercenaryassistant.filter.SqlInjectionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
 * 本文链接：https://blog.csdn.net/qq_43097201/article/details/106308459
 * @author ADAMs
 */
public class GenerateSql {

    public static void main(String[] args) {
        GameRecord gameRecord = new GameRecord();
//        gameRecord.setId(1);
        gameRecord.setCreateTime(new Date());
        gameRecord.setEnemyId("2");
        gameRecord.setEnemyTeam("3");
        gameRecord.setOwnTeam("213");
        gameRecord.setResult(1);
        gameRecord.setUserId("2134214");
        String str1 = selectSQL(gameRecord.getClass());
        System.out.println("输出的SELECT的sql：" + str1);
        String str2 = deleteSQL(gameRecord.getClass(), gameRecord);
        System.out.println("输出的DELETE的sq2：" + str2);
        String str3 = insertSQL(gameRecord.getClass(), gameRecord);
        System.out.println("输出的INSERT的sq3：" + str3);
        String str4 = updateSQL(gameRecord.getClass(), gameRecord);
        System.out.println("输出的UPDATE的sq4：" + str4);
    }

    //生成select的sql语句，
    public static String selectSQL(Class<?> clazz) {
        StringBuilder str = new StringBuilder();
        String tableName = getTableName(clazz);
        str.append("select * from ").append(tableName);
        String resultStr = str.toString();
        return resultStr;
    }

    //生成delete的sql语句
    public static String deleteSQL(Class<?> clazz, Object object) {
        StringBuilder str = new StringBuilder();
        String tableName = getTableName(clazz);
        str.append("delete from ").append(tableName).append(" where ");
        Field[] fields = clazz.getDeclaredFields();
        String pk = "";
        for (int i = 0; i < fields.length; i++) {
            Column column = fields[i].getAnnotation(Column.class);
            if (column.isPK()) {
                pk = column.label();
                break;
            }
        }
        try {
            str.append(pk).append(" = ").append(getValue(pk, object));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String resultStr = str.toString();
        return resultStr;
    }

    //生成replace的sql语句
    public static String replaceIntoSQL(Class<?> clazz, Object object) {
        StringBuilder str = new StringBuilder();
        String tableName = getTableName(clazz);
        str.append("replace into ").append(tableName).append(" (");
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length - 1; i++) {
            Column column = fields[i].getAnnotation(Column.class);
            str.append(column.label()).append(" , ");
        }
        Column columns = fields[fields.length - 1].getAnnotation(Column.class);
        str.append(columns.label()).append(") values(");
        try {
            for (int i = 0; i < fields.length - 1; i++) {
                Column column = fields[i].getAnnotation(Column.class);
                str.append(getValue(column.label(), object)).append(" , ");
            }
            str.append(getValue(columns.label(),object)).append(")");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String resultStr = str.toString();
        return resultStr;
    }

    //生成insert的sql语句
    public static String insertSQL(Class<?> clazz, Object object) {
        StringBuilder str = new StringBuilder();
        String tableName = getTableName(clazz);
        str.append("insert into ").append(tableName).append(" (");
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length - 1; i++) {
            Column column = fields[i].getAnnotation(Column.class);
            str.append(column.label()).append(" , ");
        }
        Column columns = fields[fields.length - 1].getAnnotation(Column.class);
        str.append(columns.label()).append(") values(");
        try {
            for (int i = 0; i < fields.length - 1; i++) {
                Column column = fields[i].getAnnotation(Column.class);
                str.append(getValue(column.label(), object)).append(" , ");
            }
            str.append(getValue(columns.label(),object)).append(")");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String resultStr = str.toString();
        return resultStr;
    }

    //生成update的sql语句
    public static String updateSQL(Class<?> clazz, Object object) {
        StringBuilder str = new StringBuilder();
        String tableName = getTableName(clazz);
        str.append("update ").append(tableName).append(" set ");
        Field[] fields = clazz.getDeclaredFields();
        String pk = "";
        try {
            for (int i = 0; i < fields.length - 1; i++) {
                Column column = fields[i].getAnnotation(Column.class);
                if (column.isPK()) {
                    pk = column.label();
                } else {

                    str.append(column.label()).append(" = ").append(getValue(column.label(), object)).append(",");

                }
            }
            Column columns = fields[fields.length - 1].getAnnotation(Column.class);
            str.append(columns.label()).append(" = ").append(getValue(columns.label(), object)).append(" ");
            str.append(" where ").append(pk).append(" = ").append(getValue(pk, object));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String resultStr = str.toString();
        return resultStr;
    }

    //获取表的名称
    private static String getTableName(Class<?> clazz) {
        //判断是否为Table注释类型是方法返回true，否则返回false
        if (clazz.isAnnotationPresent(Entity.class)) {
            //获取注解信息
            Entity table = clazz.getAnnotation(Entity.class);
            if (!"".equals(table.tableName())) {
                return table.tableName();
            }
        }
        return null;
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    //通过对象的值，来获取实际的值
    private static <T> String getValue(String columnName, Object object) throws Exception {
        //获取相应字段的getXXX方法
        String getMethod = columnName.substring(0, 1).toUpperCase() + StringTransTool.lineToHump(columnName.substring(1)) ;
        Method method = object.getClass().getMethod("get" + getMethod);
        Object val = method.invoke(object);
        if (val == null){
            return "null";
        }
        if (val instanceof Date ){
            return  "'"+sdf.format(val)+"'";
        }
        String tmpVal = val.toString();
        if (val instanceof Integer || val instanceof Double || val instanceof Float || val instanceof BigDecimal){
            return tmpVal;
        }
        if (val instanceof String){
            return "'"+tmpVal+"'";
        }
        return "'"+tmpVal+"'";
    }
}
