package com.yz.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author:yuze
 * @description:字符串工具类
 * @data:2021/9/11
 */
public class StringUtil {

    /**
     * 转为驼峰命名
     * @param str
     * @return string
     */
    public static String camelName(String str) {
        if (!isEmpty(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0, len = str.length(); i < len; i++) {
                if (str.charAt(i) == '_') {
                    while (str.charAt(i + 1) == '_') {
                        i++;
                    }
                    stringBuilder.append(("" + str.charAt(++i)).toUpperCase());
                } else {
                    stringBuilder.append(str.charAt(i));
                }
            }
            return stringBuilder.toString();
        }
        return str;
    }

    /**
     * 判断是否为空串
     *（三个空格的字符串是有长度的！！！）
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str != null && str.length() > 0) {
            for (int i = 0, len = str.length(); i < len; i++) {
                if (!Character.isSpaceChar(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断是否为空串(纯空，空格都不行)
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    /**
     * 将第一个字母替换为大写
     * @param str
     * @return
     */
    public static String firstUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    }

    /**
     * 获取数据库查询结果的列名
     * @author yuze
     * @date 2021/9/10 10:46
     * @param [rs]
     * @return java.lang.String[]
     */
    public static String[] getColNames(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int len = rsmd.getColumnCount();
        String[] colNames = new String[len];
        for (int i = 1; i <=len ; i++) {
            colNames[i-1] = rsmd.getColumnLabel(i);
        }
        return colNames;
    }
    /**
     *
     * 将列名首字母大写
     * @author yuze
     * @date 2021/9/10 11:20
     * @param [rs]
     * @return java.lang.String[]
     */
    public static String[] toCapitalizeColNames(String[] names) throws SQLException {
        int len = names.length;
        String[] str = new String[len];
        for (int i = 0; i <len ; i++) {
            String s = StringUtil.camelName(names[i]);
            String colname = StringUtil.firstUpperCase(s);
            str[i]=colname;
        }
        return str;
    }
}
