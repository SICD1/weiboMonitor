package com.sicdlib.dto;

/**
 * Created by maninit on 2016/9/7.
 */
public class User {
    private String u_id;
    private String u_name;
    private String u_pwd;
    private String u_telephone;
    private String u_mail;
    private String u_sex;
    private User userGroup;

    public User getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(User userGroup) {
        this.userGroup = userGroup;
    }

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

    public String getU_mail() {
        return u_mail;
    }

    public void setU_mail(String u_mail) {
        this.u_mail = u_mail;
    }

    public String getU_sex() {
        return u_sex;
    }

    public void setU_sex(String u_sex) {
        this.u_sex = u_sex;
    }

}
