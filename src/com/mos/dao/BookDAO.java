package com.mos.dao;


import com.mos.domain.Book;
import com.mos.domain.BookBean;

import java.util.List;
import java.util.Map;

/**
 * 图书
 */
public interface BookDAO {

    /**
     * 添加图书
     * @param book 参数是传入的图书对象
     *
     *  注意：不包含图书id，此项在数据库中是递增的，无需添加
     *
     * @return 返回数据库中受影响的行数
     */
    int add(Book book);

    /**
     * 根据传入的图书id删除图书
     * @param id 参数是传入的图书id
     * @return 返回数据库中受影响的行数
     */
    int Delete(int id);

    /**
     * 根据传入的图书对象对图书信息进行修改
     * @param book 参数是图书对象
     * @return 返回数据库中受影响的行数
     */

    /**
     * 全查
     * @return 返回包含图书对象的list集合
     */
    List<Book> SelectAll();

    /**
     * 根据传入的图书id查找图书信息
     * @return 返回图书对象
     */
    Book SelectById(int bid);

	Map<Integer, String> fnameList();

	Map<Integer, String> unameMap();

	int updateBook(Book book);

	int bookCounts(Map<String, String[]> condition);

    List<Book> selectByFid(int fid);

    //分页查找图书（包含复杂搜索）
    List<Book> selectByPage(int start, int rows, Map<String, String[]> condition);

    List<Book> gaoJiView(int start, int rows, Map<String, String[]> condition);
}
