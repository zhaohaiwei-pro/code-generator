/*************************************************
 * 描述：${entityComment!''}实体类
 *
 * Author：${base.author!''}
 * Date：${systemTime!''}
 * Update：
 * ************************************************/
using System;
using System.Collections.Generic;
using System.ComponentModel;

namespace CoreX.Entity
{
    /// <summary>
	/// ${entityComment!''}实体类
	/// </summary>
	[Serializable]
    [Description("${entityComment!''}实体类")]
    public class ${entityName!''}
    {
        <#list fields as field>
        /// <summary>
        /// ${field.chName}
        /// </summary>
        [<#if field.primaryKey?string("true","flase") == "true">HideField(true), </#if>Description("${field.chName}")]
        <#if field.enNameType == 'String'>
        public string ${field.enName?cap_first} { get; set; }
        <#elseif field.enNameType == 'Date'>
        public DateTime? ${field.enName?cap_first} { get; set; }
        <#elseif field.enNameType == 'BigDecimal'>
        public decimal? ${field.enName?cap_first} { get; set; }
        <#elseif field.enNameType == 'int'>
        public int? ${field.enName?cap_first} { get; set; }
        <#else>
        public string ${field.enName?cap_first} { get; set; }
        </#if>
        </#list>
    }
}