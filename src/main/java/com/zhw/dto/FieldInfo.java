package com.zhw.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TODO
 *
 * @author zhaohaiwei@mozihealthcare.cn
 * @version 2.3.0
 * @date 2020/12/7 3:47 下午
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FieldInfo implements Serializable {

    private static final long serialVersionUID = -4730222886728557525L;
    /**
     * 设置显示值 combo
     */
    private List<Map<String, String>> showValueList = new ArrayList<>();

    /**
     * 表中的字段
     */
    private String dbColumn;

    /**
     * 数据类型
     */
    private String dbColumnType;

    /**
     * 是否主键
     */
    private boolean primaryKey;

    /**
     * 实体属性名称
     */
    private String enName;

    /**
     * 实体属性类型
     */
    private String enNameType;

    /**
     * 中文名称
     */
    private String chName;

    /**
     * 是否必填
     */
    private boolean must;

    /**
     * 是否只读
     */
    private boolean readOnly;

    /**
     * 是否列表显示
     */
    private boolean listShow;
    /**
     * 显示类型:下拉框 。。。
     */
    private String showType;

    /**
     * 显示值
     */
    private String showValue;

    /**
     * 校验类型
     */
    private String valideType;

    /**
     * 唯一值校验URL
     */
    private String validUnique;

    /**
     * 最小值
     */
    private String minValue;

    /**
     * 最大值
     */
    private String maxValue;

    /**
     * 排序
     */
    private String sort;

    /**
     * 是否查询字段
     */
    private boolean queryField;

    /**
     * 查询插件
     */
    private String queryPlugin;

    /**
     * 查询条件
     */
    private String queryCondition;

}
