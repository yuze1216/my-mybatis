package com.yz.utils;

import com.yz.mapper.UserMapper;

import java.net.BindException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author:yuze
 * @description:mapper层注册
 * @data:2021/8/28
 */
public class RegistsMapper {
    private static String mapper;
//    public <T> T getMapper(Class<T> clz) throws BindException {
//        if(clz.equals(UserMapper.class)){
//            throw new BindException("Type"+clz+"is not know in regists");
//        }else {
//            try{
//                return new MyProxyUtil().Instance(clz);
//            }catch (Exception e){
//                throw new BindException("Error getting interface Cause"+e);
//            }
//        }
//    }
}
