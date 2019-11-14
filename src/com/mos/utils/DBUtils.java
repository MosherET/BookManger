package com.mos.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBUtils {

    //四大参数
    private static DataSource ds;
    //获取参数并获取驱动

    static {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = DBUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            System.out.println(inputStream);
            properties.load(inputStream);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接，返回conn
     * @return 连接字符串
     * @throws SQLException 抛出异常
     */
    public static Connection getConnection(){

        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * 关闭conn链接
     * @param conn 参数是Connection连接字符串
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭Statement连接
     * @param st 参数是Statement类型
     */
    public static void closeStatement(Statement st) {
        if(st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭PreparedStatement连接
     * @param ps 输入PreparedStatement
     */
    public static void closePrepareStatement(PreparedStatement ps) {
        if(ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭ResultSet连接
     * @param rs 参数是ResultSet类型
     */
    public static void closeResultSet(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 增删改的关闭，直接调用closeStatement() closeConnection()
     * @param conn Connection连接字符串
     * @param st Statement类型
     */
    public static void close(Connection conn, PreparedStatement ps) {
        closePrepareStatement(ps);
        closeConnection(conn);
    }

    /**
     * 查询的关闭，直接调用closeResultSet() closeStatement() closeConnection()
     * @param conn Connection连接字符串
     * @param ps   Statement类型
     * @param rs   ResultSet类型
     */
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        closeResultSet(rs);
        closePrepareStatement(ps);
        closeConnection(conn);
    }
}
