package com.sicdlib.controller;

import com.sicdlib.dto.User;
import com.sicdlib.service.ILoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * Created by Yh on 2016/9/8.
 */
@Controller
@RequestMapping("/user/*")
public class LoginController {
    @Autowired
    @Qualifier("loginService")
    private ILoginService loginService;

    @RequestMapping
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("u_name");
        String password = req.getParameter("u_pwd");
//		得到角色类型
//		Integer usertype_id = Integer.parseInt(req.getParameter("usertype_id"));


        boolean isRemPwd = Boolean.parseBoolean(req.getParameter("isRemPwd"));
//        System.out.println(u_name+":"+u_pwd);
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        User user = loginService.validateLogin(name, password);
        if(user != null) {
            if(isRemPwd){
                Cookie u_nameCookie = new Cookie("u_name", URLEncoder.encode(name,"UTF-8"));
                Cookie u_pwdCookie = new Cookie("u_pwd", password);
                u_nameCookie.setPath(req.getContextPath()+"/");
                u_pwdCookie.setPath(req.getContextPath()+"/");

                u_nameCookie.setMaxAge(7*24*60*60);
                u_pwdCookie.setMaxAge(7*24*60*60);

                resp.addCookie(u_nameCookie);
                resp.addCookie(u_pwdCookie);
            } else if(!isRemPwd){
                Cookie u_name_temp_cookie = new Cookie("u_name", null);
                u_name_temp_cookie.setMaxAge(0);
                Cookie u_pwd_temp_cookie = new Cookie("u_pwd", null);
                u_pwd_temp_cookie.setMaxAge(0);

                u_name_temp_cookie.setPath(req.getContextPath()+"/");
                u_pwd_temp_cookie.setPath(req.getContextPath()+"/");

                resp.addCookie(u_name_temp_cookie);
                resp.addCookie(u_pwd_temp_cookie);
            }
            session.setAttribute("user", user);
            out.print("success");
            return ;
        }
        out.print("failure");
    }
}
