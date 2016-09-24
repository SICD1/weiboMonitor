package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.IKeyWordsDAO;
import com.sicdlib.dto.KeyWords;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maninit on 2016/9/22.
 */
@Repository("keywordsDAO")
public class KeyWordsDAO extends DefaultEntityManager<KeyWords> implements IKeyWordsDAO {
    @Autowired
    private IBaseDAO baseDAO;
    //根据数量、省份（全部）、时间(2016-07-24)
    @Override
    public List<KeyWords> getTopKeyWords(int num, String kw_province, String kw_Date){
        //select * from tb_keywords order by kw_hotDegree desc limit 6
        //System.out.print("keywordsDao进俩了");
        String hql ="";
        hql = "select k from KeyWords k where k.kw_province = '"+kw_province+"' and k.kw_date = '"+kw_Date+"' order by k.kw_hotDegree desc";
        //System.out.println("相关关键词个数："+baseDAO.find(hql).size());
        List<KeyWords> keyWords = baseDAO.find(hql, num);
        //System.out.println("相关关键词个数："+keyWords.size());
        return keyWords;
    }

    //根据时间、关键词名获取 省份、热度等信息
    @Override
    public List<KeyWords> getSpecificKeyWords(String kw_name, String kw_date){
        String hql = "select k from KeyWords k where k.kw_name =:name and k.kw_Date =:date order by k.kw_hotDegree asc";
        Map<String, Object> params = new HashMap<>();
        params.put("name", kw_name);
        params.put("date", kw_date);
        List<KeyWords> keyWords = (List<KeyWords>) baseDAO.find(hql, params);
        //System.out.println(keyWords.size());
        return keyWords;
    }

    //根据 时间、关键词名 获取 省份、热度 等信息
    @Override
    public List<KeyWords> getkeyWordsbyNameProDate(String kw_name, String kw_province, String kw_DateBegin, String kw_DateEnd){
        String hql = "";
        List<KeyWords> keywords = null;
        System.out.println("000");
        if (kw_province !=null && kw_DateBegin != null && kw_DateEnd != null) {
            if (kw_province.equals("")&&kw_DateBegin.equals("")&&kw_DateEnd.equals("")) {
                hql = "select k from KeyWords k where k.kw_name =:name ";
                Map<String, Object> params = new HashMap<>();
                params.put("name", kw_name);
                keywords = baseDAO.find(hql, params);

            }
            if(kw_province.equals("")){
                hql = "select k from KeyWords k where k.kw_name = :name and k.kw_Date >= :dateBegin and k.kw_Date <= :dateEnd";
                Map<String, Object> params = new HashMap<>();
                params.put("name", kw_name);
                params.put("dateBegin", kw_DateBegin);
                params.put("dateEnd", kw_DateEnd);
                keywords = baseDAO.find(hql, params);
                //System.out.println("222");
            }
            else{
                hql = "select k from KeyWords k where k.kw_name = :name and k.kw_province = :province and k.kw_Date >= :dateBegin and k.kw_Date <= :dateEnd";
                Map<String, Object> params = new HashMap<>();
                params.put("name", kw_name);
                params.put("province", kw_province);
                params.put("dateBegin", kw_DateBegin);
                params.put("dateEnd", kw_DateEnd);
                keywords = baseDAO.find(hql, params);
               //System.out.println("333");
            }

        }
        return keywords;
    }
    /**
     *
     * @param keyWordsSearch
     * @return
     * @author shashajie
     */
    //根据关键词名称、关键词省份进行搜索(得到全部)
    @Override
    public List<KeyWords>  getkeyWordsSearch(String keyWordsSearch){
        String hql="select k from KeyWords k where k.kw_name = :name  or k.kw_province = :province";
        Map<String, Object> params = new HashMap<>();
        params.put("name", keyWordsSearch);
        params.put("province", keyWordsSearch);
        List<KeyWords> keyWords =(List<KeyWords>)baseDAO.find(hql, params);
        return keyWords;
    }

    /**
     *
     * @param keyWordsSearch
     * @return
     * @author shashajie
     */
    //根据关键词名称、关键词省份进行搜索(通过分页)
    @Override
    public Page<KeyWords> getkeyWordsSearchByPage(int pageNo, String keyWordsSearch){
//		String hql="select k from KeyWords k where k.kw_name = ?  or k.kw_province = ?";
        Page<KeyWords> page = new Page<KeyWords>(20);
        page.setPageNo(pageNo);
        PropertyFilter filters = new PropertyFilter("kw_name|kw_province", keyWordsSearch);
        Page<KeyWords> pages = getEntityDao().findByFilters(page, filters);
        return pages;
    }

}
