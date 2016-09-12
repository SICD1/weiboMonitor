package com.sicdlib.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by maninit on 2016/9/7.
 */
public class Role implements Serializable{
    private String r_id;
    private String r_name;

    private Set<Group> groups = new HashSet<>();

    private Set<RolePrivilege> privileges = new HashSet<>();

    public Set<RolePrivilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<RolePrivilege> privileges) {
        this.privileges = privileges;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }
}
