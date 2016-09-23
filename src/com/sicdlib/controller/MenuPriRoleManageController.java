package com.sicdlib.controller;

import com.sicdlib.dto.Menu;
import com.sicdlib.service.imple.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Yh on 2016/9/12.
 */
@Controller
public class MenuPriRoleManageController {

    @Autowired(required=true)
    @Qualifier("menuService")
    MenuService menuService;

    //@Autowired
    //SimplePrivilegeService simplePrivilegeService;
    //
    //@Autowired
    //SimpleRoleService simpleRoleService;

    @RequestMapping("/menuPriRoleAssign")
    public String menuPriRoleAssign(HttpServletRequest req){

        List<Menu> menus = menuService.getMenuByMenuId("0");

        System.out.println("已经加载到menuPriRoleAssign...");
        System.out.println(menus.get(0).getM_name());

        return "index";
    }

}
