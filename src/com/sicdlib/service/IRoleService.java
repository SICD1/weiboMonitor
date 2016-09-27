package com.sicdlib.service;

import com.sicdlib.dto.Menu;
import com.sicdlib.dto.Role;

import java.util.List;

/**
 * Created by zhang on 2016/9/23.
 */
public interface IRoleService
{
    List<Role> getAllRole();

    List<Menu> getMenuByRole(Role role);
}
