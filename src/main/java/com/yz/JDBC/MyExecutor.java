package com.yz.JDBC;
import com.yz.utils.JDBCUtil;
import com.yz.utils.StringUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:yuze
 * @description:jdbc工具
 * @data:2021/9/10
 */
public class MyExecutor {
    /**
     * 封装多个对象（List类型的）
     * @author yuze
     * @date 2021/9/10 12:18
     * @param [sql, clz]
     * @return java.util.List<java.lang.Object>
     */
    public static <T> List<Object> getObjectList(String sql , Class<T> clz) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            //String[] colNames = getColNames(rs);
            String[] colNames =StringUtil.getColNames(rs);
            String[] capitalizeColNames = StringUtil.toCapitalizeColNames(colNames);
            int len = colNames.length;
            Object object = null;
            Method[] mt = clz.getMethods();
            List<Object> list =  new ArrayList<>();
            while (rs.next()) {
                object = clz.newInstance();
                for (int i = 0; i < len; i++) {
                    String colName = colNames[i];
                    String methodName = setMethodName(capitalizeColNames[i]);
                    for (Method m : mt) {
                        if (methodName.equals(m.getName())) {
                            m.invoke(object, rs.getObject(colName));
                        }
                    }
                }
                list.add(object);
            }
            return list;
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
    }
    /**
     *
     * 查询结果封入单个对象
     * @author yuze
     * @date 2021/9/10 19:58
     * @param [sql, clz]
     * @return java.lang.Object
     */
    public static <T> Object getObject(String sql , Class<T> clz) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            String[] colNames = StringUtil.getColNames(rs);
            String[] capitalizeColNames = StringUtil.toCapitalizeColNames(colNames);
            int len = colNames.length;
            Object object = null;
            Method[] mt = clz.getMethods();
            if (rs.next()) {
                object = clz.newInstance();
                for (int i = 0; i < len; i++) {
                    String colName = colNames[i];
                    //String methodName = "set" + capitalizeColNames[i];
                    String methodName = setMethodName(capitalizeColNames[i]);
                    for (Method m : mt) {
                        if (methodName.equals(m.getName())) {
                            m.invoke(object, rs.getObject(colName));
                        }
                    }
                }
            }
            return object;
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }
    }

    /**
     * 增加、删除、修改
     * @param sql sql语句
     * @param obj 参数
     * @return
     */
    public static Object getDML(String sql){

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);

//            for (int i = 1; i <= obj.length; i++) {
//                ps.setObject(i, obj[i - 1]);
//            }
            int update = ps.executeUpdate();
            if (update > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }
        return false;
    }
    /**
     *
     * 方法名拼接
     * @author yuze
     * @date 2021/9/11 19:40
     * @param [str]
     * @return java.lang.String
     */
    private static String setMethodName(String str) {
        return "set" + StringUtil.firstUpperCase(str);
    }


}
