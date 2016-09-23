package com.sicdlib.service.imple;

import com.sicdlib.dao.ILoginDAO;
import com.sicdlib.dao.IMenuDAO;
import com.sicdlib.dao.IRoleDAO;
import com.sicdlib.dto.Menu;
import com.sicdlib.dto.Role;
import com.sicdlib.dto.User;
import com.sicdlib.service.ILoginService;

import com.sicdlib.util.MD5Util.MD5Util;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yh on 2016/9/8.
 */
@Service("loginService")
public class LoginService implements ILoginService{
    @Autowired
    @Qualifier("loginDAO")
    private ILoginDAO loginDAO;

    @Autowired
    @Qualifier("roleDAO")
    private IRoleDAO roleDAO;

    @Autowired
    @Qualifier("menuDAO")
    private IMenuDAO menuDAO;

    @Override
    public User validateLogin(String name, String password) {
        User user = loginDAO.getUserByName(name);
        if(MD5Util.validatePassword(user.getU_pwd(), password)) {
            return user;
        }
        return null;
    }

    //根据用户ID获取该用户拥有权限的菜单数据
    @Override
    public List<Menu> getMenu(int userId) {
        Role role = roleDAO.getRoleByUserID(userId);
        String roleId = role.getR_id();
        if(roleId == null) {
            return null;
        }

        List<Menu> menu = menuDAO.getMenusByRoleId(roleId);
        return menu;
    }

    //根据用户名字获取该用户
    @Override
    public User getUserByName(String name) {
        User user = loginDAO.getUserByName(name);

        return user;
    }
    @Override
    public int getIdByUserName(String name){

        return loginDAO.getIdByUserName(name);
    }
}
