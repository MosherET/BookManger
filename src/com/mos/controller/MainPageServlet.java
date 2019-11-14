package com.mos.controller;

import com.mos.domain.Book;
import com.mos.domain.PageBean;
import com.mos.domain.User;
import com.mos.service.BookService;
import com.mos.service.Impl.BookServiceImpl;
import com.mos.service.Impl.UserServiceImpl;
import com.mos.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "mainPage", urlPatterns = "/mainPage")
@MultipartConfig
public class MainPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //获取对象
        String action = request.getParameter("action");
        String bookId = request.getParameter("bid");
        String userId = request.getParameter("uid");
        BookService bookService = new BookServiceImpl();
        UserService userService = new UserServiceImpl();
        String _currentPage = request.getParameter("currentPage");
        String _rows = request.getParameter("rows");

        HttpSession session = request.getSession();


        //分页查看图书数据
        if("ViewMain".equals(action)) {
            if(_currentPage == null || "".equals(_currentPage)) {
                _currentPage = "1";
            }
            if(_rows == null || "".equals(_rows)) {
                _rows = "5";
            }
            Map<String, String[]> condition = request.getParameterMap();
            Set<String> s1 = condition.keySet();
            System.out.println(condition);
            for(String s : s1) {
                String value = condition.get(s)[0];
                System.out.println(s + "==>" + value);
            }
            PageBean<Book> pb = bookService.mainView(_currentPage, _rows, condition);
            Map<Integer, String> fnameMap = bookService.fnameList();
            session.setAttribute("fnameMap", fnameMap);
            session.setAttribute("pb", pb);
            response.sendRedirect("MainPage.jsp");
        }
        //借书
        if("BorrowBook".equals(action)) {
            //将id转换类型
            int bid = Integer.parseInt(bookId);
            int uid = Integer.parseInt(userId);
            Book book = bookService.SelectById(bid);
            book.setState("借出");
            book.setUid(uid);
            bookService.updateBook(book);
            //传入信息
            // 1: 表示借书成功
            session.setAttribute("borBookMsg", 1);
            response.sendRedirect("mainPage?action=ViewMain");
        }
        //还书
        if("ReturnBook".equals(action)) {
            //将id转换类型
            int bid = Integer.parseInt(bookId);
            int uid = Integer.parseInt(userId);
            Book book = bookService.SelectById(bid);
            System.out.println("借书人id" + book.getUid() + "用户id" + uid);
            if(uid == book.getUid()) {
                book.setState("未借出");
                book.setUid(1);
                bookService.updateBook(book);
                //传入信息
                // 2：表示还书成功
                session.setAttribute("reBookMsg", 2);
                response.sendRedirect("mainPage?action=ViewMain");
            } else {
                //传入错误
                // 1: 表示借书人和还书人不是同一人
                session.setAttribute("reBookMsg", 1);
                response.sendRedirect("mainPage?action=ViewMain");
            }
        }
        //退出系统
        if("exit".equals(action)) {
            response.sendRedirect("UserLogin.jsp");
            session.removeAttribute("reBookMsg");
            session.removeAttribute("borBookMsg");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
