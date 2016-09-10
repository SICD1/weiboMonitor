package com.sicdlib.service;

import com.sicdlib.dto.User;

/**
 * Created by Yh on 2016/9/8.
 */
public interface ILoginService {
    User validateLogin(String name, String password);
}
