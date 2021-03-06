package com.sicdlib.dao;

import com.sicdlib.dto.Role;

/**
 * Created by Yh on 2016/9/12.
 */
public interface IRoleDAO {
    Role getRoleByName(String name);
    Role getRoleByUserID(int id);

    List<Role> getAllRoles();

    //通过role得到该role所拥有的权限
    List<Menu> getMenuByRole(Role role);
}
