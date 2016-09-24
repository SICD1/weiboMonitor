package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.IMenuDAO;
import com.sicdlib.dto.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by maninit on 2016/9/13.
 */
@Repository("menuDAO")
public class MenuDAO implements IMenuDAO {
    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public List<Menu> getMenusByRoleId(String roleId) {
        String hql = "SELECT m\n" +
                "FROM RolePrivilege rp, PrivilegeMenu pm, Menu m\n" +
                "WHERE rp.privilege_id = pm.privilege_id and pm.menu_id = m.m_id and rp.role_id = '"+roleId+"'";
        List<Menu> menu = (List<Menu>) baseDAO.find(hql);
        return menu;

    }
}
