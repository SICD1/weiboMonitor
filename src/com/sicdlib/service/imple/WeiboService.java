package com.sicdlib.service.imple;

import com.sicdlib.dao.imple.WeiboDAO;
import com.sicdlib.dto.Weibo;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by maninit on 2016/9/20.
 */
@Service("")
@Transactional
public class WeiboService extends DefaultEntityManager<Weibo> {
    @Autowired
    public WeiboDAO weiboDAO;

    public Page<Weibo> getWeibosByKeywords(int pageNo, String kw_name, String kw_province, String kw_DateBegin, String kw_DateEnd){
        return    weiboDAO.getWeibosByKeywords(pageNo, kw_name, kw_province, kw_DateBegin, kw_DateEnd);
    }
}

