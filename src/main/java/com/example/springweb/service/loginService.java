package com.example.springweb.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.springweb.dao.loginMapper;
import com.example.springweb.pojo.Login;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Map;

@Service("login")
public class login {
    @Resource(name ="loginMapper")
    private loginMapper loginMapper;
    public int queryByName(Login viewLogin, HttpSession session){
        Login login=loginMapper.queryByEmail(viewLogin.getEmail());
        if(login==null) return 0;//用户名不存在
        else if(viewLogin.getPassword().equals(viewLogin.getPassword())) {
            session.setAttribute("user",viewLogin);
            return 1;//验证成功
        }else {
            return 2; //密码错误
        }
    }
    public Map<String,Object> addUser(Login viewLogin){
        String confirmCode= IdUtil.getSnowflake(1,1).nextIdStr();
        String salt= RandomUtil.randomString(9);
        String password= SecureUtil.md5(viewLogin.getPassword()+salt);
        LocalDateTime ldf=LocalDateTime.now().plusDays(1);
        viewLogin.setConfirm_code(confirmCode);
        viewLogin.setPassword(password);
        viewLogin.setSalt(salt);
        viewLogin.setValidation_time(ldf);
        loginMapper.addUser(viewLogin);
        return null;
    }
}
