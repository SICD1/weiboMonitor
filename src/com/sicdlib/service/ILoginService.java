package com.sicdlib.service;

import com.sicdlib.dto.User;
import org.hibernate.mapping.Map;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Yh on 2016/9/8.
 */
public interface ILoginService {
    User validateLogin(String name, String password);

    //根据用户ID获取该用户拥有权限的菜单
    public List<Map> getMenu(String userId);

    //根据用户名字获取该用户ID
    public String getIdByUserName(String name);
}
