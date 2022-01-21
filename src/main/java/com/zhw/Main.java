package com.zhw;

import java.net.URL;
import java.util.*;

import org.apache.log4j.PropertyConfigurator;

import com.zhw.common.ConfigUtil;
import com.zhw.common.TemplateUtil;
import com.zhw.controller.IndexController;
import com.zhw.view.AlertUtil;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 程序的入口
 */
@Slf4j
public class Main extends Application {

    /**
     * 国际化控件的文字
     */
    public static Map<String, StringProperty> LANGUAGE = new HashMap<>();

    public static void main(String[] args) {
        URL logResource = Thread.currentThread().getContextClassLoader().getResource("config/log4j.properties");
        PropertyConfigurator.configure(logResource);
        try {
            launch(args);
        } catch (Exception e) {
            log.error("运行Code-Generator-->失败:", e);
        }
    }

    /**
     * 根据Locale加载控件文本
     */
    public static void loadLanguage(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config/language/language", locale);
        Enumeration<String> keys = resourceBundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (LANGUAGE.get(key) == null) {
                LANGUAGE.put(key, new SimpleStringProperty(resourceBundle.getString(key)));
            } else {
                LANGUAGE.get(key).set(resourceBundle.getString(key));
            }
        }
    }

    private static Label infoLb;

    @Override
    public void start(Stage stage) {
        Image image = new Image("image/startup.png");
        ImageView view = new ImageView(image);
        infoLb = new Label();
        infoLb.setTextFill(Color.WHITE);
        AnchorPane.setRightAnchor(infoLb, 10.0);
        AnchorPane.setBottomAnchor(infoLb, 10.0);
        AnchorPane page = new AnchorPane();
        page.getChildren().addAll(view, infoLb);
        stage.setResizable(true);
        stage.getIcons().add(new Image("image/icon.png"));
        stage.setScene(new Scene(page));
        stage.setWidth(image.getWidth());
        stage.setHeight(image.getHeight());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        // 核心代码
        new Thread(() -> {
            initSystem();
            Platform.runLater(() -> {
                try {
                    Home home = new Home();
                    home.addEventHandler(WindowEvent.WINDOW_SHOWN, event -> {
                        stage.hide();
                    });
                    home.show();
                } catch (Throwable e) {
                    AlertUtil.showErrorAlert("初始化失败!");
                    stage.hide();
                }
            });
        }).start();
    }

    /**
     * 初始化系统
     */
    private void initSystem() {
        try {
            showInfo("初始化系统配置...");
            ConfigUtil.existsConfigDB();
            Thread.sleep(1000);
            showInfo("初始化模板文件...");
            TemplateUtil.existsTemplate();
            Thread.sleep(1500);
            showInfo("加载本地资源文件...");
            loadLanguage(Locale.getDefault());
            Thread.sleep(1000);
        } catch (Throwable e) {
            AlertUtil.showErrorAlert("初始化失败!");
        }
    }

    /**
     * 显示信息
     */
    public static void showInfo(String info) {
        Platform.runLater(() -> infoLb.setText(info));
    }

    /**
     * 主页
     */
    public static class Home extends Stage {

        public Home() throws Exception {
            URL url = Thread.currentThread().getContextClassLoader().getResource("FXML/Index.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent root = fxmlLoader.load();
            setScene(new Scene(root));
            setTitle("代码生成器");
            // setMaximized(true);
            setResizable(true);
            getIcons().add(new Image("image/icon.png"));
            IndexController controller = fxmlLoader.getController();
            controller.setPrimaryStage(this);
        }
    }
}
