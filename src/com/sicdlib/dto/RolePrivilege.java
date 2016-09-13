package com.sicdlib.dto;

import com.sicdlib.dto.Role;

public class RolePrivilege {
    private String rp_id;
    private String role_id;
    private String privilege_id;

    public String getRp_id() {
        return rp_id;
    }

    public void setRp_id(String rp_id) {
        this.rp_id = rp_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getPrivilege_id() {
        return privilege_id;
    }

    public void setPrivilege_id(String privilege_id) {
        this.privilege_id = privilege_id;
    }
}
