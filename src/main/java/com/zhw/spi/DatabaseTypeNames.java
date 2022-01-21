package com.zhw.spi;

import java.util.ArrayList;
import java.util.List;

import com.zhw.common.Constant;

/**
 * 数据库名字
 *
 */
public class DatabaseTypeNames {

    /**
     * 数据库的名称集合
     */
    public static List<String> dbTypeNames() {
        List<String> result = new ArrayList<>();
        result.add(Constant.ORACLE);
        result.add(Constant.MYSQL);
        result.add(Constant.SQL_SERVER);
        result.add(Constant.POSTGRE_SQL);
        result.add(Constant.SQLITE);
        return result;
    }
}
