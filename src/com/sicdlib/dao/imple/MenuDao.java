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
public class MenuDao implements IMenuDAO {
    @Autowired
    private IBaseDAO baseDAO;


    @Override
    public List<Menu> getMenuByuserName(String name) {
        String hql = "from Menu u where u.u_name = '" + name + "'";
        return null;
    }

    @Override
    public List<Menu> getMenuByMenuId(String m_id)
    {
        String hql = "from Menu m where m.m_id = '"+m_id+"'";

        List<Menu> menus = baseDAO.find(hql);
        return menus;
    }

}
