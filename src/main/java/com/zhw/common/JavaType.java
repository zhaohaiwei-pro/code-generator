package com.zhw.common;

/**
 * java类型
 *
 *
 *
 */
public class JavaType {

    /**
     * 将JDBC类型转换为java数据类型
     */
    public static String jdbcTypeToJavaType(String str) {
        if (str == null) {
            return "";
        }

        if (isDate(str)) {
            return "java.time.Instant";
        } else if (isInteger(str)) {
            return "Integer";
        } else if (isLong(str)) {
            return "Long";
        } else if (isDouble(str)) {
            return "Double";
        } else if (isString(str)) {
            return "String";
        } else if (isJson(str)) {
            return "JsonObject";
        } else {
            return "Object";
        }
    }

    /**
     * 判断是否为日期
     */
    public static boolean isDate(String str) {
        return "DATE".equalsIgnoreCase(str) || "DATETIME".equalsIgnoreCase(str) || "TIMESTAMP".equalsIgnoreCase(str)
            || "INTERVAL".equalsIgnoreCase(str) || "TIME".equalsIgnoreCase(str) || "DATETIME2".equalsIgnoreCase(str);
    }

    /**
     * 判断是否可为String类型
     */
    public static boolean isString(String str) {
        return "NCHAR".equalsIgnoreCase(str) || "CHAR".equalsIgnoreCase(str) || "NVARCHAR2".equalsIgnoreCase(str)
            || "VARCHAR2".equalsIgnoreCase(str) || "NVARCHAR".equalsIgnoreCase(str) || "VARCHAR".equalsIgnoreCase(str)
            || "MEDIUMBLOB".equalsIgnoreCase(str) || "MEDIUMTEXT".equalsIgnoreCase(str) || "CLOB".equalsIgnoreCase(str)
            || "NCLOB".equalsIgnoreCase(str) || "BLOB".equalsIgnoreCase(str) || "NBLOB".equalsIgnoreCase(str)
            || "BFILE".equalsIgnoreCase(str) || "XML".equalsIgnoreCase(str) || "IMAGE".equalsIgnoreCase(str)
            || "TEXT".equalsIgnoreCase(str);
    }

    /**
     * 判断类型是否为JSON格式
     */
    public static boolean isJson(String str) {
        return "JSON".equals(str);
    }

    /**
     * 判断是否为Integer类型
     */
    public static boolean isInteger(String str) {
        return "NUMBER".equalsIgnoreCase(str) || "INTEGER".equalsIgnoreCase(str) || "INT".equalsIgnoreCase(str)
            || "TINYINT".equalsIgnoreCase(str) || "SMALLINT".equalsIgnoreCase(str) || "BIT".equalsIgnoreCase(str)
            || "Int UNSIGNED".equalsIgnoreCase(str) || "TINYINT UNSIGNED".equalsIgnoreCase(str);
    }

    /**
     * 判断是否为Long类型
     */
    public static boolean isLong(String str) {
        return "Long".equalsIgnoreCase(str) || "LONG UNSIGNED".equalsIgnoreCase(str) || "BIGINT".equalsIgnoreCase(str)
            || "unsigned".equalsIgnoreCase(str) || "BIGINT UNSIGNED".equalsIgnoreCase(str);
    }

    /**
     * 判断是否为Double类型
     */
    public static boolean isDouble(String str) {
        return "BINARY_DOUBLE".equalsIgnoreCase(str) || "BINARY_FLOAT".equalsIgnoreCase(str) || "FLOAT".equalsIgnoreCase(str)
            || "DECIMAL".equalsIgnoreCase(str) || "MONEY".equalsIgnoreCase(str) || "NUMERIC".equalsIgnoreCase(str)
            || "REAL".equalsIgnoreCase(str) || "DOUBLE".equalsIgnoreCase(str);
    }

}
