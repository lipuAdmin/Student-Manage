package com.example.springweb.service;

import com.example.springweb.dao.loginMapper;
import com.example.springweb.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service("login")
public class login {
    @Resource(name ="loginMapper")
    private loginMapper loginMapper;
    public int queryByName(User viewUser, HttpSession session){
        User user=loginMapper.queryByName(viewUser.getStuNumber());
        if(user==null) return 0;//用户名不存在
        else if(user.getPassword().equals(viewUser.getPassword())) {
            session.setAttribute("user",viewUser);
            return 1;//验证成功
        }else {
            return 2; //密码错误
        }
    }
    public void addUser(User viewUser){
        loginMapper.addUser(viewUser);
    }
}
