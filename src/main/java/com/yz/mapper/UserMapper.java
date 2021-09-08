package com.yz.mapper;

import com.yz.been.User;

public interface UserMapper {

    User selectAll(int id,int age);
    User listAll();
    int update();
}
