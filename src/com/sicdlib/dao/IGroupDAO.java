package com.sicdlib.dao;

import com.sicdlib.dto.Group;

/**
 * Created by Yh on 2016/9/13.
 */
public interface IGroupDAO {
    Group getGroupByName(String groupName);
    String InsertGroup(Group group);
}
