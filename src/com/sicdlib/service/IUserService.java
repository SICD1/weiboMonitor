package com.sicdlib.service;

import com.sicdlib.dto.User;
import edu.xjtsoft.base.orm.support.Page;

/**
 * Created by Yh on 2016/9/8.
 */
public interface IUserService {
    public Page<User> getsearchUsers(int pageNo, String search_key);
}
