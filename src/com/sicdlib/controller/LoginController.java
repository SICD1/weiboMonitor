package com.sicdlib.controller;

import com.sicdlib.dto.KeyWords;
import com.sicdlib.dto.Menu;
import com.sicdlib.dto.User;
import com.sicdlib.service.ILoginService;

import com.sicdlib.service.IUserService;
import com.sicdlib.service.imple.KeyWordsService;
import com.sicdlib.service.imple.LoginService;
import com.sicdlib.service.imple.UserService;
import com.sicdlib.util.TeleValidUtil.TeleValidCode;
import com.sicdlib.util.mailUtil.SendMails;
import com.sicdlib.util.mailUtil.ValidationCode;
import com.taobao.api.ApiException;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Yh on 2016/9/8.
 */
@Controller
public class LoginController {
    @Autowired
    @Qualifier("loginService")
    private LoginService loginService;
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @RequestMapping("login")
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       //System.out.println("login!!!!!!!!!!!!!!!!!!!!");
        String name = req.getParameter("u_name");
        String password = req.getParameter("u_pwd");
        User user1= loginService.getUserByName(name);
        boolean isRemPwd = Boolean.parseBoolean(req.getParameter("isRemPwd"));
        //boolean isRemPwd = true;
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
            List<Menu> menus=loginService.getMenu(user1.getU_id());
            session.setAttribute("user", user);
            session.setAttribute("menuList",menus);
            out.print("success");
            System.out.println(menus.size());
            return ;
        }
        out.print("failture");
    }

    @RequestMapping("urlIndex")
    public  String urlIndex(HttpServletRequest req){
        return "WEB-INF/index";
    }

    /**
     * 用户权限菜单过滤
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
   @RequestMapping("check")
    public ModelAndView check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView mav = new ModelAndView();
        String name = req.getParameter("u_name");
        System.out.print(name);
        //根据用户名字获取ID
        int userId = loginService.getIdByUserName(name);
        //根据用户ID获取该用户拥有权限的菜单信息
        List list = loginService.getMenu(userId);
        //循环遍历list获取菜单的名字和路径
        if (list != null){
            //给页面返回一个List（菜单），当页面接收List时，对其进行处理
            mav.addObject("list",list);
        }
        return mav;
    }
    @RequestMapping("add")
    public String add(User user) {
        userService.saveOrUpdate(user);
        return "redirect:/user/list/1.do";
    }

//    @Deprecated
//    @RequestMapping
//    public String list2(@RequestParam(defaultValue="1") int pageNo, Model model) {
//        Page<User> page = new Page<User>(5);
//        page.setPageNo(pageNo);
//        userService.loadAll(page);
//        model.addAttribute("page", page);
//        return "user/list";
//    }
//
//    @RequestMapping("list/{pageNo}")
//    public String list(@PathVariable int pageNo, Model model) {
//        Page<User> page = new Page<User>(5);
//        page.setPageNo(pageNo);
//        userService.loadAll(page);
//        model.addAttribute("page", page);
//        return "user/list";
//    }

    @InitBinder
    public void init(WebDataBinder binder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
    }


}