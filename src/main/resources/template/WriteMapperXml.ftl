<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${config.writeDaoPackage}.${writeDaoName}">

    <!--insert-->
    <insert id="save" parameterType="${entityName}">
        INSERT INTO ${tableName}(
        <#list fields as field>
            <#if field_index +1 != fields?size>
            ${field.dbColumn},
            <#else>
            ${field.dbColumn}
            </#if>
        </#list>
        )
        VALUES(
        <#list fields as field>
        <#if field_index +1 != fields?size>
            <#if field.enNameType == 'int'>
            ${r'#{'}${field.enName}, jdbcType=INTEGER},
            <#elseif field.enNameType == 'BigDecimal'>
            ${r'#{'}${field.enName}, jdbcType=DECIMAL},
            <#elseif field.enNameType == 'Date'>
            ${r'#{'}${field.enName}, jdbcType=TIMESTAMP},
            <#elseif field.enNameType == 'float'>
            ${r'#{'}${field.enName}, jdbcType=FLOAT},
            <#else>
            ${r'#{'}${field.enName}, jdbcType=VARCHAR},
            </#if>
        <#else>
            <#if field.enNameType == 'int'>
            ${r'#{'}${field.enName}, jdbcType=INTEGER}
            <#elseif field.enNameType == 'BigDecimal'>
            ${r'#{'}${field.enName}, jdbcType=DECIMAL}
            <#elseif field.enNameType == 'Date'>
            ${r'#{'}${field.enName}, jdbcType=TIMESTAMP}
            <#elseif field.enNameType == 'float'>
            ${r'#{'}${field.enName}, jdbcType=FLOAT}
            <#else>
            ${r'#{'}${field.enName}, jdbcType=VARCHAR}
            </#if>
        </#if>
        </#list>
        )
    </insert>

    <!--Oracle batchSave-->
    <insert id="batchSave${entityName}" parameterType="java.util.List" databaseId="${dbType}">
        INSERT INTO ${tableName}(
        <#list fields as field>
            <#if field_index +1 != fields?size>
            ${field.dbColumn},
            <#else>
            ${field.dbColumn}
            </#if>
        </#list>
        )
        <foreach collection="list" item="item" index="index" separator="union all" >
            (SELECT
            <#list fields as field>
            <#if field_index +1 != fields?size>
                <#if field.enNameType == 'int'>
                ${r'#{'}item.${field.enName}, jdbcType=INTEGER},
                <#elseif field.enNameType == 'BigDecimal'>
                ${r'#{'}item.${field.enName}, jdbcType=DECIMAL},
                <#elseif field.enNameType == 'Date'>
                ${r'#{'}item.${field.enName}, jdbcType=TIMESTAMP},
                <#elseif field.enNameType == 'float'>
                ${r'#{'}item.${field.enName}, jdbcType=FLOAT},
                <#else>
                ${r'#{'}item.${field.enName}, jdbcType=VARCHAR},
                </#if>
            <#else>
                <#if field.enNameType == 'int'>
                ${r'#{'}item.${field.enName}, jdbcType=INTEGER}
                <#elseif field.enNameType == 'BigDecimal'>
                ${r'#{'}item.${field.enName}, jdbcType=DECIMAL}
                <#elseif field.enNameType == 'Date'>
                ${r'#{'}item.${field.enName}, jdbcType=TIMESTAMP}
                <#elseif field.enNameType == 'float'>
                ${r'#{'}item.${field.enName}, jdbcType=FLOAT}
                <#else>
                ${r'#{'}item.${field.enName}, jdbcType=VARCHAR}
                </#if>
            </#if>
            </#list>
            FROM DUAL)
        </foreach>
    </insert>

    <!--update-->
    <update id="update" parameterType="${entityName}">
        UPDATE ${tableName} SET
        <#list fields as field>
        <#if field.primaryKey?string("true","flase") != "true">
        <#if field_index +1 != fields?size>
            <#if field.enNameType == 'int'>
            ${field.dbColumn} = ${r'#{'}${field.enName}, jdbcType=INTEGER},
            <#elseif field.enNameType == 'BigDecimal'>
            ${field.dbColumn} = ${r'#{'}${field.enName}, jdbcType=DECIMAL},
            <#elseif field.enNameType == 'Date'>
            ${field.dbColumn} = ${r'#{'}${field.enName}, jdbcType=TIMESTAMP},
            <#elseif field.enNameType == 'float'>
            ${field.dbColumn} = ${r'#{'}${field.enName}, jdbcType=FLOAT},
            <#else>
            ${field.dbColumn} = ${r'#{'}${field.enName}, jdbcType=VARCHAR},
            </#if>
        <#else>
            <#if field.enNameType == 'int'>
            ${field.dbColumn} = ${r'#{'}${field.enName}, jdbcType=INTEGER}
            <#elseif field.enNameType == 'BigDecimal'>
            ${field.dbColumn} = ${r'#{'}${field.enName}, jdbcType=DECIMAL}
            <#elseif field.enNameType == 'Date'>
            ${field.dbColumn} = ${r'#{'}${field.enName}, jdbcType=TIMESTAMP}
            <#elseif field.enNameType == 'float'>
            ${field.dbColumn} = ${r'#{'}${field.enName}, jdbcType=FLOAT}
            <#else>
            ${field.dbColumn} = ${r'#{'}${field.enName}, jdbcType=VARCHAR}
            </#if>
        </#if>
        </#if>
        </#list>
        WHERE <#list fields as field><#if field.primaryKey?string("true","flase") == "true">${field.dbColumn} = ${r'#{'}${field.enName}${r'}'}</#if></#list>
    </update>

    <!--delete-->
    <delete id="delete" parameterType="string" >
        DELETE FROM ${tableName}
        WHERE <#list fields as field><#if field.primaryKey?string("true","flase") == "true">${field.dbColumn} = ${r'#{'}${field.enName}${r'}'}</#if></#list>
    </delete>

</mapper>