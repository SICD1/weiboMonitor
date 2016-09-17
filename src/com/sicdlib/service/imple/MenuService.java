package com.sicdlib.service.imple;

import com.sicdlib.dao.ILoginDAO;
import com.sicdlib.dao.imple.LoginDAO;
import com.sicdlib.dto.Menu;
import com.sicdlib.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by maninit on 2016/9/13.
 */
@Service("menuService")
public class MenuService implements IMenuService {
private ILoginDAO IloginDAO = new LoginDAO();

    @Override
    public List<Menu> getMenuByRoleId(String roleId) {
        return null;
    }
}
