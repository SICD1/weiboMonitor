package com.sicdlib.controller;


import com.sicdlib.dto.Menu;
import com.sicdlib.dto.User;
import com.sicdlib.service.ILoginService;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by Yh on 2016/9/8.
 */
@Controller
@RequestMapping("/user/*")
public class LoginController {
    @Autowired
    @Qualifier("loginService")
    private ILoginService loginService;

    @RequestMapping("/login")
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

    /**
     * 用户权限菜单过滤
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping("/check")
    public ModelAndView check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView mav = new ModelAndView();
        String name = req.getParameter("u_name");
        //根据用户名字获取ID
        String userId = loginService.getIdByUserName(name);
        //根据用户ID获取该用户拥有权限的菜单信息
        List list = loginService.getMenu(userId);
        //循环遍历list获取菜单的名字和路径
        if (list != null){
            //给页面返回一个List（菜单），当页面接收List时，对其进行处理
            mav.addObject("list",list);
        }
        return mav;
    }
}