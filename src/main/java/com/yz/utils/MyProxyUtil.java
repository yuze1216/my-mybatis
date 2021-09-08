package com.yz.utils;

import com.yz.been.User;
import com.yz.sqlsession.Mysqlsession;

import java.lang.reflect.*;

/**
 * @author:yuze
 * @description:mapper接口动态代理实现类
 * @data:2021/8/27
 */
public class MyProxyUtil implements InvocationHandler {

    private String obj;
    private Mysqlsession mysqlsession;
    private Class mapperInterface;

    public MyProxyUtil(){
//        this.mapperInterface = clz;
    }
//    public MyProxyUtil(Object obj){
//        this.obj = obj;
//    }
    public Object Instance(Class clazz){
        System.out.println(clazz);
        this.obj = clazz.getName();
        //该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
        //第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        //第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
        //第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        //根据传入的目标返回一个代理对象
//         o = Proxy.newProxyInstance(clazz.getClassLoader(),
//                new Class[]{clazz},this);
        return Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},this);
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
    public Object run(Method method,Object[] args) throws ClassNotFoundException {
        System.out.println(  method.getReturnType());//com.yz.been.User
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (Class parameterType:parameterTypes) {
            System.out.println(parameterType);//获取参数的类型
        }
        //Class clz = Class.forName(method.getReturnType().getName());
        
        for (int i = 0; i < method.getTypeParameters().length; i++) {
            System.out.println();
        }
        System.out.println(  method.getTypeParameters());
        if(args!=null){
            for (Object o:args) {
                System.out.println(o);
            }
        }

            Class declare_classes = method.getDeclaringClass();
            System.out.print(" Declaring Methods Class: ");
            System.out.println(declare_classes.toString());
        User user = new User();
        user.setName("yuze");
        user.setAge(Integer.parseInt(String.valueOf(args[1]))+Integer.parseInt(String.valueOf(args[0])));
        return user;
    }
}
