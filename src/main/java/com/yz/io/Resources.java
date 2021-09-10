package com.yz.io;

import java.io.InputStream;

/**
 * @author:yuze
 * @description:加载配置文件读取为流
 * @data:2021/9/10
 */
public class Resources {

    public static InputStream getResourceAsStream(String resource){
        return  Resources.class.getClassLoader().getResourceAsStream(resource);
    }
}
