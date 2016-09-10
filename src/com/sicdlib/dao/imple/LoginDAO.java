package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.ILoginDAO;
import com.sicdlib.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Yh on 2016/9/8.
 */
@Repository("loginDAO")
public class LoginDAO implements ILoginDAO {
    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public User getUserByName(String name) {
        String hql = "from User user where user.u_name = '" + name + "'";
        return (User) baseDAO.get(hql);
    }
}
