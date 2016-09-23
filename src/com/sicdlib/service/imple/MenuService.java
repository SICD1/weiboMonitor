package com.sicdlib.service.imple;

import com.sicdlib.dao.IMenuDAO;
import com.sicdlib.dto.Menu;
import com.sicdlib.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by maninit on 2016/9/13.
 */
@Service("menuService")
public class MenuService implements IMenuService{

    @Autowired
    @Qualifier("menuDAO")
    private IMenuDAO menuDAO;

    public Menu getMenuByUserName(String name) {
        //getUserRoleByUsername;

        //getMenuByRole;

      return null;
    }

    @Override
    public List<Menu> getMenuByMenuId(String m_id){
        List<Menu> menus = menuDAO.getMenuByMenuId(m_id);
        return menus;
    }
}
