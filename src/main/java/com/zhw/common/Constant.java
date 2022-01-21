package com.zhw.common;

/**
 * 工具需要用到的常量词
 *
 */
public interface Constant {

    // 数据库名字
    /** MySQL */
    String MYSQL = "MySQL";
    /** PostgreSQL */
    String POSTGRE_SQL = "PostgreSQL";
    /** SqlServer */
    String SQL_SERVER = "SqlServer";
    /** Oracle */
    String ORACLE = "Oracle";
    /** Oracle */
    String SQLITE = "Sqlite";

    /**
     * java的后缀名.java
     */
    String JAVA_SUFFIX = ".java";
    /**
     * C#的后缀名.cs
     */
    String CSHARP_SUFFIX = ".cs";
    /**
     * XML的后缀名.xml
     */
    String XML_SUFFIX = ".xml";

    /**
     * default
     */
    String DEFAULT = "default";
    /**
     * 模板的文件夹名称
     */
    String TEMPLATE_DIR_NAME = "template";
    /**
     * 实体类模板的默认名字
     */
    String TEMPLATE_NAME_ENTITY = "Entity.ftl";
    /**
     * 实体VO模板默认名字
     */
    String TEMPLATE_NAME_ENTITY_EXTEND = "EntityExtend.ftl";
    /**
     * C#实体类模板的默认名字
     */
    String TEMPLATE_NAME_CSHARP_ENTITY = "CSharpEntity.ftl";
    /**
     * C#实体VO模板默认名字
     */
    String TEMPLATE_NAME_CSHARP_ENTITY_EXTEND = "CSharpEntityExtend.ftl";
    /**
     * Service模板的默认名字
     */
    String TEMPLATE_NAME_SERVICE = "Service.ftl";
    /**
     * ServiceImpl模板的默认名字
     */
    String TEMPLATE_NAME_SERVICE_IMPL = "ServiceImpl.ftl";
    /**
     * ReadDao模板的默认名字
     */
    String TEMPLATE_NAME_READ_DAO = "ReadDao.ftl";
    /**
     * WriteDao模板的默认名字
     */
    String TEMPLATE_NAME_WRITE_DAO = "WriteDao.ftl";
    /**
     * ReadDaoMapper xml模板的默认名字
     */
    String TEMPLATE_NAME_READ_MAPPER_XML = "ReadMapperXml.ftl";
    /**
     * ReadDaoMapper xml子类模板默认名称
     */
    String TEMPLATE_NAME_READ_MAPPER_XML_EXTEND = "ReadMapperXmlExtend.ftl";
    /**
     * WriteDaoMapper xml模板的默认名字
     */
    String TEMPLATE_NAME_WRITE_MAPPER_XML = "WriteMapperXml.ftl";
    /**
     * WriteDaoMapper xml子类模板的默认名字
     */
    String TEMPLATE_NAME_WRITE_MAPPER_XML_EXTEND = "WriteMapperXmlExtend.ftl";
    /**
     * Controller模板的默认名字
     */
    String TEMPLATE_NAME_CONTROLLER = "Controller.ftl";
}
