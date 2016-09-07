package com.sicdlib.dto;

import com.sicdlib.entity.*;
import com.sicdlib.entity.Role;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by maninit on 2016/9/7.
 */
public class RolePrivilege {
    private String rp_id;
    private Set<Role> roles = new HashSet<Role>();
    private Set<Privilege> privileges=new HashSet<Privilege>();

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getRp_id() {
        return rp_id;
    }

    public void setRp_id(String rp_id) {
        this.rp_id = rp_id;
    }

}
