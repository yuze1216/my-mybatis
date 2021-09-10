package com.yz.sqlsession;

import com.yz.utils.JDBCUtil;
import sun.dc.pr.PRError;

import java.util.Properties;

/**
 * @author:yuze
 * @description:工厂
 * @data:2021/9/10
 */
public class SqlSessionFactory {
    public Properties properties;
    public SqlSessionFactory(Properties properties){
        this.properties = properties;
    }
    public  SqlSession openSession(){
        SqlSession sqlSession = new SqlSession();
        return sqlSession;
    }
}
