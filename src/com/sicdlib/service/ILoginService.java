package com.sicdlib.service;

import com.sicdlib.dto.Menu;
import com.sicdlib.dto.User;

import java.util.List;

/**
 * Created by Yh on 2016/9/8.
 */
public interface ILoginService {
    User validateLogin(String name, String password);

    //根据用户ID获取该用户拥有权限的菜单
    public List<Menu> getMenu(String userId);

    //根据用户名字获取该用户
    public User getUserByName(String name);
    public String getIdByUserName(String name);
}
