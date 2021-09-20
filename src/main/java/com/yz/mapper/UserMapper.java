package com.yz.mapper;

import com.yz.annotation.Sql;
import com.yz.been.User;

import java.util.List;

public interface UserMapper {
    @Sql("select * from user")
    List<User> selectAll(int id, int age);
    @Sql("select * from user where id =1")
    User listAll();
    @Sql("delete from user where id= 4")
    boolean deleteOne();
    @Sql("insert into user (name,age,pass_word) values ('测试添加',100,123456)")
    boolean insertOne();
    @Sql("update user set age=1 where id = 6")
    boolean updateOne();
}
