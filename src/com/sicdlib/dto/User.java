package com.sicdlib.dto;

import java.io.Serializable;

/**
 * Created by maninit on 2016/9/7.
 */
public class User implements Serializable{
    private String u_id;
    private String u_name;
    private String u_pwd;
    private String u_telephone;
    private String u_email;
    private int u_sex;
    private String group_id;
    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_pwd() {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

    public String getU_telephone() {
        return u_telephone;
    }

    public void setU_telephone(String u_telephone) {
        this.u_telephone = u_telephone;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public int getU_sex() {
        return u_sex;
    }

    public void setU_sex(int u_sex) {
        this.u_sex = u_sex;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
