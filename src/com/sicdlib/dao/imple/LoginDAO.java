package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.ILoginDAO;
import com.sicdlib.dto.User;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yh on 2016/9/8.
 */
@Repository("loginDAO")
public class LoginDAO implements ILoginDAO {
    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public User getUserByName(String name) {
       /* String hql = "from User user where user.u_name = '" + name + "'";
        return (User) baseDAO.get(hql);*/
        String sql = "select t.* from tb_user t  where  t.u_name ='"+name+"'";
        List<User> list = baseDAO.getSqlList(sql);
        return list.get(0);
    }
}
