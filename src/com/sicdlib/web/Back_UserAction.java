package com.sicdlib.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sicdlib.entity.KeyWords;
import com.sicdlib.entity.User;
import com.sicdlib.entity.UserType;
import com.sicdlib.entity.User_userType;
import com.sicdlib.service.KeyWordsService;
import com.sicdlib.service.UserService;
import com.sicdlib.service.UserTypeService;
import com.sicdlib.service.User_userTypeService;
import com.sicdlib.service.WeiboService;

import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;

/**
 * 
 * 用户管理
 * @author 
 *
 */
@Controller
@RequestMapping("/background/*")
public class Back_UserAction {
	
	@Autowired(required=true)
    UserService userService;
	
	@Autowired(required=true)
	KeyWordsService keyService;
	
	@Autowired(required=true)
	WeiboService weiboService;
	
	@Autowired(required=true)
	UserTypeService userTypeService;
	
	@Autowired(required=true)
	User_userTypeService user_userTypeService;
	@RequestMapping
	public String manager(){
		System.out.println("load to manager ......");
		return "/WEB-INF/background/manager";
	}
	/*
	 * 增加用户信息
	 */
	@RequestMapping("addUser")
	public String addUser(HttpServletRequest  request,int id){
		String name=request.getParameter("u_name"); 
		String password=request.getParameter("u_pwd");
		String telephone=request.getParameter("u_telephone");
		String mail=request.getParameter("u_mail");
		boolean sex=(Boolean.parseBoolean(request.getParameter("u_sex")));
		int int_usertype = Integer.parseInt(request.getParameter("usertype"));
		User user=new User();
		user.setU_name(name);
		user.setU_pwd(password);
		user.setU_telphone(telephone);
		user.setU_mail(mail);
		user.setU_sex(sex);
		//得到用户类型
		UserType userType = userTypeService.load(int_usertype);
		//得到中间表
		User_userType user_userType = userType.getUser_userType();
		Set<User> users = new HashSet<User>();
		users.add(user);
		user_userType.setUsers(users);
		//用户设置中间表
		user.setUser_userType(user_userType);
		userService.saveOrUpdate(user);
//		users.add(user);
//		user_userType.setUsers(users);
//		user_userTypeService.saveOrUpdate(userType);
		//用户--》中间表--》用户类型：用户，设置中间表，设置用户类型
		System.out.println("load to addUser ......");
		return "/WEB-INF/background/manageusers";
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
	    return "/WEB-INF/background/manageusers";
	
	}
	
	/*
	 * 根据id查询
	 */
	@RequestMapping
	public String load(int id){
		userService.load(id);
	return "/WEB-INF/background/manageusers";
	}
	
	/*
	 * 根据字段名查询
	 */
	@RequestMapping("userSearch")
	public String userSearch(HttpServletRequest request,Model model){
		String search = request.getParameter("search_userKey");
//		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		Page<User> page = (Page<User>) userService.getsearchUsers(1, search);
		model.addAttribute("page",page);
		return "/WEB-INF/background/manageusers";
	}
	
	/*
	 * 修改用户
	 */
	@RequestMapping("editUser")
	public String editUser(int pageNo, User user, Model model){
		userService.saveOrUpdate(user);
		Page<User> page = new Page<User>();
		page.setPageNo(pageNo);
		page.setPageSize(10);
		Page<User> loadAll = userService.loadAll(page);
		//List<User> users = loadAll.getResult();
		model.addAttribute("page", loadAll);
		return "/WEB-INF/background/manageusers";
	}
	
	/*
	 * 列出全部信息
	 */
	@RequestMapping(value="manageusers")
	public String listUser(int pageNo,Model model){
		System.out.println("load to manageusers ......");
		Page<User> page = new Page<User>();
		page.setPageNo(pageNo);
		page.setPageSize(10);
		Page<User> loadAll = userService.loadAll(page);
		List<User> user= loadAll.getResult();
		//System.out.println(user.size());
		//System.out.println(user.get(0).isU_sex());
		model.addAttribute("page",loadAll);
		return "/WEB-INF/background/manageusers";
	}
}
