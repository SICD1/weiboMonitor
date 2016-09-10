//package com.sicdlib.util.CNSegment;
//
//import java.io.IOException;
//import java.io.StringReader;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//import java.util.Map.Entry;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.wltea.analyzer.core.IKSegmenter;
//import org.wltea.analyzer.core.Lexeme;
//
//import com.sicdlib.entity.Corpus_StopWords;
//import com.sicdlib.entity.Corpus_WordList;
//import com.sicdlib.entity.EssaySimilarityDegree;
//import com.sicdlib.service.Corpus_StopWordsService;
//import com.sicdlib.service.Corpus_WordListService;
//
///**
// *
// * 中文分词 && TFIDF工具类 && CosineSimilary余弦相似性
// * 以下工作实现了数据库中相关数据文章相似性的排名，两两文章的相似性排名
// *
// * *********接下来实现，对于特定的微博从数据库中选取相似性最高的前5篇文章********
// * @author
// *
// */
//
//public class CNSegment {
//
//	//获得停用词表
//	private static List<String> stopwordsNames = getStopWords();
//
//	public List<String> getStopwordsNames() {
//		return stopwordsNames;
//	}
//
//	/**
//	 * 【0】 获取停用词：
//	 * 停用词存放位置：Corpus_StopWords类
//	 * @returns topwordsNames 获取停用词集合
//	 */
//	public static List<String> getStopWords(){
//
//		//获取停用词：
//		ApplicationContext atx = new ClassPathXmlApplicationContext("beans.xml");
//		Corpus_StopWordsService stopwordsService = (Corpus_StopWordsService)
//				atx.getBean("corpus_StopWordsService");
//		List<String> stopwordsNames = new LinkedList<String>();
//		List<Corpus_StopWords> stopwords = stopwordsService.loadAll();
//
//		for (Corpus_StopWords stopword : stopwords) {
//			stopwordsNames.add(stopword.getCsd_word());
//		}
//
//		return stopwordsNames;
//	}
//
//	/**
//	 * 【1】 中文分词( 传入一篇文档，通过【0】去除停用词，进行中文分词  )
//	 * @param text			传入一篇文档
//	 * @return words		获得一篇文档分词对象（已经去除停用词）
//	 * @throws IOException	iksegmenter.next()输出的异常
//	 */
//	public static ArrayList<String> getCutWords(String text) throws IOException{
//
//		//分词结果存储在words中
//		ArrayList<String> words = new ArrayList<String>();
//		//创建分词对象
//		StringReader strReader = new StringReader(text);
//		IKSegmenter iksegmenter = new IKSegmenter(strReader, false);
//		Lexeme lex = null;
//
//		while((lex = iksegmenter.next()) != null){
//			//去除停用词
//			if (stopwordsNames.contains(lex.getLexemeText())) {
//				continue;
//			}else{
//
//				words.add(lex.getLexemeText());
//			}
//		}
////		//中文分词库工具
////		IKAnalyzer analyzer = new IKAnalyzer();
////		words = analyzer.split(text);
//
//		return words;
//	}
//
//	/**
//	 *
//	 * 【2】 输入一篇文档中的分词列表，通过【1】实现, 统计该文档的分词出现的次数（times）。
//	 * @param words 传入一个文档的分词列表，通过【1】实现
//	 * @return 		返回一个文档的HashMap<分词,次数>。
//	 */
//	public static HashMap<String, Integer> normalOneTFTimes(ArrayList<String> words){
//
//		HashMap<String, Integer> resTF = new HashMap<String,Integer>();
//
//		for (String word : words) {
//			//如果是新词，resTF中不存在该词，设该词为1
//			if(resTF.get(word) == null){
//				resTF.put(word, 1);
//				//System.out.println(word);
//			}else{
//				//如果该词已经存在，+1
//				resTF.put(word, resTF.get(word)+1);
//
//			}
//		}
//		/*Iterator itertest = resTF.entrySet().iterator();
//		while(itertest.hasNext()){
//			Entry entrytest = (Entry) itertest.next();
//			System.out.println(entrytest.getKey()+" : "+entrytest.getValue());
//		}*/
//
//		return resTF;
//	}
//
//	/**
//	 *
//	 * 【3】 计算一个文档的TF（词频）, 输入分词列表，首先通过【2】计算词出现次数
//	 * @param words	通过【2】实现词出现的次数
//	 * @return		返回一个文档的TF（词频）
//	 */
//	//ONE Text TF: 一个文件中词频，每个单词的词频(Frequency)
//	public static HashMap<String, Float> oneTF(ArrayList<String> words){
//
//		HashMap<String, Float> resTF = new HashMap<String, Float>();
//		int wordsLen = words.size();
//		HashMap<String, Integer> intTF = normalOneTFTimes(words);
//
//		Iterator iterator = intTF.entrySet().iterator();
//		while (iterator.hasNext()) {
//			Entry entry = (Entry) iterator.next();
//			Float tfValue = (float) (Integer.parseInt(entry.getValue().toString())*1.0 / wordsLen);
//			resTF.put(entry.getKey().toString(), tfValue);
//		}
//
//		return resTF;
//	}
//
//	/**
//	 *
//	 * 【4】 输出所有文档中的词次数
//	 * @param allText		输入所有文档集合
//	 * @return				输出HashMap<某篇文档,HashMap<分词,词出现次数>>集合
//	 * @throws IOException
//	 */
//	//ALL Text Times: 在所有文件中的每个单词出现的次数（times）
//	public static HashMap<String,
//			HashMap<String,Integer>> normalAllTF(List<String> allText) throws IOException{
//
//		HashMap<String, HashMap<String, Integer>> allnormalTF
//			= new HashMap<String, HashMap<String, Integer>>();
//
//		//oneText表一个文本,allText为全部文本.words为获得一个文本的中文分词,oneTF为获得一个文本的词数。
//		for (String oneText : allText) {
//			HashMap<String, Integer> oneTFTimes = new HashMap<String, Integer>();
//			ArrayList<String> words = getCutWords(oneText);
//			oneTFTimes = normalOneTFTimes(words);
//
//			//将单个文本和分词次数存在allnormalTF中
//			allnormalTF.put(oneText, oneTFTimes);
//		}
//
//		return allnormalTF;
//	}
//
//	/**
//	 *
//	 * 【5】 计算所有文档的TF（词频）
//	 * @param allText		输入的为所有文档
//	 * @return				输出的是所有文档的TF：HashMap<某篇文档, HashMap<分词, 词频>>
//	 * @throws IOException
//	 */
//	//ALL Text TF: 所有文件词频，包括每个文本中的每个单词的词频(Frequency)集合
//	public static HashMap<String, HashMap<String, Float>> allTF(List<String> allText) throws IOException{
//
//		HashMap<String, HashMap<String, Float>> allTextTF = new HashMap<String, HashMap<String, Float>>();
//		System.out.println(allText.size());
//		for (String oneText : allText) {
//			//得到一个文本的中文分词
//			ArrayList<String> words = getCutWords(oneText);
//			HashMap<String, Float> oneTextTF = oneTF(words);
//			allTextTF.put(oneText, oneTextTF);
//		}
//
//		System.out.println(allTextTF.size());
//
//		return allTextTF;
//	}
//
//	/**
//	 *
//	 * 【6】 计算所有文档的IDF(逆文档词频)
//	 * @param allTextTF		输入所有文档的词频，通过【5】获得
//	 * @return				输出所有文档的逆文档词频IDF
//	 * @throws IOException
//	 */
//	//IDF:逆文档频率,idf = log(文档总数/包含词语 ti 的文档总数)
//	public static HashMap<String, Float> IDF(HashMap<String,
//			HashMap<String, Float>> allTextTF) throws IOException{
//
//		HashMap<String, Float> resIDF = new HashMap<String, Float>();
//
//		//containWordsTextTimes表示词语ti在全部文档中出现次数。
//		HashMap<String, Integer> containWordsTextTimes = new HashMap<String, Integer>();
//
//		int allTextCount = allTextTF.size();
//		Iterator iter_alltext = allTextTF.entrySet().iterator();
//		while (iter_alltext.hasNext()) {
//			Entry entry_alltext = (Entry) iter_alltext.next();
//			String oneText = entry_alltext.getKey().toString();
//			HashMap<String, Float> tempOneTF = (HashMap<String, Float>) entry_alltext.getValue();
//			Iterator iter_tempone = tempOneTF.entrySet().iterator();
//			//获得一个文档中是否包含containWordsTextTimes中的单词
//			while (iter_tempone.hasNext()) {
//				Entry entry_tempone = (Entry) iter_tempone.next();
//				String word = entry_tempone.getKey().toString();
//				if (containWordsTextTimes.get(word) == null) {
//					containWordsTextTimes.put(word, 1);
//				}else{
//					containWordsTextTimes.put(word,
//						containWordsTextTimes.get(word) + 1);
//				}
//			}
//
//		}
//
//		//System.out.println("每个词语的IDF:");
//		Iterator iterator2 = containWordsTextTimes.entrySet().iterator();
//		while (iterator2.hasNext()) {
//			Entry entry = (Entry) iterator2.next();
//			//计算IDF：文档总数/包含词Word的总词数
//			float value = (float) Math.log(allTextCount
//					/ Float.parseFloat(entry.getValue().toString()));
//			resIDF.put((String) entry.getKey(), value);
//		}
//
//		return resIDF;
//	}
//
//	/**
//	 *
//	 * 获取在线语料库词频（在本项目中暂时不用）
//	 * @return		输出获得分词权重
//	 */
//	//IDF:2、通过在线现代语料库词频数据获得分词权重
//	public static HashMap<String, Float> onlineModernCorpusIDF(){
//
//		HashMap<String, Float> onlinecorpusIDF = new HashMap<String, Float>();
//		ApplicationContext atx = new ClassPathXmlApplicationContext("beans.xml");
//		Corpus_WordListService wordslistService = (Corpus_WordListService)
//				atx.getBean("corpus_WordListService");
//		List<Corpus_WordList> corpus_wordlist = wordslistService.loadAll();
//		for (int i = 0; i < corpus_wordlist.size(); i++) {
//			String word = corpus_wordlist.get(i).getCwl_word();
//			Double val_accum_freq = corpus_wordlist.get(i).getCwl_accumFrequency();
//			Float float_val_accum_freq = Float.valueOf(val_accum_freq.toString());
//			onlinecorpusIDF.put(word, float_val_accum_freq);
//		}
//		Iterator iter = onlinecorpusIDF.entrySet().iterator();
//		while(iter.hasNext()){
//			Entry entry = (Entry) iter.next();
//			//System.out.println(entry.getKey()+":"+entry.getValue());
//		}
//
//		return onlinecorpusIDF;
//	}
//
//	/**
//	 *
//	 * 【7】 输入所有的文档列表和所有文档的IDFs(通过【6】获得), 输出所有文档的TFIDF（词频/逆文档词频）
//	 * TFIDF = TF * IDF
//	 * @param allText		输入所有文档列表
//	 * @param idfs			所有文档的IDFs
//	 * @return				返回所有文档的TFIDF
//	 * @throws IOException
//	 */
//	public static HashMap<String, HashMap<String, Float>> TF_IDF(List<String> allText,
//			HashMap<String, Float> idfs) throws IOException{
//
//		HashMap<String, HashMap<String, Float>> resTFIDF = new HashMap<String, HashMap<String,Float>>();
//
//		//得到AllTF:
//		HashMap<String, HashMap<String, Float>> allTextTF = allTF(allText);
//		//得到AllIDF:
//		HashMap<String, Float> allTextIDF =  idfs;
//
//		Iterator iter_alltexttf = allTextTF.entrySet().iterator();
//		while (iter_alltexttf.hasNext()) {
//			HashMap<String, Float> tfidf = new HashMap<String, Float>();
//			Entry entry_alltexttf = (Entry) iter_alltexttf.next();
//			String oneText = entry_alltexttf.getKey().toString();
//			HashMap<String, Float> oneTextTF = (HashMap<String, Float>) entry_alltexttf.getValue();
//			//遍历一篇文章中的单词,得到TFIDF,存入tfidf中
//			Iterator iter_onetexttf = oneTextTF.entrySet().iterator();
//			while (iter_onetexttf.hasNext()) {
//				Entry entry = (Entry) iter_onetexttf.next();
//				String word = entry.getKey().toString();
//				Float word_tf = Float.valueOf(entry.getValue().toString());
//				//System.out.println(word+":"+word_tf);
//				Float value = word_tf * Float.valueOf(allTextIDF.get(word).toString());
//				tfidf.put(word, value);
//				//System.out.println(value);
//			}
//			resTFIDF.put(oneText, tfidf);
//		}
//
//		//System.out.println("每个文档的TF/IDF是：");
//		//显示结果:
//		//DisplayTFIDF(resTFIDF);
//
//		return resTFIDF;
//	}
//
//	/**
//	 *
//	 * 【8】 显示打印【7】中计算的TFIDF
//	 * @param tfidf		输入【7】中获得的TFIDF
//	 */
//	public static void DisplayTFIDF(HashMap<String, HashMap<String, Float>> tfidf){
//
//		Iterator iterator = tfidf.entrySet().iterator();
//		int i = 0;
//		while (iterator.hasNext()) {
//			i++;
//			Entry entry = (Entry) iterator.next();
//			System.out.println("文档"+i+":"+entry.getKey().toString());
//			System.out.print("【");
//			HashMap<String, Float> temp = (HashMap<String, Float>) entry.getValue();
//			Iterator itertor2 = temp.entrySet().iterator();
//			while (itertor2.hasNext()) {
//				Entry entry2 = (Entry) itertor2.next();
//				System.out.print(entry2.getKey().toString() + ":" +
//					entry2.getValue().toString()+"\t");
//			}
//			System.out.print("】");
//			System.out.println("\n");
//		}
//
//	}
//
//	/**
//	 *
//	 * 【9】 输入【7】中获得的所有文档的TFIDF，输出频率排名前20位的关键词
//	 * @param AllTFIDF	输入所有文档的TFIDF
//	 * @return			输出所有文档中排名前20位的关键词TreeMap
//	 */
//	public static TreeMap<String, TreeMap<String, Float>> sortOfTopAllTFIDF(HashMap<String,
//			HashMap<String, Float>> AllTFIDF){
//
//		TreeMap<String, TreeMap<String, Float>> sortedAlltfidf = new TreeMap<String, TreeMap<String, Float>>();
//		Iterator iter_alltfidf = AllTFIDF.entrySet().iterator();
//		while(iter_alltfidf.hasNext()){
//			Entry entry_alltfidf = (Entry) iter_alltfidf.next();
//			String oneText = entry_alltfidf.getKey().toString();
//			HashMap<String, Float> onetfidf = (HashMap<String, Float>) entry_alltfidf.getValue();
////			//对一个文本进行排序
////			ByValueComparator byValueComparator = new ByValueComparator(onetfidf);
////			TreeMap<String, Float> sortedmap = new TreeMap<String, Float>(byValueComparator);
////			//获取全部
////			sortedmap.putAll(onetfidf);
//
//			List<Map.Entry<String, Float>> list_onetfidf = new ArrayList<Map.Entry<String, Float>>(onetfidf.entrySet());
//			Collections.sort(list_onetfidf, new Comparator<Map.Entry<String, Float>>(){
//
//				@Override
//				public int compare(Entry<String, Float> o1,
//						Entry<String, Float> o2) {
//					//降序排序
//					return o2.getValue().compareTo(o1.getValue());
//				}
//
//			});
//
////			for (int i = 0; i < list_onetfidf.size(); i++) {
////				System.out.println(list_onetfidf.get(i).getKey()+":"+list_onetfidf.get(i).getValue());
////			}
////			System.out.println("*****************************列出全部*****************************");
//
//
//			List<Map.Entry<String, Float>> list_toponetfidf = new ArrayList<Map.Entry<String, Float>>();
//			for (int i = 0; i < list_onetfidf.size(); i++) {
//				if(i < 20){
//					list_toponetfidf.add(i, list_onetfidf.get(i));
//				}
//			}
//
//			TreeMap<String, Float> top20sortedmap = new TreeMap<String, Float>();
//			for (int i = 0; i < list_toponetfidf.size(); i++) {
//				top20sortedmap.put(list_toponetfidf.get(i).getKey(), list_toponetfidf.get(i).getValue());
//			}
//
//			//获取前20条数据
//			sortedAlltfidf.put(oneText, top20sortedmap);
//
////			Iterator iter_test = top20sortedmap.entrySet().iterator();
////			while(iter_test.hasNext()){
////				Entry entry_test = (Entry) iter_test.next();
////				System.out.println(entry_test.getKey()+":"+entry_test.getValue());
////			}
////			System.out.println("*******************下一个*******************");
//		}
//
//
////		//display:展示排序后的TFIDF
////		Iterator iter_testalltfidf = sortedAlltfidf.entrySet().iterator();
////		int i = 0;
////		while (iter_testalltfidf.hasNext()) {
////			Entry entry_test = (Entry) iter_testalltfidf.next();
////			String testOneText = entry_test.getKey().toString();
////			System.out.println("文档"+(i++)+":"+testOneText);
////			System.out.println("分词排序:");
////			TreeMap<String, Float> testSortedWordsTfidf = (TreeMap<String, Float>) entry_test.getValue();
////			Iterator iter_testSortedwordsTfidf = testSortedWordsTfidf.entrySet().iterator();
////			while(iter_testSortedwordsTfidf.hasNext()){
////				Entry entry_testSortedwords = (Entry) iter_testSortedwordsTfidf.next();
////				String word = entry_testSortedwords.getKey().toString();
////				Float tfidfValue = Float.valueOf(entry_testSortedwords.getValue().toString());
////				System.out.print(word+":"+tfidfValue+"\t");
////			}
////			System.out.println("】");
////			System.out.println("\n");
////		}
//
//		return sortedAlltfidf;
//	}
//
//	/**
//	 *
//	 * 【10】 实现功能：将【9】的TreeMap转变为HashMap
//	 * @param sortedAlltfidf	输入【9】中获得的全部文档的排名前20位的关键词序列
//	 * @return					输出HashMap的TFIDF
//	 */
//	//将treeMap转变为HashMap,然后获取单个文本中关键字TFIDF值
//	public static HashMap<String, HashMap<String, Float>> getTopSomeSortedTFIDF(TreeMap<String,
//			TreeMap<String, Float>> sortedAlltfidf){
//
//		//首先将TreeMap转换为HashMap:hmp_sortedAlltfidf
//		HashMap<String, HashMap<String, Float>> hmp_sortedAlltfidf = new HashMap<String, HashMap<String, Float>>();
//
//		Iterator iter_sortedtfidf = sortedAlltfidf.entrySet().iterator();
//		System.out.println("sorted大小："+sortedAlltfidf.size()+":"+iter_sortedtfidf.hasNext());
//		while(iter_sortedtfidf.hasNext()){
//			Entry entry_sortedtfidf = (Entry) iter_sortedtfidf.next();
//			String oneText = entry_sortedtfidf.getKey().toString();
//			TreeMap<String, Float> onetfidf = (TreeMap<String, Float>) entry_sortedtfidf.getValue();
//			HashMap<String, Float> onetemp = new HashMap<String, Float>();
//
//			Iterator iter_onetfidf = onetfidf.entrySet().iterator();
//			while(iter_onetfidf.hasNext()){
//				Entry entry_onetfidf = (Entry) iter_onetfidf.next();
//				String word = entry_onetfidf.getKey().toString();
//				Float value = Float.valueOf(entry_onetfidf.getValue().toString());
////				System.out.println(word+":"+value);
//				onetemp.put(word, value);
//			}
////			System.out.println("*****************************列出排名前20关键词信息***************************");
//			hmp_sortedAlltfidf.put(oneText, onetemp);
//		}
//
//		return hmp_sortedAlltfidf;
//	}
//
//	/**
//	 *
//	 * 【11】计算所有文档的两两相似度，输入【10】中数据，输出
//	 * @param hmp_sortedAlltfidf	输入【10】中数据
//	 */
//	public static List<EssaySimilarityDegree> getAllTextConsineSimilary(HashMap<String, HashMap<String, Float>> hmp_sortedAlltfidf){
//
//		//将文本一一对应比较
//		List<EssaySimilarityDegree> essaySimiDegreeList = new LinkedList<EssaySimilarityDegree>();
//		//获得所有的文本集合(List<String> allText)
//		List<String> allText = new LinkedList<String>();
//		Iterator iter_hmpsortedtfidf = hmp_sortedAlltfidf.entrySet().iterator();
//		while(iter_hmpsortedtfidf.hasNext()){
//			Entry entry_hmpsortedtfidf = (Entry) iter_hmpsortedtfidf.next();
//			String oneText = entry_hmpsortedtfidf.getKey().toString();
//			allText.add(oneText);
//		}
//
//		for (int i = 0; i < allText.size(); i++) {
//			//通过文本内容获得分词关键词
//			HashMap<String, Float> text_i = hmp_sortedAlltfidf.get(allText.get(i));
//			for (int j = i+1; j < allText.size(); j++) {
//				HashMap<String, Float> text_j = hmp_sortedAlltfidf.get(allText.get(j));
//				Float cosineSim = CosineSimilary.getMatrix(text_i, text_j);
//				EssaySimilarityDegree essaysimidegree = new EssaySimilarityDegree();
//				//设置第一篇文档
//				essaysimidegree.setOneText(allText.get(i));
//				//设置第二篇文档
//				essaysimidegree.setTwoText(allText.get(j));
//				//设置相似度
//				essaysimidegree.setSimiDegree(cosineSim);
//				//将相似度对象essaysimidegree存入集合essaySimiDegreeList中
//				essaySimiDegreeList.add(essaysimidegree);
//			}
//		}
//
////		//展示所有文档相似度信息：
////		for (int i = 0; i < essaySimiDegreeList.size(); i++) {
////			System.out.println("【"+essaySimiDegreeList.get(i).getOneText());
////			System.out.println(essaySimiDegreeList.get(i).getTwoText());
////			System.out.println(essaySimiDegreeList.get(i).getSimiDegree()+"】");
////		}
//
//		return essaySimiDegreeList;
//	}
//
//	/**
//	 *
//	 * 测试文档与所有文档的相比较，输出前20个文档
//	 * @param test_text
//	 * @param hmp_sortedAlltfidf
//	 * @return
//	 */
//	//将测试文档与所有文档Cosine一一对比,test_text为了获得测试文档的TFIDF
//	public static List<EssaySimilarityDegree> getTopOneTextAndAllTextConsineSimilary(String test_text,
//			HashMap<String, HashMap<String, Float>> hmp_sortedAlltfidf){
//
//		List<EssaySimilarityDegree> essaySimiDegreeList = new LinkedList<EssaySimilarityDegree>();
//		//获得所有的文本集合(List<String> allText)
//		List<String> allText = new LinkedList<String>();
//		Iterator iter_hmpsortedtfidf = hmp_sortedAlltfidf.entrySet().iterator();
//		while(iter_hmpsortedtfidf.hasNext()){
//			Entry entry_hmpsortedtfidf = (Entry) iter_hmpsortedtfidf.next();
//			String oneText = entry_hmpsortedtfidf.getKey().toString();
//			allText.add(oneText);
//		}
//
////		//展示显示所有文档+测试文档内容
////		for (int i = 0; i < allText.size(); i++) {
////			System.out.println("文档:"+i+":\t"+allText.get(i));
////		}
//
//		HashMap<String, Float> test_map = new HashMap<String, Float>();
//		//获得测试文档的与数据库所有文档TFIDF相似性
//		test_map = hmp_sortedAlltfidf.get(test_text);
//		for (int i = 0; i < allText.size(); i++) {
//			EssaySimilarityDegree essaysimi = new EssaySimilarityDegree();
//			HashMap<String, Float> text_i = hmp_sortedAlltfidf.get(allText.get(i));
//			//测试文档与所有文档相比较
//			Float cosineSim = CosineSimilary.getMatrix(test_map, text_i);
//			essaysimi.setOneText(test_text);
//			essaysimi.setTwoText(allText.get(i));
//			essaysimi.setSimiDegree(cosineSim);
//			essaySimiDegreeList.add(essaysimi);
//		}
//
//		return essaySimiDegreeList;
//	}
//
//	/**
//	 *
//	 *【12】对所有文档相似度集合进行排序，获取前20个文章
//	 * @param essaySimiDegreeList	输入所有文档相似度集合
//	 * @return						输出排序好的相似度集合
//	 */
//	public static List<EssaySimilarityDegree> getTop20sortedAllTextCosineSimilary(
//			List<EssaySimilarityDegree> essaySimiDegreeList){
//
//		EssaySimilarityDegree temp_essay = new EssaySimilarityDegree();
//		Collections.sort(essaySimiDegreeList, new Comparator<EssaySimilarityDegree>(){
//
//			@Override
//			public int compare(EssaySimilarityDegree essay1,
//					EssaySimilarityDegree essay2) {
//				//从大到小排序
//				if(essay1.getSimiDegree() > essay2.getSimiDegree()){
//					return -1;
//				}
//				if(essay1.getSimiDegree() < essay2.getSimiDegree()){
//					return 1;
//				}
//
//				return 0;
//			}
//
//		});
//
//		//选取前20个文章
//		List<EssaySimilarityDegree> essaySimiTopDegrees = new LinkedList<EssaySimilarityDegree>();
//		for (int i = 0; i < essaySimiDegreeList.size(); i++) {
//			if(i < 20){
//				essaySimiTopDegrees.add(essaySimiDegreeList.get(i));
//			}
//		}
//
////		//展示与显示：前20个与测试文本test_text相似的文档
////		for (int i = 0; i < essaySimiTopDegrees.size(); i++) {
////			EssaySimilarityDegree essaysimidegree = essaySimiTopDegrees.get(i);
////			System.out.println("测试文档*****："+essaysimidegree.getOneText());
////			System.out.println("相似文档*****："+essaysimidegree.getTwoText());
////			System.out.println("相似度****："+essaysimidegree.getSimiDegree());
////		}
//
//		return essaySimiTopDegrees;
//	}
//
//
//	//HashMap<String, Float>排序方法
//	static class ByValueComparator implements Comparator<String>{
//
//		HashMap<String, Float> baseMap;
//		public ByValueComparator(HashMap<String, Float> baseMap){
//			this.baseMap = baseMap;
//		}
//
//		@Override
//		public int compare(String o1, String o2) {
//			if(!baseMap.containsKey(o1)||!baseMap.containsKey(o2)){
//				return 0;
//			}
//			if (baseMap.get(o1) >= baseMap.get(o2)) {
//				return -1;
//			}
//			if(baseMap.get(o1) < baseMap.get(o2)){
//				return 1;
//			}
//			return 0;
//		}
//	}
//
//	//【13】将所有文档的前20位关键字组合在一起，形成整个文档库中所有关键词TFIDF集合，输入【10】中数据
//	public static HashMap<String, Float> getAllTextKeywords(HashMap<String,	HashMap<String, Float>> hmp_sortedAlltfidf){
//
//		HashMap<String, Float> allWordsTfidf = new HashMap<String, Float>();
//
//		Iterator iter_hmp_sorted = hmp_sortedAlltfidf.entrySet().iterator();
//		while(iter_hmp_sorted.hasNext()){
//			Entry entry_hmp_sorted = (Entry) iter_hmp_sorted.next();
//			HashMap<String, Float> oneTextTfidf = (HashMap<String, Float>) entry_hmp_sorted.getValue();
//			Iterator iter_oneTextTfidf = oneTextTfidf.entrySet().iterator();
//			while(iter_oneTextTfidf.hasNext()){
//				Entry entry_oneTextTfidf = (Entry) iter_oneTextTfidf.next();
//				String one_word = entry_oneTextTfidf.getKey().toString();
//				Float one_word_value = Float.valueOf(entry_oneTextTfidf.getValue().toString());
//				if(allWordsTfidf.get(one_word) != null){
//					//如果该关键词已经存在，那么将该词的权重加上原来的HashMap中该词的权重
//					Float accuAddValue = Float.valueOf(allWordsTfidf.get(one_word).toString())+one_word_value;
//					allWordsTfidf.put(one_word, accuAddValue);
//				}
//				else{
//					allWordsTfidf.put(one_word, one_word_value);
//				}
//			}
//		}
//
////		//展示与显示：所有文档中前20个关键词集合display
////		Iterator iter_test_allwords = allWordsTfidf.entrySet().iterator();
////		while(iter_test_allwords.hasNext()){
////			Entry entry_test_allwords = (Entry) iter_test_allwords.next();
////			System.out.println(entry_test_allwords.getKey()+":"+entry_test_allwords.getValue());
////		}
////		System.out.println("所有关键字的集合大小："+allWordsTfidf.size());
//
//		return allWordsTfidf;
//	}
//
//	//【14】获得整个文档库中所有关键词排名前50位的关键词,输入数据从【13】获取，并没有排序好
//	public static HashMap<String, Float> getTopTextKeywords(HashMap<String, Float> allWordsTfidf){
//
//		//对关键词降序排序
//		List<Map.Entry<String, Float>> list_allwords
//			= new LinkedList<Map.Entry<String, Float>>(allWordsTfidf.entrySet());
//		Collections.sort(list_allwords, new Comparator<Map.Entry<String, Float>>(){
//
//			@Override
//			public int compare(Entry<String, Float> o1, Entry<String, Float> o2) {
//				//降序排序
//				return o2.getValue().compareTo(o1.getValue());
//			}
//		});
//
////		//展示与显示：降序排序全部关键词
////		for (int i = 0; i < list_allwords.size(); i++) {
////			System.out.println(list_allwords.get(i).getKey()+":"+list_allwords.get(i).getValue());
////		}
//
//		//将List转换为HashMap，取出前50个关键词
//		HashMap<String, Float> topKeywords = new HashMap<String, Float>();
//		for (int i = 0; i < list_allwords.size(); i++) {
//			if(i < 50){
//				String word = list_allwords.get(i).getKey();
//				Float word_value = list_allwords.get(i).getValue();
//				topKeywords.put(word, word_value);
//			}
//			else{
//				break;
//			}
//		}
//
////		//展示与显示：前50个关键词信息
////		Iterator iter_test = allKeywords.entrySet().iterator();
////		while(iter_test.hasNext()){
////			Entry entry_test = (Entry) iter_test.next();
////			System.out.println(entry_test.getKey()+":"+entry_test.getValue());
////		}
//
//		return topKeywords;
//	}
//
//	//【15】将前50个关键词和所有文档的TFIDF，放入全部文档中，找到相关文章(不统计热度)
//	public static List<String> getRelativeEssaysKeywords(HashMap<String, Float> topKeywords,
//			HashMap<String, HashMap<String, Float>> hmp_sortedAlltfidf){
//
//		List<String> relativeEssays = new LinkedList<String>();
//		//遍历所有的文档
//		Iterator iter_hmp_sorted = hmp_sortedAlltfidf.entrySet().iterator();
//		while(iter_hmp_sorted.hasNext()){
//			Entry entry_hmp_sorted = (Entry) iter_hmp_sorted.next();
//			String oneText = entry_hmp_sorted.getKey().toString();
//			HashMap<String, Float> onetext_tfidf = (HashMap<String, Float>) entry_hmp_sorted.getValue();
//
//			int flag = 0;
//			//前50个关键词与文档中所有关键词比较
//			Iterator iter_topkeywords = topKeywords.entrySet().iterator();
//			while(iter_topkeywords.hasNext()){
//				Entry entry_topkeywords = (Entry) iter_topkeywords.next();
//				String word = entry_topkeywords.getKey().toString();
//				if(onetext_tfidf.get(word) != null){
//					flag = 1;
//					break;
//				}
//			}
//			if(flag == 1){
//				relativeEssays.add(oneText);
//			}
//		}
//
////		//展示与显示：相关文档展示
////		for (int i = 0; i < relativeEssays.size(); i++) {
////			System.out.println("相关文档"+i+":\t"+relativeEssays.get(i));
////		}
//
//		return relativeEssays;
//	}
//
//	//【16】得到前5个关键词。输入通过【15】得到的相关文档和【10】得到的全部文档的排名前20位的关键词序列
//	public static HashMap<String, HashMap<String, Float>> getTopKeywordsByRelativeEssays(List<String> relativeEssays,
//			HashMap<String, HashMap<String, Float>> hmp_sortedAlltfidf){
//
//		HashMap<String,	HashMap<String, Float>> topKeywords
//			= new HashMap<String, HashMap<String, Float>>();
//
//		//得到相关关键词前20
//		for (int i = 0; i < relativeEssays.size(); i++) {
//			if(hmp_sortedAlltfidf.get(relativeEssays.get(i)) != null){
//				topKeywords.put(relativeEssays.get(i), hmp_sortedAlltfidf.get(relativeEssays.get(i)));
//			}
//		}
//
//		//得到相关关键词前5
//		HashMap<String,	HashMap<String, Float>> top5Keywords
//		= new HashMap<String, HashMap<String, Float>>();
//		Iterator iter_hmp_sortedall = topKeywords.entrySet().iterator();
//		while(iter_hmp_sortedall.hasNext()){
//			Entry entry_hmp_sortedall = (Entry) iter_hmp_sortedall.next();
//			String oneText = entry_hmp_sortedall.getKey().toString();
//			HashMap<String, Float> oneTfidf = (HashMap<String, Float>) entry_hmp_sortedall.getValue();
//			List<Map.Entry<String, Float>> list_onetfidf
//				= new LinkedList<Map.Entry<String, Float>>(oneTfidf.entrySet());
//			Collections.sort(list_onetfidf, new Comparator<Map.Entry<String, Float>>(){
//
//				@Override
//				public int compare(Entry<String, Float> o1,
//						Entry<String, Float> o2) {
//					return o2.getValue().compareTo(o1.getValue());
//				}
//
//			});
//
//			HashMap<String, Float> top5onetfidf = new HashMap<String, Float>();
//			for (int i = 0; i < list_onetfidf.size(); i++) {
//				if(i < 5){
//					top5onetfidf.put(list_onetfidf.get(i).getKey(), list_onetfidf.get(i).getValue());
//				}
//			}
//			top5Keywords.put(oneText, top5onetfidf);
//		}
//
////		//展示与显示：相关关键词前5
////		Iterator iter_test_hmp_sortedall = top5Keywords.entrySet().iterator();
////		while(iter_test_hmp_sortedall.hasNext()){
////			Entry entry_hmp_sortedall = (Entry) iter_test_hmp_sortedall.next();
////			String oneText = entry_hmp_sortedall.getKey().toString();
////			HashMap<String, Float> oneTfidf = (HashMap<String, Float>) entry_hmp_sortedall.getValue();
////
////			Iterator iter_oneTfidf = oneTfidf.entrySet().iterator();
////			while(iter_oneTfidf.hasNext()){
////				Entry entry_oneTfidf = (Entry) iter_oneTfidf.next();
////				String word = entry_oneTfidf.getKey().toString();
////				Float word_value = Float.valueOf(entry_oneTfidf.getValue().toString());
////				System.out.println(word+":"+word_value);
////			}
////			System.out.println("*******************************************************");
////		}
//
//		return top5Keywords;
//	}
//
//	//【17】得到相关关键词字符串组合
//	public static void getKeywords(HashMap<String, HashMap<String, Float>> top5Keywords){
//
//		HashMap<String, String> topStrkeywords = new HashMap<String, String>();
//
//		Iterator iter_top5keyword = top5Keywords.entrySet().iterator();
//		while(iter_top5keyword.hasNext()){
//			Entry entry_top5keyword = (Entry) iter_top5keyword.next();
//			String oneText = entry_top5keyword.getKey().toString();
//			HashMap<String, Float> onetfidf = (HashMap<String, Float>) entry_top5keyword.getValue();
//			Iterator iter_onetfidf = onetfidf.entrySet().iterator();
//			String strword = "";
//			while(iter_onetfidf.hasNext()){
//				Entry entry_onetfidf = (Entry) iter_onetfidf.next();
//				String word = entry_onetfidf.getKey().toString();
//				strword += word+"/";
//			}
//			topStrkeywords.put(oneText, strword);
//		}
//
//		//展示与显示：显示所有关键词组
//		Iterator iter_test_strkeyword = topStrkeywords.entrySet().iterator();
//		while(iter_test_strkeyword.hasNext()){
//			Entry entry_test_str = (Entry) iter_test_strkeyword.next();
//			System.out.println("文章：\t"+entry_test_str.getKey());
//			System.out.println("关键词组：\t"+entry_test_str.getValue());
//			System.out.println();
//		}
//	}
//
//}
