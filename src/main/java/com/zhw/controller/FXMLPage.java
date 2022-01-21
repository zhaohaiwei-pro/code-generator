package com.zhw.controller;

/**
 * 页面枚举类
 */
public enum FXMLPage {

    /** 主页面 */
    INDEX("FXML/Index.fxml"),
    /** 数据库连接页面 */
    CONNECTION("FXML/Connection.fxml"),
    /** 数据库修改页面 */
    UPDATE_CONNECTION("FXML/UpdateConnection.fxml"),
    /** 配置信息页面 */
    HISTORY_CONFIG("FXML/HistoryConfig.fxml"),
    /** 基础信息页面 */
    BASE_INFO_CONFIG("FXML/BaseInfoConfig.fxml"),
    /** 设置页面 */
    SETTING("FXML/Setting.fxml");

    private final String fxml;

    FXMLPage(String fxml) {
        this.fxml = fxml;
    }

    public String getFxml() {
        return this.fxml;
    }

}
