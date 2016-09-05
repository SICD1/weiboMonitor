package com.sicdlib.interceptor;


import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sicdlib.entity.User;
import com.sicdlib.entity.UserType;
import com.sicdlib.entity.User_userType;

/**
 * 
 * http://blog.csdn.net/tonytfjing/article/details/39207551
 *
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);

	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object Handler) throws ServletException, IOException{
				
		if("GET".equalsIgnoreCase(req.getMethod())){
			
			System.out.println("GET ......");
			
		}
		log.info("***************执行1：preHandler**************");
		
		User user = (User) req.getSession().getAttribute("user");
		//如果用户为空
		if(user == null){
			log.info("interceptor...跳转到login页面...");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return false;
		}
		else if(user != null){
			User_userType user_userType = user.getUser_userType();
			if(user_userType.getU_ut_id() == 2){
				return true;
			}else {
				req.getRequestDispatcher("adminlogin.jsp").forward(req, resp);
				return false;
			}
		}
		else {
			return true;
		}
	}
	
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, 
			ModelAndView modelAndView){
		log.info("***********执行2：postHandle*************");
		if(modelAndView != null){
			modelAndView.addObject("var", "测试postHandle");
		}
	} 
	
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp){
		log.info("************执行3：afterCompletion**************");
	}
}
