package com.zhw.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 表信息
 *
 * @author zhaohaiwei@mozihealthcare.cn
 * @version 2.3.0
 * @date 2020/12/7 3:27 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TableInfo implements Serializable {

    private static final long serialVersionUID = 1897986909934442539L;

    /**
     * 表名
     */
    private String tableName;
    /**
     * 实体类名
     */
    private String entityName;
    /**
     * 表注释
     */
    private String comment;
}
