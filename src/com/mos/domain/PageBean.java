package com.mos.domain;

import java.util.List;

/**
 * 分页显示
 */
public class PageBean<T> {
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private List<T> list;//每页的数据
    private int rows;//每页显示的行数
    private int currentPage;//当前页数

    public PageBean() {

    }

    public PageBean(int totalCount, int totalPage, List<T> list, int rows, int currentPage) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
        this.rows = rows;
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
