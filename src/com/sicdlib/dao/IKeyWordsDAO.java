package com.sicdlib.dao;

import com.sicdlib.dao.imple.KeyWordsDAO;
import com.sicdlib.dto.KeyWords;
import edu.xjtsoft.base.orm.support.Page;

import java.util.List;

/**
 * Created by maninit on 2016/9/22.
 */
public interface IKeyWordsDAO {
    List<KeyWords> getTopKeyWords(int num, String kw_province, String kw_Date);
    List<KeyWords> getSpecificKeyWords(String kw_name, String kw_date);
    List<KeyWords> getkeyWordsbyNameProDate(String kw_name, String kw_province, String kw_DateBegin, String kw_DateEnd);
    List<KeyWords> getkeyWordsSearch(String keyWordsSearch);
    Page<KeyWords> getkeyWordsSearchByPage(int pageNo, String keyWordsSearch);
}
