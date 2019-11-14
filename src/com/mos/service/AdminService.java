package com.mos.service;

import com.mos.domain.Admin;

public interface AdminService {

    /**
     * 修改管理员密码
     * @param aacount 传入用户名
     * @param newPassword 新密码
     * @return 返回数据库中受影响的行数
     */
    int changeAdminPassword(String aacount, String newPassword);

    /**
     * 查询密码
     * @param aacount 传入账户名
     * @return 返回密码
     */
    String verifyPassword(String aacount);

    /**
     * 登录方法
     * @param aacount 参数是输入的用户名
     * @return 返回密码，如果用户名不存在或者错误，密码返回空
     */
    String login(String aacount);

    /**
     * 注册方法
     * @param admin 参数是Admin对象
     * @return 返回数据库中受影响的行数
     */
    int register(Admin admin);

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
    int modifyPassword(String aacount, String newPassword);
}
