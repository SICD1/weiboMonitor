package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.IRoleDAO;
import com.sicdlib.dto.Menu;
import com.sicdlib.dto.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yh on 2016/9/12.
 */
@Repository("roleDAO")
public class RoleDAO implements IRoleDAO{

    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public List<Menu> getMenuByRole(Role role) {
        //String sql = "select m.* from tb_role r, tb_privilege p, tb_menu m where r.r_id = p.p_id and p.p_id = m.m_id and r.r_id = '"+role.getR_id()+"';";

        String sql = "SELECT\n" +
                "\tm.*\n" +
                "FROM\n" +
                "\ttb_role r\n" +
                "LEFT JOIN tb_role_privilege rp ON r.r_id = rp.role_id\n" +
                "LEFT JOIN tb_privilege p ON rp.privilege_id = p.p_id\n" +
                "LEFT JOIN tb_privilege_menu pm ON p.p_id = pm.privilege_id\n" +
                "LEFT JOIN tb_menu m ON pm.menu_id = m.m_id\n" +
                "WHERE\n" +
                "\t r.r_id = \""+role.getR_id()+"\"";

        List<Object[]> lists = baseDAO.getSqlList(sql);
        List<Menu> menuList = new ArrayList<>();
        for(Object[] list: lists) {
            Menu menu = new Menu();
            menu.setM_id((String) list[0]);
            menu.setM_name((String) list[1]);
            menu.setM_icon((String) list[2]);
            menu.setM_url((String) list[3]);
            menu.setParent_id((String) list[4]);
            menuList.add(menu);
        }
        if(menuList.size() > 0 ){
            return menuList;
        }
        return null;
    }

    @Override
    public Role getRoleByName(String name) {
        String hql = "from Role r where r.r_name = '" + name + "'";
        return (Role) baseDAO.get(hql);
    }

    @Override
    public List<Role> getAllRoles()
    {
        String hql = "from Role r";
        return baseDAO.find(hql);
    }

}
