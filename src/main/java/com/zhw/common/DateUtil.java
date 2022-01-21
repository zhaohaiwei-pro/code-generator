package com.zhw.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常用日期操作
 */
public class DateUtil {

    /**
     * 描述:设置为私有，防止工具类实例化
     */
    private DateUtil() {}

    /**
     * 将系统当前日期格式化为 yyyy-MM-dd HH:mm:ss 的字符串
     *
     * @return 系统日期时间字符串
     */
    public static String getSystemCurrentDateTimeStr() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        return df.format(new Date());
    }

}
