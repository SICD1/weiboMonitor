package com.sicdlib.service.imple;

import com.sicdlib.dao.IKeyWordsDAO;
import com.sicdlib.dao.imple.KeyWordsDAO;
import com.sicdlib.dto.KeyWords;
import com.sicdlib.service.IKeyWordsService;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by maninit on 2016/9/20.
 */
@Service()
@Transactional
public class KeyWordsService extends DefaultEntityManager<KeyWords>{
    @Autowired
    @Qualifier("keyWordsDAO")
   private IKeyWordsDAO keyWordsDAO;
   public List<KeyWords> getTopKeyWords(int num, String kw_province, String kw_Date){
       //System.out.print("keywordService 进俩了");
       List<KeyWords> keyWordsList =  keyWordsDAO.getTopKeyWords(num, kw_province, kw_Date);
       return keyWordsList;
   }
  public List<KeyWords> getSpecificKeyWords(String kw_name, String kw_date) {
  return keyWordsDAO.getSpecificKeyWords(kw_name, kw_date);
  }
  public List<KeyWords> getkeyWordsbyNameProDate(String kw_name, String kw_province, String kw_DateBegin, String kw_DateEnd){
      return keyWordsDAO.getkeyWordsbyNameProDate(kw_name, kw_province, kw_DateBegin, kw_DateEnd);

  }
  public List<KeyWords>  getkeyWordsSearch(String keyWordsSearch){
      return keyWordsDAO.getkeyWordsSearch(keyWordsSearch);
  }
  public Page<KeyWords> getkeyWordsSearchByPage(int pageNo, String keyWordsSearch){
      return keyWordsDAO.getkeyWordsSearchByPage(pageNo, keyWordsSearch);
  }
}
