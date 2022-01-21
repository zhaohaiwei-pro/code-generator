package com.zhw.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.zhw.Main;
import com.zhw.common.ConfigUtil;
import com.zhw.common.DBUtil;
import com.zhw.common.LanguageKey;
import com.zhw.options.DatabaseConfig;
import com.zhw.spi.DatabaseTypeNames;
import com.zhw.view.AlertUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class UpdateConnection extends BaseController {

    private IndexController indexController;

    /** 连接名称 */
    @FXML
    private Label connName;
    /** 连接名称 */
    @FXML
    private Label connUrl;
    /** 监听端口号 */
    @FXML
    private Label listenPort;
    /** 数据库类型 */
    @FXML
    private Label dbType;
    /** 数据库名字 */
    @FXML
    private Label dbName;
    /** 数据库用户名 */
    @FXML
    private Label userName;
    /** 数据库密码 */
    @FXML
    private Label userPwd;
    /** 数据库编码格式 */
    @FXML
    private Label dbCoding;
    /** 连接名称 */
    @FXML
    private TextField txtConnName;
    /** 连接URL */
    @FXML
    private TextField txtConnUrl;
    /** 端口号 */
    @FXML
    private TextField txtListenPort;
    /** 数据库名称 */
    @FXML
    private TextField txtDbName;
    /** 用户名 */
    @FXML
    private TextField txtUserName;
    /** 密码 */
    @FXML
    private TextField txtUserPwd;
    /** 数据库类型 */
    @FXML
    private ComboBox<String> cboDbType;
    /** 数据库编码 */
    @FXML
    private ComboBox<String> cboDbCoding;
    /** 连接测试 */
    @FXML
    private Button btnTestConn;
    /** 取消 */
    @FXML
    private Button btnCancel;
    /** 保存 */
    @FXML
    private Button btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnTestConn.widthProperty().addListener(w -> {
            double cw = btnTestConn.getLayoutX() + btnTestConn.getWidth() + 20;
            btnSave.setLayoutX(cw);
        });
        btnSave.widthProperty().addListener(w -> {
            double cw = btnSave.getLayoutX() + btnSave.getWidth() + 20;
            btnCancel.setLayoutX(cw);
        });
        initLanguage();
    }

    public void init() {
        DatabaseConfig config = indexController.getUpdateOfDatabaseConfig();
        // 初始化下拉列表
        cboDbType.setEditable(true);
        cboDbType.getItems().addAll(DatabaseTypeNames.dbTypeNames());
        cboDbType.setValue(config.getDbType());
        cboDbCoding.setEditable(true);
        cboDbCoding.getItems().addAll("utf8", "utf16", "utf32", "utf8mb4", "gb2312", "gbk", "ascii");
        cboDbCoding.setValue(config.getEncoding());
        txtConnName.setText(config.getConnName());
        txtConnUrl.setText(config.getConnUrl());
        txtDbName.setText(config.getDbName());
        txtListenPort.setText(config.getListenPort());
        txtUserName.setText(config.getUserName());
        txtUserPwd.setText(config.getUserPwd());
    }

    /**
     * 初始化语言
     */
    private void initLanguage() {
        connName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_CONN_NAME));
        connUrl.textProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_CONN_URL));
        listenPort.textProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_LISTEN_PORT));
        dbType.textProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_DB_TYPE));
        dbName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_DB_NAME));
        userName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_USER_NAME));
        userPwd.textProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_USER_PWD));
        dbCoding.textProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_DB_CODING));
        txtConnName.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_TXT_CONN_NAME));
        txtConnUrl.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_TXT_CONN_URL));
        txtListenPort.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_TXT_LISTEN_PORT));
        cboDbType.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_CBO_DB_TYPE));
        txtDbName.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_TXT_DB_NAME));
        txtUserName.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_TXT_USER_NAME));
        txtUserPwd.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_TXT_USER_PWD));
        cboDbCoding.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_DB_CODING));
        btnTestConn.textProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_BTN_TEST_CONN));
        btnSave.textProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_BTN_SAVE));
        btnCancel.textProperty().bind(Main.LANGUAGE.get(LanguageKey.CONN_BTN_CANCEL));
    }

    /**
     * 确定修改
     */
    public void btnUpdate() {
        DatabaseConfig dbConfig = getDatabaseConfig();
        if (dbConfig == null) {
            return;
        }
        try {
            ConfigUtil.updateDatabaseConfig(dbConfig);
            AlertUtil.showAndWaitInfoAlert("修改数据库连接成功!");
            indexController.loadTvDataBase();
            getDialogStage().close();
        } catch (Exception e) {
            AlertUtil.showErrorAlert(e.getMessage());
        }
    }

    /**
     * 取消修改
     */
    public void onCancel() {
        getDialogStage().close();
    }

    /**
     * 测试连接
     */
    public void testConnection() {
        DatabaseConfig config = getDatabaseConfig();
        if (config == null) {
            return;
        }
        try {
            DBUtil.getConnection(config);
            AlertUtil.showInfoAlert("连接成功!");
        } catch (Exception e) {
            AlertUtil.showErrorAlert("连接失败!原因:" + e.getMessage());
        }
    }

    /**
     * 获得连接的所有字段
     */
    public DatabaseConfig getDatabaseConfig() {
        String connName = txtConnName.getText().trim();
        String connUrl = txtConnUrl.getText().trim();
        String listenPort = txtListenPort.getText().trim();
        String dbName = txtDbName.getText().trim();
        String userName = txtUserName.getText().trim();
        String userPwd = txtUserPwd.getText().trim();
        String dbType = cboDbType.getValue();
        String encoding = cboDbCoding.getValue();
        boolean isEmpty = validata(connName, connUrl, listenPort, dbName, userName, dbType, encoding);
        if (isEmpty) {
            return new DatabaseConfig(connName, connUrl, listenPort, dbName, userName, userPwd, dbType, encoding);
        } else {
            AlertUtil.showWarnAlert("除了密码以外所有属性都为必需填与选择");
            return null;
        }

    }

    /**
     * 验证所有属性是否已经填写
     */
    public boolean validata(String... str) {
        for (String string : str) {
            if (string == null || "".equals(string)) {
                return false;
            }
        }
        return true;
    }

}
