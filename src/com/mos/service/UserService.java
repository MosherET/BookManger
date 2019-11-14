package com.mos.service;


import com.mos.domain.Book;
import com.mos.domain.PageBean;
import com.mos.domain.User;

import java.util.List;

public interface UserService {

    User userLogin(User loginUser);

    int add(User user);

    int Delete(int id);

    int Update(User user);

    List<User> SelectAll();

    User SelectById(int id);

    User SelectByAccout(String account);

    int selectCount();

    PageBean<User> selectByPage(int pageSize, int currentPage);

    List<Book> selectUserBook(int uid);
}

