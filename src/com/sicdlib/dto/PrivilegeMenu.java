package com.sicdlib.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by maninit on 2016/9/7.
 */
public class PrivilegeMenu {
    private String mp_id;
    private Menu menu_id;
    private Privilege privilege_id;

    public String getMp_id() {
        return mp_id;
    }

    public void setMp_id(String mp_id) {
        this.mp_id = mp_id;
    }

    public Menu getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Menu menu_id) {
        this.menu_id = menu_id;
    }

    public Privilege getPrivilege_id() {
        return privilege_id;
    }

    public void setPrivilege_id(Privilege privilege_id) {
        this.privilege_id = privilege_id;
    }
}
