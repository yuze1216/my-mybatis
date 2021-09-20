package com.yz.utils;

import com.yz.annotation.Sql;
import com.yz.sqlsession.SqlSession;

import java.lang.reflect.*;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author:yuze
 * @description:mapper接口动态代理实现类
 * @data:2021/8/27
 */
public class MyMapperProxyUtil implements InvocationHandler {

    private String obj;
    private SqlSession sqlSession;
    private Class mapperInterface;
    private Set<String> set = new HashSet<String>(){
        {
            add("java.lang.Integer") ;
            add("java.lang.Double");
            add("java.lang.Float");
            add("java.lang.Long");
            add("java.lang.Short");
            add("java.lang.Byte");
            add("java.lang.Boolean");
            add("java.lang.Character");
            add("java.lang.String");
            add("int");
            add("double");
            add("long");
            add("short");
            add("byte");add("boolean");add("char");add("float");
        }
    };
    static {

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (Throwable t) {
                t.printStackTrace();
            }
            //如果传进来的是一个接口（核心)
        } else {
            obj= run(method, args);
        }
        return obj;
    }
    public static Object run(Method method,Object[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException, SQLException {
        System.out.println(method.getReturnType() );
        //Field[] declaredFields = method.getReturnType().getDeclaredFields();
//        Class<?>[] parameterTypes = method.getParameterTypes();
//        for (Class parameterType:parameterTypes) {
//            System.out.println(parameterType);//获取参数的类型
//        }
        String sql = getSql(method);
        if("java.util.List".equals(method.getReturnType().getName())){
            System.out.println("返回List对象");
            ParameterizedType pt= (ParameterizedType)method.getGenericReturnType();
            Class type = (Class)pt.getActualTypeArguments()[0];
            Class clz = Class.forName(type.getName());
            Object object = SqlSession.selectList(sql, clz);
            return object;
        }else if("boolean".equals(method.getReturnType().getName())) {
            return SqlSession.getDML(sql);
        } else {
            System.out.println("返回单个对象");
            Class clz = Class.forName(method.getReturnType().getName());
            System.out.println(clz);
            Object object = SqlSession.selectOne(sql, clz);
            return object;
        }

    }

    public static Object createObject(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> clz = Class.forName(name);
        return clz.newInstance();
    }

    public static String getSql(Method method){
        Sql value = method.getDeclaredAnnotation(Sql.class);
        String sql = value.value();
        return sql;
    }

//    public static List getArgs(Object[] args){
//        List<Object> list=new ArrayList<>();
//        if(args!=null){
//            for (Object o:args) {
//                list.add(o);
//            }
//        }
//        return list;
//    }

}
