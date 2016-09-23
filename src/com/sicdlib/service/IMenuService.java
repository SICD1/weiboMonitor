package com.sicdlib.service;

import com.sicdlib.dto.Menu;

import java.util.List;

/**
 * Created by Yh on 2016/9/8.
 */
public interface IMenuService{

    List<Menu> getMenuByMenuId(String m_id);

}
