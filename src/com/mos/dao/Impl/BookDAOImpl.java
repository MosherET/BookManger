package com.mos.dao.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.mos.dao.BookDAO;
import com.mos.domain.Book;
import com.mos.domain.BookBean;
import com.mos.utils.DBUtils;


public class BookDAOImpl implements BookDAO {
	
	
	private ResultSet rs=null;
	private PreparedStatement ps=null;
	private Connection connection=null;
	
    @Override
    public int add(Book book) {
    	connection= DBUtils.getConnection();
		int i=0;
		int fid=book.getFid();
		String bname=book.getBname();
		double price=book.getPrice();
		String press=book.getPress();
		String state=book.getState();
		String pic=book.getPic();
		int uid=book.getUid();
		try {
			ps= connection.prepareStatement("INSERT INTO book(fid,bname,price,press,state,pic,uid) VALUES(?,?,?,?,?,?,?)");
			ps.setInt(1, fid);
			ps.setString(2, bname);
			ps.setDouble(3, price);
			ps.setString(4, press);
			ps.setString(5, state);
			ps.setString(6,pic);
			ps.setInt(7, uid);
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtils.close(connection, ps, rs);
		return i;
    }

    @Override
    public int Delete(int id) {
    	connection=DBUtils.getConnection();
		int i=0;
//		System.out.println(book.toString()+12333);
		try {
			ps= connection.prepareStatement("DELETE FROM book WHERE bid=?");
			ps.setInt(1, id);
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtils.close(connection, ps, rs);
		return i;
    }

    @Override
    public List<Book> SelectAll() {
    	connection=DBUtils.getConnection();
		List<Book> list= new ArrayList<Book>();
		try {
			ps= connection.prepareStatement("SELECT bid,fname,bname,price,press,state,pic,uname FROM book,fenlei,user where book.fid=fenlei.fid and book.uid=user.uid");
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				int bid=rs.getInt(1);
				String fname=rs.getString(2);
				String bname=rs.getString(3);
				double price=rs.getDouble(4);
				String press=rs.getString(5);
				String state=rs.getString(6);
				String pic=rs.getString(7);
				String uname =rs.getString(8);
				Book book=new Book(bid,fname,bname,price,press,state,pic,uname);
				list.add(book);
				//
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, ps, rs);
		}
		
		return list;
    }

    @Override
    public Book SelectById(int id) {
    	connection=DBUtils.getConnection();
		Book book = null;
		try {
			ps= connection.prepareStatement("SELECT * FROM book WHERE bid=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				int bid=rs.getInt(1);
				int fid=rs.getInt(2);
				String bname=rs.getString(3);
				double price=rs.getDouble(4);
				String press=rs.getString(5);
				String state=rs.getString(6);
				String pic=rs.getString(7);
				int uid =rs.getInt(8);
				book=new Book(bid,fid,bname,price,press, state,pic,uid);
				//
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, ps, rs);
		}
		
		return book;
    }
    
	@Override
	public Map<Integer, String> fnameList() {
		// TODO Auto-generated method stub
		
		connection=DBUtils.getConnection();
//		if(connection!=null)
//			System.out.println("succ");
//		else {
//			System.out.println("err");
//		}
		Map<Integer, String>  map=new HashMap<Integer, String>();
		try {
			ps= connection.prepareStatement("SELECT * FROM fenlei");
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//List<String> list=new ArrayList<>();

		try {
			while(rs.next()){
				int fid=rs.getInt(1);
				String fname=rs.getString(2);
				map.put(fid, fname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, ps, rs);
		}
		
		return map;
	}

	@Override
	public Map<Integer, String> unameMap() {
		// TODO Auto-generated method stub
		connection=DBUtils.getConnection();
//		if(connection!=null)
//			System.out.println("succ");
//		else {
//			System.out.println("err");
//		}
		Map<Integer, String>  map=new HashMap<Integer, String>();
		try {
			ps= connection.prepareStatement("SELECT uid,uname FROM user");
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//List<String> list=new ArrayList<>();

		try {
			while(rs.next()){
				int uid=rs.getInt(1);
				String uname=rs.getString(2);
				map.put(uid, uname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, ps, rs);
		}
		
		return map;
	}

	@Override
	public int updateBook(Book book) {
		// TODO Auto-generated method stub
		connection=DBUtils.getConnection();
		int i=0;
		int bid=book.getBid();
		int fid=book.getFid();
		String bname=book.getBname();
		double price=book.getPrice();
		String press=book.getPress();
		String state=book.getState();
		String pic=book.getPic();
		int uid=book.getUid();
//		System.out.println(book.toString()+12333);
		try {
			ps= connection.prepareStatement("UPDATE book SET fid=?,bname=?,price=?,press=?,state=?,pic=?,uid=? WHERE bid=?");
			ps.setInt(1, fid);
			ps.setString(2, bname);
			ps.setDouble(3, price);
			ps.setString(4, press);
			ps.setString(5, state);
			ps.setString(6, pic);
			ps.setInt(7, uid);
			ps.setInt(8, bid);
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtils.close(connection, ps, rs);
		return i;
		
	}


	@Override
	public int bookCounts(Map<String, String[]> condition) {
    	String sql = "select count(*) from book,user,fenlei where 1=1 and book.fid=fenlei.fid and book.uid=user.uid";
    	StringBuilder sb = new StringBuilder(sql);
    	Set<String> set = condition.keySet();
    	List<Object> parms = new ArrayList<Object>();
    	for(String s : set) {
    		if("currentPage".equals(s) || "rows".equals(s) || "action".equals(s)) {
    			continue;
			}
    		String value = condition.get(s)[0];
    		if(value != null && !"".equals(value) && !"<--图书分类-->".equals(value) && !"<--图书状态-->".equals(value)) {
    			sb.append(" and " + s + " like ? ");
    			parms.add("%" + value + "%");
    			if("借出".equals(value)) {
    				sb.append(" and " + s + " like ? ");
    				parms.add(value);
				}
			}
		}
    	sql = sb.toString();
		System.out.println(sql);

		// TODO Auto-generated method stub
		connection=DBUtils.getConnection();
		int i=0;

		try {
			ps= connection.prepareStatement(sql);
			for(int j = 0; j < parms.size(); j++) {
				ps.setObject(j+1, parms.get(j));
			}
			rs=ps.executeQuery();
			if(rs.next()) {
				i=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtils.close(connection, ps, rs);
		return i;
	}

	@Override
	public List<Book> selectByFid(int fid) {
		connection=DBUtils.getConnection();
		List<Book> list= new ArrayList<Book>();
		try {
			ps= connection.prepareStatement("SELECT bid,fname,bname,price,press,state,pic,uname FROM book,fenlei,user where book.fid=fenlei.fid and book.uid=user.uid and book.fid=?");
			ps.setInt(1, fid);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				int bid=rs.getInt(1);
				String fname=rs.getString(2);
				String bname=rs.getString(3);
				double price=rs.getDouble(4);
				String press=rs.getString(5);
				String state=rs.getString(6);
				String pic=rs.getString(7);
				String uname =rs.getString(8);
				Book book=new Book(bid,fname,bname,price,press,state,pic,uname);
				list.add(book);
				//
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(connection, ps, rs);
		}

		return list;
	}

	@Override
	public List<Book> selectByPage(int start, int rows, Map<String, String[]> condition) {
    	List<Book> list = new ArrayList<Book>();
    	String sql = "select bid,pic,fname,bname,price,press,state from book,fenlei where 1=1 and book.fid=fenlei.fid";
    	StringBuilder sb = new StringBuilder(sql);
    	Set<String> set = condition.keySet();
    	List<Object> parms = new ArrayList<Object>();
    	for (String s : set) {
    		String ss = condition.get(s)[0];
			System.out.println(s + "==>" + ss + ".");
		}
    	for(String key : set) {
    		if("currentPage".equals(key) || "action".equals(key) || "rows".equals(key)) {
    			continue;
			}
    		String value = condition.get(key)[0];
    		if(value != null && !"".equals(value) && !"<--请选择-->".equals(value)){
    			sb.append(" and " + key + " like ? ");
    			parms.add("%" + value + "%");//?的值
			}
		}
    	sb.append(" limit ?,? ");
    	parms.add(start);
    	parms.add(rows);
    	sql = sb.toString();
		System.out.println(sql);
		connection = DBUtils.getConnection();
		try {
			ps = connection.prepareStatement(sql);
			for(int i = 0; i < parms.size(); i++) {
				ps.setObject(i+1, parms.get(i));
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setBid(rs.getInt(1));
				book.setPic(rs.getString(2));
				book.setFname(rs.getString(3));
				book.setBname(rs.getString(4));
				book.setPrice(rs.getDouble(5));
				book.setPress(rs.getString(6));
				book.setState(rs.getString(7));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, ps, rs);
		}
		return list;
	}

	@Override
	public List<Book> gaoJiView(int start, int rows, Map<String, String[]> condition) {
		List<Book> list = new ArrayList<Book>();
		String sql = "select bid,pic,fname,bname,price,press,state,uname from book,user,fenlei where 1=1 and book.fid=fenlei.fid and user.uid=book.uid";
		StringBuilder sb = new StringBuilder(sql);
		Set<String> set = condition.keySet();
		List<Object> parms = new ArrayList<Object>();
		for(String s : set) {
			String ss = condition.get(s)[0];
		}
		for(String key : set) {
			if("currentPage".equals(key) || "rows".equals(key) || "action".equals(key)) {
				continue;
			}
			String value = condition.get(key)[0];
			if(!"".equals(value) && value!=null && !"<--图书分类-->".equals(value) && !"<--图书状态-->".equals(value)) {
				sb.append(" and " + key + " like ? ");
				parms.add("%" + value + "%");//?的值
                if("state".equals(key)) {
                    sb.append(" and " + key + " like ? ");
                    parms.add(value);
                }
			}
		}
		sb.append(" limit ?,? ");
		parms.add(start);
		parms.add(rows);
		System.out.println(sb.toString());
		connection = DBUtils.getConnection();
		try {
			ps = connection.prepareStatement(sb.toString());
			for(int i = 0; i < parms.size(); i++) {
				ps.setObject(i+1, parms.get(i));
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setBid(rs.getInt(1));
				book.setPic(rs.getString(2));
				book.setFname(rs.getString(3));
				book.setBname(rs.getString(4));
				book.setPrice(rs.getDouble(5));
				book.setPress(rs.getString(6));
				book.setState(rs.getString(7));
				book.setUname(rs.getString(8));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, ps, rs);
		}
		return list;
	}

	public int bookCountsByWhere(Book where) {
		connection=DBUtils.getConnection();
		int i=0;

		StringBuilder sb=new StringBuilder("SELECT COUNT(bname) FROM book,fenlei,user WHERE book.fid=fenlei.fid AND book.uid=user.uid");
		List<String> parms=new ArrayList<String>();
		int fid=where.getFid();
		if(fid!=0) {
			sb.append(" and book.fid=?");
			parms.add(fid+"");
		}

		String bname=where.getBname();
		if(bname!=null && !bname.trim().isEmpty()) {
			sb.append(" and bname like ?");
			parms.add("%"+bname+"%");
		}

		String uname=where.getUname();
		if(uname!=null && !uname.trim().isEmpty()) {
			sb.append(" and user.uname like ?");
			parms.add("%"+uname+"%");
		}

		String state=where.getState();
//		System.out.println(state);
		if(!"0".equals(state)) {
			sb.append(" and state = ?");
			parms.add(state);
		}
//		System.out.println(sb.toString());

		try {
			ps= connection.prepareStatement(sb.toString());

			for(int j=0;j<parms.size();j++) {
				ps.setString(j+1, parms.get(j));
			}

			rs=ps.executeQuery();
			if(rs.next())
			{
				i=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtils.close(connection, ps, rs);
		return i;
	}
}
