package com.sicdlib.controller;

import com.sicdlib.dto.Menu;
import com.sicdlib.dto.Role;
import com.sicdlib.service.imple.MenuService;
import com.sicdlib.service.imple.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yh on 2016/9/12.
 */
@Controller
public class MenuPriRoleManageController {

    @Autowired(required=true)
    @Qualifier("menuService")
    MenuService menuService;

    @Autowired
    RoleService roleService;

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

    @RequestMapping("/menuManage")
    public ModelAndView menuManage(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        System.out.println("已经加载到menuManage...");
        ModelAndView mav = new ModelAndView();
        List<Role> roles = roleService.getAllRole();
        mav.addObject("roles",roles);
        //普通用户：
        Role role = new Role();
        role.setR_id("1");
        List<Menu> menulist = roleService.getMenuByRole(role);
        System.out.println(menulist.get(0));
        mav.addObject("menulist", menulist);

        //根据用户名字获取ID
        //String userId = loginService.getIdByUserName(name);
        ////根据用户ID获取该用户拥有权限的菜单信息
        //List list = loginService.getMenu(userId);
        ////循环遍历list获取菜单的名字和路径
        //if (list != null){
        //    //给页面返回一个List（菜单），当页面接收List时，对其进行处理
        //    mav.addObject("list",list);
        //}
        return mav;
    }

}
