package com.mos.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int uid;
    private String uname;
    private String uacount;
    private String upassword;
    private String usphone;
    private String regdate;
    private String touxiang;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUacount() {
        return uacount;
    }

    public void setUacount(String uacount) {
        this.uacount = uacount;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUsphone() {
        return usphone;
    }

    public void setUsphone(String usphone) {
        this.usphone = usphone;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", uacount='" + uacount + '\'' +
                ", upassword='" + upassword + '\'' +
                ", usphone='" + usphone + '\'' +
                ", regdate='" + regdate + '\'' +
                ", touxiang='" + touxiang + '\'' +
                '}';
    }
}
