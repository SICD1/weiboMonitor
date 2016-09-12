package com.sicdlib.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by maninit on 2016/9/7.
 */
public class Group implements Serializable{
    private String g_id;
    private String g_name;
    //一个用户与用户组中间表，包含多个用户
    private Set<User> users = new HashSet<>();
    private Role role;

    private Set<Group> childGroup = new HashSet<>();
    private Group parentGroup;

    public Set<Group> getChildGroup() {
        return childGroup;
    }

    public void setChildGroup(Set<Group> childGroup) {
        this.childGroup = childGroup;
    }

    public Group getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(Group parentGroup) {
        this.parentGroup = parentGroup;
    }

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
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
