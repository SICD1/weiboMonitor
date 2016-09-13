package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.IRegisterDAO;
import com.sicdlib.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Yh on 2016/9/12.
 */
@Repository("registerDAO")
public class RegisterDAO implements IRegisterDAO{
    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public boolean insertUser(User user) {
        Serializable s = baseDAO.save(user);
        if(s != null && !s.equals("")) {
            return true;
        }

        return false;
    }
}
