package com.zhw.controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import com.zhw.Main;
import com.zhw.common.LanguageKey;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;

public class SettingController extends BaseController {

    /** 语言 */
    @FXML
    private Label language;
    /** 语言选择 */
    @FXML
    private MenuButton mbLanguage;
    /** 英语 */
    @FXML
    private Label english;
    /** 中文 */
    @FXML
    private Label simpleChinese;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        language.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SETTING_LANGUAGE));
        language.widthProperty().addListener(ev -> mbLanguage.setLayoutX(10 + language.getLayoutX() + language.getWidth()));
        // 设置选择框里面的文字跟选择框一样大
        mbLanguage.widthProperty().addListener(ev -> {
            if (mbLanguage.getWidth() > 100) {
                english.setPrefWidth(mbLanguage.getWidth() - 15);
                simpleChinese.setPrefWidth(mbLanguage.getWidth() - 15);
            }
        });
        // 给语言添加事件
        english.setOnMouseClicked(ev -> {
            Main.loadLanguage(Locale.ENGLISH);
            closeDialogStage();
        });
        simpleChinese.setOnMouseClicked(ev -> {
            Main.loadLanguage(Locale.SIMPLIFIED_CHINESE);
            closeDialogStage();
        });
    }

}
