package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.ILoginDAO;
import com.sicdlib.dto.Menu;
import com.sicdlib.dto.User;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yh on 2016/9/8.
 */
@Repository("loginDAO")
public class LoginDAO implements ILoginDAO {
    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public User getUserByName(String name) {
        String hql = "from User user where user.u_name = '" + name + "'";
        return (User) baseDAO.get(hql);
//        String sql = "select t.* from tb_user t  where  t.u_name ='"+name+"'";
//        List<User> list = baseDAO.getSqlList(sql);
//        return list.get(0);
    }

    @Override
    public List<Menu> getMenu(String userId) {
        //System.out.println("*****************");
        String sql = "SELECT\n" +
                "\tu.*, m.*, r.*, p.*\n" +
                "FROM\n" +
                "\ttb_user u\n" +
                "LEFT JOIN tb_group g ON u.group_id = g.g_id\n" +
                "LEFT JOIN tb_role r ON g.role_id = r.r_id\n" +
                "LEFT JOIN tb_role_privilege rp ON r.r_id = rp.role_id\n" +
                "LEFT JOIN tb_privilege p ON rp.privilege_id = p.p_id\n" +
                "LEFT JOIN tb_privilege_menu pm ON p.p_id = pm.privilege_id\n" +
                "LEFT JOIN tb_menu m ON pm.menu_id = m.m_id\n" +
                "WHERE\n" +
                "\t u.u_id = \""+userId+"\"";
        System.out.println("sql: "+sql);
        List list = baseDAO.getSqlList(sql);
        if(list.size() > 0 ){
            return list;
        }
        return null;
    }

    @Override
    public String getIdByUserName(String name) {

        String hql = "from User user where user.u_name = '" + name + "'";
        User user= (User) baseDAO.get(hql);
        return user.getU_id();

    }
}
