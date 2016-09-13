package com.sicdlib.dao;

import com.sicdlib.dto.Menu;

import java.util.List;

/**
 * Created by maninit on 2016/9/13.
 */
public interface IMenuDAO {
    List<Menu> getMenuByuserName(String name);
}
