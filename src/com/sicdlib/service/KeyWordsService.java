package com.sicdlib.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sicdlib.entity.KeyWords;

import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import edu.xjtsoft.base.service.DefaultEntityManager;

@Service
@Transactional
public class KeyWordsService extends DefaultEntityManager<KeyWords> {
	
	//根据数量、省份（全部）、时间(2016-07-24)
	public List<KeyWords> getTopKeyWords(int num, String kw_province, String kw_Date){
		//select * from tb_keywords order by kw_hotDegree desc limit 6
		String hql ="";
		hql = "select k from KeyWords k where k.kw_province = '"+kw_province+"' and k.kw_Date = '"+kw_Date+"' order by k.kw_hotDegree desc limit "+num;
		List<KeyWords> keyWords = (List<KeyWords>) getEntityDao().find(hql);
		
//		Criterion criterions;
//		getEntityDao().findByCriteria(page, criterions);
		
		System.out.println("相关关键词个数："+keyWords.size());
		return keyWords;
	}
	
	//根据时间、关键词名获取 省份、热度等信息
	public List<KeyWords> getSpecificKeyWords(String kw_name, String kw_date){
		String hql = "select k from KeyWords k where k.kw_name = ? and k.kw_Date = ? order by k.kw_hotDegree asc";
		Object []values = new Object[2];
		values[0] = kw_name;
		values[1] = kw_date;
		List<KeyWords> keyWords =  getEntityDao().find(hql, values);
		System.out.println(keyWords.size());
		return keyWords;
	}
	
	//根据 时间、关键词名 获取 省份、热度 等信息
	public List<KeyWords> getkeyWordsbyNameProDate(String kw_name, String kw_province, String kw_DateBegin,String kw_DateEnd){
		String hql = "";
		List<KeyWords> keywords = null;
		System.out.println("000");
		if (kw_province !=null && kw_DateBegin != null && kw_DateEnd != null) {
			if (kw_province.equals("")&&kw_DateBegin.equals("")&&kw_DateEnd.equals("")) {
				hql = "select k from KeyWords k where k.kw_name = ? ";
				keywords = getEntityDao().find(hql, kw_name);
				System.out.println("111");
			}
			if(kw_province.equals("")){
				hql = "select k from KeyWords k where k.kw_name = ? and k.kw_Date >= ? and k.kw_Date <= ?";
				Object[] values = new Object[3];
				values[0] = kw_name;
				values[1] = kw_DateBegin;
				values[2] = kw_DateEnd;
				keywords = getEntityDao().find(hql, values);
				System.out.println("222");
			}
			else{
				hql = "select k from KeyWords k where k.kw_name = ? and k.kw_province = ? and k.kw_Date >= ? and k.kw_Date <= ?";
				Object[] values = new Object[4];
				values[0] = kw_name;
				values[1] = kw_province;
				values[2] = kw_DateBegin;
				values[3] = kw_DateEnd;
				keywords = getEntityDao().find(hql, values);
				System.out.println("333");
			}
			
		}
		return keywords;
	}
	
//	//获得分页获得微博信息
//	public Page<KeyWords> getKeywords(int pageNo, 
//			String kw_name, String kw_province, String kw_DateBegin,String kw_DateEnd){
//		Page<KeyWords> page = new Page<KeyWords>(10);
//		page.setPageNo(pageNo);
//		String hql = "select k from KeyWords k, Weibo w where w.keyWords.kw_id = k.kw_id and k.kw_name = ? and k.kw_province like ? and k.kw_Date >= ? and k.kw_Date <= ?";
//		Object[] values = new Object[4];
//		values[0] = kw_name;
//		values[1] = kw_province;
//		values[2] = kw_DateBegin;
//		values[3] = kw_DateEnd;
//		Page<KeyWords> page_keywords = getEntityDao().find(page, hql, values);
//		return page_keywords;
//	}
	
	

	/**
	 * 
	 * @param keyWordsSearch
	 * @return
	 * @author shashajie
	 */
	//根据关键词名称、关键词省份进行搜索(得到全部)
	public List<KeyWords>  getkeyWordsSearch(String keyWordsSearch){
		String hql="select k from KeyWords k where k.kw_name = ?  or k.kw_province = ?";
		Object[] values=new Object[2];
		values[0] = keyWordsSearch;
		values[1] = keyWordsSearch;
		List<KeyWords> keyWords = getEntityDao().find(hql, values);
		return keyWords;
	}

	/**
	 * 
	 * @param keyWordsSearch
	 * @return
	 * @author shashajie
	 */
	//根据关键词名称、关键词省份进行搜索(通过分页)
	public Page<KeyWords> getkeyWordsSearchByPage(int pageNo, String keyWordsSearch){
//		String hql="select k from KeyWords k where k.kw_name = ?  or k.kw_province = ?";
		
		Page<KeyWords> page = new Page<KeyWords>(20);
		page.setPageNo(pageNo);
		PropertyFilter filters = new PropertyFilter("kw_name|kw_province", keyWordsSearch);
		Page<KeyWords> pages = getEntityDao().findByFilters(page, filters);
		
		return pages;
	}
	
}
