package com.sicdlib.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.TokenProcessor;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sicdlib.entity.Corpus_Essays;
import com.sicdlib.entity.EssaySimilarityDegree;
import com.sicdlib.entity.KeyWords;
import com.sicdlib.entity.SentenceCluster;
import com.sicdlib.entity.User;
import com.sicdlib.entity.Weibo;
import com.sicdlib.service.KeyWordsService;
import com.sicdlib.service.WeiboService;
import com.sicdlib.util.CNSegment.CNSegment;
import com.sicdlib.util.CNSegment.TextSummarization;
import com.sicdlib.util.fileUtil.FileUtil;

import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;

@Controller
@RequestMapping("/*")
public class IndexAction {
	
	@Autowired(required=true)
	KeyWordsService keyService;
	
	@Autowired(required=true)
	WeiboService weiboService;
	
	List<KeyWords> topkeywords = null;
	
	@RequestMapping
	public String index(HttpServletRequest req, Model mode){
		System.out.println("已经加载到index...");
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//topkeywords = keyService.getTopKeyWords(10,"1",df.format(date));
		topkeywords = keyService.getTopKeyWords(12,"1","2016-07-24");
		mode.addAttribute("topkeywords", topkeywords);
		
		return "/WEB-INF/index";
	}
	
	
	@RequestMapping
	public void getRandomKeyWords(HttpServletResponse resp) throws JsonMappingException, JsonGenerationException{
		System.out.println("已经加载到rdmkw...");
		//获得随机数：
		int[] arrInt = new int[10];
		int flag = 0;
		int point = 0;
		for (int i = 0; point < 9; i++) {
			int rdmInt = (int)(Math.random()*10);
			for (int j = 0; j < arrInt.length; j++) {
				if(arrInt[j] == rdmInt){
					flag = 1;break;
				}
			}
			if(flag == 0){
				arrInt[point] = rdmInt;
				point ++;
			}
			flag = 0;
		}
		
		List<KeyWords> topkeywordsRandom = new LinkedList<KeyWords>();
		
		for (int i = 0; i < arrInt.length; i++) {
			topkeywordsRandom.add(topkeywords.get(arrInt[i]));
		}
		for (int i = 0; i < topkeywordsRandom.size(); i++) {
			System.out.println(topkeywordsRandom.get(i).getKw_name());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(topkeywordsRandom);
			System.out.println(json);
			//解决中文乱码问题
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();			
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping
	public String latest(Model mode){
		System.out.println("已经加载到latest...");
		List<KeyWords> td0_topkeywords = null;
		List<KeyWords> td1_topkeywords = null;
		List<KeyWords> td2_topkeywords = null;
		List<KeyWords> td3_topkeywords = null;
		List<KeyWords> td4_topkeywords = null;
		Date date = new Date();
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		td0_topkeywords = keyService.getTopKeyWords(10,"1",df.format(date));
//		td1_topkeywords = keyService.getTopKeyWords(10,"1",df.format(date.getTime()-1*24*60*60*1000));
//		td2_topkeywords = keyService.getTopKeyWords(10,"1",df.format(date.getTime()-2*24*60*60*1000));
//		td3_topkeywords = keyService.getTopKeyWords(10,"1",df.format(date.getTime()-3*24*60*60*1000));
//		td4_topkeywords = keyService.getTopKeyWords(10,"1",df.format(date.getTime()-4*24*60*60*1000));
		
		td0_topkeywords = keyService.getTopKeyWords(10,"1","2016-07-24");
		td1_topkeywords = keyService.getTopKeyWords(10,"1","2016-07-23");
		td2_topkeywords = keyService.getTopKeyWords(10,"1","2016-07-22");
		td3_topkeywords = keyService.getTopKeyWords(10,"1","2016-07-21");
		td4_topkeywords = keyService.getTopKeyWords(10,"1","2016-07-20");
		mode.addAttribute("td0_topkeywords", td0_topkeywords);
		mode.addAttribute("td1_topkeywords", td1_topkeywords);
		mode.addAttribute("td2_topkeywords", td2_topkeywords);
		mode.addAttribute("td3_topkeywords", td3_topkeywords);
		mode.addAttribute("td4_topkeywords", td4_topkeywords);
		System.out.println(td0_topkeywords.size()+":"+td1_topkeywords.size()+""
		+td2_topkeywords.size()+""+td3_topkeywords.size()+""+td4_topkeywords.size());
		
		return "/WEB-INF/latest";
	}
	
	
	@RequestMapping
	public String maps(Model mode){
		System.out.println("已经加载到maps...");
		
//		String[] pro_names = new String[]{
//				"keywords_p1", "keywords_p2", "keywords_p3",
//				"keywords_p4", "keywords_p5", "keywords_p6",
//				"keywords_p7", "keywords_p8", "keywords_p9", 
//				"keywords_p10", "keywords_p11", "keywords_p12",
//				"keywords_p13", "keywords_p14", "keywords_p15",
//				"keywords_p16", "keywords_p17", "keywords_p18",
//				"keywords_p19", "keywords_p20", "keywords_p21",
//				"keywords_p22", "keywords_p23",	"keywords_p24",
//				"keywords_p25", "keywords_p26", "keywords_p27",
//				"keywords_p28", "keywords_p29", "keywords_p30",
//				"keywords_p31", "keywords_p32", "keywords_p33",
//				"keywords_p34"
//		};
//		Date date = new Date();
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		String[] provinces = new String[]{
//				"河北", "山西", "辽宁", "吉林", "黑龙江",
//				"江苏", "浙江", "安徽", "福建", "江西", "山东",
//				"河南", "湖北", "湖南", "广东", "海南", "四川", 
//				"贵州", "云南", "陕西", "甘肃", "青海", "台湾", 
//				"北京", "上海", "重庆", "天津", "广西", "宁夏",
//				"西藏", "新疆", "内蒙古", "香港","澳门"
//		};
//		
//		for (int i = 0; i < pro_names.length; i++) {
//			List<KeyWords> as = keyService.getTopKeyWords(5, provinces[i], df.format(date));
//		}
		//keyService.getTopKeyWords(5, provinces[i], df.format(date));
		
		List<KeyWords> keywords_hebei,keywords_shanxi,keywords_liaoning,
		keywords_jilin,keywords_heilongjiang,keywords_jiangsu,keywords_zhejiang,
		keywords_anhui,keywords_fujian,keywords_jiangxi,keywords_shandong,
		keywords_henan,keywords_hubei,keywords_hunan,keywords_guangdong,
		keywords_hainan,keywords_sichuan,keywords_guizhou,keywords_yunnan,
		keywords_shananxi,keywords_gansu,keywords_qinghai,keywords_taiwan,
		keywords_beijing,keywords_shanghai,keywords_chongqing,keywords_tianjin,
		keywords_guangxi,keywords_ningxia,keywords_xizang,keywords_xinjiang,
		keywords_neimenggu,keywords_xianggang,keywords_aomen;
//		//在实际场景中使用
//		Date date = new Date();
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(df.format(date));
//		String date_format_str = df.format(date);
		//在模拟演示中使用：
		String date_format_str = "2016-07-26";
		keywords_hebei = keyService.getTopKeyWords(5, "河北", date_format_str);
		keywords_shanxi = keyService.getTopKeyWords(5, "陕西", date_format_str);
		keywords_liaoning = keyService.getTopKeyWords(5, "辽宁", date_format_str);
		keywords_jilin = keyService.getTopKeyWords(5, "吉林", date_format_str);
		keywords_heilongjiang = keyService.getTopKeyWords(5, "黑龙江", date_format_str);
		keywords_jiangsu = keyService.getTopKeyWords(5, "江苏", date_format_str);
		keywords_zhejiang = keyService.getTopKeyWords(5, "浙江", date_format_str);
		keywords_anhui = keyService.getTopKeyWords(5, "安徽", date_format_str);
		keywords_fujian = keyService.getTopKeyWords(5, "福建", date_format_str);
		keywords_jiangxi = keyService.getTopKeyWords(5, "江西", date_format_str);
		keywords_shandong = keyService.getTopKeyWords(5, "山东", date_format_str);
		keywords_henan = keyService.getTopKeyWords(5, "河南", date_format_str);
		keywords_hubei = keyService.getTopKeyWords(5, "湖北", date_format_str);
		keywords_hunan = keyService.getTopKeyWords(5, "湖南", date_format_str);
		keywords_guangdong = keyService.getTopKeyWords(5, "广东", date_format_str);
		keywords_hainan = keyService.getTopKeyWords(5, "海南", date_format_str);
		keywords_sichuan = keyService.getTopKeyWords(5, "四川", date_format_str);
		keywords_guizhou = keyService.getTopKeyWords(5, "贵州", date_format_str);
		keywords_yunnan = keyService.getTopKeyWords(5, "云南", date_format_str);
		keywords_shananxi = keyService.getTopKeyWords(5, "陕西", date_format_str);
		keywords_gansu = keyService.getTopKeyWords(5, "甘肃", date_format_str);
		keywords_qinghai = keyService.getTopKeyWords(5, "青海", date_format_str);
		keywords_taiwan = keyService.getTopKeyWords(5, "台湾", date_format_str);
		keywords_beijing = keyService.getTopKeyWords(5, "北京", date_format_str);
		keywords_shanghai = keyService.getTopKeyWords(5, "上海", date_format_str);
		keywords_chongqing = keyService.getTopKeyWords(5, "重庆", date_format_str);
		keywords_tianjin = keyService.getTopKeyWords(5, "天津", date_format_str);
		keywords_guangxi = keyService.getTopKeyWords(5, "广西", date_format_str);
		keywords_ningxia = keyService.getTopKeyWords(5, "宁夏", date_format_str);
		keywords_xizang = keyService.getTopKeyWords(5, "西藏", date_format_str);
		keywords_xinjiang = keyService.getTopKeyWords(5, "新疆", date_format_str);
		keywords_neimenggu = keyService.getTopKeyWords(5, "内蒙古", date_format_str);
		keywords_xianggang = keyService.getTopKeyWords(5, "香港", date_format_str);
		keywords_aomen = keyService.getTopKeyWords(5, "澳门", date_format_str);
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
	
	@RequestMapping
	public String perkeymaps(){
		System.out.println("已经加载到perkeymaps...");
		return "/WEB-INF/perkeymaps";
	}
	
	@RequestMapping
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
			List<KeyWords> keywords = keyService.getkeyWordsbyNameProDate(kw_name, kw_province, kw_DateBegin,kw_DateEnd);
			
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
	
	@RequestMapping
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
	
	@RequestMapping
	public String weibolists(HttpServletRequest req, Model mode){
		
		Integer kwid = Integer.parseInt(req.getParameter("kwid"));
		System.out.println(kwid);
		KeyWords keyword = keyService.load(kwid);
		PropertyFilter filters = new PropertyFilter("keyWords", keyword);
		List<Weibo> weibos = weiboService.search(filters); 
		mode.addAttribute("keyword", keyword);
		mode.addAttribute("weiboSize", weibos.size());
		mode.addAttribute("weibos", weibos);
		return "/WEB-INF/weibolists";
	}
	
	@RequestMapping
	public String analyser(HttpServletRequest req){
		
		return "/WEB-INF/analyser";
	}
	
	
	@RequestMapping
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
	
	
	@RequestMapping
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
	@RequestMapping
	public String adminlogin(){
		
		return "adminlogin";
	}
	
	
}
