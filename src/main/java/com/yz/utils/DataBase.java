package com.yz.utils;

import com.yz.been.User;
import com.yz.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Properties;

/**
 * @author:yuze
 * @description:数据库连接信息
 * @data:2021/8/24
 */

public class DataBase {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static String mapper;
    private static Logger logger = LogManager.getLogger();
    static {//静态代码块
        Properties properties = new Properties();
        //通过类加载器读取把文件转化成输入字节流
        InputStream in = DataBase.class.getClassLoader().getResourceAsStream("database.properties");
        try {
            properties.load(in);//读取输入字节流
        }catch (IOException e){
            e.printStackTrace();
        }
        driver = properties.getProperty("driver").trim();
        url = properties.getProperty("url").trim();
        username = properties.getProperty("username").trim();
        password = properties.getProperty("password").trim();
        mapper =  properties.getProperty("mapper").trim();
        logger.info("driver: {}",driver);
        logger.info("url: {}",url);
        logger.info("username: {}",username);
        logger.info("password: {}",password);
        logger.info("mapper: {}",mapper);
    }
    /**
     *
     * 连接数据库
     * @author yuze
     * @date 2021/8/28 1:08
     * @param []
     * @return java.sql.Connection
     */
    public static Connection getConnection(){
        Connection coon = null;
        try{
            coon = DriverManager.getConnection(url,username,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return coon;
    }
    /**
     *
     * 关闭数据库
     * @author yuze
     * @date 2021/8/28 1:07
     * @param [conn, st, rs]
     * @return void
     */
    public static void close(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = UserMapper.class;//Class.forName("com.yz.mapper.UserMapper");
        System.out.println(clazz.getName());
//        System.out.println(clazz.getName());
//        System.out.println("====================================");
        //System.out.println(UserMapper.class.getName());
        UserMapper instance =(UserMapper) new MyProxyUtil().Instance(UserMapper.class);
        User user = instance.selectAll(1, 2);
        logger.info("user :{}",user.toString());




        //DataBase.getConnection();
    }
}
