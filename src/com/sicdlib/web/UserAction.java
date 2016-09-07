package com.sicdlib.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sicdlib.entity.KeyWords;
import com.sicdlib.entity.User;
import com.sicdlib.entity.UserType;
import com.sicdlib.entity.User_userType;
import com.sicdlib.service.KeyWordsService;
import com.sicdlib.service.UserService;
import com.sicdlib.service.UserTypeService;
import com.sicdlib.service.User_userTypeService;
import com.sicdlib.service.WeiboService;
import com.sicdlib.util.MD5Util.MD5Util;
import com.sicdlib.util.TeleValidUtil.TeleValidCode;
import com.sicdlib.util.mailUtil.SendMails;
import com.sicdlib.util.mailUtil.SendPop3Mails;
import com.sicdlib.util.mailUtil.ValidationCode;
import com.taobao.api.ApiException;

import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;

@Controller
@RequestMapping("/user/*")
public class UserAction {

	@Autowired(required=true)
	private UserService userService;
	
	@Autowired
	private UserTypeService userTypeService;
	
	@Autowired
	private User_userTypeService user_userTypeService;
	
	@Autowired(required=true)
	KeyWordsService keyService;
	
	@Autowired(required=true)
	WeiboService weiboService;
	
	List<KeyWords> topkeywords = null;
	
	//登录模块
	@RequestMapping
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String u_name = req.getParameter("u_name");
		String u_pwd = req.getParameter("u_pwd");
//		得到角色类型
//		Integer usertype_id = Integer.parseInt(req.getParameter("usertype_id"));
		boolean isRemPwd = Boolean.parseBoolean(req.getParameter("isRemPwd"));
		System.out.println(u_name+":"+u_pwd);
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		PropertyFilter filters = new PropertyFilter("u_name",u_name);
		List<User> searchUsers = userService.search(filters);
		if (searchUsers.size()!=0) {
			if(searchUsers.get(0)!=null){
				if (MD5Util.validatePassword(searchUsers.get(0).getU_pwd(), u_pwd)) {
					if(isRemPwd){
						Cookie u_nameCookie = new Cookie("u_name", URLEncoder.encode(u_name,"UTF-8"));
						Cookie u_pwdCookie = new Cookie("u_pwd", u_pwd);
						u_nameCookie.setPath(req.getContextPath()+"/");
						u_pwdCookie.setPath(req.getContextPath()+"/");
						
						u_nameCookie.setMaxAge(7*24*60*60);
						u_pwdCookie.setMaxAge(7*24*60*60);
						
						resp.addCookie(u_nameCookie);
						resp.addCookie(u_pwdCookie);
					} else if(!isRemPwd){
						Cookie u_name_temp_cookie = new Cookie("u_name", null);
						u_name_temp_cookie.setMaxAge(0);
						Cookie u_pwd_temp_cookie = new Cookie("u_pwd", null);
						u_pwd_temp_cookie.setMaxAge(0);
						
						u_name_temp_cookie.setPath(req.getContextPath()+"/");
						u_pwd_temp_cookie.setPath(req.getContextPath()+"/");
						
						resp.addCookie(u_name_temp_cookie);
						resp.addCookie(u_pwd_temp_cookie);
					}					
					session.setAttribute("user", searchUsers.get(0));
					out.print("success");
					return ;
				}
			}
		}
		out.print("failure");
	}
	
//	//管理员登录模块
//	@RequestMapping
//	public void adminlogin(HttpServletRequest req, HttpServletResponse resp) throws IOException{
//		String u_name = req.getParameter("u_name");
//		String u_pwd = req.getParameter("u_pwd");
////			得到角色类型
////			Integer usertype_id = Integer.parseInt(req.getParameter("usertype_id"));
//		boolean isRemPwd = Boolean.parseBoolean(req.getParameter("isRemPwd"));
//		System.out.println(u_name+":"+u_pwd);
//		PrintWriter out = resp.getWriter();
//		HttpSession session = req.getSession();
//		PropertyFilter filters = new PropertyFilter("u_name",u_name);
//		List<User> searchUsers = userService.search(filters);
//		if (searchUsers.size()!=0) {
//			if(searchUsers.get(0)!=null){
//				if (MD5Util.validatePassword(searchUsers.get(0).getU_pwd(), u_pwd)) {
//					if(isRemPwd){
//						Cookie u_nameCookie = new Cookie("u_name", URLEncoder.encode(u_name,"UTF-8"));
//						Cookie u_pwdCookie = new Cookie("u_pwd", u_pwd);
//						u_nameCookie.setPath(req.getContextPath()+"/");
//						u_pwdCookie.setPath(req.getContextPath()+"/");
//						
//						u_nameCookie.setMaxAge(7*24*60*60);
//						u_pwdCookie.setMaxAge(7*24*60*60);
//						
//						resp.addCookie(u_nameCookie);
//						resp.addCookie(u_pwdCookie);
//					} else if(!isRemPwd){
//						Cookie u_name_temp_cookie = new Cookie("u_name", null);
//						u_name_temp_cookie.setMaxAge(0);
//						Cookie u_pwd_temp_cookie = new Cookie("u_pwd", null);
//						u_pwd_temp_cookie.setMaxAge(0);
//						
//						u_name_temp_cookie.setPath(req.getContextPath()+"/");
//						u_pwd_temp_cookie.setPath(req.getContextPath()+"/");
//						
//						resp.addCookie(u_name_temp_cookie);
//						resp.addCookie(u_pwd_temp_cookie);
//					}					
//					session.setAttribute("user", searchUsers.get(0));
//					out.print("success");
//					return ;
//				}
//			}
//		}
//		out.print("failure");
//	}
	
	//注册模块--开始
	@RequestMapping(value="register/{id}", method = RequestMethod.GET)
	public String register(@PathVariable String id){
		System.out.println(id);
		//0:跳转注册页面
		if(id.equals("0")){
			return "register";
		}
		return "login";
	}
	
	//注册
	@RequestMapping
	public String reg(HttpServletRequest req , User user){
		//得到角色类型
		Integer usertype_id = Integer.parseInt(req.getParameter("usertype_id"));
		UserType usertype = userTypeService.load(usertype_id);
		User_userType user_userType = usertype.getUser_userType();
		
		System.out.println("U_ut_id:\t"+user_userType.getU_ut_id());
		
		System.out.println(user.getU_name()+":"+user.getU_pwd());
		String u_pwd = user.getU_pwd();
		u_pwd = MD5Util.generatePassword(u_pwd);
		user.setU_pwd(u_pwd);
		//一的一方设置为inverse='true'，使得多对一维护关系就好
		user.setUser_userType(user_userType);
		userService.saveOrUpdate(user);
		return "redirect:../login.jsp";
	}
	
	//修改个人信息
	@RequestMapping
	public String modMyInfo(HttpServletRequest req, User user, Model mode){
		
		User session_user = (User) req.getSession().getAttribute("user");
		System.out.println(user.getU_name());
		System.out.println(user.getU_pwd());
		System.out.println(user.getU_mail());
		System.out.println(user.getU_telphone());
		if(!user.getU_pwd().equals("***")){
			session_user.setU_pwd(user.getU_pwd());
		}
		session_user.setU_name(user.getU_name());
		session_user.setU_mail(user.getU_mail());
		session_user.setU_telphone(user.getU_telphone());
		userService.saveOrUpdate(session_user);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//topkeywords = keyService.getTopKeyWords(10,"1",df.format(date));
		topkeywords = keyService.getTopKeyWords(12,"1","2016-07-24");
		mode.addAttribute("topkeywords", topkeywords);
		
		return "/WEB-INF/index";
	}
	
	//注销用户
	@RequestMapping
	public String logout(HttpServletRequest req){
		System.out.println("load to logout ......");
		req.getSession().removeAttribute("user");
		
		return "login";
	}
	
	@RequestMapping
	public void isExistUser(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		System.out.println("已经加载...");
		String u_name = req.getParameter("u_name");
		PropertyFilter filters = new PropertyFilter("u_name",u_name);
		List<User> searchUsers = userService.search(filters);
		PrintWriter out = resp.getWriter();
		if (searchUsers.size()!=0) {
			if (searchUsers.get(0)!=null) {
				out.print("success");
			}
		}
	}
	
	//发送邮件注册
	@RequestMapping
	public void sendMail(HttpServletRequest req) throws MessagingException{
		System.out.println("已经加载...");
		String u_mail = req.getParameter("u_mail");
		StringBuffer stringBuffer = new StringBuffer("验证码<br>");
		String code = ValidationCode.getValidationCode(4);
		stringBuffer.append(code);
		req.getSession().setAttribute("valication_mail_code", code);		
		SendMails.sendMail(u_mail, stringBuffer.toString());
	}
	
	//发送手机验证码注册：
	@RequestMapping
	public void sendTelephoneCode(HttpServletRequest req) throws ApiException, JsonGenerationException, JsonMappingException, IOException{
		System.out.println("已经加载到tel..");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString("123");
		TeleValidCode.getTeleValidCode();
		
	}
	
	//注册模块--结束
	
	@RequestMapping
	public String add(User user) {
		userService.saveOrUpdate(user);
		return "redirect:/user/list/1.do";
	}
		
	@Deprecated
	@RequestMapping
	public String list2(@RequestParam(defaultValue="1") int pageNo, Model model) {
		Page<User> page = new Page<User>(5);
		page.setPageNo(pageNo);
		userService.loadAll(page);
		model.addAttribute("page", page);
		return "user/list";
	}
	
	@RequestMapping("list/{pageNo}")
	public String list(@PathVariable int pageNo, Model model) {
		Page<User> page = new Page<User>(5);
		page.setPageNo(pageNo);
		userService.loadAll(page);
		model.addAttribute("page", page);
		return "user/list";
	}
	
	@InitBinder
	public void init(WebDataBinder binder) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
	}
	
}