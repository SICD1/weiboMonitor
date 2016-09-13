package com.sicdlib.service;

import com.sicdlib.dto.User;

/**
 * Created by Yh on 2016/9/12.
 */
public interface IRegisterService {
    boolean registerNormalUser(User user);
    boolean isExistUser(String name);
}
