package ${config.controllerPackage};

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mozi.corex.common.base.handler.BaseHandler;
import com.mozi.corex.common.log.annotation.SystemModuleLog;
import com.mozi.corex.common.utils.mapper.JsonMapper;
import com.mozi.corex.common.utils.page.Page;
import com.mozi.corex.common.vo.OptParaVo;
import com.mozi.corex.common.vo.ResultVo;
import ${config.entityPackage}.${entityName};
import ${config.servicePackage}.${serviceName};
<#assign swagger = config.useSwagger?string("true","flase")>
<#if swagger == "true">
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
</#if>

/**
 * ${entityComment!''}管理Handler
 * @author ${base.author!''}
 * @date ${systemTime!''}
 * @version ${base.version!''}
 */
@SystemModuleLog(name = "${entityComment!''}管理")
<#if swagger == "true">
@Api(value = "${entityComment!''}管理", tags = {"${entityComment!''}管理"})
</#if>
@RestController
@RequestMapping(value = "/admin/${interfaceModelName}/${controllerName}", method = RequestMethod.POST)
public class ${controllerName} extends BaseHandler {

    <#assign serviceNameInjection = serviceImplName?uncap_first>
    @Autowired
    private ${serviceName} ${serviceNameInjection};

    /**
     * 分页查询列表数据
     */
    @RequestMapping("/listData")
    <#if swagger == "true">
    @ApiOperation(value = "${entityComment!''}列表查询", notes = "${entityComment!''}列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Page", value = "分页Bean", paramType = "Page")
    })
    </#if>
    public ResultVo listData(HttpServletRequest request)  {
        OptParaVo<Page> paraVo = JsonMapper.fromJson(requestStreamToString(request), OptParaVo.class, Page.class);
        return ResultVo.success(paraVo.getReqToken(), "${entityComment!''}查询成功", ${serviceNameInjection}.searchByPage(paraVo.getOptObj()));
    }

    /**
     * ${entityComment!''}新增
     */
    @RequestMapping(value = "/save")
    <#if swagger == "true">
    @ApiOperation(value = "${entityComment!''}新增", notes = "${entityComment!''}新增")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "${entityName}", value = "${entityName}实体", paramType = "${entityName}")
    })
    </#if>
    public ResultVo save(HttpServletRequest request) {
        OptParaVo<${entityName}> paraVo = JsonMapper.fromJson(requestStreamToString(request), OptParaVo.class, ${entityName}.class);
        return ResultVo.success(paraVo.getReqToken(), "${entityComment!''}新增成功", ${serviceNameInjection}.save${entityName}(paraVo.getOptObj()));
    }

    /**
     * ${entityComment!''}批量新增
     */
    @RequestMapping(value = "/batchSave")
    <#if swagger == "true">
    @ApiOperation(value = "${entityComment!''}批量新增", notes = "${entityComment!''}批量新增")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "List<${entityName}>", value = "${entityComment!''} list集合字符串", paramType = "String")
    })
    </#if>
    public ResultVo batchSave(HttpServletRequest request) {
        OptParaVo<String> paraVo = JsonMapper.fromJson(requestStreamToString(request), OptParaVo.class, String.class);
        ${serviceNameInjection}.batchSave${entityName}(JsonMapper.fromJson(paraVo.getOptObj(), ArrayList.class, ${entityName}.class));
        return ResultVo.success(paraVo.getReqToken(), "${entityComment!''}批量新增成功");
    }

    /**
     * ${entityComment!''}修改
     */
    @RequestMapping(value = "/update")
    <#if swagger == "true">
    @ApiOperation(value = "${entityComment!''}修改", notes = "${entityComment!''}修改")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "${entityName}", value = "${entityName}实体", paramType = "${entityName}")
    })
    </#if>
    public ResultVo update(HttpServletRequest request) {
        OptParaVo<${entityName}> paraVo = JsonMapper.fromJson(requestStreamToString(request), OptParaVo.class, ${entityName}.class);
        ${serviceNameInjection}.update${entityName}(paraVo.getOptObj());
        return ResultVo.success(paraVo.getReqToken(), "${entityComment!''}更新成功");
    }

    /**
     * ${entityComment!''}删除
     */
    @RequestMapping(value = "/delete")
    <#if swagger == "true">
    @ApiOperation(value = "${entityComment!''}删除", notes = "${entityComment!''}删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "${entityComment!''}主键id", value = "${entityComment!''}主键id", paramType = "String")
    })
    </#if>
    public ResultVo delete(HttpServletRequest request) {
        OptParaVo<String> paraVo = JsonMapper.fromJson(requestStreamToString(request), OptParaVo.class, String.class);
        ${serviceNameInjection}.delete${entityName}(paraVo.getOptObj());
        return ResultVo.success(paraVo.getReqToken(), "${entityComment!''}删除成功");
    }

    /**
     * ${entityComment!''}查询所有
     */
    @RequestMapping(value = "/getAll")
    <#if swagger == "true">
    @ApiOperation(value = "${entityComment!''}查询所有", notes = "${entityComment!''}查询所有")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Page", value = "分页Bean", paramType = "Page")
    })
    </#if>
    public ResultVo getAll(HttpServletRequest request) {
        OptParaVo<Page> paraVo = JsonMapper.fromJson(requestStreamToString(request), OptParaVo.class, Page.class);
        return ResultVo.success(paraVo.getReqToken(), "${entityComment!''}查询成功", ${serviceNameInjection}.getAll(paraVo.getOptObj()));
    }

    /**
     * 通过主键获取${entityComment!''}
     */
    @RequestMapping(value = "/getByPk")
    <#if swagger == "true">
    @ApiOperation(value = "通过主键获取${entityComment!''}", notes = "通过主键获取${entityComment!''}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "${entityComment!''}主键id", value = "${entityComment!''}主键id", paramType = "String")
    })
    </#if>
    public ResultVo getByPk(HttpServletRequest request) {
        OptParaVo<String> paraVo = JsonMapper.fromJson(requestStreamToString(request), OptParaVo.class, String.class);
        return ResultVo.success(paraVo.getReqToken(), "${entityComment!''}查询成功", ${serviceNameInjection}.get(paraVo.getOptObj()));
    }

}