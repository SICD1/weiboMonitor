package com.sicdlib.dto;


public class PrivilegeMenu {
    private String mp_id;
    private Menu menu;
    private Privilege privilege;

    public String getMp_id() {
        return mp_id;
    }

    public void setMp_id(String mp_id) {
        this.mp_id = mp_id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}
