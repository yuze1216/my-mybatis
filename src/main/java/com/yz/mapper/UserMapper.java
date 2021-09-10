package com.yz.mapper;

import com.yz.annotation.Value;
import com.yz.been.User;

import java.util.List;

public interface UserMapper {
    @Value("select * from user")
    List<User> selectAll(int id, int age);
    @Value("select * from user where id =1")
    User listAll();
    @Value("delete from user where id= 4")
    boolean deleteOne();
    @Value("insert into user (name,age) values ('测试添加',100)")
    boolean insertOne();
    @Value("update user set age=1 where id = 6")
    boolean updateOne();
}
