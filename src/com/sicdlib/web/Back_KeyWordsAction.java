package com.sicdlib.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sicdlib.entity.KeyWords;
import com.sicdlib.service.KeyWordsService;
import com.sicdlib.service.WeiboService;

import edu.xjtsoft.base.orm.support.Page;

/**
 * 
 * 后台关键词管理
 * @author 
 *
 */
@Controller
@RequestMapping("/background/")
public class Back_KeyWordsAction {
	
	@Autowired(required=true)
	KeyWordsService keyService;
	
	@Autowired(required=true)
	WeiboService weiboService;
	
	@RequestMapping()
	public String kwmanage(){
		System.out.println("load to kwmanage ......");
		return "/WEB-INF/background/kwmanage";
	}
	
	@RequestMapping(value="kwmanage")
	public String listKeyWords( int pageNo,Model model) {
		Page<KeyWords> page = new Page<KeyWords>(10);
		page.setPageNo(pageNo);
		Page<KeyWords> loadAll = keyService.loadAll(page);
		//List<KeyWords> list = loadAll.getResult();
		model.addAttribute("page",loadAll);
		System.out.println(loadAll.getResult().size());
		return "/WEB-INF/background/kwmanage";
	}
	
	@RequestMapping("editKeyWords")
	public  String editKeyWords(KeyWords keyWords, Model model){
		/*List<KeyWords> keyWords=keyService.getTopKeyWords(id);
		System.out.println(id+".....");
		model.addAttribute("id",id);
		model.addAttribute("kw_name",((KeyWords) keyWords).getKw_name());
		model.addAttribute("kw_hotDegree",((KeyWords) keyWords).getKw_hotDegree());
		model.addAttribute("kw_province",((KeyWords) keyWords).getKw_province());
		model.addAttribute("kw_Date",((KeyWords) keyWords).getKw_Date());*/
		keyService.saveOrUpdate(keyWords);
		Page<KeyWords> page = new Page<KeyWords>(20);
		page.setPageNo(1);
	    Page<KeyWords> loadAll = keyService.loadAll(page);
	    //List<KeyWords> list=loadAll.getResult();
	    model.addAttribute("page",loadAll);
		return "/WEB-INF/background/kwmanage";
	}
	
	@RequestMapping("deleteKeyWords")
	public String deleteKeyWords(int id, Model model){
		//System.out.println(id);
		keyService.remove(id);
		Page<KeyWords> page = new Page<KeyWords>();
		page.setPageNo(1);
		page.setPageSize(20);
		Page<KeyWords> loadAll = keyService.loadAll(page);
		List<KeyWords> list = loadAll.getResult();
		model.addAttribute("page", loadAll);
	return "/WEB-INF/background/kwmanage";
	}
	
	@RequestMapping("keyWordsSerach")
	public String keyWordsSerach(HttpServletRequest request,Model model){
		String  keywords_search=request.getParameter("keywords_search");
		Page<KeyWords> keywords=(Page<KeyWords>) keyService.getkeyWordsSearch(keywords_search);
	    model.addAttribute("page", keywords);
		return "/WEB-INF/background/kwmanage";
	}
	
	@RequestMapping("keywordsSearchByPage")
	public String keywordsSearchByPage(HttpServletRequest req, Model mode) throws IOException{
		System.out.println("load to keywordsSearchByPage ......");
		
		//2乱码
		String keyWordsSearch =(req.getParameter("keywords_search"));
		int pageNo = Integer.parseInt(req.getParameter("pageNo"));
		if(pageNo != 1){
			keyWordsSearch = new String(keyWordsSearch.getBytes("iso-8859-1"), "utf8");
		}
		System.out.println(pageNo+":"+keyWordsSearch);
		Page<KeyWords> page = keyService.getkeyWordsSearchByPage(pageNo, keyWordsSearch);
		mode.addAttribute("page", page);
		System.out.println(page.getResult().size());
		mode.addAttribute("keyWordsSearch", keyWordsSearch);
		return "/WEB-INF/background/searchkeyword";
	}
	
}
