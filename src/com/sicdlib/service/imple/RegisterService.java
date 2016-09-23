package com.sicdlib.service.imple;

import com.sicdlib.dao.IRegisterDAO;
import com.sicdlib.dao.IRoleDAO;
import com.sicdlib.dao.IUserDAO;
import com.sicdlib.dto.Constant;
import com.sicdlib.dto.Group;
import com.sicdlib.dto.Role;
import com.sicdlib.dto.User;
import com.sicdlib.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Yh on 2016/9/12.
 */
@Service("registerService")
public class RegisterService implements IRegisterService {
    @Autowired
    @Qualifier("registerDAO")
    private IRegisterDAO registerDAO;

    @Autowired
    @Qualifier("roleDAO")
    private IRoleDAO roleDAO;

    @Autowired
    @Qualifier("userDAO")
    private IUserDAO userDAO;


    @Override
    public boolean registerNormalUser(User user) {
        Group group = getNormalGroup();
        //user.setUserGroup(group);

        boolean result = registerDAO.insertUser(user);

        return result;
    }

    @Override
    public boolean isExistUser(String name) {
        User user = userDAO.getUserByName(name);
        if(user != null) {
            return true;
        }

        return false;
    }

    //产生普通用户的组
    private Group getNormalGroup() {
        String roleName = Constant.ROLE_NORMAL_USER;
        Role role = roleDAO.getRoleByName(roleName);

        Group group = new Group();
        //group.setParent_id("0");
        //group.setRole(role);
        group.setG_name(Constant.GROUP_NORMAL_USER);

        return group;
    }
}
