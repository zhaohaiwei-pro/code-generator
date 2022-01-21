package ${config.serviceImplPackage};

import java.util.List;
<#assign useCache = config.useCache?string("true","flase")>
import org.springframework.beans.factory.annotation.Autowired;
<#if useCache == "true">
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
</#if>

import org.springframework.stereotype.Component;

import com.mozi.corex.common.constant.CacheKeyConstant;
import com.mozi.corex.common.exception.AssertUtil;
import com.mozi.corex.common.log.annotation.SystemServiceLog;
import com.mozi.corex.common.utils.Identities;
import com.mozi.corex.common.utils.page.Page;
import ${config.readDaoPackage}.${readDaoName};
import ${config.writeDaoPackage}.${writeDaoName};
import ${config.entityPackage}.${entityName};
import ${config.servicePackage}.${serviceName};

import lombok.extern.slf4j.Slf4j;

/**
 * ${entityComment!''}Service实现类
 * @author ${base.author!''}
 * @date ${systemTime!''}
 * @version ${base.version!''}
 */
@Slf4j
@Component
public class ${serviceImplName} implements ${serviceName} {

    <#assign readDaoNameInjection = readDaoName?uncap_first>
    @Autowired
    private ${readDaoName} ${readDaoNameInjection};

    <#assign writeDaoNameInjection = writeDaoName?uncap_first>
    @Autowired
    private ${writeDaoName} ${writeDaoNameInjection};

    /**
     * ${entityComment!''}分页查询
     * @param page
     * @return Page<${entityName}>
     */
    @Override
    <#if useCache == "true">
    @Cacheable(value = "${entityName}", key = "#root.targetClass + #root.methodName + #page.sortList + #page.criteriaList + #page.pageNo + #page.pageSize + #page.parameter")
    </#if>
    public Page<${entityName}> searchByPage(Page<${entityName}> page){
        AssertUtil.notNull(page, "参数page为空!");
        page.setResult(${readDaoNameInjection}.queryAllByPage(page));
        return page;
    }

    /**
     * ${entityComment!''}查询全部
     * @param page
     * @return List<${entityName}>
     */
    @Override
    <#if useCache == "true">
    @Cacheable(value = "${entityName}", key = "#root.targetClass + #root.methodName + #page.sortList + #page.criteriaList + #page.pageNo + #page.pageSize + #page.parameter")
    </#if>
    public List<${entityName}> getAll(Page<${entityName}> page){
        AssertUtil.notNull(page, "参数page为空!");
        return ${readDaoNameInjection}.getAll(page);
    }

    <#assign entityNameInjection = entityName?uncap_first>
    /**
     * ${entityComment!''}保存
     * @param ${entityNameInjection}
     * @return 主键id
     */
    @Override
    @ServiceLog(description = "保存${entityComment!''}")
    <#if useCache == "true">
    @CacheEvict(value = "${entityName}", allEntries = true)
    </#if>
    public String save${entityName}(${entityName} ${entityNameInjection}){
        AssertUtil.notNull(${entityNameInjection}, "参数${entityNameInjection}为空!");
        AssertUtil.validate(${entityNameInjection});
        <#list fields as field>
        <#if field.primaryKey?string("true","flase") == "true">
        String id = Identities.getNextId("${tableName}");
        ${entityNameInjection}.set${field.enName?cap_first}(id);
        </#if>
        </#list>
        ${writeDaoNameInjection}.save(${entityNameInjection});
        return id;
    }

    /**
     * ${entityComment!''}批量保存
     * @param ${entityNameInjection}List
     */
    @Override
    @ServiceLog(description = "批量保存${entityComment!''}")
    <#if useCache == "true">
    @CacheEvict(value = "${entityName}", allEntries = true)
    </#if>
    public void batchSave${entityName}(List<${entityName}> ${entityNameInjection}List){
        AssertUtil.isNotEmpty(${entityNameInjection}List, "参数${entityNameInjection}List为空!");
        List<String> primaryKeyList = Identities.dbidList("${tableName}", ${entityNameInjection}List.size());
        ${entityNameInjection}List.forEach(${entityNameInjection} -> {
            AssertUtil.validate(${entityNameInjection});
            <#list fields as field>
            <#if field.primaryKey?string("true","flase") == "true">
            ${entityNameInjection}.set${field.enName?cap_first}(primaryKeyList.get(${entityNameInjection}List.indexOf(${entityNameInjection})));
            </#if>
            </#list>
        });
        ${writeDaoNameInjection}.batchSave${entityName}(${entityNameInjection}List);
    }

    /**
     * ${entityComment!''}更新
     * @param ${entityNameInjection}
     */
    @Override
    @ServiceLog(description = "${entityComment!''}更新")
    <#if useCache == "true">
    @CacheEvict(value = "${entityName}", allEntries = true)
    </#if>
    public void update${entityName}(${entityName} ${entityNameInjection}){
        AssertUtil.notNull(${entityNameInjection}, "参数${entityNameInjection}为空!");
        AssertUtil.validate(${entityNameInjection});
        ${writeDaoNameInjection}.update(${entityNameInjection});
    }

    /**
     * ${entityComment!''}删除
     * @param id
     */
    @Override
    @ServiceLog(description = "${entityComment!''}删除")
    <#if useCache == "true">
    @CacheEvict(value = "${entityName}", allEntries = true)
    </#if>
    public void delete${entityName}(String id){
        AssertUtil.isNotBlank(id, "参数id为空!");
        ${writeDaoNameInjection}.delete(id);
    }

    /**
     * ${entityComment!''}根据主键查询
     * @param id
     * @return ${entityName}
     */
    @Override
    <#if useCache == "true">
    @Cacheable(value = "${entityName}", key = "#root.targetClass + #root.methodName + #id")
    </#if>
    public ${entityName} get(String id){
        AssertUtil.isNotBlank(id, "参数id为空!");
        return ${readDaoNameInjection}.get(id);
    }

}