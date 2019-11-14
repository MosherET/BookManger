package com.mos.domain;

/**
 * 管理员实体类
 */
public class Admin {
    private int aid;//管理员id
    private String aname;//管理员姓名
    private String aacount;//管理员账户
    private String asphone;//电话
    private String apassword;//密码
    private String adminPic;//头像

    public Admin() {
    }

    public Admin(String aname, String aacount, String asphone, String apassword) {
        this.aname = aname;
        this.aacount = aacount;
        this.asphone = asphone;
        this.apassword = apassword;
    }

    public Admin(int aid, String aname, String aacount, String asphone, String apassword) {
        this.aid = aid;
        this.aname = aname;
        this.aacount = aacount;
        this.asphone = asphone;
        this.apassword = apassword;
    }

    public Admin(int aid, String aname, String aacount, String asphone, String apassword, String adminPic) {
        this.aid = aid;
        this.aname = aname;
        this.aacount = aacount;
        this.asphone = asphone;
        this.apassword = apassword;
        this.adminPic = adminPic;
    }

    public int getAid() {
        return aid;
    }

    public String getAdminPic() {
        return adminPic;
    }

    public void setAdminPic(String adminPic) {
        this.adminPic = adminPic;
    }

    public int getAId() {
        return aid;
    }

    public void setAid(int id) {
        this.aid = id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAsphone() {
        return asphone;
    }

    public void setAsphone(String asphone) {
        this.asphone = asphone;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    public String getAacount() {
        return aacount;
    }

    public void setAacount(String aacount) {
        this.aacount = aacount;
    }
}
