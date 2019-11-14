package com.mos.service;

import com.mos.domain.PageBean;
import com.mos.domain.Sort;

import java.util.List;

public interface SortService {
    /**
     * 查询所有分类
     * @return 返回包含Sort对象的List集合
     */
    List<Sort> viewAll();

    /**
     * 通过分类id删除分类
     * @param fid 参数是分类id
     */
    int delSort(int fid);

    /**
     * 添加分类
     * @param sort Sort对象
     * @return 返回数据库中受影响的行数
     */
    int addSort(Sort sort);

    /**
     * 校验分类名称是否存在
     * @param fname 分类名称
     * @return 存在返回true，不存在返回false
     */
    boolean verifyFname(String fname);

    /**
     * 修改分类
     * @param sort 分类对象
     * @return
     */
    int modifySort(Sort sort);

    /**
     * 分页查看分类
     * @param currentPage
     * @param rows
     * @return
     */
    PageBean<Sort> selectByPage(String currentPage, String rows);
}
