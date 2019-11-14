package com.mos.dao.Impl;

import com.mos.dao.UserDAO;
import com.mos.domain.Book;
import com.mos.domain.User;
import com.mos.utils.DBUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    JdbcTemplate template = new JdbcTemplate(DBUtils.getDataSource());

    @Override
    public User userLogin(User loginUser) {
        String sql = "select * from user where uacount=? and upassword=?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUacount(), loginUser.getUpassword());
        } catch (EmptyResultDataAccessException e) {
            System.out.println("用户不存在");
        }
        return user;
    }

    @Override
    public int add(User user) {
        String sql = "insert into user(uname,uacount,upassword,usphone,regdate,touxiang) values(?,?,?,?,?,?)";
        int result = template.update(sql, user.getUname(), user.getUacount(), user.getUpassword(), user.getUsphone(), user.getRegdate(),user.getTouxiang());
        return result;
    }

    @Override
    public int Delete(int id) {
        String sql = "delete from user where uid=?";
        int result = template.update(sql, id);
        return result;
    }

    @Override
    public int Update(User user) {
        String sql = "update user set uname=?,upassword=?,usphone=?,regdate=?,uacount=?,touxiang=? where uid = ?";
        int result = template.update(sql, user.getUname(), user.getUpassword(), user.getUsphone(), user.getRegdate(),user.getUacount(),user.getTouxiang(),user.getUid());
        return result;
    }

    @Override
    public List<User> SelectAll() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public User SelectById(int id) {
        String sql = "select * from user where uid=?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),id);
        } catch (DataAccessException e) {
            System.out.println("用户不存在");
        }
        return user;
    }

    @Override
    public User SelectByAccount(String account) {
        String sql = "select * from user where uacount=?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), account);
        } catch (DataAccessException e) {
            System.out.println("用户不存在");
        }
        return user;
    }

    @Override
    public int selectCount() {
        String sql = "select count(uid) from user";
        return template.queryForObject(sql, Integer.class);
    }

    @Override
    public List<User> selectByPage(int pageSize, int currentPage) {
        String sql = "select * from user limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), currentPage, pageSize);
    }

    @Override
    public List<Book> selectUserBook(int uid) {
        String sql = "select b.* from user u,book b where b.uid = u.uid and u.uid=?";
        List<Book> userBook = null;
        try {
            userBook = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class), uid);
        } catch (DataAccessException e) {
            System.out.println("用户未借用图书");
            e.printStackTrace();
        }
        return userBook;
    }
}
