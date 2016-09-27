package com.sicdlib.service;

import com.sicdlib.dto.KeyWords;
import edu.xjtsoft.base.orm.support.Page;

import java.util.List;

/**
 * Created by Yh on 2016/9/13.
 */
public interface IKeyWordsService {
    List<KeyWords> getTopKeyWords(int num, String kw_province, String kw_Date);
    List<KeyWords> getSpecificKeyWords(String kw_name, String kw_date);
    List<KeyWords> getkeyWordsbyNameProDate(String kw_name, String kw_province, String kw_DateBegin, String kw_DateEnd);
    List<KeyWords>  getkeyWordsSearch(String keyWordsSearch);
    Page<KeyWords> getkeyWordsSearchByPage(int pageNo, String keyWordsSearch);
}
