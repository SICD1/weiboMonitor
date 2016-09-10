package com.sicdlib.dao;

import com.sicdlib.dto.User;

/**
 * Created by Yh on 2016/9/8.
 */
public interface ILoginDAO {
    User getUserByName(String name);
}
