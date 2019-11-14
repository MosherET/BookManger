package com.mos.dao.Impl;

import com.mos.dao.AdminDAO;
import com.mos.domain.Admin;
import com.mos.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public int add(Admin admin) {
        int i = 0;
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("insert into admin (aname,asphone,aacount,apassword,aphoto) values(?,?,?,?,?)");
            ps.setString(1, admin.getAname());
            ps.setString(2, admin.getAsphone());
            ps.setString(3, admin.getAacount());
            ps.setString(4, admin.getApassword());
            ps.setString(5, admin.getAdminPic());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps);
        }
        return i;
    }

    @Override
    public int Delete(int aid) {
        int i = 0;
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("delete from admin where aid=?");
            ps.setInt(1, aid);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps);
        }
        return i;
    }

    @Override
    public int Update(Admin admin) {
        int i = 0;
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("update admin set aname=?,asphone=?,aacount=?,apassword=?");
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps);
        }
        return i;
    }

    @Override
    public List<Admin> SelectAll() {
        List<Admin> list = new ArrayList<Admin>();
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("select aid,aphoto,aname,asphone,aacount,apassword from admin");
            rs = ps.executeQuery();
            while(rs.next()) {
                Admin admin = new Admin();
                admin.setAid(rs.getInt(1));
                admin.setAdminPic(rs.getString(2));
                admin.setAname(rs.getString(3));
                admin.setAsphone(rs.getString(4));
                admin.setAacount(rs.getString(5));
                admin.setApassword(rs.getString(6));
                list.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public int UpdatePassword(String aacount, String newPassword) {
        int i = 0;
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("update admin set apassword=? where aacount=?");
            ps.setString(1, newPassword);
            ps.setString(2, aacount);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps);
        }
        return i;
    }

    @Override
    public String selectPasswordByAccount(String aacount) {
        String password = null;
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("select apassword from admin where aacount=?");
            ps.setString(1, aacount);
            rs = ps.executeQuery();
            while(rs.next()) {
                password = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return password;
    }

    @Override
    public boolean verifyAacount(String aacount) {
        boolean flag = false;
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("select * from admin where aacount=?");
            ps.setString(1, aacount);
            rs = ps.executeQuery();
            if(rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return flag;
    }

    @Override
    public Admin selectByAacount(String aacount) {
        Admin admin = new Admin();
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("select aphoto,aname,asphone,aacount,apassword from admin where aacount=?");
            ps.setString(1, aacount);
            rs = ps.executeQuery();
            while(rs.next()) {
                admin.setAdminPic(rs.getString(1));
                admin.setAname(rs.getString(2));
                admin.setAsphone(rs.getString(3));
                admin.setAacount(rs.getString(4));
                admin.setApassword(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return admin;
    }

    @Override
    public int updatePassword(String aacount, String newPassword) {
        int i = 0;
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("update admin set apassword=? where aacount=?");
            ps.setString(1, newPassword);
            ps.setString(2, aacount);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps);
        }
        return i;
    }
}
