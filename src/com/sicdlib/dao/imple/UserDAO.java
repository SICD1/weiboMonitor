package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.IUserDAO;
import com.sicdlib.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.ws.Action;

/**
 * Created by Yh on 2016/9/12.
 */
@Repository("userDAO")
public class UserDAO implements IUserDAO {
    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public User getUserByName(String name) {
        String hql = "from User u where u.u_name = '" + name + "'";
        User user = (User) baseDAO.get(hql);

        return user;
    }
}
