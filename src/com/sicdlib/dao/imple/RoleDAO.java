package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.IRoleDAO;
import com.sicdlib.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Yh on 2016/9/12.
 */
@Repository("roleDAO")
public class RoleDAO implements IRoleDAO{
    @Autowired
    private IBaseDAO baseDAO;


    @Override
    public Role getRoleByName(String name) {
        String hql = "from Role r where r.r_name = '" + name + "'";
        return (Role) baseDAO.get(hql);
    }

    @Override
    public Role getRoleByUserID(int id) {
        String hql = "SELECT r\n" +
                "FROM User u, Group g, Role r\n" +
                "WHERE u.group_id = g.g_id and g.role_id = r.r_id and u.u_id = '"+ id + "'";
        Role role = (Role) baseDAO.get(hql);
        return role;
    }
}
