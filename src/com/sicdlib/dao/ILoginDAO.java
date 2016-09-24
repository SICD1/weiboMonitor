package com.sicdlib.dao;

import com.sicdlib.dto.Menu;
import com.sicdlib.dto.User;
import org.hibernate.mapping.Map;

import java.util.List;

/**
 * Created by Yh on 2016/9/8.
 */
public interface ILoginDAO {
    User getUserByName(String name);
    List<Menu> getMenu(String userId);
    int getIdByUserName(String name);

}
