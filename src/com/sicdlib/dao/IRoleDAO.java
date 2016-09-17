package com.sicdlib.dao;

import com.sicdlib.dto.Role;

/**
 * Created by Yh on 2016/9/12.
 */
public interface IRoleDAO {
    Role getRoleByName(String name);
    Role getRoleByUserID(String id);
}
