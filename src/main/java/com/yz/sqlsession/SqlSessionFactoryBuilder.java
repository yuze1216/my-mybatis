package com.yz.sqlsession;

import com.yz.utils.JDBCUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author:yuze
 * @description:
 * @data:2021/9/10
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) {
        try {
            Properties properties = new Properties();
            //通过类加载器读取把文件转化成输入字节流
            try {
                properties.load(inputStream);//读取输入字节流
            }catch (IOException e){
                e.printStackTrace();
            }
            return build(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public SqlSessionFactory build(Properties properties) {
        return new SqlSessionFactory(properties);
    }
}
