package com.mos.dao;


import com.mos.domain.Admin;

import java.util.List;

/**
 * 管理员
 */
public interface AdminDAO {

    /**
     * 添加管理员
     *
     * 注意：管理员对象不包含管理员id，因为在数据库中是依次递增的
     *       只需传入其他属性
     *
     * @param admin 参数是管理员对象
     * @return 返回数据库中受影响的行数
     */
    int add(Admin admin);

    /**
     * 通过id删除数据库中的数据
     * @param aid 参数是管理员id
     * @return 返回数据库中受影响的行数
     */
    int Delete(int aid);

    /**
     * 通过id修改数据库中的数据
     * @param admin 参数是修改后的管理员对象
     * @return 返回数据库中受影响的行数
     */
    int Update(Admin admin);

    /**
     * 全查
     * @return 返回包含管理员对象的list集合
     */
    List<Admin> SelectAll();

    /**
     * 根据传入的账户名修改密码为新密码
     * @param aacount 传入的用户名
     * @param newPassword 修改后的新密码
     * @return 返回数据库中受影响的行数
     */
    int UpdatePassword(String aacount, String newPassword);

    /**
     * 根据传入的账户名查找密码
     * @param aacount 传入账户名
     * @return 返回密码
     */
    String selectPasswordByAccount(String aacount);

    /**
     * 验证用户名是否存在
     * @param aacount
     * @return
     */
    boolean verifyAacount(String aacount);

    /**
     * 通过账户名查找管理员信息
     * @param aacount
     * @return
     */
    Admin selectByAacount(String aacount);

    /**
     * 修改密码
     * @param newPassword
     * @return
     */
    int updatePassword(String aacount, String newPassword);
}
