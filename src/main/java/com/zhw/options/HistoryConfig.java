package com.zhw.options;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 配置信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryConfig {

    /**
     * 配置信息的名字
     */
    private String historyConfigName;
    /**
     * 生产路径
     */
    private String projectPath;
    /**
     * 实体类的包名
     */
    private String entityPackage;
    /**
     * 实体类的类名
     */
    private String entityName;
    /**
     * service包名
     */
    private String servicePackage;
    /**
     * service类名
     */
    private String serviceName;
    /**
     * service实现类包名
     */
    private String serviceImplPackage;
    /**
     * service实现类名
     */
    private String serviceImplName;
    /**
     * Controller类包名
     */
    private String controllerPackage;
    /**
     * Controller类名
     */
    private String controllerName;
    /**
     * ReadDao类的包
     */
    private String readDaoPackage;
    /**
     * ReadDao类名
     */
    private String readDaoName;
    /**
     * ReadMapper类的包
     */
    private String readMapperPackage;
    /**
     * ReadMapper名称
     */
    private String readMapperName;
    /**
     * WriteDao类的包
     */
    private String writeDaoPackage;
    /**
     * WriteDao类名
     */
    private String writeDaoName;
    /**
     * WriteMapper类的包
     */
    private String writeMapperPackage;
    /**
     * WriteMapper名称
     */
    private String writeMapperName;
    /**
     * 表名前缀
     */
    private String tablePreFix;
    /**
     * 字段名前缀
     */
    private String fieldPreFix;
    /**
     * 公共字段
     */
    private String publicField;
    /**
     * 是否使用缓存
     */
    private boolean useCache;
    /**
     * 是否使用Swagger
     */
    private boolean useSwagger;
    /**
     * 是否使用Lombok
     */
    private boolean useLombok;
    /**
     * 是否覆盖已生成文件
     */
    private boolean coverFile;
    /**
     * 字符编码格式
     */
    private String codeFormat;
    /**
     * 所属模块
     */
    private String moduleName;
    /**
     * 数据库配置文件
     */
    private DatabaseConfig dbConfig;

}
