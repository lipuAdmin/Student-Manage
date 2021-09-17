package com.example.springweb.dao;

import com.example.springweb.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("loginMapper")
public interface loginMapper {
    User queryByName(String stuNumber);
    void addUser(User viewUser);
}
