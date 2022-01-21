package ${config.entityPackage};

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mozi.corex.common.base.entity.ExtEntity;
import com.mozi.corex.common.validator.annotation.MaxLength;
import com.mozi.corex.common.validator.annotation.NotBlank;
<#assign useLombok = config.useLombok?string("true","flase")>
<#if useLombok == "true" >
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
</#if>

/**
 * ${entityComment!''}实体类
 * @author ${base.author!''}
 * @date ${systemTime!''}
 * @version ${base.version!''}
 */
<#if useLombok == "true" >
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
</#if>
public class ${entityName!''} extends ExtEntity {

    private static final long serialVersionUID = 1L;

<#list fields as field>
<#if !config.publicField?contains(field.dbColumn)>
    /**
     * ${field.chName}
     */
    <#if field.enNameType == 'Date'>
    @JsonFormat
    </#if>
    <#if field.must?string("true","flase") == "true" && field.primaryKey?string("true","flase") != "true">
    @NotBlank(message = "${field.chName}不能为空")
    </#if>
    <#if field.enNameType == 'String' && field.primaryKey?string("true","flase") != "true">
    @MaxLength(message = "${field.chName}最大长度不能超过", length = ${field.maxValue})
    </#if>
    private ${field.enNameType} ${field.enName}<#if field.enNameType == 'BigDecimal'> = new BigDecimal("0")</#if>;

</#if>
</#list>
<#if useLombok != "true" >
    <#list fields as field>
        <#if !config.publicField?contains(field.dbColumn)>
        public ${field.enNameType} get${field.enName?cap_first}(){
            return this.${field.enName};
        }

        public void set${field.enName?cap_first}(${field.enNameType} ${field.enName}){
            this.${field.enName} = ${field.enName};
        }

        </#if>
    </#list>
    @Override
    public String toString() {
        return "${entityName!''} ["+
        <#list fields as field>
            <#if !config.publicField?contains(field.dbColumn)>
                "${field.enName} = " + ${field.enName} +
            </#if>
        </#list>
        "]";
    }
</#if>
}