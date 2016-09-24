package com.sicdlib.service.imple;

import com.sicdlib.dto.User;
import com.sicdlib.service.IUserService;
import edu.xjtsoft.base.orm.HibernateDao;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.service.DefaultEntityManager;
import edu.xjtsoft.base.service.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yh on 2016/9/8.
 */
@Service("userService")
@Transactional
public class UserService extends DefaultEntityManager<User> {
    //后台用户搜索
    public Page<User> getsearchUsers(int pageNo,String search_key){
        String hql="select u from User u where u.u_name = ? or u.u_telephone = ? or u.u_email = ?";
        Object[] values=new Object[3];
        values[0]=search_key;
        values[1]=search_key;
        values[2]=search_key;
        Page<User> page=new Page<User>();
        page.setPageNo(pageNo);
        Page<User> users=getEntityDao().find(page,hql,values);
        return users;
    }
}
