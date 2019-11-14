package com.mos.domain;

/**
 * 分类实体类
 */
public class Sort {

    private int fid;//分类id
    private String fname;//分类名称

    public Sort() {
    }

    public Sort(String fname) {
        this.fname = fname;
    }

    public Sort(int fid, String fname) {
        this.fid = fid;
        this.fname = fname;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
