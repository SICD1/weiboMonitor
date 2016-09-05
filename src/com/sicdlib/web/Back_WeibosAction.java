package com.sicdlib.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sicdlib.entity.Corpus_StopWords;
import com.sicdlib.entity.Weibo;
import com.sicdlib.service.KeyWordsService;
import com.sicdlib.service.WeiboService;

import edu.xjtsoft.base.orm.support.Page;

/**
 * 
 * 后台微博管理
 * @author 
 *
 */
@Controller
@RequestMapping("/background/")
public class Back_WeibosAction {

	@Autowired
	KeyWordsService keyService;
	
	@Autowired
	WeiboService weiboService;
	
	
	
	@RequestMapping("weibosManager/{pageNo}")
	public String weibosManager(@PathVariable int pageNo, HttpServletRequest req, Model mode){
		
		System.out.println("load to weibosManager ......");
		Page<Weibo> page = new Page(10);
		//设置页码
		page.setPageNo(pageNo);
		weiboService.loadAll(page);
		mode.addAttribute("page",page);
		return "/WEB-INF/background/weibosManager";
	}
	
	@RequestMapping("deleteWeibo")
	public String deleteStopwords(HttpServletRequest req, Model mode){
		//删除
		String wb_id_str = req.getParameter("wb_id");
		System.out.println(wb_id_str);
		int wb_id = Integer.parseInt(wb_id_str);
		weiboService.remove(wb_id); 		
		
		Page<Weibo> page = new Page(10);
		//设置页码
		page.setPageNo(1);
		weiboService.loadAll(page);
		mode.addAttribute("page",page);
		return "/WEB-INF/background/weibosManager";
	}
}












