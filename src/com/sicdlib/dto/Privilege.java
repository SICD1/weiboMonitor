package com.sicdlib.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by maninit on 2016/9/7.
 */
public class Privilege {
    private String p_id;
    private String p_type;

    private Set<RolePrivilege> rolePrivileges = new HashSet<>();
    private PrivilegeMenu privilegeMenu;

    public Set<RolePrivilege> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }

    public PrivilegeMenu getPrivilegeMenu() {
        return privilegeMenu;
    }

    public void setPrivilegeMenu(PrivilegeMenu privilegeMenu) {
        this.privilegeMenu = privilegeMenu;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }
}
