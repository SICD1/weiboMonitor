package com.sicdlib.service.imple;

import com.sicdlib.dao.ILoginDAO;
import com.sicdlib.dto.User;
import com.sicdlib.service.ILoginService;

import com.sicdlib.util.MD5Util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Yh on 2016/9/8.
 */
@Service("loginService")
public class LoginService implements ILoginService{
    @Autowired
    @Qualifier("loginDAO")
    private ILoginDAO loginDAO;

    @Override
    public User validateLogin(String name, String password) {
        User user = loginDAO.getUserByName(name);
        if(MD5Util.validatePassword(user.getU_pwd(), password)) {
            return user;
        }

        return null;
    }
}
