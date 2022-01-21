package com.zhw.options;

/**
 * 数据库连接配置文件
 *
 *
 *
 */
public class DatabaseConfig {

    private String connName;
    private String connUrl;
    private String listenPort;
    private String dbName;
    private String userName;
    private String userPwd;
    private String dbType;
    private String encoding;

    public DatabaseConfig() {
        super();
    }

    public DatabaseConfig(String connName, String connUrl, String listenPort, String dbName, String userName, String userPwd, String dbType,
        String encoding) {
        super();
        this.connName = connName;
        this.connUrl = connUrl;
        this.listenPort = listenPort;
        this.dbName = dbName;
        this.userName = userName;
        this.userPwd = userPwd;
        this.dbType = dbType;
        this.encoding = encoding;
    }

    public String getConnName() {
        return connName;
    }

    public void setConnName(String connName) {
        this.connName = connName;
    }

    public String getConnUrl() {
        return connUrl;
    }

    public void setConnUrl(String connUrl) {
        this.connUrl = connUrl;
    }

    public String getListenPort() {
        return listenPort;
    }

    public void setListenPort(String listenPort) {
        this.listenPort = listenPort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String toString() {
        return "DatabaseConfig [connName=" + connName + ", connURL=" + connUrl + ", listenPort=" + listenPort + ", dbName=" + dbName
            + ", userName=" + userName + ", userPwd=" + userPwd + ", dbType=" + dbType + ", encoding=" + encoding + "]";
    }

}
