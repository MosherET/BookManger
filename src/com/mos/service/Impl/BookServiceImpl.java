package com.mos.service.Impl;

import java.util.List;
import java.util.Map;


import com.mos.dao.BookDAO;
import com.mos.dao.Impl.BookDAOImpl;
import com.mos.domain.Book;
import com.mos.domain.BookBean;
import com.mos.domain.PageBean;
import com.mos.service.BookService;

public class BookServiceImpl implements BookService {

	BookDAO bd=new BookDAOImpl();
	
	@Override
	public Map<Integer, String> fnameList() {
		// TODO Auto-generated method stub
		return bd.fnameList();
	}

	@Override
	public int add(Book book) {
		// TODO Auto-generated method stub
		return bd.add(book);
	}

	@Override
	public List<Book> SelectAll() {
		// TODO Auto-generated method stub
		return bd.SelectAll();
	}

	@Override
	public int Delete(int bid) {
		// TODO Auto-generated method stub
		return bd.Delete(bid);
	}

	@Override
	public Book SelectById(int bid) {
		// TODO Auto-generated method stub
		return bd.SelectById(bid);
	}

	@Override
	public Map<Integer, String> unameMap() {
		// TODO Auto-generated method stub
		return bd.unameMap();
	}

	@Override
	public int updateBook(Book book) {
		// TODO Auto-generated method stub
		return bd.updateBook(book);
	}

	@Override
	public List<Book> findBookByFid(int fid) {
		return bd.selectByFid(fid);
	}

	@Override
	public PageBean<Book> mainView(String _currentPage, String _rows, Map<String, String[]> condition) {
		int currentPage = Integer.parseInt(_currentPage);
		int rows = Integer.parseInt(_rows);
		if(currentPage <= 0) {
			currentPage = 1;
		}
		//创建空对象
		PageBean<Book> pb = new PageBean<Book>();
		pb.setCurrentPage(currentPage);
		pb.setRows(rows);
		//查询总记录数
		int totalCount = bd.bookCounts(condition);
		System.out.println("总记录数：" + totalCount);
		//计算开始的suoyin
		int start = (currentPage - 1) * rows;
		List<Book> list = bd.selectByPage(start, rows, condition);
		pb.setList(list);
		//计算总页码
		int totalPage = totalCount % rows == 0 ? (totalCount/rows) : (totalCount/rows)+1;
		pb.setTotalPage(totalPage);
		return pb;
	}


	@Override
	public PageBean<Book> gaoJiView(String _currentPage, String _rows, Map<String, String[]> condition) {
		int currentPage = Integer.parseInt(_currentPage);
		int rows = Integer.parseInt(_rows);
		if(currentPage <= 0) {
			currentPage = 1;
		}
		//创建空对象
		PageBean<Book> pb = new PageBean<Book>();
		pb.setCurrentPage(currentPage);
		pb.setRows(rows);
		//查询总记录数
		int totalCount = bd.bookCounts(condition);
		System.out.println("总记录数：" + totalCount);
		//计算开始的suoyin
		int start = (currentPage - 1) * rows;
		List<Book> list = bd.gaoJiView(start, rows, condition);
		pb.setList(list);
		//计算总页码
		int totalPage = totalCount % rows == 0 ? (totalCount/rows) : (totalCount/rows)+1;
		pb.setTotalPage(totalPage);
		return pb;
	}


}
