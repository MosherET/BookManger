package com.mos.service.Impl;


import com.mos.dao.Impl.UserDAOImpl;
import com.mos.dao.UserDAO;
import com.mos.domain.Book;
import com.mos.domain.PageBean;
import com.mos.domain.User;
import com.mos.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDAO dao = new UserDAOImpl();

    @Override
    public User userLogin(User loginUser) {
        return dao.userLogin(loginUser);
    }

    @Override
    public int add(User user) {
        return dao.add(user);
    }

    @Override
    public int Delete(int id) {
        return dao.Delete(id);
    }

    @Override
    public int Update(User user) {
        return dao.Update(user);
    }

    @Override
    public List<User> SelectAll() {
        return dao.SelectAll();
    }

    @Override
    public User SelectById(int id) {
        return dao.SelectById(id);
    }

    @Override
    public User SelectByAccout(String account) {
        return dao.SelectByAccount(account);
    }

    @Override
    public int selectCount() {
        return dao.selectCount();
    }

    @Override
    public PageBean<User> selectByPage(int pageSize, int currentPage) {
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setRows(pageSize);
        //封装总记录条数进bean
        int total = dao.selectCount();
        pageBean.setTotalCount(total);
        //封装总页码
        int totalPage = ((total % pageSize) == 0) ? (total / pageSize):(total / pageSize) + 1;
        pageBean.setTotalPage(totalPage);
        if (currentPage > totalPage){
            currentPage = totalPage;
        }
        pageBean.setCurrentPage(currentPage);
        //封装每页数据
        int start = (currentPage - 1) * pageSize;
        List<User> page = dao.selectByPage(pageSize,start);
        pageBean.setList(page);
        return pageBean;
    }

    @Override
    public List<Book> selectUserBook(int uid) {
        return dao.selectUserBook(uid);
    }
}
