package com.example.springweb.controller;

import com.example.springweb.pojo.User;
import com.example.springweb.service.login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class index {
    @Resource(name = "login")
    login login;
    @RequestMapping("/login")
    public String loginIn(User viewUser, Model model, HttpSession session){
        if(viewUser.getStuNumber()!=null){
            int num=login.queryByName(viewUser, session);
            if(num==0){
                model.addAttribute("str","用户名不存在");
                return "login/login";
            }else if(num==1){
                return "redirect:student/list";
            }else {
                model.addAttribute("str","密码错误");
                return "login/login";
            }
        }else {
            return "login/login";
        }
    }
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "login/login";
    }
    @RequestMapping("/register")
    public String abc(User viewUser){
        if(viewUser.getStuNumber()!=null){
            login.addUser(viewUser);
            return "redirect:/login";
        }else {
            return "login/register";
        }
    }
}
