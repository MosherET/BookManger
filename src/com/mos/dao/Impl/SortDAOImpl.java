package com.mos.dao.Impl;

import com.mos.dao.SortDAO;
import com.mos.domain.Sort;
import com.mos.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SortDAOImpl implements SortDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    @Override
    public int add(Sort sort) {
        int i = 0;
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("insert into fenlei (fname) values(?)");
            ps.setString(1, sort.getFname());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps);
        }
        return i;
    }

    @Override
    public int Delete(int id) {
        return 0;
    }

    @Override
    public int Update(Sort sort) {
        int i = 0;
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("update fenlei set fname=? where fid=?");
            ps.setString(1, sort.getFname());
            ps.setInt(2, sort.getFid());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps);
        }

        return i;
    }

    @Override
    public List<Sort> SelectAll() {
        List<Sort> list = new ArrayList<Sort>();
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("select * from fenlei");
            rs = ps.executeQuery();
            while(rs.next()) {
                Sort sort = new Sort();
                sort.setFid(rs.getInt(1));
                sort.setFname(rs.getString(2));
                list.add(sort);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public Sort SelectById() {
        return null;
    }

    @Override
    public int delByFid(int fid) {
        int i = 0;
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("delete from fenlei where fid=?");
            ps.setInt(1, fid);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return i;
    }

    @Override
    public boolean selectByFname(String fname) {
        boolean flag = false;
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("select * from fenlei where fname=?");
            ps.setString(1, fname);
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
    public int getTotalCount() {
        int i = 0;
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("select count(*) from fenlei");
            rs = ps.executeQuery();
            while(rs.next()) {
                i = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return i;
    }

    @Override
    public List<Sort> getSortByPage(int startRow, int intRows) {
        List<Sort> list = new ArrayList<Sort>();
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement("select fid,fname from fenlei limit ?,?");
            ps.setInt(1, startRow);
            ps.setInt(2, intRows);
            rs = ps.executeQuery();
            while(rs.next()) {
                Sort sort = new Sort();
                sort.setFid(rs.getInt(1));
                sort.setFname(rs.getString(2));
                list.add(sort);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return list;
    }
}
