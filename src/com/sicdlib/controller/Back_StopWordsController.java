package com.sicdlib.controller;

import com.sicdlib.dto.Corpus_StopWords;
import com.sicdlib.service.imple.Corpus_StopWordsService;
import edu.xjtsoft.base.orm.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台停用词管理
 * Created by maninit on 2016/9/22.
 */
@Controller
@RequestMapping("/background/*")
public class Back_StopWordsController {
    @Autowired
    Corpus_StopWordsService corpus_StopWordsService;

    @RequestMapping("stopwords/{pageNo}")
    public String stopwords(@PathVariable int pageNo, HttpServletRequest req, Model mode){
        System.out.println("load to stopwords ......");
        Page<Corpus_StopWords> page = new Page(10);
        //设置页码
        page.setPageNo(pageNo);
        corpus_StopWordsService.loadAll(page);
        mode.addAttribute("page",page);
        return "/WEB-INF/background/stopwords";
    }

    @RequestMapping("deleteStopwords")
    public String deleteStopwords(HttpServletRequest req, Model mode){
        //删除
        String csd_id_str = req.getParameter("csd_id");
        System.out.println(csd_id_str);
        int csd_id = Integer.parseInt(csd_id_str);
        corpus_StopWordsService.remove(csd_id);

        Page<Corpus_StopWords> page = new Page(10);
        //设置页码
        page.setPageNo(1);
        corpus_StopWordsService.loadAll(page);
        mode.addAttribute("page",page);
        return "/WEB-INF/background/stopwords";
    }

    @RequestMapping("editStopwords")
    public String editStopwords(Model mode,Corpus_StopWords corpus_StopWords)
    {
        corpus_StopWordsService.saveOrUpdate(corpus_StopWords);
        Page<Corpus_StopWords>  page=new Page<Corpus_StopWords>();
        page.setPageNo(1);
        page.setPageSize(10);
        Page<Corpus_StopWords> loadAll=corpus_StopWordsService.loadAll(page);
        mode.addAttribute("page",loadAll);
        return "/WEB-INF/background/stopwords";

    }
}
