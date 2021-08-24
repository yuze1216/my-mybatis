package com.yz.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        logger.info("driver: {}",driver);
        logger.info("url: {}",url);
        logger.info("username: {}",username);
        logger.info("password: {}",password);
    }

    public static Connection getConnection(){
        Connection coon = null;
        try{
            coon = DriverManager.getConnection(url,username,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return coon;
    }

    public static void main(String[] args) {
        DataBase.getConnection();
    }
}
