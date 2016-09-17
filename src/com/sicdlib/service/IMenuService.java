package com.sicdlib.service;


import com.sicdlib.dto.Menu;

import java.util.List;

/**
 * Created by maninit on 2016/9/13.
 */
public interface IMenuService {
    public List<Menu> getMenuByRoleId(String roleId);
}
