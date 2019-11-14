package com.mos.dao;

import com.mos.domain.Sort;

import java.util.List;

/**
 * 分类
 */
public interface SortDAO {

    /**
     * 添加分类
     * @param sort 参数是传入的分类对象
     *
     *  注意：不包含分类id，此项在数据库中是递增的，无需添加
     *
     * @return 返回数据库中受影响的行数
     */
    int add(Sort sort);

    /**
     * 根据传入的分类id删除图书
     * @param id 参数是传入的分类id
     * @return 返回数据库中受影响的行数
     */
    int Delete(int id);

    /**
     * 根据传入的分类对象对分类信息进行修改
     * @param sort 参数是分类对象
     * @return 返回数据库中受影响的行数
     */
    int Update(Sort sort);

    /**
     * 全查
     * @return 返回包含分类对象的list集合
     */
    List<Sort> SelectAll();

    /**
     * 根据传入的分类id查找分类信息
     * @return 返回分类对象
     */
    Sort SelectById();

    int delByFid(int fid);

    /**
     * 通过fname查找数据
     * @param fname
     * @return
     */
    boolean selectByFname(String fname);

    //计算数据库中不为空的行数
    int getTotalCount();

    //分页查找数据库
    List<Sort> getSortByPage(int startRow, int intRows);
}
