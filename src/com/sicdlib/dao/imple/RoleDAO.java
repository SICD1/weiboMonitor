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
}
