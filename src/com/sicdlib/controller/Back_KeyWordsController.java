package com.sicdlib.controller;

import com.sicdlib.dto.KeyWords;
import com.sicdlib.service.imple.KeyWordsService;
import com.sicdlib.service.imple.WeiboService;
import edu.xjtsoft.base.orm.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 *
 * 后台关键词管理
 * @author Created by maninit on 2016/9/22.
 *
 */
@Controller
@RequestMapping("/background/*")
public class Back_KeyWordsController {
        @Autowired(required=true)
        KeyWordsService keyWordsService;

        @Autowired(required=true)
        WeiboService weiboService;

        @RequestMapping("kwmanage")
        public String kwmanage(){
            System.out.println("load to kwmanage ......");
            return "/WEB-INF/background/kwmanage";
        }

        @RequestMapping("kwmanage/{pageNo}")
        public String listKeyWords(@PathVariable int pageNo, Model model) {
            Page<KeyWords> page = new Page<KeyWords>(10);
            page.setPageNo(pageNo);
            Page<KeyWords> loadAll =  keyWordsService.loadAll(page);
            //List<KeyWords> list = loadAll.getResult();
            model.addAttribute("page",loadAll);
            System.out.println(loadAll.getResult().size());
            return "/WEB-INF/background/kwmanage";
        }

        @RequestMapping("editKeyWords")
        public  String editKeyWords(KeyWords keyWords, Model model){
            keyWordsService.saveOrUpdate(keyWords);
            Page<KeyWords> page = new Page<KeyWords>(20);
            page.setPageNo(1);
            Page<KeyWords> loadAll = keyWordsService.loadAll(page);
            model.addAttribute("page",loadAll);
            return "/WEB-INF/background/kwmanage";
        }

        @RequestMapping("deleteKeyWords")
        public String deleteKeyWords(int id, Model model){
            //System.out.println(id);
            keyWordsService.remove(id);
            Page<KeyWords> page = new Page<KeyWords>();
            page.setPageNo(1);
            page.setPageSize(20);
            Page<KeyWords> loadAll = keyWordsService.loadAll(page);
            List<KeyWords> list = loadAll.getResult();
            model.addAttribute("page", loadAll);
            return "/WEB-INF/background/kwmanage";
        }

        @RequestMapping("keyWordsSerach")
        public String keyWordsSerach(HttpServletRequest request, Model model){
            String  keywords_search=request.getParameter("keywords_search");
            Page<KeyWords> keywords=(Page<KeyWords>) keyWordsService.getkeyWordsSearch(keywords_search);
            model.addAttribute("page", keywords);
            return "/WEB-INF/background/kwmanage";
        }

        @RequestMapping("keyWordsSearchByPage")
        public String keyWordsSearchByPage(HttpServletRequest req, Model mode) throws IOException {
            System.out.println("load to keyWordsSearchByPage ......");

            //2乱码
            String keyWordsSearch =(req.getParameter("keywords_search"));
            int pageNo = Integer.parseInt(req.getParameter("pageNo"));
            if(pageNo != 1){
                keyWordsSearch = new String(keyWordsSearch.getBytes("iso-8859-1"), "utf8");
            }
            System.out.println(pageNo+":"+keyWordsSearch);
            Page<KeyWords> page = keyWordsService.getkeyWordsSearchByPage(pageNo, keyWordsSearch);
            mode.addAttribute("page", page);
            System.out.println(page.getResult().size());
            mode.addAttribute("keyWordsSearch", keyWordsSearch);
            return "/WEB-INF/background/searchkeyword";
        }

    }
