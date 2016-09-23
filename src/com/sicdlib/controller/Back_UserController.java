package com.sicdlib.controller;

import com.sicdlib.dto.User;
import com.sicdlib.service.imple.KeyWordsService;
import com.sicdlib.service.imple.UserService;
import com.sicdlib.service.imple.WeiboService;
import edu.xjtsoft.base.orm.support.Page;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户管理
 * Created by maninit on 2016/9/22.
 */
@Controller
@RequestMapping("/background/*")
public class Back_UserController {
    @Autowired(required=true)
    UserService userService;

    @Autowired(required=true)
    KeyWordsService keyService;

    @Autowired(required=true)
    WeiboService weiboService;

    /*@Autowired(required=true)
    UserTypeService userTypeService;

    @Autowired(required=true)
    User_userTypeService user_userTypeService;*/
    @RequestMapping("manager")
    public String manager(){
        System.out.println("load to manager ......");
        return "/WEB-INF/background/manager";
    }


    /*
     * 增加用户信息
     */
    @RequestMapping("addUser")
    public String addUser(HttpServletRequest request, int id){
        String name=request.getParameter("u_name");
        String password=request.getParameter("u_pwd");
        String telephone=request.getParameter("u_telephone");
        String mail=request.getParameter("u_email");
        int sex=(Integer.parseInt(request.getParameter("u_sex")));
        //int int_usertype = Integer.parseInt(request.getParameter("usertype"));
        User user=new User();
        user.setU_name(name);
        user.setU_pwd(password);
        user.setU_telephone(telephone);
        user.setU_email(mail);
        user.setU_sex(sex);
        //得到用户类型
//        UserType userType = userTypeService.load(int_usertype);
//        //得到中间表
//        User_userType user_userType = userType.getUser_userType();
        Set<User> users = new HashSet<User>();
        users.add(user);
        //user_userType.setUsers(users);
        //用户设置中间表
        //user.setUser_userType(user_userType);
        userService.saveOrUpdate(user);
//		users.add(user);
//		user_userType.setUsers(users);
//		user_userTypeService.saveOrUpdate(userType);
        //用户--》中间表--》用户类型：用户，设置中间表，设置用户类型
        System.out.println("load to addUser ......");
        return "/WEB-INF/background/managesuperusers";
    }

    /*
     * 删除对象
     */
    @RequestMapping("deleteUser")
    public String deleteUser(int id,Model model){
        userService.remove(id);
        Page<User> page = new Page<User>();
        page.setPageNo(1);
        page.setPageSize(20);
        Page<User> loadAll = userService.loadAll(page);
        //List<User> user = loadAll.getResult();
        model.addAttribute("page",loadAll);
        return "/WEB-INF/background/managesuperusers";

    }

    /*
     * 根据id查询
     */
   /* @RequestMapping
//    public String load(int id){
//        userService.load(id);
//        return "/WEB-INF/background/managesuperusers";
//    }
*/
    /*
     * 根据字段名查询
     */
    @RequestMapping("userSearch")
    public String userSearch(HttpServletRequest request,Model model){
        String search = request.getParameter("search_userKey");
//		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        Page<User> page = (Page<User>) userService.getsearchUsers(1, search);
        model.addAttribute("page",page);
        return "/WEB-INF/background/managesuperusers";
    }

    /*
     * 修改用户
     */
    @RequestMapping("editUser")
    public String editUser(Integer pageNo, User user, Model model){
        userService.saveOrUpdate(user);
        //System.out.print("......"+user.getU_name());
        Page<User> page = new Page<User>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        Page<User> loadAll = userService.loadAll(page);
        //List<User> users = loadAll.getResult();
        model.addAttribute("page", loadAll);
        return "/WEB-INF/background/managesuperusers";
    }

    /*
     * 列出全部信息
     */
    @RequestMapping("managesuperusers/{pageNo}")
    public String listUser(@PathVariable Integer pageNo, Model model){
        System.out.println("load to manageusers ......");
        Page<User> page = new Page<User>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        Page<User> loadAll = userService.loadAll(page);
//        List<User> user= loadAll.getResult();
        //System.out.println(user.size());
        //System.out.println(user.get(0).isU_sex());
        model.addAttribute("page",loadAll);
        return "/WEB-INF/background/managesuperusers";
    }
}
