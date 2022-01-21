package com.zhw.models;

/**
 * 数据库类型
 */
public enum DBType {

    // @formatter:off
    Oracle("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s"),
    MySQL("com.mysql.cj.jdbc.Driver", "jdbc:mysql://%s:%s/%s?useUnicode=true&useSSL=false&characterEncoding=%s&serverTimezone=UTC"),
    SqlServer("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s:%s;databaseName=%s"),
    PostgreSQL("org.postgresql.Driver", "jdbc:postgresql://%s:%s/%s"),
    Sqlite("org.sqlite.JDBC", "jdbc:sqlite:%s%s/%s");
    // @formatter:on

    private final String driverClass;
    private final String connectionUrlPattern;

    DBType(String driverClass, String connectionUrlPattern) {
        this.driverClass = driverClass;
        this.connectionUrlPattern = connectionUrlPattern;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getConnectionUrlPattern() {
        return connectionUrlPattern;
    }

}
