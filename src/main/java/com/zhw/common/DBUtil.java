package com.zhw.common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import com.zhw.dto.FieldInfo;
import com.zhw.dto.TableInfo;
import com.zhw.models.DBType;
import com.zhw.options.DatabaseConfig;
import com.zhw.options.HistoryConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据库工具
 *
 *
 *
 */
@Slf4j
public class DBUtil {

    private static final int DB_CONNECTION_TIMEOUTS_SECONDS = 1;

    /**
     * 获得数据库连接
     */
    public static Connection getConnection(DatabaseConfig config) throws ClassNotFoundException, SQLException {
        DriverManager.setLoginTimeout(DB_CONNECTION_TIMEOUTS_SECONDS);
        DBType dbType = DBType.valueOf(config.getDbType());
        Class.forName(dbType.getDriverClass());
        String url = getConnectionUrl(config);
        Properties props = new Properties();
        props.setProperty("user", config.getUserName());
        props.setProperty("password", config.getUserPwd());
        props.setProperty("remarks", "true");
        return DriverManager.getConnection(url, props);
    }

    /**
     * 获得数据库连接URL
     */
    public static String getConnectionUrl(DatabaseConfig dbConfig) {
        DBType dbType = DBType.valueOf(dbConfig.getDbType());
        return String.format(dbType.getConnectionUrlPattern(), dbConfig.getConnUrl(), dbConfig.getListenPort(), dbConfig.getDbName(),
            dbConfig.getEncoding());
    }

    /**
     * 获得数据库的表名
     */
    public static List<TableInfo> getTableNames(DatabaseConfig config) throws Exception {
        Connection conn = getConnection(config);
        List<TableInfo> tables = new ArrayList<>();
        ResultSet rs;
        if (config.getDbType().equalsIgnoreCase(Constant.SQL_SERVER)) {
            // 如果是sqlserver数据库通过查询获得所有表跟视图
            String sql = "select name from sysobjects where UPPER(xtype)='U' or UPPER(xtype)='V'";
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                tables.add(new TableInfo(rs.getString("name"), null, null));
            }
        } else {
            // 如果非sqlserver类型的数据库通过JDBC获得所有表跟视图
            DatabaseMetaData md = conn.getMetaData();
            String[] types = {"TABLE", "VIEW"};
            if (config.getDbType().equalsIgnoreCase(Constant.POSTGRE_SQL)) {
                rs = md.getTables(null, null, null, types);
            } else {
                String catalog = conn.getCatalog() == null ? null : conn.getCatalog();
                rs = md.getTables(catalog, conn.getSchema(), null, types);
            }
            while (rs.next()) {
                // 表名
                String tableName = rs.getString("TABLE_NAME");
                // 注释
                String remarks = rs.getString("REMARKS");
                tables.add(new TableInfo(tableName, null, remarks));
            }
        }
        return tables;
    }

    /**
     * 获得指定表的属性
     */
    public static TableInfo getTableInfo(DatabaseConfig config, HistoryConfig historyConfig, String tableName) throws Exception {
        Connection conn = getConnection(config);
        TableInfo tableInfo = new TableInfo();
        ResultSet rs;
        DatabaseMetaData md = conn.getMetaData();
        String[] types = {"TABLE", "VIEW"};
        if (config.getDbType().equalsIgnoreCase(Constant.POSTGRE_SQL)) {
            rs = md.getTables(null, null, tableName, types);
        } else {
            String catalog = conn.getCatalog() == null ? null : conn.getCatalog();
            rs = md.getTables(catalog, config.getUserName().toUpperCase(), tableName, types);
        }
        if (rs.next()) {
            tableInfo.setTableName(rs.getString("TABLE_NAME"));
            tableInfo.setEntityName(StrUtil.unlineToPascal(tableInfo.getTableName().replaceFirst(historyConfig.getTablePreFix(), "")));
            tableInfo.setComment(rs.getString("REMARKS"));
        }
        return tableInfo;
    }

    /**
     * 获取表的列属性
     */
    public static List<FieldInfo> getTableColumns(DatabaseConfig databaseConfig, HistoryConfig historyConfig, String tableName)
        throws Exception {
        List<FieldInfo> fieldList = new ArrayList<>();
        Connection conn = getConnection(databaseConfig);
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs;
        if (databaseConfig.getDbType().equalsIgnoreCase(Constant.MYSQL)) {
            rs = md.getColumns(conn.getCatalog(), "%%", tableName, "%%");
        } else {
            rs = md.getColumns(null, md.getUserName(), tableName, "%");
        }
        // 主键的名字
        List<String> primaryKeys = null;
        try {
            primaryKeys = getPrimaryKey(databaseConfig, tableName);
        } catch (Exception e) {
            log.error("获取表主键失败!");
        }
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            String columnType = rs.getString("TYPE_NAME");
            String comment = rs.getString("REMARKS");
            String nullAble = rs.getString("NULLABLE");
            // 列的大小。对于char或date类型，列的大小是最大字符数，对于numeric和decimal类型，列的大小就是精度
            String size = rs.getString("COLUMN_SIZE");
            // 小数部分的位数
            int decimalDigits = rs.getInt("DECIMAL_DIGITS");
            FieldInfo fieldInfo = new FieldInfo();
            if (!Objects.requireNonNull(primaryKeys).isEmpty() && primaryKeys.contains(columnName)) {
                fieldInfo.setPrimaryKey(true);
            }
            fieldInfo.setDbColumn(columnName);
            fieldInfo.setDbColumnType(columnType);
            fieldInfo.setChName(comment);
            fieldInfo.setMaxValue(size);
            if ("0".equals(nullAble)) {
                fieldInfo.setMust(true);
            }
            fieldInfo.setEnName(StrUtil.unlineToCamel(columnName.replaceFirst(historyConfig.getFieldPreFix(), "")));
            if ("NUMBER".equals(columnType) && decimalDigits > 0) {
                fieldInfo.setEnNameType("BigDecimal");
            } else if ("NUMBER".equals(columnType)) {
                fieldInfo.setEnNameType("int");
            } else if ("DATE".equals(columnType)) {
                fieldInfo.setEnNameType("Date");
            } else {
                fieldInfo.setEnNameType("String");
            }
            fieldList.add(fieldInfo);

        }
        return fieldList;
    }

    /**
     * 获得主键名称
     */
    public static List<String> getPrimaryKey(DatabaseConfig config, String tableName) throws Exception {
        List<String> list = new ArrayList<>();
        Connection conn = getConnection(config);
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getPrimaryKeys(conn.getCatalog(), conn.getSchema(), tableName);
        while (rs.next()) {
            list.add(rs.getString("COLUMN_NAME"));
        }
        return list;
    }

}
