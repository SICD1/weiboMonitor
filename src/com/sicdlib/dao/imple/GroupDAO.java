package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.IGroupDAO;
import com.sicdlib.dto.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Yh on 2016/9/13.
 */
@Repository("groupDAO")
public class GroupDAO implements IGroupDAO {
    @Autowired
    IBaseDAO baseDAO;

    @Override
    public Group getGroupByName(String groupName) {
        String hql = "from Group g where g.g_name = '" + groupName + "'";
        Group group = (Group) baseDAO.get(hql);
        return group;
    }

    @Override
    public String InsertGroup(Group group) {
        String id = (String) baseDAO.save(group);

        return id;
    }
}
