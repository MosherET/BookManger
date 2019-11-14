package com.mos.service;

import com.mos.domain.Book;
import com.mos.domain.BookBean;
import com.mos.domain.PageBean;

import java.util.List;
import java.util.Map;

public interface BookService {

	Map<Integer, String> fnameList();

	int add(Book book);

	List<Book> SelectAll();

	int Delete(int bid);

	Book SelectById(int bid);

	Map<Integer, String> unameMap();

	int updateBook(Book book);

	List<Book> findBookByFid(int fid);

	//用户主页显示图书信息
    PageBean<Book> mainView(String _currentPage, String _rows, Map<String, String[]> condition);

    PageBean<Book> gaoJiView(String _currentPage, String _rows, Map<String, String[]> condition);
}
