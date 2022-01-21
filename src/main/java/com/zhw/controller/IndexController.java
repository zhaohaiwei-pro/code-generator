package com.zhw.controller;

import com.zhw.Main;
import com.zhw.common.*;
import com.zhw.dto.TableInfo;
import com.zhw.options.DatabaseConfig;
import com.zhw.options.HistoryConfig;
import com.zhw.view.AlertUtil;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

/**
 * IndexController
 */
@Slf4j @Getter @Setter public class IndexController extends BaseController {

    /**
     * 提示文字的默认文字
     */
    private final String runCreateTipsText = "正在生成...";

    /**
     * 配置信息的名字
     */
    private String historyConfigName;
    /**
     * 模板文件夹中模板现有模板名字
     */
    private List<String> templateNameItems;
    /**
     * 存储数据库指定数据库,修改属性时用
     */
    private DatabaseConfig selectedDatabaseConfig;
    private DatabaseConfig updateOfDatabaseConfig;
    /**
     * 记录存储的表名,修改属性时用
     */
    private String selectedTableName;
    /**
     * 实体类名默认的占位符
     */
    private String entityNamePlace;
    /**
     * Service默认占位符
     */
    private String serviceNamePlace;
    /**
     * ServiceImpl默认占位符
     */
    private String serviceImplNamePlace;
    /**
     * Controller默认占位符
     */
    private String controllerNamePlace;
    /**
     * ReadDao默认占位符
     */
    private String readDaoNamePlace;
    /**
     * ReadMapper默认占位符
     */
    private String readMapperNamePlace;
    /**
     * WriteDao默认占位符
     */
    private String writeDaoNamePlace;
    /**
     * WriteMapper默认占位符
     */
    private String writeMapperNamePlace;

    // ========================fxml控件============================
    /**
     * 数据库连接
     */
    @FXML private Label dbConnection;
    /**
     * 配置信息
     */
    @FXML private Label customConfig;
    /**
     * 基础信息配置
     */
    @FXML private Label baseConfig;
    /**
     * 设置
     */
    @FXML private Label globleSetting;
    /**
     * 存放目录
     */
    @FXML private Label projectPath;
    /**
     * 数据库表名
     */
    @FXML private Label tableName;
    /**
     * 实体类包名
     */
    @FXML private Label entityPackage;
    /**
     * Service包名
     */
    @FXML private Label servicePackage;
    /**
     * ServiceImpl包名
     */
    @FXML private Label serviceImplPackage;
    /**
     * controller包名
     */
    @FXML private Label controllerPackage;
    /**
     * ReadDao包名
     */
    @FXML private Label readDaoPackage;
    /**
     * ReadMapper包名
     */
    @FXML private Label readMapperPackage;
    /**
     * WriteDao包名
     */
    @FXML private Label writeDaoPackage;
    /**
     * WriteMapper包名
     */
    @FXML private Label writeMapperPackage;
    /**
     * 实体类类名
     */
    @FXML private Label entityName;
    /**
     * Service类名
     */
    @FXML private Label serviceName;
    /**
     * ServiceImpl类名
     */
    @FXML private Label serviceImplName;
    /**
     * controller类名
     */
    @FXML private Label controllerName;
    /**
     * ReadDao类名
     */
    @FXML private Label readDaoName;
    /**
     * ReadMapper的名字
     */
    @FXML private Label readMapperName;
    /**
     * WriteDao类名
     */
    @FXML private Label writeDaoName;
    /**
     * WriteMapper的名字
     */
    @FXML private Label writeMapperName;
    /**
     * 表名前缀
     */
    @FXML private Label tablePreFix;
    /**
     * 字段前缀
     */
    @FXML private Label fieldPreFix;
    /**
     * 公共字段
     */
    @FXML private Label publicField;
    /**
     * 生成文件的编码格式
     */
    @FXML private Label codeFormat;
    /**
     * 提示文字进度条
     */
    @FXML private Label runCreateAllTips;
    /**
     * 数据树列表
     */
    @FXML private TreeView<String> tvDataBase;
    /**
     * 存放目录
     */
    @FXML private TextField txtProjectPath;
    /**
     * 数据库表名
     */
    @FXML private TextField txtTableName;
    /**
     * 实体类包名
     */
    @FXML private TextField txtEntityPackage;
    /**
     * Service包名
     */
    @FXML private TextField txtServicePackage;
    /**
     * ServiceImpl包名
     */
    @FXML private TextField txtServiceImplPackage;
    /**
     * conntroller包名
     */
    @FXML private TextField txtControllerPackage;
    /**
     * ReadDao包名
     */
    @FXML private TextField txtReadDaoPackage;
    /**
     * ReadMapper包名
     */
    @FXML private TextField txtReadMapperPackage;
    /**
     * WriteDao包名
     */
    @FXML private TextField txtWriteDaoPackage;
    /**
     * WriteMapper包名
     */
    @FXML private TextField txtWriteMapperPackage;
    /**
     * 实体类类名
     */
    @FXML private TextField txtEntityName;
    /**
     * Service类名
     */
    @FXML private TextField txtServiceName;
    /**
     * ServiceImpl类名
     */
    @FXML private TextField txtServiceImplName;
    /**
     * controller类名
     */
    @FXML private TextField txtControllerName;
    /**
     * ReadDao类名
     */
    @FXML private TextField txtReadDaoName;
    /**
     * ReadMapper类名
     */
    @FXML private TextField txtReadMapperName;
    /**
     * WriteDao类名
     */
    @FXML private TextField txtWriteDaoName;
    /**
     * WriteMapper类名
     */
    @FXML private TextField txtWriteMapperName;
    /**
     * 表名前缀名
     */
    @FXML private TextField txtTablePreFix;
    /**
     * 字段前缀名
     */
    @FXML private TextField txtFieldPreFix;
    /**
     * 公共字段名
     */
    @FXML private TextField txtPublicField;
    /**
     * 启用缓存
     */
    @FXML private CheckBox useCache;
    /**
     * 启用Swagger
     */
    @FXML private CheckBox useSwagger;
    /**
     * 启用Lombok插件
     */
    @FXML private CheckBox useLombok;
    /**
     * 覆盖已生成文件
     */
    @FXML private CheckBox coverFile;
    /**
     * 模块名称
     */
    @FXML private Label moduleName;
    /**
     * 选择根目录按钮
     */
    @FXML private Button btnSelectFile;
    /**
     * 执行创建
     */
    @FXML private Button btnRunCreate;
    /**
     * 保存配置文件
     */
    @FXML private Button btnSaveConfig;
    /**
     * 打开生成文件目录
     */
    @FXML private Button btnOpenDir;
    /**
     * 字符编码格式
     */
    @FXML private ComboBox<String> cboCodeFormat;
    /**
     * 模块下拉框
     */
    @FXML private ComboBox<String> cboModuleName;

    /**
     * 生成进度条
     */
    @FXML private ProgressBar probCreateAll;

    @Override public void initialize(URL location, ResourceBundle resources) {
        final int ml = 20;// 左外边距
        // 初始化图标连接与配置信息
        ImageView connImage = new ImageView("image/computer.png");
        connImage.setFitHeight(40);
        connImage.setFitWidth(40);
        dbConnection.setGraphic(connImage);
        dbConnection.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_CONNECTION));
        dbConnection.setOnMouseClicked(this::onConnection);
        dbConnection.widthProperty()
            .addListener(event -> dbConnection.setLayoutX(ml + dbConnection.getLayoutX() + dbConnection.getWidth()));

        ImageView confImage = new ImageView("image/config.png");
        confImage.setFitHeight(40);
        confImage.setFitWidth(40);
        customConfig.setGraphic(confImage);
        customConfig.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_CONFIG));
        customConfig.setOnMouseClicked(this::onConfig);
        customConfig.widthProperty()
            .addListener(event -> customConfig.setLayoutX(ml + customConfig.getLayoutX() + customConfig.getWidth()));

        ImageView baseConfImage = new ImageView("image/basecfg.png");
        baseConfImage.setFitHeight(40);
        baseConfImage.setFitWidth(40);
        baseConfig.setGraphic(baseConfImage);
        baseConfig.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_BASE_CONFIG));
        baseConfig.setOnMouseClicked(this::onBaseInfoConfig);
        baseConfig.widthProperty()
            .addListener(event -> baseConfig.setLayoutX(ml + baseConfig.getLayoutX() + baseConfig.getWidth()));

        ImageView lblSettingImage = new ImageView("image/setting.png");
        lblSettingImage.setFitHeight(40);
        lblSettingImage.setFitWidth(40);
        globleSetting.setGraphic(lblSettingImage);
        globleSetting.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_SETTING));
        globleSetting.setOnMouseClicked(this::onSetting);

        cboCodeFormat.setEditable(true);
        cboCodeFormat.getItems().addAll("UTF-8", "GBK", "UTF-16", "UTF-32", "GB2312", "GB18030", "ISO-8859-1");
        cboCodeFormat.setValue("UTF-8");

        cboModuleName.setEditable(true);
        cboModuleName.getItems().addAll("his", "platform", "emr", "finance", "mi", "platform", "mobile", "bi");
        cboModuleName.setValue("his");
        cboModuleName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (txtEntityPackage.getText().contains(oldValue)) {
                txtEntityPackage.setText(txtEntityPackage.getText().replace(oldValue, newValue));
            }
            if (txtServicePackage.getText().contains(oldValue)) {
                txtServicePackage.setText(txtServicePackage.getText().replace(oldValue, newValue));
            }
            if (txtServiceImplPackage.getText().contains(oldValue)) {
                txtServiceImplPackage.setText(txtServiceImplPackage.getText().replace(oldValue, newValue));
            }
            if (txtReadDaoPackage.getText().contains(oldValue)) {
                txtReadDaoPackage.setText(txtReadDaoPackage.getText().replace(oldValue, newValue));
            }
            if (txtReadMapperPackage.getText().contains(oldValue)) {
                txtReadMapperPackage.setText(txtReadMapperPackage.getText().replace(oldValue, newValue));
            }
            if (txtWriteDaoPackage.getText().contains(oldValue)) {
                txtWriteDaoPackage.setText(txtWriteDaoPackage.getText().replace(oldValue, newValue));
            }
            if (txtWriteMapperPackage.getText().contains(oldValue)) {
                txtWriteMapperPackage.setText(txtWriteMapperPackage.getText().replace(oldValue, newValue));
            }
            if (txtControllerPackage.getText().contains(oldValue)) {
                txtControllerPackage.setText(txtControllerPackage.getText().replace(oldValue, newValue));
            }
        });
        initLanguage();
        try {
            // 加载左边数据库树
            initTvDataBase();
            loadTvDataBase();
        } catch (Exception e1) {
            AlertUtil.showErrorAlert(e1.getMessage());
        }
        try {
            // 加载首页配置信息
            loadIndexConfigInfo(Constant.DEFAULT);// 查询使用有默认的配置,如果有就加载
            loadPlace();// 设置默认的占位符名字
            // loadTemplate();// 获取模板文件夹中所有模板的名字
        } catch (Exception e) {
            AlertUtil.showErrorAlert("加载配置失败!失败原因:\r\n" + e.getMessage());
        }
    }

    // ======================方法区域================================

    /**
     * 加载首页配置文件
     */
    public void loadIndexConfigInfo(String name) throws Exception {
        HistoryConfig config = ConfigUtil.getHistoryConfigByName(name);
        if (config == null) {
            return;
        }
        historyConfigName = config.getHistoryConfigName();
        txtProjectPath.setText(config.getProjectPath());
        txtEntityPackage.setText(config.getEntityPackage());
        if (txtEntityName.getText().contains("{c}")) {
            txtEntityName.setText(config.getEntityName());
        }
        txtServicePackage.setText(config.getServicePackage());
        if (txtServiceName.getText().contains("{c}")) {
            txtServiceName.setText(config.getServiceName());
        }
        txtServiceImplPackage.setText(config.getServiceImplPackage());
        if (txtServiceImplName.getText().contains("{c}")) {
            txtServiceImplName.setText(config.getServiceImplName());
        }
        txtControllerPackage.setText(config.getControllerPackage());
        if (txtControllerName.getText().contains("{c}")) {
            txtControllerName.setText(config.getControllerName());
        }
        txtReadDaoPackage.setText(config.getReadDaoPackage());
        if (txtReadDaoName.getText().contains("{c}")) {
            txtReadDaoName.setText(config.getReadDaoName());
        }
        txtReadMapperPackage.setText(config.getReadMapperPackage());
        if (txtReadMapperName.getText().contains("{c}")) {
            txtReadMapperName.setText(config.getReadMapperName());
        }
        txtWriteDaoPackage.setText(config.getWriteDaoPackage());
        if (txtWriteDaoName.getText().contains("{c}")) {
            txtWriteDaoName.setText(config.getWriteDaoName());
        }
        txtWriteMapperPackage.setText(config.getWriteMapperPackage());
        if (txtWriteMapperName.getText().contains("{c}")) {
            txtWriteMapperName.setText(config.getWriteMapperName());
        }
        txtTablePreFix.setText(config.getTablePreFix());
        txtFieldPreFix.setText(config.getFieldPreFix());
        txtPublicField.setText(config.getPublicField());
        useCache.setSelected(config.isUseCache());
        useSwagger.setSelected(config.isUseSwagger());
        useLombok.setSelected(config.isUseLombok());
        coverFile.setSelected(config.isCoverFile());
        cboCodeFormat.setValue(config.getCodeFormat());
        cboModuleName.setValue(config.getModuleName());
    }

    /**
     * 初始化语言
     */
    private void initLanguage() {
        projectPath.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_PROJECT_PATH));
        txtProjectPath.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TXT_PROJECT_PATH));
        tableName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TABLE_NAME));
        txtTableName.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TXT_TABLE_NAME));
        entityPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_ENTITY_PACKAGE));
        entityName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_ENTITY_NAME));
        servicePackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_SERVICE_PACKAGE));
        serviceName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_SERVICE_NAME));
        serviceImplPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_SERVICE_IMPL_PACKAGE));
        serviceImplName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_SERVICE_IMPL_NAME));
        controllerPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_CONTROLLER_PACKAGE));
        controllerName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_CONTROLLER_NAME));
        readDaoPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_READ_DAO_PACKAGE));
        readDaoName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_READ_DAO_NAME));
        readMapperPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_READ_MAPPER_PACKAGE));
        readMapperName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_READ_MAPPER_NAME));
        writeDaoPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_WRITE_DAO_PACKAGE));
        writeDaoName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_WRITE_DAO_NAME));
        writeMapperPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_WRITE_MAPPER_PACKAGE));
        writeMapperName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_WRITE_MAPPER_NAME));
        tablePreFix.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TABLE_PREFIX));
        fieldPreFix.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_FIELD_PREFIX));
        publicField.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_PUBLIC_FIELD));
        codeFormat.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_CODE_FORMAT));
        moduleName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_MODULE_NAME));
        useCache.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_USE_CACHE));
        useSwagger.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_USE_SWAGGER));
        useLombok.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_USE_LOMBOK));
        coverFile.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_COVER_FILE));
        btnSelectFile.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_BTN_SELECT_FILE));
        btnRunCreate.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_BTN_RUN_CREATE));
        btnSaveConfig.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_BTN_SAVE_CONFIG));
        btnOpenDir.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_BTN_OPEN_DIR));
    }

    /**
     * 获得当前页面的信息并实例化为配置信息对象,
     */
    private HistoryConfig getThisHistoryConfig() {
        String projectPath = txtProjectPath.getText();
        String entityPackage = txtEntityPackage.getText();
        String entityName = txtEntityName.getText();
        String servicePackage = txtServicePackage.getText();
        String serviceName = txtServiceName.getText();
        String serviceImplPackage = txtServiceImplPackage.getText();
        String serviceImplName = txtServiceImplName.getText();
        String controllerPackage = txtControllerPackage.getText();
        String controllerName = txtControllerName.getText();
        String readDaoPackage = txtReadDaoPackage.getText();
        String readDaoName = txtReadDaoName.getText();
        String readMapperPackage = txtReadMapperPackage.getText();
        String readMapperName = txtReadMapperName.getText();
        String writeDaoPackage = txtWriteDaoPackage.getText();
        String writeDaoName = txtWriteDaoName.getText();
        String writeMapperPackage = txtWriteMapperPackage.getText();
        String writeMapperName = txtWriteMapperName.getText();
        String tablePreFix = txtTablePreFix.getText();
        String fieldPreFix = txtFieldPreFix.getText();
        String publicField = txtPublicField.getText();
        boolean isUseCache = useCache.isSelected();
        boolean isUseSwagger = useSwagger.isSelected();
        boolean isUseLombok = useLombok.isSelected();
        boolean isCoverFile = coverFile.isSelected();
        String codeFormat = cboCodeFormat.getValue();
        String moduleName = cboModuleName.getValue();
        return new HistoryConfig(null, projectPath, entityPackage, entityName, servicePackage, serviceName,
            serviceImplPackage, serviceImplName, controllerPackage, controllerName, readDaoPackage, readDaoName,
            readMapperPackage, readMapperName, writeDaoPackage, writeDaoName, writeMapperPackage, writeMapperName,
            tablePreFix, fieldPreFix, publicField, isUseCache, isUseSwagger, isUseLombok, isCoverFile, codeFormat,
            moduleName, selectedDatabaseConfig);
    }

    /**
     * 加载默认名字
     */
    private void loadPlace() {
        entityNamePlace = txtEntityName.getText();
        serviceNamePlace = txtServiceName.getText();
        serviceImplNamePlace = txtServiceImplName.getText();
        controllerNamePlace = txtControllerName.getText();
        readDaoNamePlace = txtReadDaoName.getText();
        readMapperNamePlace = txtReadMapperName.getText();
        writeDaoNamePlace = txtWriteDaoName.getText();
        writeMapperNamePlace = txtWriteMapperName.getText();
    }

    /**
     * 右边数据库树与事件
     */
    @SuppressWarnings("unchecked") public void initTvDataBase() {
        tvDataBase.setShowRoot(false);
        tvDataBase.setRoot(new TreeItem<>());
        ContextMenu contextMenu1 = new ContextMenu();
        MenuItem itemr = new MenuItem("刷新列表");
        itemr.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_RELOAD_CONNECT));
        itemr.setOnAction(event1 -> {
            try {
                loadTvDataBase();
            } catch (Exception e) {
                AlertUtil.showErrorAlert("获取数据库列表失败！");
            }
        });
        contextMenu1.getItems().addAll(itemr);
        tvDataBase.setContextMenu(contextMenu1);
        Callback<TreeView<String>, TreeCell<String>> defaultCellFactory = TextFieldTreeCell.forTreeView();
        tvDataBase.setCellFactory((TreeView<String> tv) -> {
            TreeCell<String> cell = defaultCellFactory.call(tv);
            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                int level = tvDataBase.getTreeItemLevel(cell.getTreeItem());
                TreeCell<String> treeCell = (TreeCell<String>) event.getSource();
                TreeItem<String> treeItem = treeCell.getTreeItem();
                if (level == 1) {
                    final ContextMenu contextMenu = new ContextMenu();
                    MenuItem item0 = new MenuItem("打开连接");
                    item0.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_OPEN_CONNECT));
                    item0.setOnAction(event1 -> {
                        DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
                        try {
                            List<TableInfo> tables = DBUtil.getTableNames(selectedConfig);
                            if (tables.size() > 0) {
                                ObservableList<TreeItem<String>> children = cell.getTreeItem().getChildren();
                                children.clear();
                                for (TableInfo table : tables) {
                                    TreeItem<String> newTreeItem = new TreeItem<>();
                                    ImageView imageView = new ImageView("image/table.png");
                                    imageView.setFitHeight(16);
                                    imageView.setFitWidth(16);
                                    newTreeItem.setGraphic(imageView);
                                    newTreeItem.setValue(table.getTableName() + "\t" + table.getComment());
                                    children.add(newTreeItem);
                                }
                            }
                        } catch (Exception e) {
                            AlertUtil.showErrorAlert(e.getMessage());
                        }
                    });
                    MenuItem item1 = new MenuItem("关闭连接");
                    item1.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_CLOSE_CONNECT));
                    item1.setOnAction(event1 -> {
                        treeItem.getChildren().clear();
                    });
                    MenuItem item3 = new MenuItem("修改连接");
                    item3.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_UPDATE_CONNECT));
                    item3.setOnAction(event1 -> {
                        updateOfDatabaseConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
                        if (updateOfDatabaseConfig != null) {
                            StringProperty property = Main.LANGUAGE.get(LanguageKey.PAGE_UPDATE_CONNECTION);
                            String title = property == null ? "修改数据库连接" : property.get();
                            UpdateConnection controller =
                                (UpdateConnection) loadFXMLPage(title, FXMLPage.UPDATE_CONNECTION, false);
                            controller.setIndexController(this);
                            controller.init();
                            controller.showDialogStage();
                        }
                    });
                    MenuItem item2 = new MenuItem("删除连接");
                    item2.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_DELETE_CONNECT));
                    item2.setOnAction(event1 -> {
                        if (!AlertUtil.showConfirmAlert("确定删除该连接吗")) {
                            return;
                        }
                        DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
                        try {
                            ConfigUtil.deleteDatabaseConfig(selectedConfig.getConnName());
                            this.loadTvDataBase();
                        } catch (Exception e) {
                            AlertUtil.showErrorAlert("删除数据库连接失败: " + e.getMessage());
                        }
                    });
                    contextMenu.getItems().addAll(item0, item1, item3, item2);
                    cell.setContextMenu(contextMenu);
                }
                // 加载所有表
                if (event.getClickCount() == 2) {
                    if (treeItem == null) {
                        return;
                    }
                    treeItem.setExpanded(true);
                    if (level == 1) {
                        DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
                        try {
                            List<TableInfo> tables = DBUtil.getTableNames(selectedConfig);
                            if (tables.size() > 0) {
                                ObservableList<TreeItem<String>> children = cell.getTreeItem().getChildren();
                                children.clear();
                                // 获得树节点
                                for (TableInfo table : tables) {
                                    TreeItem<String> newTreeItem = new TreeItem<>();
                                    ImageView imageView = new ImageView("image/table.png");
                                    imageView.setFitHeight(18);
                                    imageView.setFitWidth(18);
                                    newTreeItem.setGraphic(imageView);
                                    newTreeItem.setValue(table.getTableName() + "\t" + table.getComment());
                                    children.add(newTreeItem);
                                }
                            }
                        } catch (Exception e) {
                            AlertUtil.showErrorAlert(e.getMessage());
                        }
                    } else if (level == 2) {
                        String tableName = treeCell.getTreeItem().getValue();
                        selectedTableName = tableName.substring(0, tableName.indexOf("\t"));
                        selectedDatabaseConfig = (DatabaseConfig) treeItem.getParent().getGraphic().getUserData();
                        txtTableName.setText(selectedTableName);
                        String pascalTableName =
                            StrUtil.unlineToPascal(selectedTableName.replaceFirst(txtTablePreFix.getText(), ""));
                        txtEntityName.setText(entityNamePlace.replace("{c}", pascalTableName));
                        txtServiceName.setText(serviceNamePlace.replace("{c}", pascalTableName));
                        txtServiceImplName.setText(serviceImplNamePlace.replace("{c}", pascalTableName));
                        txtControllerName.setText(controllerNamePlace.replace("{c}", pascalTableName));
                        txtReadDaoName.setText(readDaoNamePlace.replace("{c}", pascalTableName));
                        txtReadMapperName.setText(readMapperNamePlace.replace("{c}", pascalTableName));
                        txtWriteDaoName.setText(writeDaoNamePlace.replace("{c}", pascalTableName));
                        txtWriteMapperName.setText(writeMapperNamePlace.replace("{c}", pascalTableName));
                    }
                }
            });
            return cell;
        });
    }

    /**
     * 加载数据库到树集
     */
    public void loadTvDataBase() throws Exception {
        TreeItem<String> rootTreeItem = tvDataBase.getRoot();
        rootTreeItem.getChildren().clear();
        List<DatabaseConfig> item;
        item = ConfigUtil.getDatabaseConfig();
        for (DatabaseConfig dbConfig : item) {
            TreeItem<String> treeItem = new TreeItem<>();
            treeItem.setValue(dbConfig.getConnName());
            ImageView dbImage = new ImageView("image/database.png");
            dbImage.setFitHeight(20);
            dbImage.setFitWidth(20);
            dbImage.setUserData(dbConfig);
            treeItem.setGraphic(dbImage);
            rootTreeItem.getChildren().add(treeItem);
        }
    }

    /**
     * 加载模板文件夹里面所有模板的名字
     */
    public void loadTemplate() {
        try {
            this.templateNameItems = Files.list(Paths.get(Constant.TEMPLATE_DIR_NAME))
                .filter(f -> f.getFileName().toString().endsWith(".ftl")).map(p -> p.getFileName().toString())
                .collect(Collectors.toList());
        } catch (Exception e) {
            AlertUtil.showErrorAlert("加载模板失败!");
        }
    }

    /**
     * 获得模板需要的上下文
     *
     * @param databaseConfig 数据库配置文件
     * @param tableName      表的名字,如果表名不为空,将类名设置为默认值占位表名,如果直接使用版面数据输入null
     */
    public Map<String, Object> getConfigContent(DatabaseConfig databaseConfig, HistoryConfig historyConfig,
        String tableName) throws Exception {
        Map<String, Object> content = new HashMap<>(32);
        if (StrUtil.isNullOrEmpty(tableName)) {
            tableName = selectedTableName;
        }
        TableInfo table = DBUtil.getTableInfo(databaseConfig, historyConfig, tableName);
        content.put("tableName", table.getTableName());
        content.put("entityName", historyConfig.getEntityName());
        content.put("entityComment", table.getComment());
        content.put("fields", DBUtil.getTableColumns(databaseConfig, historyConfig, tableName));
        String serviceName = historyConfig.getServiceName().contains("{c}") ?
            historyConfig.getServiceName().replace("{c}", table.getEntityName()) :
            historyConfig.getServiceName();
        String serviceImplName = historyConfig.getServiceImplName().contains("{c}") ?
            historyConfig.getServiceImplName().replace("{c}", table.getEntityName()) :
            historyConfig.getServiceImplName();
        String readDaoName = historyConfig.getReadDaoName().contains("{c}") ?
            historyConfig.getReadDaoName().replace("{c}", table.getEntityName()) :
            historyConfig.getReadDaoName();
        String readMapperName = historyConfig.getReadMapperName().contains("{c}") ?
            historyConfig.getReadMapperName().replace("{c}", table.getEntityName()) :
            historyConfig.getReadMapperName();
        String writeDaoName = historyConfig.getWriteDaoName().contains("{c}") ?
            historyConfig.getWriteDaoName().replace("{c}", table.getEntityName()) :
            historyConfig.getWriteDaoName();
        String writeMapperName = historyConfig.getWriteMapperName().contains("{c}") ?
            historyConfig.getWriteMapperName().replace("{c}", table.getEntityName()) :
            historyConfig.getWriteMapperName();
        String controllerName = historyConfig.getControllerName().contains("{c}") ?
            historyConfig.getControllerName().replace("{c}", table.getEntityName()) :
            historyConfig.getControllerName();
        content.put("serviceName", serviceName);
        content.put("serviceImplName", serviceImplName);
        content.put("readDaoName", readDaoName);
        content.put("readMapperName", readMapperName);
        content.put("writeDaoName", writeDaoName);
        content.put("writeMapperName", writeMapperName);
        content.put("dbType", databaseConfig.getDbType());
        content.put("controllerName", controllerName);
        content.put("interfaceModelName", historyConfig.getModuleName());
        return content;
    }

    // ============================事件区域=================================

    /**
     * 执行创建
     */
    public void onCreate() {
        try {
            if (StrUtil.isNullOrEmpty(txtProjectPath.getText())) {
                StringProperty property = Main.LANGUAGE.get(LanguageKey.TIPS_PATH_CANT_EMPTY);
                String tips = property == null ? "生成的路径不能为空" : property.get();
                AlertUtil.showWarnAlert(tips);
                return;
            }
            if (StrUtil.isNullOrEmpty(txtTableName.getText())) {
                StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_TIPS_SELECT_TABLE);
                String tips = property == null ? "请双击左侧数据选择想要生成的表!" : property.get();
                AlertUtil.showWarnAlert(tips);
                return;
            }
            probCreateAll.setVisible(true);
            Task<Void> task = new Task<Void>() {

                @Override protected Void call() throws Exception {
                    updateProgress(1, 8);
                    HistoryConfig config = getThisHistoryConfig();
                    // 项目生成的路径
                    String projectPath = txtProjectPath.getText();
                    String codeFormat = cboCodeFormat.getValue();
                    // 获取模板需要的内容
                    Map<String, Object> content = getConfigContent(selectedDatabaseConfig, config, null);
                    content.put("config", config);
                    content.put("base", ConfigUtil.getBaseInfoConfig("BaseInfo"));
                    content.put("systemTime", DateUtil.getSystemCurrentDateTimeStr());
                    // 生成实体类
                    log.debug("生成实体类...");
                    try {
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtEntityName.getText()));
                        CreateFileUtil
                            .createFile(content, Constant.TEMPLATE_NAME_ENTITY, projectPath, txtEntityPackage.getText(),
                                txtEntityName.getText() + Constant.JAVA_SUFFIX, codeFormat, coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成实体类:" + txtEntityName.getText() + "失败:" + e);
                    }
                    log.debug("生成C#实体类...");
                    try {
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtEntityName.getText()));
                        CreateFileUtil
                            .createFile(content, Constant.TEMPLATE_NAME_CSHARP_ENTITY, projectPath, "Corex.Entity",
                                txtEntityName.getText() + Constant.CSHARP_SUFFIX, codeFormat, coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成C#实体类:" + txtEntityName.getText() + "失败:" + e);
                    }
                    log.debug("生成实体VO...");
                    try {
                        String entityExtendsPackageName = txtEntityPackage.getText() + ".vo";
                        String entityExtendsName = txtEntityName.getText() + "Vo";
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", entityExtendsName));
                        CreateFileUtil.createFile(content, Constant.TEMPLATE_NAME_ENTITY_EXTEND, projectPath,
                            entityExtendsPackageName, entityExtendsName + Constant.JAVA_SUFFIX, codeFormat,
                            coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成实体VO:" + txtEntityName.getText() + "Vo失败:" + e);
                    }
                    log.debug("生成C#实体类VO...");
                    try {
                        String csharpEntityVoName = txtEntityName.getText() + "Vo";
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", csharpEntityVoName));
                        CreateFileUtil.createFile(content, Constant.TEMPLATE_NAME_CSHARP_ENTITY_EXTEND, projectPath,
                            "Corex.Extend", csharpEntityVoName + Constant.CSHARP_SUFFIX, codeFormat,
                            coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成C#实体类VO:" + txtEntityName.getText() + "Vo失败:" + e);
                    }
                    // 生成Service
                    updateProgress(2, 8);
                    log.debug("生成Service...");
                    try {
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtServiceName.getText()));
                        CreateFileUtil.createFile(content, Constant.TEMPLATE_NAME_SERVICE, projectPath,
                            txtServicePackage.getText(), txtServiceName.getText() + Constant.JAVA_SUFFIX, codeFormat,
                            coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成Service:" + txtServiceName.getText() + "失败:" + e);
                    }
                    // 生成ServiceImpl
                    updateProgress(3, 8);
                    log.debug("生成ServiceImpl...");
                    try {
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtServiceImplName.getText()));
                        CreateFileUtil.createFile(content, Constant.TEMPLATE_NAME_SERVICE_IMPL, projectPath,
                            txtServiceImplPackage.getText(), txtServiceImplName.getText() + Constant.JAVA_SUFFIX,
                            codeFormat, coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成ServiceImpl:" + txtServiceImplName.getText() + "失败:" + e);
                    }
                    // 生成ReadDao
                    updateProgress(4, 8);
                    log.debug("生成ReadDao...");
                    try {
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtReadDaoName.getText()));
                        CreateFileUtil.createFile(content, Constant.TEMPLATE_NAME_READ_DAO, projectPath,
                            txtReadDaoPackage.getText(), txtReadDaoName.getText() + Constant.JAVA_SUFFIX, codeFormat,
                            coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成ReadDao:" + txtReadDaoName.getText() + "失败:" + e);
                    }
                    // 生成WriteDao
                    updateProgress(5, 8);
                    log.debug("生成WriteDao...");
                    try {
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtWriteDaoName.getText()));
                        CreateFileUtil.createFile(content, Constant.TEMPLATE_NAME_WRITE_DAO, projectPath,
                            txtWriteDaoPackage.getText(), txtWriteDaoName.getText() + Constant.JAVA_SUFFIX, codeFormat,
                            coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成WriteDao:" + txtWriteDaoName.getText() + "失败:" + e);
                    }
                    // 生成ReadDaoMapper xml
                    updateProgress(6, 8);
                    log.debug("生成ReadDaoMapperXml...");
                    try {
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtReadMapperName.getText()));
                        CreateFileUtil.createFile(content, Constant.TEMPLATE_NAME_READ_MAPPER_XML, projectPath,
                            txtReadMapperPackage.getText(), txtReadMapperName.getText(), codeFormat,
                            coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成ReadDaoMapperXml:" + txtReadMapperName.getText() + "失败:" + e);
                    }
                    log.debug("生成ReadDaoMapperExtendXml...");
                    try {
                        String readMapperExtendName = txtReadMapperName.getText().replace(".xml", "") + "Extend.xml";
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", readMapperExtendName));
                        CreateFileUtil.createFile(content, Constant.TEMPLATE_NAME_READ_MAPPER_XML_EXTEND, projectPath,
                            txtReadMapperPackage.getText() + ".extend", readMapperExtendName, codeFormat,
                            coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成ReadDaoMapperExtendXml:" + txtReadMapperName.getText() + "Extend失败:" + e);
                    }
                    // 生成WriteDaoMapper xml
                    updateProgress(7, 8);
                    log.debug("生成WriteDaoMapperXml...");
                    try {
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtWriteMapperName.getText()));
                        CreateFileUtil.createFile(content, Constant.TEMPLATE_NAME_WRITE_MAPPER_XML, projectPath,
                            txtWriteMapperPackage.getText(), txtWriteMapperName.getText(), codeFormat,
                            coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成WriteDaoMapperXml:" + txtWriteMapperName.getText() + "失败:" + e);
                    }
                    log.debug("生成WriteDaoMapperExtendXml...");
                    try {
                        String writeMapperExtendName = txtReadMapperName.getText().replace(".xml", "") + "Extend.xml";
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", writeMapperExtendName));
                        CreateFileUtil.createFile(content, Constant.TEMPLATE_NAME_WRITE_MAPPER_XML_EXTEND, projectPath,
                            txtWriteMapperPackage.getText() + ".extend", writeMapperExtendName, codeFormat,
                            coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成WriteDaoMapperExtendXml:" + txtWriteMapperName.getText() + "Extend失败:" + e);
                    }
                    // 生成Controller
                    updateProgress(8, 8);
                    log.debug("生成Controller...");
                    try {
                        updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtControllerName.getText()));
                        CreateFileUtil.createFile(content, Constant.TEMPLATE_NAME_CONTROLLER, projectPath,
                            txtControllerPackage.getText(), txtControllerName.getText() + Constant.JAVA_SUFFIX,
                            codeFormat, coverFile.isSelected());
                    } catch (Exception e) {
                        updateMessage("执行生成Controller:" + txtControllerName.getText() + "失败:" + e);
                    }
                    updateMessage("创建成功!");
                    return null;
                }
            };
            probCreateAll.progressProperty().bind(task.progressProperty());
            runCreateAllTips.textProperty().bind(task.messageProperty());
            new Thread(task).start();
        } catch (Exception e) {
            AlertUtil.showErrorAlert("创建文件失败:" + e);
        }
    }

    /**
     * 保存配置文件
     */
    public void onSaveConfig() {
        // 检查是否类名是否存在占位符
        boolean indexOf = StrUtil
            .indexOf("{c}", txtEntityName.getText(), txtServiceName.getText(), txtServiceImplName.getText(),
                txtControllerName.getText(), txtReadDaoName.getText(), txtReadMapperName.getText(),
                txtWriteDaoName.getText(), txtWriteMapperName.getText());
        if (!indexOf) {
            StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_SAVE_CONFIG_NOT_C_TIPS);
            String title = property == null ? "所有类名里面必须包含用于替换表名的占位符: {c}" : property.get();
            AlertUtil.showWarnAlert(title);
            return;
        }
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("保存当前配置");
        StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_SAVE_CONFIG_TIPS);
        String title = property == null ? "请输入配置名称:\\r\\n(表名不在保存范围内必须通过数据库加载!!!)" : property.get();
        dialog.setContentText(title);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                String name = Constant.DEFAULT;
                name = StrUtil.isNullOrEmpty(result.get()) ? Constant.DEFAULT : result.get();
                HistoryConfig config = getThisHistoryConfig();
                config.setHistoryConfigName(name);
                ConfigUtil.saveHistoryConfig(config);
                AlertUtil.showInfoAlert("保存配置成功!");
            } catch (Exception e) {
                AlertUtil.showErrorAlert("保存配置失败!失败原因:\r\n" + e.getMessage());
            }
        }
    }

    /**
     * 数据库连接
     */
    public void onConnection(MouseEvent event) {
        StringProperty property = Main.LANGUAGE.get(LanguageKey.PAGE_CREATE_CONNECTION);
        String title = property == null ? "新建数据库连接" : property.get();
        ConnectionController controller = (ConnectionController) loadFXMLPage(title, FXMLPage.CONNECTION, false);
        controller.setIndexController(this);
        controller.showDialogStage();
    }

    /**
     * 配置信息
     */
    public void onConfig(MouseEvent event) {
        HistoryConfigController controller =
            (HistoryConfigController) loadFXMLPage("配置信息管理", FXMLPage.HISTORY_CONFIG, false);
        controller.setIndexController(this);
        controller.showDialogStage();
    }

    /**
     * 基础配置信息
     */
    public void onBaseInfoConfig(MouseEvent event) {
        BaseInfoConfigController controller =
            (BaseInfoConfigController) loadFXMLPage("基础信息管理", FXMLPage.BASE_INFO_CONFIG, false);
        controller.setIndexController(this);
        controller.showDialogStage();
    }

    /**
     * 打开设置的事件
     */
    public void onSetting(MouseEvent event) {
        SettingController controller = (SettingController) loadFXMLPage("设置", FXMLPage.SETTING, false, false);
        controller.showDialogStage();
    }

    /**
     * 选择项目文件
     */
    public void onSelectProjectPath() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(super.getPrimaryStage());
        if (file != null) {
            txtProjectPath.setText(file.getPath());
        }
    }

    /**
     * 打开生成文件目录
     */
    public void onOpenCreateDir() {
        try {
            File file = new File(txtProjectPath.getText());
            if (file.exists() && file.isDirectory()) {
                Desktop.getDesktop().open(file);
            } else {
                AlertUtil.showErrorAlert("打开生成文件目录失败!原因:\r\n目录不存在!");
            }
        } catch (Exception e) {
            AlertUtil.showErrorAlert("打开生成文件目录失败!原因:\r\n" + e.getMessage());
        }

    }

}
