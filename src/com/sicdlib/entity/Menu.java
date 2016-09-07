package com.sicdlib.entity;

/**
 * 菜单属性
 */
public class Menu {
    private String  m_id;
    private String m_name;
    public String m_icon;
    private String m_url;
    private Menu menu;//父类的id映射属性
    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_icon() {
        return m_icon;
    }

    public void setM_icon(String m_icon) {
        this.m_icon = m_icon;
    }

    public String getM_url() {
        return m_url;
    }

    public void setM_url(String m_url) {
        this.m_url = m_url;
    }
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }


}
