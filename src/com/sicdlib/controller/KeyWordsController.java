package com.sicdlib.controller;

import com.sicdlib.dto.KeyWords;
import com.sicdlib.service.imple.KeyWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 关键词管理
 * Created by maninit on 2016/9/22.
 */
@Controller
@RequestMapping("/*")
public class KeyWordsController {
    @Autowired(required=true)
    KeyWordsService keyWordsService;
    List<KeyWords> topkeywords = null;

    //对应页面perkeymaps.jsp
    @RequestMapping(value="keywords")
    public String searchSpecificKeyWordsPerProvince(HttpServletRequest req, Model mode){
        String kw_name = req.getParameter("kw_name");
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String [] colorArray = new String[]{
                "#F8D000", "#FFD707", "#F1C90E",
                "#EAC215", "#E3BB1C", "#DCB423",
                "#D5AD2A", "#CEA62A", "#C79F2A",
                "#C0982A", "#B9912A", "#B28A2A",
                "#AB832A", "#A57C2A", "#A5752A",
                "#A56E2A", "#A5672A", "#A5602A",
                "#A5602A", "#A5592A", "#A5522A",
                "#A54B2A", "#A5442A", "#A53D2A",
                "#A5362A", "#A52F2A", "#A52A2A",
                "#A52A2A", "#A52A2A", "#A52A2A",
                "#A52A2A", "#A52A2A", "#A52A2A",
                "#A52A2A"
        };
        List<KeyWords> keywords = keyWordsService.getSpecificKeyWords(kw_name, "2016-07-26");
        for (int i = 0; i < keywords.size(); i++) {
            if(!keywords.get(i).getKw_province().equals("1")){
                colorArray[i] = keywords.get(i).getKw_province()+"-"+colorArray[i];
            }
        }
        for (int i = 0; i < colorArray.length; i++) {
            System.out.print(colorArray[i]);
        }
        mode.addAttribute("colorArray", colorArray);
        mode.addAttribute("keywords", keywords);
        return "/WEB-INF/perkeymaps";
    }

}
