package com.sicdlib.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by maninit on 2016/9/7.
 */
public class Group implements Serializable {
    private String g_id;
    private String g_name;
    private String g_parent_id;
    private String role_id;
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

    public String getG_parent_id() {
        return g_parent_id;
    }

    public void setG_parent_id(String g_parent_id) {
        this.g_parent_id = g_parent_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
}
