package com.zhw.options;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 配置信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseInfoConfig {

    /**
     * 配置名称
     */
    private String name;
    /**
     * 代码作者名称
     */
    private String author;
    /**
     * 代码版本号
     */
    private String version;
}
