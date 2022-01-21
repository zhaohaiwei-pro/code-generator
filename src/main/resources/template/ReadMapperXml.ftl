<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${config.readDaoPackage}.${readDaoName}">
    <resultMap id="${entityName}ResultMap" type="${entityName}">
        <#list fields as field>
        <#if field.primaryKey?string("true","flase") == "true">
        <id column="${field.dbColumn}" property="${field.enName}"/>
        </#if>
        </#list>
        <#list fields as field>
        <#if field.primaryKey?string("true","flase") != "true">
        <result column="${field.dbColumn}" property="${field.enName}"/>
        </#if>
        </#list>
    </resultMap>

    <sql id="${entityName}_Column_List">
        <#list fields as field>
        <#if field_index +1 != fields?size>
        ${field.dbColumn},
        <#else>
        ${field.dbColumn}
        </#if>
        </#list>
    </sql>

    <!-- queryPage-->
    <select id="queryAllByPage" parameterType="Page" resultMap="${entityName}ResultMap" resultType="${entityName}">
        SELECT
        <include refid="${entityName}_Column_List" />
        FROM ${tableName}
        <where>
            <if test="criteriaList != null and criteriaList.size() > 0">
                AND ${r'${criteriaStr}'}
            </if>
        </where>
        <!-- ORDER BY ${r'${orderBy}'} ${r'${order}'} -->
        <if test="sortList != null and sortList.size() > 0">
        ORDER BY
        <foreach collection="sortList" item="sort" index="index">
            <if test="index > 0"> ,</if>
            ${r'${sort.columnName}'}  ${r'${sort.direction}'}
        </foreach>
        </if>
    </select>

    <select id="getAll" parameterType="Page" resultMap="${entityName}ResultMap" resultType="${entityName}">
        SELECT
        <include refid="${entityName}_Column_List" />
        FROM ${tableName}
        <where>
            <if test="criteriaList != null and criteriaList.size() > 0">
                AND ${r'${criteriaStr}'}
            </if>
        </where>
        <!-- ORDER BY ${r'${orderBy}'} ${r'${order}'} -->
        <if test="sortList != null and sortList.size() > 0">
        ORDER BY
        <foreach collection="sortList" item="sort" index="index">
            <if test="index > 0"> ,</if>
            ${r'${sort.columnName}'}  ${r'${sort.direction}'}
        </foreach>
        </if>
    </select>

    <!--select by primary key-->
    <select id="get" parameterType="string" resultMap="${entityName}ResultMap" resultType="${entityName}">
        SELECT
        <include refid="${entityName}_Column_List" />
        FROM ${tableName}
        <#list fields as field>
        <#if field.primaryKey?string("true","flase") == "true">
        WHERE ${field.dbColumn} = ${r'#{'}${field.enName}${r'}'}
        </#if>
        </#list>
    </select>

</mapper>
