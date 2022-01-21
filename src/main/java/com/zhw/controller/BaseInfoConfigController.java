package com.zhw.controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.zhw.Main;
import com.zhw.common.ConfigUtil;
import com.zhw.common.LanguageKey;
import com.zhw.options.BaseInfoConfig;
import com.zhw.view.AlertUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class BaseInfoConfigController extends BaseController {

    private IndexController indexController;

    /** 代码作者 */
    @FXML
    private Label author;
    /** 代码版本 */
    @FXML
    private Label version;
    /** 代码作者名称 */
    @FXML
    private TextField authorName;
    /** 代码版本号 */
    @FXML
    private TextField codeVersion;
    /** 取消 */
    @FXML
    private Button btnCancel;
    /** 保存 */
    @FXML
    private Button btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            BaseInfoConfig baseInfoConfig = ConfigUtil.getBaseInfoConfig("BaseInfo");
            authorName.setText(Objects.requireNonNull(baseInfoConfig).getAuthor());
            codeVersion.setText(Objects.requireNonNull(baseInfoConfig).getVersion());
        } catch (Exception ignored) {
        }
        btnSave.widthProperty().addListener(w -> {
            double cw = btnSave.getLayoutX() + btnSave.getWidth() + 20;
            btnCancel.setLayoutX(cw);
        });
        initLanguage();
    }

    /**
     * 初始化语言
     */
    private void initLanguage() {
        author.textProperty().bind(Main.LANGUAGE.get(LanguageKey.BASE_AUTHOR));
        version.textProperty().bind(Main.LANGUAGE.get(LanguageKey.BASE_VERSION));
        btnSave.textProperty().bind(Main.LANGUAGE.get(LanguageKey.BASE_BTN_SAVE));
        btnCancel.textProperty().bind(Main.LANGUAGE.get(LanguageKey.BASE_BTN_CANCEL));
    }

    /**
     * 保存连接
     *
     */
    public void saveBaseInfo() {
        try {
            BaseInfoConfig config = getBaseInfoConfig();
            ConfigUtil.saveBaseInfoConfig(config);
            AlertUtil.showInfoAlert("保存成功!");
            getDialogStage().close();
        } catch (Exception e) {
            AlertUtil.showErrorAlert(e.getMessage());
        }

    }

    /**
     * 关闭当前窗口
     *
     */
    public void onCancel() {
        closeDialogStage();
    }

    /**
     * 获得基础信息所有字段
     */
    public BaseInfoConfig getBaseInfoConfig() {
        String author = authorName.getText().trim();
        String version = codeVersion.getText().trim();
        return new BaseInfoConfig("BaseInfo", author, version);
    }
}
