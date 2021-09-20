package com.yz.sqlsession;


import com.yz.JDBC.MyExecutor;
import com.yz.utils.MyMapperProxyUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.net.BindException;
import java.sql.SQLException;

/**
 * @author:yuze
 * @description:sqlsession调度类
 * @data:2021/9/6
 */
public class SqlSession {


    /**
     * 将目标class对象交给代理
     * @author yuze
     * @date 2021/9/10 20:12
     * @param [clz]
     * @return java.lang.Object
     */
    public Object getMapper(Class<?> clz) throws BindException {
        MyMapperProxyUtil myMapperProxyUtil = new MyMapperProxyUtil();
        //该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
        //第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        //第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
        //第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        //根据传入的目标返回一个代理对象
        return Proxy.newProxyInstance(clz.getClassLoader(),
                new Class[]{clz}, myMapperProxyUtil);
    }

    /**
     * 查询list
     * @author yuze
     * @date 2021/9/20 20:24
     * @param
     * @return
     */
    public static Object selectList(String sql,Class<?> clz) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException {
        return MyExecutor.getObjectList(sql,clz);
    }

    /**
     * 查询单个对象
     * @author yuze
     * @date 2021/9/20 20:24
     * @param
     * @return
     */
    public static Object selectOne(String sql,Class<?> clz) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException {
        return MyExecutor.getObject(sql,clz);
    }
    /**
     * 增，删，改
     * @author yuze
     * @date 2021/9/20 20:24
     * @param
     * @return
     */
    public static Object getDML(String sql) {
        return MyExecutor.getDML(sql);
    }
}
