package ${config.entityPackage}.vo;

import ${config.entityPackage}.${entityName!''};
<#assign useLombok = config.useLombok?string("true","flase")>
<#if useLombok == "true" >
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
</#if>

/**
 * ${entityComment!''}实体VO
 * @author ${base.author!''}
 * @date ${systemTime!''}
 * @version ${base.version!''}
 */
<#if useLombok == "true" >
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
</#if>
public class ${entityName!''}Vo extends ${entityName!''} {

    private static final long serialVersionUID = 1L;

}