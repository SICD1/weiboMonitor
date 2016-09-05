package com.sicdlib.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sicdlib.entity.Weibo;

import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.service.DefaultEntityManager;

@Service
@Transactional
public class WeiboService extends DefaultEntityManager<Weibo> {
	
	//获得分页获得微博信息
	public Page<Weibo> getWeibosByKeywords(int pageNo, 
			String kw_name, String kw_province, String kw_DateBegin,String kw_DateEnd){
		Page<Weibo> page = new Page<Weibo>(10);
		page.setPageNo(pageNo);
		String hql = "select w from KeyWords k, Weibo w where w.keyWords.kw_id = k.kw_id and k.kw_name = ? and k.kw_province like ? and k.kw_Date >= ? and k.kw_Date <= ?";
		Object[] values = new Object[4];
		values[0] = kw_name;
		values[1] = kw_province;
		values[2] = kw_DateBegin;
		values[3] = kw_DateEnd;
		Page<Weibo> page_keywords = getEntityDao().find(page, hql, values);
		return page_keywords;
	}
	
}
