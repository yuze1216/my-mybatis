package com.yz;

import com.yz.been.User;
import com.yz.io.Resources;
import com.yz.mapper.UserMapper;
import com.yz.sqlsession.SqlSession;
import com.yz.sqlsession.SqlSessionFactory;
import com.yz.sqlsession.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:yuze
 * @description:测试类
 * @data:2021/8/24
 */
public class Test {
    private static Logger logger = LogManager.getLogger();
    private InputStream resourceAsStream = Resources.getResourceAsStream("database.properties");
    private SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    private SqlSession sqlSession = sqlSessionFactory.openSession();

    @org.junit.Test
    public void testList() throws BindException {
        UserMapper instance =(UserMapper)sqlSession.getMapper(UserMapper.class);
        List<User> user = instance.selectAll(1, 2);
        logger.info("user :{}",user.toString());
    }
    @org.junit.Test
    public void testOne() throws  BindException {
        UserMapper instance =(UserMapper)sqlSession.getMapper(UserMapper.class);
        User user = instance.listAll();
        logger.info("user :{}",user.toString());
    }
    /**
     * 测试删除
     * @author yuze
     * @date 2021/9/10 22:13
     * @param []
     * @return void
     */
    @org.junit.Test
    public void deleteOne() throws BindException {
        UserMapper instance =(UserMapper)sqlSession.getMapper(UserMapper.class);
        boolean flag = instance.deleteOne();
        logger.info("flag: {}",flag);
        if(flag){
            logger.info("删除成功");
        }else {
            logger.info("删除失败");
        }
    }
    /**
     *测试添加
     * @author yuze
     * @date 2021/9/10 22:13
     * @param []
     * @return void
     */
    @org.junit.Test
    public void insertOne() throws BindException {
        UserMapper instance =(UserMapper)sqlSession.getMapper(UserMapper.class);
        boolean flag = instance.insertOne();
        logger.info("flag: {}",flag);
        if(flag){
            logger.info("添加成功");
        }else {
            logger.info("添加失败");
        }
    }
    /**
     *测试更改
     * @author yuze
     * @date 2021/9/10 22:13
     * @param []
     * @return void
     */
    @org.junit.Test
    public void updateOne() throws BindException {
        UserMapper instance =(UserMapper)sqlSession.getMapper(UserMapper.class);
        boolean flag = instance.updateOne();
        logger.info("flag: {}",flag);
        if(flag){
            logger.info("更改成功");
        }else {
            logger.info("更改失败");
        }
    }

}
