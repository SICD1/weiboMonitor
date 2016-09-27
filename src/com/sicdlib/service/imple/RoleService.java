package com.sicdlib.service.imple;

import com.sicdlib.dao.imple.RoleDAO;
import com.sicdlib.dto.Menu;
import com.sicdlib.dto.Role;
import com.sicdlib.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhang on 2016/9/23.
 */
@Service
public class RoleService implements IRoleService
{
    @Autowired
    RoleDAO roleDAO;
    @Override
    public List<Role> getAllRole()
    {
        List<Role> roles = roleDAO.getAllRoles();
        return roles;
    }

    @Override
    public List<Menu> getMenuByRole(Role role)
    {
        List<Menu> menulist = roleDAO.getMenuByRole(role);
        return menulist;
    }
}
