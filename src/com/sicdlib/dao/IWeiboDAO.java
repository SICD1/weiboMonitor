package com.sicdlib.dao;

import com.sicdlib.dto.Weibo;
import edu.xjtsoft.base.orm.support.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by maninit on 2016/9/22.
 */
public interface IWeiboDAO {
    //获得分页获得微博信息
    Page<Weibo> getWeibosByKeywords(int pageNo,String kw_name,String kw_province,String kw_DateBegin,String kw_DateEnd);
}
