package com.sicdlib.controller;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.sicdlib.dao.imple.WeiboDAO;
import com.sicdlib.dto.*;
import com.sicdlib.service.IKeyWordsService;
import com.sicdlib.service.imple.KeyWordsService;
import com.sicdlib.service.imple.WeiboService;
import com.sicdlib.util.CNSegment.CNSegment;
import com.sicdlib.util.CNSegment.TextSummarization;
import com.sicdlib.util.fileUtil.FileUtil;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.apache.struts.util.TokenProcessor;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by maninit on 2016/9/20.
 */
@Controller
public class AlgorithmController {

    @Autowired()
    KeyWordsService keyWordsService;

    @Autowired
    WeiboService weiboService;

    List<KeyWords> topkeywords = null;

    @RequestMapping("/index")
    public String index(HttpServletRequest req, Model mode){
        System.out.println("已经加载到index...");
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        topkeywords = keyWordsService.getTopKeyWords(12,"1","2016-07-24");
        mode.addAttribute("topkeywords", topkeywords);
        return "/WEB-INF/index";
    }

//    @RequestMapping("/getRandomKeyWords")
//    public void getRandomKeyWords(HttpServletResponse resp) throws org.codehaus.jackson.map.JsonMappingException, JsonGenerationException {
//        System.out.println("已经加载到rdmkw...");
//        //获得随机数：
//        int[] arrInt = new int[10];
//        int flag = 0;
//        int point = 0;
//        for (int i = 0; point < 9; i++) {
//            int rdmInt = (int)(Math.random()*10);
//            for (int j = 0; j < arrInt.length; j++) {
//                if(arrInt[j] == rdmInt){
//                    flag = 1;break;
//                }
//            }
//            if(flag == 0){
//                arrInt[point] = rdmInt;
//                point ++;
//            }
//            flag = 0;
//        }
//        List<KeyWords> topkeywordsRandom = new LinkedList<KeyWords>();
//        for (int i = 0; i < arrInt.length; i++) {
//            topkeywordsRandom.add(topkeywords.get(arrInt[i]));
//        }
//        for (int i = 0; i < topkeywordsRandom.size(); i++) {
//            System.out.println(topkeywordsRandom.get(i).getKw_name());
//        }
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            String json = mapper.writeValueAsString(topkeywordsRandom);
//            System.out.println(json);
//            //解决中文乱码问题
//            resp.setContentType("text/html;charset=utf-8");
//            PrintWriter out = resp.getWriter();
//            out.write(json);
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    @RequestMapping("/latest")
    public String latest(Model mode){
        System.out.println("已经加载到latest...");
        List<KeyWords> td0_topkeywords = null;
        List<KeyWords> td1_topkeywords = null;
        List<KeyWords> td2_topkeywords = null;
        List<KeyWords> td3_topkeywords = null;
        List<KeyWords> td4_topkeywords = null;
        Date date = new Date();
        td0_topkeywords = keyWordsService.getTopKeyWords(10,"1","2016-07-24");
        td1_topkeywords = keyWordsService.getTopKeyWords(10,"1","2016-07-23");
        td2_topkeywords = keyWordsService.getTopKeyWords(10,"1","2016-07-22");
        td3_topkeywords = keyWordsService.getTopKeyWords(10,"1","2016-07-21");
        td4_topkeywords = keyWordsService.getTopKeyWords(10,"1","2016-07-20");
        td4_topkeywords = keyWordsService.getTopKeyWords(10,"1","2016-07-20");
        mode.addAttribute("td0_topkeywords", td0_topkeywords);
        mode.addAttribute("td1_topkeywords", td1_topkeywords);
        mode.addAttribute("td2_topkeywords", td2_topkeywords);
        mode.addAttribute("td3_topkeywords", td3_topkeywords);
        mode.addAttribute("td4_topkeywords", td4_topkeywords);
        System.out.println(td0_topkeywords.size()+":"+td1_topkeywords.size()+""
                +td2_topkeywords.size()+""+td3_topkeywords.size()+""+td4_topkeywords.size());
        return "/WEB-INF/latest";
    }

    @RequestMapping("/maps")
    public String maps(Model mode){
        System.out.println("已经加载到maps...");
        List<KeyWords> keywords_hebei,keywords_shanxi,keywords_liaoning,
        keywords_jilin,keywords_heilongjiang,keywords_jiangsu,keywords_zhejiang,
        keywords_anhui,keywords_fujian,keywords_jiangxi,keywords_shandong,
        keywords_henan,keywords_hubei,keywords_hunan,keywords_guangdong,
        keywords_hainan,keywords_sichuan,keywords_guizhou,keywords_yunnan,
        keywords_shananxi,keywords_gansu,keywords_qinghai,keywords_taiwan,
        keywords_beijing,keywords_shanghai,keywords_chongqing,keywords_tianjin,
        keywords_guangxi,keywords_ningxia,keywords_xizang,keywords_xinjiang,
        keywords_neimenggu,keywords_xianggang,keywords_aomen;
        String date_format_str = "2016-07-26";
        keywords_hebei = keyWordsService.getTopKeyWords(5, "河北", date_format_str);
        keywords_shanxi = keyWordsService.getTopKeyWords(5, "陕西", date_format_str);
        keywords_liaoning = keyWordsService.getTopKeyWords(5, "辽宁", date_format_str);
        keywords_jilin = keyWordsService.getTopKeyWords(5, "吉林", date_format_str);
        keywords_heilongjiang = keyWordsService.getTopKeyWords(5, "黑龙江", date_format_str);
        keywords_jiangsu = keyWordsService.getTopKeyWords(5, "江苏", date_format_str);
        keywords_zhejiang = keyWordsService.getTopKeyWords(5, "浙江", date_format_str);
        keywords_anhui = keyWordsService.getTopKeyWords(5, "安徽", date_format_str);
        keywords_fujian = keyWordsService.getTopKeyWords(5, "福建", date_format_str);
        keywords_jiangxi = keyWordsService.getTopKeyWords(5, "江西", date_format_str);
        keywords_shandong = keyWordsService.getTopKeyWords(5, "山东", date_format_str);
        keywords_henan = keyWordsService.getTopKeyWords(5, "河南", date_format_str);
        keywords_hubei = keyWordsService.getTopKeyWords(5, "湖北", date_format_str);
        keywords_hunan = keyWordsService.getTopKeyWords(5, "湖南", date_format_str);
        keywords_guangdong = keyWordsService.getTopKeyWords(5, "广东", date_format_str);
        keywords_hainan = keyWordsService.getTopKeyWords(5, "海南", date_format_str);
        keywords_sichuan = keyWordsService.getTopKeyWords(5, "四川", date_format_str);
        keywords_guizhou = keyWordsService.getTopKeyWords(5, "贵州", date_format_str);
        keywords_yunnan = keyWordsService.getTopKeyWords(5, "云南", date_format_str);
        keywords_shananxi = keyWordsService.getTopKeyWords(5, "陕西", date_format_str);
        keywords_gansu = keyWordsService.getTopKeyWords(5, "甘肃", date_format_str);
        keywords_qinghai = keyWordsService.getTopKeyWords(5, "青海", date_format_str);
        keywords_taiwan = keyWordsService.getTopKeyWords(5, "台湾", date_format_str);
        keywords_beijing = keyWordsService.getTopKeyWords(5, "北京", date_format_str);
        keywords_shanghai = keyWordsService.getTopKeyWords(5, "上海", date_format_str);
        keywords_chongqing = keyWordsService.getTopKeyWords(5, "重庆", date_format_str);
        keywords_tianjin = keyWordsService.getTopKeyWords(5, "天津", date_format_str);
        keywords_guangxi = keyWordsService.getTopKeyWords(5, "广西", date_format_str);
        keywords_ningxia = keyWordsService.getTopKeyWords(5, "宁夏", date_format_str);
        keywords_xizang = keyWordsService.getTopKeyWords(5, "西藏", date_format_str);
        keywords_xinjiang = keyWordsService.getTopKeyWords(5, "新疆", date_format_str);
        keywords_neimenggu = keyWordsService.getTopKeyWords(5, "内蒙古", date_format_str);
        keywords_xianggang = keyWordsService.getTopKeyWords(5, "香港", date_format_str);
        keywords_aomen = keyWordsService.getTopKeyWords(5, "澳门", date_format_str);
        mode.addAttribute("keywords_hebei", keywords_hebei);
        mode.addAttribute("keywords_shanxi", keywords_shanxi);
        mode.addAttribute("keywords_liaoning", keywords_liaoning);
        mode.addAttribute("keywords_jilin", keywords_jilin);
        mode.addAttribute("keywords_heilongjiang", keywords_heilongjiang);
        mode.addAttribute("keywords_jiangsu", keywords_jiangsu);
        mode.addAttribute("keywords_zhejiang", keywords_zhejiang);
        mode.addAttribute("keywords_anhui", keywords_anhui);
        mode.addAttribute("keywords_fujian", keywords_fujian);
        mode.addAttribute("keywords_jiangxi", keywords_jiangxi);
        mode.addAttribute("keywords_shandong", keywords_shandong);
        mode.addAttribute("keywords_henan", keywords_henan);
        mode.addAttribute("keywords_hubei", keywords_hubei);
        mode.addAttribute("keywords_hunan", keywords_hunan);
        mode.addAttribute("keywords_guangdong", keywords_guangdong);
        mode.addAttribute("keywords_hainan", keywords_hainan);
        mode.addAttribute("keywords_sichuan", keywords_sichuan);
        mode.addAttribute("keywords_guizhou", keywords_guizhou);
        mode.addAttribute("keywords_yunnan", keywords_yunnan);
        mode.addAttribute("keywords_shananxi", keywords_shananxi);
        mode.addAttribute("keywords_gansu", keywords_gansu);
        mode.addAttribute("keywords_qinghai", keywords_qinghai);
        mode.addAttribute("keywords_taiwan", keywords_taiwan);
        mode.addAttribute("keywords_beijing", keywords_beijing);
        mode.addAttribute("keywords_shanghai", keywords_shanghai);
        mode.addAttribute("keywords_chongqing", keywords_chongqing);
        mode.addAttribute("keywords_tianjin", keywords_tianjin);
        mode.addAttribute("keywords_guangxi", keywords_guangxi);
        mode.addAttribute("keywords_ningxia", keywords_ningxia);
        mode.addAttribute("keywords_xizang", keywords_xizang);
        mode.addAttribute("keywords_xinjiang", keywords_xinjiang);
        mode.addAttribute("keywords_neimenggu", keywords_neimenggu);
        mode.addAttribute("keywords_xianggang", keywords_xianggang);
        mode.addAttribute("keywords_aomen", keywords_aomen);
        return "/WEB-INF/maps";
    }
    @RequestMapping("/perkeymaps")
    public String perkeymaps(){
        System.out.println("已经加载到perkeymaps...");
        return "/WEB-INF/perkeymaps";
    }

    @RequestMapping("/search")
    public String search(HttpServletRequest req){
        String token = TokenProcessor.getInstance().generateToken(req);
        System.out.println("生成的Token："+token);
        req.getSession().setAttribute("token", token);
        System.out.println("已经加载到search...");
        return "/WEB-INF/search";
    }


    @RequestMapping("so/{pageNo}")
    public String so(@PathVariable int pageNo, HttpServletRequest req, Model mode){
        System.out.println("load to so ......");
        String kw_DateBegin = req.getParameter("kw_DateBegin");
        String kw_DateEnd = req.getParameter("kw_DateEnd");
        String kw_province = req.getParameter("kw_province");
        String kw_name = req.getParameter("kw_name");
        if(kw_province != null){
            if(kw_province.equals("")){
                kw_province = "%";
            }
        }
        if(kw_province == null){
            kw_province = "%";
        }

        System.out.println(kw_DateBegin+":"+kw_DateEnd+":"+kw_province+":"+kw_name);

        Page<Weibo> page = weiboService.getWeibosByKeywords(pageNo, kw_name, kw_province, kw_DateBegin, kw_DateEnd);

        System.out.println("得到关键词的大小为："+page.getResult().size());

        mode.addAttribute("kw_name", kw_name);
        mode.addAttribute("kw_DateBegin", kw_DateBegin);
        mode.addAttribute("kw_DateEnd", kw_DateEnd);
        mode.addAttribute("kw_province", kw_province);
        mode.addAttribute("page", page);
        return "/WEB-INF/search";
    }


    //ajax获得的数据，获得更多
    @RequestMapping("somore/{pageNo}")
    public void somore(@PathVariable int pageNo, HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException, JsonMappingException, IOException{
        System.out.println("load to so ......");
        String kw_DateBegin = req.getParameter("kw_DateBegin");
        String kw_DateEnd = req.getParameter("kw_DateEnd");
        String kw_province = req.getParameter("kw_province");
        String kw_name = req.getParameter("kw_name");
        if(kw_province == null){
            kw_province = "%";
        }
        System.out.println(kw_DateBegin+":"+kw_DateEnd+":"+kw_province+":"+kw_name);
        kw_name = "山西煤矿";kw_province = "%";
        Page<Weibo> page = weiboService.getWeibosByKeywords(pageNo, kw_name, kw_province, kw_DateBegin, kw_DateEnd);

        List<Weibo> weibos = page.getResult();
        System.out.println("得到微博的大小为："+page.getResult().size());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(weibos);
        PrintWriter out = resp.getWriter();
        out.write(json);
    }

    List<Weibo> weibosAll = new LinkedList<Weibo>();
    //每页显示10个查询结果：
    @RequestMapping(value="searchs/{pageNo}", method = RequestMethod.POST)
    public String searchs(@PathVariable int pageNo, HttpServletRequest req, Model mode){
        String client_token = req.getParameter("token");
        System.out.println("form得到的Token："+client_token);
        if(client_token == null){
            System.out.println("client_token:"+client_token);
            System.out.println("请不要重新提交");
        }
        String server_token = (String) req.getSession().getAttribute("token");
        if(server_token == null){
            System.out.println("server_client:"+server_token);
            System.out.println("请不要重新提交");
        }
        if(!client_token.equals(server_token)){
            System.out.println("client与server不相同,请不要重新提交");
        }else{
            req.getSession().removeAttribute("token");
            System.out.println("移除token...");

            String kw_DateBegin = req.getParameter("kw_DateBegin");
            String kw_DateEnd = req.getParameter("kw_DateEnd");
            String kw_province = req.getParameter("kw_province");
            String kw_name = req.getParameter("kw_name");
            System.out.println(kw_DateBegin+":"+kw_DateEnd+":"+kw_province+":"+kw_name);
            List<KeyWords> keywords = keyWordsService.getkeyWordsbyNameProDate(kw_name, kw_province, kw_DateBegin,kw_DateEnd);

            PropertyFilter filters = null;
            if (keywords!=null && keywords.size()!=0) {
                for (int i = 0; i < keywords.size(); i++) {
                    filters = new PropertyFilter("keyWords", keywords.get(i));
                    List<Weibo> weibos = weiboService.search(filters);
                    weibosAll.addAll(weibos);
                }
            }
            List<Weibo> weibosPartial = new LinkedList<Weibo>();
            if(pageNo > 0){
                //设定每页显示10个
                for (int i = (pageNo-1)*10; i < pageNo*10; i++) {
                    if(i<weibosAll.size()){
                        weibosPartial.add(weibosAll.get(i));
                    }
                }
            }

            System.out.println(weibosAll.size());
            mode.addAttribute("keywords", keywords);
            mode.addAttribute("weibos", weibosPartial);
            if(keywords!=null){
                System.out.println("关键词数量："+keywords.size());
                if(weibosAll!=null){
                    System.out.println("微博数量："+weibosAll.size());
                    for (int i = 0; i < weibosAll.size(); i++) {
                        System.out.println(weibosAll.get(i).getWb_id());
                    }
                }
            }

        }

        return "/WEB-INF/search";
    }

    @RequestMapping("/searchsPage")
    public String searchsPage(HttpServletRequest req, Model mode){
        List<Weibo> weibosPartial = new LinkedList<Weibo>();
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        if(pageNo > 0){
            //设定每页显示10个
            for (int i = (pageNo-1)*10; i < pageNo*10; i++) {
                if(i<weibosAll.size()){
                    weibosPartial.add(weibosAll.get(i));
                }
            }
            mode.addAttribute("weibos", weibosPartial);
        }
        return "/WEB-INF/search";
    }

    @RequestMapping("/weibolists")
    public String weibolists(HttpServletRequest req, Model mode){
        String  kwid = req.getParameter("kwid");
        System.out.println(kwid);
        KeyWords keyword = (KeyWords) keyWordsService.load(kwid);
        PropertyFilter filters = new PropertyFilter("keyWords", keyword);
        List<Weibo> weibos = weiboService.search(filters);
        mode.addAttribute("keyword", keyword);
        mode.addAttribute("weiboSize", weibos.size());
        mode.addAttribute("weibos", weibos);
        return "/WEB-INF/weibolists";
    }

    @RequestMapping("/analyser")
    public String analyser(HttpServletRequest req){

        return "/WEB-INF/analyser";
    }


    @RequestMapping("/genesummarize")
    public void genesummarize(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        System.out.println("summarize.....");
        String oneText = req.getParameter("news");
//		System.out.println(oneText);
        //【1】 - 【5】.文章分词,获取关键词，并对文章关键词排序,提取文章关键词排名前5
        HashMap<String, Integer> sortedhashmap = TextSummarization.EssayTopSortedCutWords(oneText);
        //【6】.提取文章句子
        String[] sentences = TextSummarization.getSentences(oneText);
        //【7】.输入切分句子、排名前5的关键词，输出包含关键词的句子
        List<SentenceCluster> sentenceClusters = TextSummarization.getSentencesContainsKeywords(sentences, sortedhashmap);
        //【8】.计算整个文章的每个切分句子的簇权重，簇权重 = (包含的关键词)^2 / (该句子的簇的大小)
        List<SentenceCluster> sentenceClustersIncIMP = TextSummarization.getSenClusterIMPDegree(sentenceClusters);
        //【9】.选出排名前5的分值最高的句子，按照句子在文章中的先后顺序输出
        List<SentenceCluster> topSentences = TextSummarization.getTopIMPSentenceByOrder(sentenceClustersIncIMP);
        String summarization = TextSummarization.getSummarization(topSentences);
        System.out.println("文本内容:\n"+oneText);
        System.out.println("自动形成摘要:\n"+summarization);
//		String summar = new String(summarization.getBytes("ISO-8859-1"),"UTF-8");
        //去除%等字符，然后转码
        summarization = summarization.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
        summarization = summarization.replaceAll("\\+", "%2B");
        String summar = "";
        summar = URLDecoder.decode(summarization, "utf-8");
        //要放在resp.getWriter()之前，而不是之后。
        resp.setContentType("text/text;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(summarization);
    }


    @RequestMapping("/relativeSummarWeibo")
    public void relativeSummarWeibo(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        System.out.println("relativeSummarWeibo.......");
        String summar = req.getParameter("summar");
        String filepath = "E:/news.dat";
        System.out.println(filepath);

        List<Corpus_Essays> essays = FileUtil.writeFileToDB(filepath);
        List<String> contents = new LinkedList<String>();
        for (int i = 0; i < essays.size(); i++) {
            contents.add(essays.get(i).getCe_content());
        }
        //将所有文档中添加这个测试文档
        contents.add(summar);
        //得到所有文档+测试文档的TF
        HashMap<String,HashMap<String, Float>> allTextTF = CNSegment.allTF(contents);
        //得到所有文档+测试文档的IDFs
        HashMap<String, Float> IDFs = CNSegment.IDF(allTextTF);
        //得到所有文档+测试文档的TFIDF
        HashMap<String, HashMap<String, Float>> tfidf = CNSegment.TF_IDF(contents, IDFs);
        //得到所有文档+测试文档前20位的关键词
        TreeMap<String, TreeMap<String, Float>> sortedAlltfidf = CNSegment.sortOfTopAllTFIDF(tfidf);
        //将TreeMap转换为HashMap
        HashMap<String, HashMap<String, Float>> hmp_sortedAlltfidf = CNSegment.getTopSomeSortedTFIDF(sortedAlltfidf);
        //得到一篇文档与所有文档之间的相似性
        List<EssaySimilarityDegree> essaySimiDegreeList = CNSegment.getTopOneTextAndAllTextConsineSimilary(summar,hmp_sortedAlltfidf);
        //【12】对所有文档相似度集合进行排序，获取前20个文章
        List<EssaySimilarityDegree> essaySimiTopDegrees = CNSegment.getTop20sortedAllTextCosineSimilary(essaySimiDegreeList);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(essaySimiTopDegrees);
//		System.out.println(essaySimiTopDegrees.get(0)+"json格式:\t"+json);
        resp.setContentType("text/text;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(json);
    }

    //后台登录界面跳转
    @RequestMapping("/adminlogin")
    public String adminlogin(){
        return "adminlogin";
    }
}
