package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBackgroundUserDAO;
import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dto.User;
import edu.xjtsoft.base.orm.support.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by maninit on 2016/9/22.
 */
public class BackgroundUserDAO implements IBackgroundUserDAO {
    @Autowired
    private IBaseDAO baseDAO;
    //后台用户搜索
    @Override
    public Page<User> getsearchUsers(int pageNo, String search_key) {
            String hql = "select u from User u where u.u_name = ? or u.u_telphone = ? or u.u_mail = ?";
            Object[] values = new Object[3];
            values[0] = search_key;
            values[1] = search_key;
            values[2] = search_key;
            Page<User> page = new Page<User>(10);
            page.setPageNo(pageNo);
            Page<User> users = (Page<User>) baseDAO.find(page , hql, values);
            return users;
        }
    }

