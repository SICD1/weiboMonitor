package com.sicdlib.dao;

import com.sicdlib.dto.User;
import edu.xjtsoft.base.orm.support.Page;

/**
 * Created by maninit on 2016/9/22.
 */
public interface IBackgroundUserDAO {
    Page<User> getsearchUsers(int pageNo, String search_key);
}
