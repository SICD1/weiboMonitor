package com.sicdlib.util.CNSegment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

//实现两篇文档的相似性比较，返回相似度。
public class CosineSimilary {
	
	public static Float getMatrix(HashMap<String, Float> oneDoc, HashMap<String, Float> twoDoc){
		
//		HashMap<String, Float> twoInOne = new HashMap<String, Float>();
//		HashMap<String, Float> twoInOne_oneDoc = new HashMap<String, Float>();
//		HashMap<String, Float> twoInOne_twoDoc = new HashMap<String, Float>();
//		
//		//首先将两个文档中的单词进行合并为twoInOne,设置值为0
//		Iterator iter_oneDoc = oneDoc.entrySet().iterator();
//		while (iter_oneDoc.hasNext()) {
//			Entry entry_oneDoc = (Entry) iter_oneDoc.next();
//			String word = entry_oneDoc.getKey().toString();
//			Float value = Float.valueOf(entry_oneDoc.getValue().toString());
//			twoInOne.put(word, (float) 0.0);
//		}
//		
//		Iterator iter_twoDoc = twoDoc.entrySet().iterator();
//		while (iter_twoDoc.hasNext()) {
//			Entry entry_twoDoc = (Entry) iter_twoDoc.next();
//			String word = entry_twoDoc.getKey().toString();
//			Float value = Float.valueOf(entry_twoDoc.getValue().toString());
//			twoInOne.put(word, (float) 0.0);
//		}		
//		//将第一个文档oneDoc数据扩充为twoInOne大小，并且将数据传递给twoInOne_oneDoc
		
		HashMap<String, Float> twoInOne_one = new HashMap<String, Float>();
		HashMap<String, Float> twoInOne_two = new HashMap<String, Float>();
		
		Iterator iter_one = oneDoc.entrySet().iterator();
		while(iter_one.hasNext()){
			Entry entry_one = (Entry) iter_one.next();
			String word = entry_one.getKey().toString();
			Float value = Float.valueOf(entry_one.getValue().toString()) * 100;
			//将oneDoc中的数据放入twoInOne_one中
			twoInOne_one.put(word, value);
			
			if(twoInOne_two.get(word) == null){
				twoInOne_two.put(word, (float) 0.0);
			}
		}
		
		Iterator iter_two = twoDoc.entrySet().iterator();
		while(iter_two.hasNext()){
			Entry entry_two = (Entry) iter_two.next();
			String word = entry_two.getKey().toString();
			//将所有的TFIDF * 100，减小精度误差
			Float value = Float.valueOf(entry_two.getValue().toString()) * 100;
			if (twoInOne_one.get(word) == null) {
				twoInOne_one.put(word, (float) 0.0);
			}
			twoInOne_two.put(word, value);
		}
		
//		System.out.println("两个文档合一:");
//		Iterator iter_test = twoInOne_one.entrySet().iterator();
//		while(iter_test.hasNext()){
//			Entry entry_test = (Entry) iter_test.next();
//			System.out.println(entry_test.getKey()+":"+entry_test.getValue());
//		}
//		
//		System.out.println("两个文档合一2:");
//		Iterator iter_test2 = twoInOne_two.entrySet().iterator();
//		while(iter_test2.hasNext()){
//			Entry entry_test = (Entry) iter_test2.next();
//			System.out.println(entry_test.getKey()+":"+entry_test.getValue());
//		}
		
		//计算Cosine
		Iterator iter_twoinone_one = twoInOne_one.entrySet().iterator();
		Float result_twoDoc_cos = (float) 0.0;
		//分子乘积之和
		Float product_twoword = (float) 0.0;
		//oneDoc的单词权重平方和
		Float sqrt_value_one = (float) 0.0;
		//twoDoc的单词平方和
		Float sqrt_value_two = (float) 0.0;
		while(iter_twoinone_one.hasNext()){
			Entry entry_twoinone_one = (Entry) iter_twoinone_one.next();
			String word = entry_twoinone_one.getKey().toString();
			Float value_one = Float.valueOf(entry_twoinone_one.getValue().toString());
			if(twoInOne_two.get(word) != null){
				Float value_two = twoInOne_two.get(word);
				product_twoword += value_one * value_two;
			}
			sqrt_value_one += value_one * value_one;
			sqrt_value_two += twoInOne_two.get(word) * twoInOne_two.get(word);
		}
		//cosA = ((Xi * Yi)求和) / (sqrt(X*X) * sqrt(Y * Y))
		if(sqrt_value_one > 0 && sqrt_value_two > 0){
			result_twoDoc_cos = (float) (product_twoword 
					/ (Math.sqrt(sqrt_value_one) * Math.sqrt(sqrt_value_two)));
		}else{
			result_twoDoc_cos = (float) 0.0;
		}
		
//		System.out.println("结果：乘积："+product_twoword+"平方1:"+sqrt_value_one+"平方2：:"+sqrt_value_two
//				+"cos结果:"+result_twoDoc_cos);
		return result_twoDoc_cos;
	}
}
