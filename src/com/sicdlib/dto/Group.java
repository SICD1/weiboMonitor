package com.sicdlib.dto;

import com.sicdlib.entity.*;
import com.sicdlib.entity.Role;
import com.sicdlib.entity.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by maninit on 2016/9/7.
 */
public class Group {
    private String g_id;
    private Group groupParentId;
    private String g_name;
    //一个用户与用户组中间表，包含多个用户
    private Set<User> users = new HashSet<User>();
    private Role role;

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

    public Group getGroupParentId() {
        return groupParentId;
    }

    public void setGroupParentId(Group groupParentId) {
        this.groupParentId = groupParentId;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
