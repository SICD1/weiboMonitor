package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.IWeiboDAO;
import com.sicdlib.dto.Weibo;
import edu.xjtsoft.base.orm.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by maninit on 2016/9/22.
 */
@Repository("WeiboDao")
public class WeiboDAO implements IWeiboDAO {
    @Autowired
    private IBaseDAO baseDAO;
    @Override
    public Page<Weibo> getWeibosByKeywords(int pageNo, String kw_name, String kw_province, String kw_DateBegin, String kw_DateEnd) {
        Page<Weibo> page = new Page<Weibo>(10);
        page.setPageNo(pageNo);
        String hql = "from KeyWords k, Weibo w where w.keyWords.kw_id = k.kw_id and k.kw_name = ? and k.kw_province like ? and k.kw_date >= ? and k.kw_date <= ?";
        Object[] values = new Object[4];
        values[0] = kw_name;
        values[1] = kw_province;
        values[2] = kw_DateBegin;
        values[3] = kw_DateEnd;
        Page<Weibo> page_keywords = (Page<Weibo>) baseDAO.find(page, hql, values);
        return page_keywords;
    }
}
