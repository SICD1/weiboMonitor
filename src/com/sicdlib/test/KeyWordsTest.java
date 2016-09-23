//package com.sicdlib.test;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.TreeMap;
//
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.sicdlib.entity.Corpus_Essays;
//import com.sicdlib.entity.EssaySimilarityDegree;
//import com.sicdlib.entity.KeyWordsDAO;
//import com.sicdlib.entity.SentenceCluster;
//import com.sicdlib.entity.Weibo;
//import com.sicdlib.service.Corpus_EssaysService;
//import com.sicdlib.service.KeyWordsService;
//import com.sicdlib.service.WeiboService;
//import com.sicdlib.util.CNSegment.CNSegment;
//import com.sicdlib.util.CNSegment.TextSummarization;
//import com.sicdlib.util.fileUtil.FileUtil;
//
//import edu.xjtsoft.base.orm.support.Page;
//
//public class KeyWordsTest {
//
////	ApplicationContext atx = new ClassPathXmlApplicationContext("beans.xml");
////	KeyWordsService keyService = (KeyWordsService) atx.getBean("keyWordsService");
////	Corpus_EssaysService essasyService = (Corpus_EssaysService) atx.getBean("corpus_EssaysService");
//
//
//	public static void main(String[] args) {
//		System.out.println(Integer.MAX_VALUE);
//
//		int[] arrInt = new int[10];
//		int flag = 0;
//		int point = 0;
//		for (int i = 0; point < 9; i++) {
//			int rdmInt = (int)(Math.random()*10);
//			for (int j = 0; j < arrInt.length; j++) {
//				if(arrInt[j] == rdmInt){
//					flag = 1;break;
//				}
//			}
//			if(flag == 0){
//				arrInt[point] = rdmInt;
//				point ++;
//			}
//			flag = 0;
//		}
//		for (int i = 0; i < arrInt.length; i++) {
//			System.out.println(arrInt[i]);
//		}
//	}
//
///*	@Test
//	public void testKeyWords(){
//		Date date = new Date();
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		//今天与昨天
//		System.out.println(df.format(date)+":"+df.format(date.getTime()-1*24*60*60*1000));
//
////		List<KeyWordsDAO> keywords = keyService.getTopKeyWords(10,"1",df.format(date));
//		List<KeyWordsDAO> keywords = keyService.getTopKeyWords(10,"1","2016-7-24");
//		System.out.println(keywords.get(0).getKw_name());
//	}*/
//
///*	@Test
//	public void TestProvinceKeyWords(){
//		Date date = new Date();
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		List<KeyWordsDAO> keywords = keyService.getTopKeyWords(10,"陕西",df.format(date));
//		System.out.println(keywords.size());
//	}
//
//	@Test
//	public void TestgetSpecificKeyWords(){
//		keyService.getSpecificKeyWords("山西煤矿", "2016-07-26");
//	}*/
//
//	/*@Test
//	public void TestgetKeyWords(){
//		String kw_DateBegin = "2016-07-24";
//		String kw_DateEnd = "2016-07-26";
//		String kw_province = "山西煤矿";
//		String kw_name = "山西";
//		keyService.getkeyWordsbyNameProDate(kw_name, kw_province, kw_DateBegin, kw_DateEnd);
//	}*/
//
//	/*@Test
//	public void testCNSegment() throws IOException{
//		CNSegment cnSegment = new CNSegment();
//		String text ="中国南海发生在前几天";
//		ArrayList<String> words = cnSegment.getCutWords(text);
//		for (int i = 0; i < words.size(); i++) {
//			System.out.println(words.get(i));
//		}
//		cnSegment.oneTF(words);
//	}*/
//
////	//测试文章相似性（CNSegment）
////	@Test
////	public void testStopwords() throws IOException{
////		String filepath = "news.dat";
////		List<Corpus_Essays> essays = FileUtil.writeFileToDB(filepath);
////		List<String> contents = new LinkedList<String>();
////		for (int i = 0; i < essays.size(); i++) {
////			contents.add(essays.get(i).getCe_content());
////		}
////
////		HashMap<String,HashMap<String, Float>> allTextTF = CNSegment.allTF(contents);
////		//IDF:(1.通过从该文档集合中获取分词权重)
////		HashMap<String, Float> IDFs = CNSegment.IDF(allTextTF);
////		//获取IDF:(2.通过在线现代语料库词频数据获得分词权重)
////		//HashMap<String, Float> IDFs = CNSegment.onlineModernCorpusIDF();
////		//得到所有文档的TFIDF
////		HashMap<String, HashMap<String, Float>> tfidf = CNSegment.TF_IDF(contents, IDFs);
////		//得到前20位的关键词
////		TreeMap<String, TreeMap<String, Float>> sortedAlltfidf = CNSegment.sortOfTopAllTFIDF(tfidf);
////		//将TreeMap转换为HashMap
////		HashMap<String, HashMap<String, Float>> hmp_sortedAlltfidf = CNSegment.getTopSomeSortedTFIDF(sortedAlltfidf);
////		//计算所有文档两两的相似度，【功能点】
////		List<EssaySimilarityDegree> essaySimiDegreeList = CNSegment.getAllTextConsineSimilary(hmp_sortedAlltfidf);
////		CNSegment.sortedAllTextCosineSimilary(essaySimiDegreeList);
////
////	}
//
//	//测试自动摘要
//	@Test
//	public void testTextSummarization() throws IOException {
//		String txt1 = "【环球网综合报道】据《篮球杂志》8月4日微博此前消息，中国男篮与大批中国媒体记者在巴西乘大巴前往目的地时遇到惊魂一幕，"
//				+ "大巴行至中途遇到路边枪战。不过，随后该杂志官方微博又称，遭遇枪战的是记者所乘坐的大巴，男篮的大巴并未遇到，"
//				+ "虚惊一场!《篮球杂志》微博称，“刚刚，小编和中国男篮内部人员取得了联系，询问男篮遇枪战一事，该人员介绍说："
//				+ "中国男篮一到里约，就在机场和很多国内专职篮球记者相遇，他乡遇故知，大家都很高兴，寒暄合影之后，各自乘坐大巴前往目的地。"
//				+ "后来，记者乘坐的大巴遇到了枪战，并有6名当地人死亡，男篮的大巴并未遇到，虚惊一场!无论如何，我国体育健儿为了国家的荣誉，"
//				+ "远离祖国，祝他们顺利。同时也祝新闻不断的里约奥运会顺利举行。类似这些枪战，再也不要发生了!”该微博此前称，"
//				+ "大巴刚驶出里约机场两公里，路边突然发生枪战。后据记者们透露，初步统计共有6人死亡，中方人员安全无恙。"
//				+ "中国男篮主帅宫鲁鸣在出发前就曾表示，此次奥运之行，成绩都是其次，他最担忧的是安全问题，他希望教练、队员们都能顺利完成奥运之旅，"
//				+ "安全的回家。";
//		//【1】 - 【5】.文章分词,获取关键词，并对文章关键词排序,提取文章关键词排名前5
//		HashMap<String, Integer> sortedhashmap = TextSummarization.EssayTopSortedCutWords(txt1);
//		//【6】.提取文章句子
//		String[] sentences = TextSummarization.getSentences(txt1);
//		//【7】.输入切分句子、排名前5的关键词，输出包含关键词的句子
//		List<SentenceCluster> sentenceClusters = TextSummarization.getSentencesContainsKeywords(sentences, sortedhashmap);
//		//【8】.计算整个文章的每个切分句子的簇权重，簇权重 = (包含的关键词)^2 / (该句子的簇的大小)
//		List<SentenceCluster> sentenceClustersIncIMP = TextSummarization.getSenClusterIMPDegree(sentenceClusters);
//		//【9】.选出排名前5的分值最高的句子，按照句子在文章中的先后顺序输出
//		List<SentenceCluster> topSentences = TextSummarization.getTopIMPSentenceByOrder(sentenceClustersIncIMP);
//		String summarization = TextSummarization.getSummarization(topSentences);
//		System.out.println("文本内容:\n"+txt1);
//		System.out.println("自动形成摘要:\n"+summarization);
//	}
//
////	@Test
////	public void testOneTextSimilaryDegree() throws IOException{
////		String filepath = "news.dat";
////		List<Corpus_Essays> essays = FileUtil.writeFileToDB(filepath);
////		List<String> contents = new LinkedList<String>();
////		for (int i = 0; i < essays.size(); i++) {
////			contents.add(essays.get(i).getCe_content());
////		}
////
////		String test_text = "【环球网综合报道】据《篮球杂志》8月4日微博此前消息，中国男篮与大批中国媒体记者在巴西乘大巴前往目的地时遇到惊魂一幕";
////		//将所有文档中添加这个测试文档
////		contents.add(test_text);
////		//得到所有文档+测试文档的TF
////		HashMap<String,HashMap<String, Float>> allTextTF = CNSegment.allTF(contents);
////		//得到所有文档+测试文档的IDFs
////		HashMap<String, Float> IDFs = CNSegment.IDF(allTextTF);
////		//得到所有文档+测试文档的TFIDF
////		HashMap<String, HashMap<String, Float>> tfidf = CNSegment.TF_IDF(contents, IDFs);
////		//得到所有文档+测试文档前20位的关键词
////		TreeMap<String, TreeMap<String, Float>> sortedAlltfidf = CNSegment.sortOfTopAllTFIDF(tfidf);
////		//将TreeMap转换为HashMap
////		HashMap<String, HashMap<String, Float>> hmp_sortedAlltfidf = CNSegment.getTopSomeSortedTFIDF(sortedAlltfidf);
////		//得到一篇文档与所有文档之间的相似性
////		List<EssaySimilarityDegree> essaySimiDegreeList = CNSegment.getTopOneTextAndAllTextConsineSimilary(test_text,hmp_sortedAlltfidf);
////		//【12】对所有文档相似度集合进行排序，获取前20个文章
////		CNSegment.getTop20sortedAllTextCosineSimilary(essaySimiDegreeList);
////		//获取所有文档的前20位关键字，输入【10】中数据
////		HashMap<String, Float> allWordsTfidf = CNSegment.getAllTextKeywords(hmp_sortedAlltfidf);
////		//【14】获得整个文档库中所有关键词排名前50位的关键词,输入数据从【13】获取，并没有排序好
////		HashMap<String, Float> topKeywords = CNSegment.getTopTextKeywords(allWordsTfidf);
////		//【15】将前50个关键词和所有文档的TFIDF，放入全部文档中，找到相关文章(不统计热度)
////		List<String> relativeEssays = CNSegment.getRelativeEssaysKeywords(topKeywords, hmp_sortedAlltfidf);
////		//【16】得到前5个关键词。输入通过【15】得到的相关文档和【10】得到的全部文档的排名前20位的关键词序列
////		HashMap<String,	HashMap<String, Float>> top5Keywords = CNSegment.getTopKeywordsByRelativeEssays(relativeEssays, hmp_sortedAlltfidf);
////		//【17】得到相关关键词字符串组合
////		CNSegment.getKeywords(top5Keywords);
////	}
//
//
////	@Test
////	public void testSo(){
////		ApplicationContext atx = new ClassPathXmlApplicationContext("beans.xml");
////		WeiboService weiboService = (WeiboService) atx.getBean("weiboService");
////		Page<Weibo> page = weiboService.getWeibosByKeywords(1, "山西煤矿", "%", "2016-07-20", "2016-08-30");
////		List<Weibo> weibos = page.getResult();
////		for (int i = 0; i < weibos.size(); i++) {
////			System.out.println(weibos.get(i).getWb_content()+":"+weibos.get(i).getWb_id());
////		}
////		System.out.println(page.getResult());
////	}
//
//
//}
//
//
///**
// * 功能点：
// * 关键词提取。已经提取每篇微博的关键词-》整合所有关键词（将所有关键词，权重排名）-》取出前50关键词-》将这些关键词与原来文本中出现的较低的关键词相组合，形成热词。
// * 然后将所有微博关键词整合--页面-->今日热点，最近要闻
// * 1.输入一篇长文本文章形成摘要
// * 2.根据摘要搜索相关微博，根据文章相似性
// * 输入一篇
// * 3.将微博评论拼接在一起，与微博拼在一起，比较相似性。
// */
////这周 微分析、完成RBAC，周天后台界面搭建好
////下周后台。
