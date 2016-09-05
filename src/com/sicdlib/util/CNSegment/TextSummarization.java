package com.sicdlib.util.CNSegment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.sicdlib.entity.SentenceCluster;
import com.sicdlib.util.CNSegment.CNSegment.ByValueComparator;

/**
 * 文本自动摘要：
 * 原始文章-->【1】得到停用词-->【2】中文分词(去除停用词)-->【3】获取文章的关键词以及词出现的次数-->
 * 【4】文章关键词排序-->【5】获取排名前5的关键词-->【6】文章切分为句子(汉语中的句子切分主要包括：句号、逗号)-->
 *【7】输入切分句子、排名前5的关键词，输出包含关键词的句子-->
 *【8】计算整个文章的每个切分句子的簇权重，簇权重 = (包含的关键词)^2 / (该句子的簇的大小)-->
 *【9】选出排名前5的分值最高的句子，按照句子在文章中的先后顺序输出-->
 *【10】将得到的list集合变成句子形成摘要
 * @author xxy
 *
 */

public class TextSummarization {

	//【1】 -【5】文章分词,获取关键词，并对文章关键词排序,得到排名前5的关键词
	public static HashMap<String, Integer> EssayTopSortedCutWords(String originalText) throws IOException{
		
		//【1】.得到停用词
		List<String> stopwordsNames = CNSegment.getStopWords();
		//【2】.中文分词,在【1】中已经去除停用词
		ArrayList<String> words = CNSegment.getCutWords(originalText);
		//【3】.获取文章的关键词以及词出现次数
		HashMap<String, Integer> word = CNSegment.normalOneTFTimes(words);
		//【4】.文章关键词排序
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(word.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				//降序排序
				return o2.getValue().compareTo(o1.getValue());
			}		
		});
		
		//【5】.提取文章排名前5的关键词
		List<Map.Entry<String, Integer>> list_test = new ArrayList<Map.Entry<String, Integer>>();
		for (int i = 0; i < list.size(); i++) {
			if(i<5){
				list_test.add(i,list.get(i));
			}
		}
		//将List转变为HashMap
		HashMap<String, Integer> sortedhashmap = new HashMap<String, Integer>();
		for (int i = 0; i < list_test.size(); i++) {
			sortedhashmap.put(list_test.get(i).getKey(), list_test.get(i).getValue());
		}
		
//		Iterator iter_test = sortedhashmap.entrySet().iterator();
//		while(iter_test.hasNext()){
//			Entry entry_test = (Entry) iter_test.next();
//			System.out.println(entry_test.getKey()+":"+entry_test.getValue());
//		}
				
		return sortedhashmap;
	}
	
	//【6】.文章切分为句子(汉语中的句子切分主要包括：句号、逗号)
	public static String[] getSentences(String originalText){
		String regex = "，|。|,";
		String []sentences = originalText.split(regex);

//		//展示
//		for (int i = 0; i < sentences.length; i++) {
//			System.out.println(sentences[i]);
//		}
		
		return sentences;
	}
	
	//【7】.输入切分句子、排名前5的关键词，输出包含关键词的句子
	public static List<SentenceCluster> getSentencesContainsKeywords(String[] sentences, HashMap<String, Integer> sortedhashmap) throws IOException{
		List<SentenceCluster> sentenceClusters = new LinkedList<SentenceCluster>();
		for (int i = 0; i < sentences.length; i++) {
			SentenceCluster senCluster = new SentenceCluster();
			//得到其中一个句子
			String onesent = sentences[i];
			//得到这个句子的切分分词
			ArrayList<String> words = CNSegment.getCutWords(onesent);
			//得到这个句子的切分分词长度
			Integer senLength = words.size();
			//判断包含关键词数组以及个数
			String keywords = "";
			int kwordsLen = 0;
			for (int j = 0; j < words.size(); j++) {
				String senword = words.get(j);
				if(sortedhashmap.get(senword) != null){
					//将重复的关键词去掉
					if(!keywords.contains(senword)){
						keywords += senword+",";
					}
					kwordsLen ++;
				}
			}
//			System.out.println(onesent + ":" + senLength +":" + keywords +":" + kwordsLen);
			//设置句子顺序
			senCluster.setOrderID(i);	
			//设置句子簇的关键词
			senCluster.setKeywords(keywords);
			//设置句子簇的关键词个数
			senCluster.setKwordsLength(kwordsLen);
			//设置句子分词个数
			senCluster.setSenLength(senLength);
			//设置句子内容
			senCluster.setSentence(onesent);
			
			//下次循环归一清0
			keywords = "";
			kwordsLen = 0;
			
			//将该篇文章的所有切分句子的句子簇信息放入sentenceClusters中
			sentenceClusters.add(senCluster);
		}
		
		return sentenceClusters;
	}
	
	//【8】.计算整个文章的每个切分句子的簇权重，簇权重 = (包含的关键词)^2 / (该句子的簇的大小)
	public static List<SentenceCluster> getSenClusterIMPDegree(List<SentenceCluster> sentenceClusters){
		
		//计算句子簇
		for (int i = 0; i < sentenceClusters.size(); i++) {
			Float clusterDegreeValue = (float) 0.0;
			clusterDegreeValue = (float) ((sentenceClusters.get(i).getKwordsLength() 
					* sentenceClusters.get(i).getKwordsLength())*1.0/sentenceClusters.get(i).getSenLength() * 1.0);
			sentenceClusters.get(i).setSenIMPDegree(clusterDegreeValue);
//			sentenceIMPDegrees.put(sentenceClusters.get(i).getSentence(), clusterDegreeValue);
		}
		
//		//展示显示
//		Iterator iter_test = sentenceIMPDegrees.entrySet().iterator();
//		while(iter_test.hasNext()){
//			Entry entry_test = (Entry) iter_test.next();
//			System.out.println(entry_test.getKey()+":"+entry_test.getValue());
//		}
		
		return sentenceClusters;
	}
	
	//【9】.选出排名前5的分值最高的句子，按照句子在文章中的先后顺序输出
	public static List<SentenceCluster> getTopIMPSentenceByOrder(List<SentenceCluster> sentenceClusters){
		
		//首先根据IMPDegree降序排序
		Collections.sort(sentenceClusters, new Comparator<SentenceCluster>(){

			@Override
			public int compare(SentenceCluster o1, SentenceCluster o2) {
				//降序排序
				return o2.getSenIMPDegree().compareTo(o1.getSenIMPDegree());
			}
			
		});
		//得到排名前5的分值最高的句子
		List<SentenceCluster> topSentences = new LinkedList<SentenceCluster>();		
		for (int i = 0; i < sentenceClusters.size(); i++) {
			if(i < 5){
				topSentences.add(sentenceClusters.get(i));
			}
		}
		
		//按照句子顺序OrderID进行升序排序
		Collections.sort(topSentences, new Comparator<SentenceCluster>(){

			@Override
			public int compare(SentenceCluster o1, SentenceCluster o2) {
				//升序排序
				return o1.getOrderID().compareTo(o2.getOrderID());
			}
			
		});

//		//显示展示
//		for (int i = 0; i < topSentences.size(); i++) {
//			System.out.println(topSentences.get(i).getOrderID()
//					+":"+topSentences.get(i).getSentence()+":"+topSentences.get(i).getSenIMPDegree());
//		}
		
		return topSentences;
	}
	
	//将得到的句子形成摘要
	public static String getSummarization(List<SentenceCluster> topSentences){
		//摘要
		String summarization = "";
		for (int i = 0; i < topSentences.size(); i++) {
			
			if(i == topSentences.size()-1){
				summarization += topSentences.get(i).getSentence()+"。";
			}
			else{
				summarization += topSentences.get(i).getSentence()+"，";
			}
		}
		
		return summarization;
	}
	
	//HashMap<String, Integer>排序方法
	static class ByValueComparator implements Comparator<String>{
		
		HashMap<String, Integer> baseMap;
		public ByValueComparator(HashMap<String, Integer> baseMap){
			this.baseMap = baseMap;
		}
		
		@Override
		public int compare(String o1, String o2) {
			if(!baseMap.containsKey(o1)||!baseMap.containsKey(o2)){
				return 0;
			}
			if (baseMap.get(o1) >= baseMap.get(o2)) {
				return -1;
			}
			if(baseMap.get(o1) < baseMap.get(o2)){
				return 1;
			}
			return 0;
		}
	}
}
