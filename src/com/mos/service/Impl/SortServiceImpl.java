package com.mos.service.Impl;

import com.mos.dao.Impl.SortDAOImpl;
import com.mos.dao.SortDAO;
import com.mos.domain.PageBean;
import com.mos.domain.Sort;
import com.mos.service.SortService;

import java.awt.print.Pageable;
import java.util.List;

public class SortServiceImpl implements SortService {
    SortDAO sdao = new SortDAOImpl();
    @Override
    public List<Sort> viewAll() {
        return sdao.SelectAll();
    }

    @Override
    public int delSort(int fid) {
        return sdao.delByFid(fid);
    }

    @Override
    public int addSort(Sort sort) {
        return sdao.add(sort);
    }

    @Override
    public boolean verifyFname(String fname) {
        return sdao.selectByFname(fname);
    }

    @Override
    public int modifySort(Sort sort) {
        return sdao.Update(sort);
    }

    @Override
    public PageBean<Sort> selectByPage(String currentPage, String rows) {
        int intCurrentPage = Integer.parseInt(currentPage);
        int intRows = Integer.parseInt(rows);
        int totalCount = sdao.getTotalCount();
        int totalPage = totalCount % intRows == 0 ? totalCount/intRows : (totalCount/intRows)+1;
        if(intCurrentPage > totalPage) {
            intCurrentPage = totalPage;
        }
        if(intCurrentPage <= 0) {
            intCurrentPage = 1;
        }
        int startRow = (intCurrentPage-1)*intRows;
        List<Sort> list = sdao.getSortByPage(startRow, intRows);
        PageBean<Sort> pb = new PageBean<>(totalCount, totalPage, list, intRows, intCurrentPage);
        return pb;
    }
}
