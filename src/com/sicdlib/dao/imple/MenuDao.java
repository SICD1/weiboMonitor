package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.IMenuDAO;
import com.sicdlib.dto.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by maninit on 2016/9/13.
 */
public class MenuDao implements IMenuDAO {
    @Autowired
    private IBaseDAO baseDAO;


    @Override
    public List<Menu> getMenuByuserName(String name) {
        String sql="select     "
        return null;
    }
}
