package com.zhw.common;

import java.util.List;

/**
 * 字符串工具
 */
public class StrUtil {

    /**
     * 去掉下划线并将字符串转换成帕斯卡命名规范
     */
    public static String unlineToPascal(String str) {
        if (str != null) {
            if (!str.contains("_")) {
                return fristToUpCaseLaterToLoCase(str);
            }
            StringBuilder result = new StringBuilder();
            String[] temp = str.split("_");
            for (String s : temp) {
                if ("".equals(s) || s.isEmpty()) {
                    continue;
                }
                result.append(fristToUpCaseLaterToLoCase(s));
            }
            return result.toString();
        }

        return str;
    }

    /**
     * 去掉下划线并将字符串转换成驼峰命名规范
     */
    public static String unlineToCamel(String str) {
        if (str != null) {
            if (!str.contains("_")) {
                return str.toLowerCase();
            }
            StringBuilder result = new StringBuilder();
            String[] temp = str.split("_");
            for (int i = 0; i < temp.length; i++) {
                if (i == 0) {
                    result.append(temp[i].toLowerCase());
                } else {
                    result.append(fristToUpCaseLaterToLoCase(temp[i]));
                }
            }
            return result.toString();
        }

        return str;
    }

    /**
     * 将字符串首字母大写其后小写
     */
    public static String fristToUpCaseLaterToLoCase(String str) {
        if (str != null && str.length() > 0) {
            str = (str.substring(0, 1).toUpperCase()) + (str.substring(1).toLowerCase());
        }
        return str;
    }

    /**
     * 将字符串首字母小写
     */
    public static String fristToLoCase(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return str;
    }

    /**
     * 检查字符串里面是否包含指定字符,包含返回true
     */
    public static boolean indexOf(String regex, String... str) {
        if (str == null) {
            return false;
        }
        for (String temp : str) {
            if (!temp.contains(regex)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将字符串str中带有集合中rep[0]的字符串,代替为rep[1]的中的字符串
     */
    public static String replace(String str, List<String[]> rep) {
        for (String[] item : rep) {
            if (item[1] == null) {
                item[1] = "";
            }
            str = str.replace(item[0], item[1]);
        }
        return str;
    }

    /**
     * 判断字符串是否为null或者空,如果是返回true
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 判断字符串是否为null或者空,如果是返回true
     */
    public static boolean isNullOrEmpty(String... str) {
        for (String s : str) {
            if (s == null || "".equals(s.trim())) {
                return true;
            }
        }
        return false;
    }

}
