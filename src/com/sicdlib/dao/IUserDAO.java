package com.sicdlib.dao;

import com.sicdlib.dto.User;

/**
 * Created by Yh on 2016/9/12.
 */
public interface IUserDAO {
    User getUserByName(String name);
}
