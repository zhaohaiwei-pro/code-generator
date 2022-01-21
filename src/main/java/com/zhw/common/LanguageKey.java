package com.zhw.common;

/**
 * 语言国际化的常量
 */
public interface LanguageKey {

    // =========================页面标题==============================
    /** 创建数据库连接 */
    String PAGE_CREATE_CONNECTION = "page.createConnection";
    /** 修改数据库连接 */
    String PAGE_UPDATE_CONNECTION = "page.updateConnection";
    // =========================提示语================================
    /** 生成路径不能为空 */
    String TIPS_PATH_CANT_EMPTY = "tips.pathCantEmpty";
    /** 首页-提示先选择表名 */
    String INDEX_TIPS_SELECT_TABLE = "index.selectTableTips";
    /** 首页-配置文件的配置信息表格提示 */
    String HISTORY_CONFIG_TABLE_TIPS = "historyConfig.tableTips";

    // ======================首页相关区域==============================
    /** 首页的数据连接 */
    String INDEX_CONNECTION = "index.connection";
    /** 首页的配置信息 */
    String INDEX_CONFIG = "index.config";
    /** 首页的基础配置信息 */
    String INDEX_BASE_CONFIG = "index.baseConfig";
    /** 首页的设置 */
    String INDEX_SETTING = "index.setting";
    /** 首页的保存配置提示 */
    String INDEX_SAVE_CONFIG_TIPS = "index.saveConfigTips";
    /** 首页的保存配置不合格提示 */
    String INDEX_SAVE_CONFIG_NOT_C_TIPS = "index.saveConfigNotCTips";

    /** 首页-项目路径文字 */
    String INDEX_PROJECT_PATH = "index.projectPath";
    /** 首页-项目路径输入框 */
    String INDEX_TXT_PROJECT_PATH = "index.txtProjectPath";
    /** 首页-选择项目路径 */
    String INDEX_BTN_SELECT_FILE = "index.btnSelectFile";
    /** 首页-表名 */
    String INDEX_TABLE_NAME = "index.tableName";
    /** 首页-表名输入框提示 */
    String INDEX_TXT_TABLE_NAME = "index.txtTableName";
    /** 首页-实体类包名 */
    String INDEX_ENTITY_PACKAGE = "index.entityPackage";
    /** 首页-Service接口包名 */
    String INDEX_SERVICE_PACKAGE = "index.servicePackage";
    /** 首页-Service实现类包名 */
    String INDEX_SERVICE_IMPL_PACKAGE = "index.serviceImplPackage";
    /** 首页-Controller包名 */
    String INDEX_CONTROLLER_PACKAGE = "index.controllerPackage";
    /** 首页-ReadDao包名 */
    String INDEX_READ_DAO_PACKAGE = "index.readDaoPackage";
    /** 首页-ReadMapper包名 */
    String INDEX_READ_MAPPER_PACKAGE = "index.readMapperPackage";
    /** 首页-WriteDao包名 */
    String INDEX_WRITE_DAO_PACKAGE = "index.writeDaoPackage";
    /** 首页-WriteMapper包名 */
    String INDEX_WRITE_MAPPER_PACKAGE = "index.writeMapperPackage";
    /** 首页-实体类类名 */
    String INDEX_ENTITY_NAME = "index.entityName";
    /** 首页-Service接口名 */
    String INDEX_SERVICE_NAME = "index.serviceName";
    /** 首页-Service实现类名 */
    String INDEX_SERVICE_IMPL_NAME = "index.serviceImplName";
    /** 首页-Controller类名 */
    String INDEX_CONTROLLER_NAME = "index.controllerName";
    /** 首页-ReadDao类名 */
    String INDEX_READ_DAO_NAME = "index.readDaoName";
    /** 首页-ReadMapper名称 */
    String INDEX_READ_MAPPER_NAME = "index.readMapperName";
    /** 首页-WriteDao类名 */
    String INDEX_WRITE_DAO_NAME = "index.writeDaoName";
    /** 首页-WriteMapper名称 */
    String INDEX_WRITE_MAPPER_NAME = "index.writeMapperName";
    /** 首页-表名前缀 */
    String INDEX_TABLE_PREFIX = "index.tablePrefix";
    /** 首页-字段名前缀 */
    String INDEX_FIELD_PREFIX = "index.fieldPrefix";
    /** 首页-公共字段 */
    String INDEX_PUBLIC_FIELD = "index.publicField";
    /** 首页-文件编码格式 */
    String INDEX_CODE_FORMAT = "index.codeFormat";
    /** 首页-模块名称 */
    String INDEX_MODULE_NAME = "index.moduleName";
    /** 首页-启用缓存 */
    String INDEX_USE_CACHE = "index.useCache";
    /** 首页-启用Swagger */
    String INDEX_USE_SWAGGER = "index.useSwagger";
    /** 首页-启用Lombok插件 */
    String INDEX_USE_LOMBOK = "index.useLombok";
    /** 首页-覆盖原文件 */
    String INDEX_COVER_FILE = "index.coverFile";
    /** 首页-执行创建 */
    String INDEX_BTN_RUN_CREATE = "index.btnRunCreate";
    /** 首页-保存配置文件 */
    String INDEX_BTN_SAVE_CONFIG = "index.btnSaveConfig";
    /** 首页-保存配置文件 */
    String INDEX_BTN_OPEN_DIR = "index.btnOpenDir";
    /** 首页-数据库数右键刷新连接 */
    String INDEX_TVMI_RELOAD_CONNECT = "index.tvmiReloadConnect";
    /** 首页-数据库数右键打开连接 */
    String INDEX_TVMI_OPEN_CONNECT = "index.tvmiOpenConnect";
    /** 首页-数据库数右键关闭连接 */
    String INDEX_TVMI_CLOSE_CONNECT = "index.tvmiCloseConnect";
    /** 首页-数据库数右键修改连接 */
    String INDEX_TVMI_UPDATE_CONNECT = "index.tvmiUpdateConnect";
    /** 首页-数据库数右键删除连接 */
    String INDEX_TVMI_DELETE_CONNECT = "index.tvmiDeleteConnect";

    // =======================设置区域================================
    /** 设置-语言 */
    String SETTING_LANGUAGE = "setting.language";

    // ======================新建数据库连接==============================
    /** 数据库连接-连接名称 */
    String CONN_CONN_NAME = "conn.connName";
    /** 数据库连接-连接地址 */
    String CONN_CONN_URL = "conn.connUrl";
    /** 数据库连接-端口号 */
    String CONN_LISTEN_PORT = "conn.listenPort";
    /** 数据库连接-数据库类型 */
    String CONN_DB_TYPE = "conn.dBType";
    /** 数据库连接-数据库名字 */
    String CONN_DB_NAME = "conn.dbName";
    /** 数据库连接-用户名 */
    String CONN_USER_NAME = "conn.userName";
    /** 数据库连接-用户密码 */
    String CONN_USER_PWD = "conn.userPwd";
    /** 数据库连接-数据库编码 */
    String CONN_DB_CODING = "conn.dbCoding";
    /** 数据库连接-连接名称 */
    String CONN_TXT_CONN_NAME = "conn.txtConnName";
    /** 数据库连接-连接地址 */
    String CONN_TXT_CONN_URL = "conn.txtConnUrl";
    /** 数据库连接-端口号 */
    String CONN_TXT_LISTEN_PORT = "conn.txtListenPort";
    /** 数据库连接-数据库类型 */
    String CONN_CBO_DB_TYPE = "conn.cboDBType";
    /** 数据库连接-数据库名称 */
    String CONN_TXT_DB_NAME = "conn.txtDBName";
    /** 数据库连接-用户名字 */
    String CONN_TXT_USER_NAME = "conn.txtUserName";
    /** 数据库连接-用户密码 */
    String CONN_TXT_USER_PWD = "conn.txtUserPwd";
    /** 数据库连接-测试连接 */
    String CONN_BTN_TEST_CONN = "conn.btnTestConn";
    /** 数据库连接-保存 */
    String CONN_BTN_SAVE = "conn.btnSave";
    /** 数据库连接-取消 */
    String CONN_BTN_CANCEL = "conn.btnCancel";

    // ======================基础信息==================================
    /** 基础信息-作者 */
    String BASE_AUTHOR = "base.author";
    /** 基础信息-版本 */
    String BASE_VERSION = "base.version";
    /** 基础信息-保存 */
    String BASE_BTN_SAVE = "base.btnSave";
    /** 基础信息-取消 */
    String BASE_BTN_CANCEL = "base.btnCancel";

    // ========================配置信息==================================
    /** 数据库连接-提示语句 */
    String CONFIG_TIPS = "config.tips";
    /** 数据库连接-配置信息文件名 */
    String CONFIG_TD_INFO = "config.tdInfo";
    /** 数据库连接-操作 */
    String CONFIG_TD_OPERATION = "config.tdOperation";
    /** 数据库连接-加载 */
    String CONFIG_BTN_LOAD = "config.btnLoad";
    /** 数据库连接-删除 */
    String CONFIG_BTN_DATELE = "config.btnDelete";
}
