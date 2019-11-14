package com.mos.dao;


import com.mos.domain.Book;
import com.mos.domain.User;

import java.util.List;

public interface UserDAO {

    User userLogin(User loginUser);
    /**
     * 添加用户
     * @param user 参数是传入的用户对象
     * @return 返回数据库中受影响的行数
     */
    int add(User user);

    /**
     * 根据传入的用户id删除用户
     * @param id 参数是传入的用户id
     * @return 返回数据库中受影响的行数
     */
    int Delete(int id);

    /**
     * 根据传入的用户对象对用户信息进行修改
     * @param user 参数是用户对象
     * @return 返回数据库中受影响的行数
     */
    int Update(User user);

    /**
     * 全查
     * @return 返回包含用户对象的list集合
     */
    List<User> SelectAll();

    /**
     * 根据传入的图书id查找用户信息
     * @return 返回用户对象
     */
    User SelectById(int id);

    User SelectByAccount(String account);

    int selectCount();

    List<User> selectByPage(int pageSize, int currentPage);

    List<Book> selectUserBook(int uid);
}
