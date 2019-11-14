package com.mos.controller;

import com.alibaba.excel.EasyExcel;
import com.mos.domain.*;
import com.mos.service.AdminService;
import com.mos.service.BookService;
import com.mos.service.Impl.AdminServiceImpl;
import com.mos.service.Impl.BookServiceImpl;
import com.mos.service.Impl.SortServiceImpl;
import com.mos.service.Impl.UserServiceImpl;
import com.mos.service.SortService;
import com.mos.service.UserService;
import com.mos.utils.DownUtils;
import com.mos.utils.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "BookMangerServlet", urlPatterns = "/BookMangerServlet")
@MultipartConfig
public class BookMangerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        //获取全局参数
        String id = request.getParameter("uid");
        String uname = request.getParameter("uname");
        String uacount = request.getParameter("uacount");
        String upassword = request.getParameter("upassword");
        String usphone = request.getParameter("usphone");
        String regdate = request.getParameter("regdate");
        String fname = request.getParameter("fname");
        String sortid = request.getParameter("fid");
        String apassword = request.getParameter("apassword");
        String aacount = request.getParameter("aacount");
        String aname = request.getParameter("aname");
        String asphone = request.getParameter("asphone");
        String _pageSize = request.getParameter("pageSize");
        String _rows = request.getParameter("rows");
        String _currentPage = request.getParameter("currentPage");

        Date date = new Date();

        //创建对象
        UserService userService = new UserServiceImpl();
        SortService sortService = new SortServiceImpl();
        BookService bookService = new BookServiceImpl();
        AdminService adminService = new AdminServiceImpl();
        HttpSession session = request.getSession();

        //校验用户不存在
        if ("checkAccount".equals(action)){
            User user = userService.SelectByAccout(uacount);
            PrintWriter writer = response.getWriter();
            if (user != null){
                //用户存在
                writer.write("{\"valid\":false}");
                return;
            }else {
                //用户不存在
                writer.write("{\"valid\":true}");
                return;
            }
        }
        //检验账户存在
        if ("checkAccountExist".equals(action)){
            User user = userService.SelectByAccout(uacount);
            PrintWriter writer = response.getWriter();
            if (user != null){
                //用户存在
                writer.write("{\"valid\":true}");
                return;
            }else {
                //用户不存在
                writer.write("{\"valid\":false}");
                return;
            }
        }
        //用户注册和添加用户
        if("UserRegister".equals(action)) {
//获取上传文件
            Part part = request.getPart("photo");
            //获取文件上传名字
            String name = part.getSubmittedFileName();
            //更改文件上传名
            String fileName = null;
            //定义文件保存路径
            String path = this.getServletContext().getRealPath("/uploadFile/userimg");
            //如果不存就创建一个
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            if ("".equals(name)) {
                fileName = "default_image.jpg";
                System.out.println(name + "1");
            } else {
                fileName = this.getServletContext().getRealPath("/uploadFile/userimg") + new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(date) + "_" + name;
                //保存文件
                part.write(path + "/" + fileName);
            }


            //封装参数
            User register = new User();
            //加密密码
            try {
                upassword = Md5Util.encodeByMd5(upassword);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //封装参数
            boolean flag = false;
            register.setUname(uname);
            register.setUacount(uacount);
            register.setUpassword(upassword);
            register.setUsphone(usphone);
            register.setTouxiang(fileName);
            if (regdate == null) {
                flag = true;//用户自行注册
                regdate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            }
            register.setRegdate(regdate);

            //执行添加操作
            userService.add(register);
            request.getSession().setAttribute("register", register);

            //重定向
            if (flag) {
                //用户自行注册
                response.sendRedirect("reg_succ.jsp");
                return;
            } else {
                //管理员修改
                response.sendRedirect(request.getContextPath() + "/BookMangerServlet?action=FenYe&pageSize=5&currentPage=" + _currentPage);
                return;
            }
        }
        //添加用户

        //用户登录方法
        if("UserLogin".equals(action)) {
            //封装参数
            User loginUser = new User();
            loginUser.setUacount(uacount);//封装用户名
            //加密密码
            try {
                upassword = Md5Util.encodeByMd5(upassword);
            } catch (Exception e) {
                e.printStackTrace();
            }
            loginUser.setUpassword(upassword);//封装密码

            User user = userService.userLogin(loginUser);
            if (user != null){
                //验证成功
                session.setAttribute("userLogin", user);
                response.sendRedirect("mainPage?action=ViewMain");
                return;
            }else {
                //验证失败
                response.sendRedirect("UserLogin.jsp");
                return;
            }
        }
        //删除用户
        if("DelUser".equals(action)) {
            System.out.println("待删除用户的ID：" + id);
            int uid = Integer.parseInt(id);
            List<Book> userBook = userService.selectUserBook(uid);
            System.out.println(userBook);
            if (userBook.size() == 0){
                //用户未借书
                userService.Delete(uid);
                response.sendRedirect(request.getContextPath() + "/BookMangerServlet?action=FenYe&pageSize=5&currentPage=" + _currentPage);
                return;
            }else{
                request.getSession().setAttribute("info","1");
                response.sendRedirect(request.getContextPath() + "/BookMangerServlet?action=FenYe&pageSize=5&currentPage=" + _currentPage);
            }
        }
        //修改用户
        if("UpdateUser".equals(action)) {
            //获取上传文件
            Part part = request.getPart("photo");
            //获取文件上传名字
            String name = part.getSubmittedFileName();
            //更改文件上传名
            String fileName = uacount + "_" + new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(date)+ "_"+ name;
            //定义文件保存路径
            String path = this.getServletContext().getRealPath("/uploadFile/userimg/");
            //如果不存就创建一个
            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }
            //保存文件
            part.write(path + "/" + fileName);

            //封装请求参数
            User user = new User();
            //加密密码
            try {
                upassword = Md5Util.encodeByMd5(upassword);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //封装参数
            user.setUid(Integer.parseInt(id));
            user.setUname(uname);
            user.setUpassword(upassword);
            user.setUacount(uacount);
            user.setUsphone(usphone);
            user.setTouxiang(fileName);

            //如果未输入日期则默认当前时间
            if (regdate == null){
                regdate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            }
            user.setRegdate(regdate);
            System.out.println(user);

            //执行修改操作
            userService.Update(user);

            //跳转到查询所有页面
            response.sendRedirect(request.getContextPath() + "/BookMangerServlet?action=FenYe");
        }
        //查看所有用户
        if("ViewUser".equals(action)) {
            List<User> users = userService.SelectAll();
            request.getSession().setAttribute("users",users);
            response.sendRedirect("ViewUser.jsp");
        }
        //分页查看用户
        if("FenYe".equals(action)) {
            //获取页面记录条数和当前页码
            if (_currentPage == null || "".equals(_currentPage)){
                _currentPage = "1";
            }
            if (_pageSize == null || "".equals(_pageSize)){
                _pageSize = "5";
            }
            //计算开始条数
            int pageSize = Integer.parseInt(_pageSize);
            int currentPage = Integer.parseInt(_currentPage);

            //调用service 的方法
            PageBean<User> pageBean = userService.selectByPage(pageSize, currentPage);
            request.getSession().setAttribute("page",pageBean);
            request.getRequestDispatcher("ViewUser.jsp").forward(request,response);
        }
        if("getUser".equals(action)) {
            User user = new User();
            int uid = Integer.parseInt(id);
            System.out.println(uid);
            user = userService.SelectById(uid);
            System.out.println("待修改的用户信息：" + user);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("ModifyUser.jsp?currentPage=" + _currentPage);
        }
        //导出所有用户信息到excel
        if("outputAll".equals(action)) {
            //获取所有用户
            List<User> all = userService.SelectAll();
            for(User user:all){
                System.out.println(user);
            }
            //获取文件输出流
            ServletOutputStream sos = response.getOutputStream();
            //设置文件的mime类型
            response.setContentType("application/vnd.ms-excel");
            //设置默认文件名称
            String fileName = DownUtils.filenameEncoding("全体用户信息表.xls",request);
            //设置响应头
            response.setHeader("Content-Disposition","attachment;filename="+fileName);
            //相应给服务器
            EasyExcel.write(sos,User.class).sheet("用户信息").doWrite(all);
        }
        if("outputPage".equals(action)) {
            //计算开始条数
            int pageSize = Integer.parseInt(_pageSize);
            int currentPage = Integer.parseInt(_currentPage);
            PageBean<User> pageBean = userService.selectByPage(pageSize, currentPage);
            //获取所有用户
            List<User> all = pageBean.getList();
            //获取文件输出流
            ServletOutputStream sos = response.getOutputStream();
            //设置文件的mime类型
            response.setContentType("application/vnd.ms-excel");
            //设置默认文件名称
            String fileName = DownUtils.filenameEncoding("第" + currentPage + "页信息表.xls", request);
            //设置响应头
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            //相应给服务器
            EasyExcel.write(sos, User.class).sheet("用户信息").doWrite(all);

        }
        //添加图书
        if("AddBook".equals(action)) {
            Part part=request.getPart("img");
            String realFileName = part.getSubmittedFileName();
            // 1 获取文件拓展名
            String ext = realFileName.substring(realFileName.lastIndexOf("."),realFileName.length());
            // 2 生成唯一字符串拼接文件名
            String pic = UUID.randomUUID().toString() + ext;
            File upPath = new File(getServletContext().getRealPath("/uploadFile/bookimg/"));
            upPath.mkdirs();
            String realPath = getServletContext().getRealPath("/uploadFile/bookimg/")+pic;
            part.write(realPath);
            String bookname=(String) request.getParameter("bookname");
            String publish=(String) request.getParameter("publish");
            String state="未借出";
            int fid=Integer.parseInt(request.getParameter("fid"));
            double bookprice=Double.parseDouble(request.getParameter("bookprice"));
            Book book=new Book(fid, bookname, bookprice, publish, state,"uploadFile/bookimg/"+pic,1);
            BookService bs=new BookServiceImpl();
            int i=bs.add(book);
            session.setAttribute("add",i);
            response.sendRedirect("BookMangerServlet?action=Book");
        }
        //删除图书
        if("DelBook".equals(action)) {
            int bid=Integer.parseInt(request.getParameter("id"));
            String state=request.getParameter("state");
            System.out.println(state);
            if(state.equals("借出")) {
                session.setAttribute("del",0);
                response.sendRedirect("BookMangerServlet?action=Book");
            }
            else {
                File file=new File(getServletContext().getRealPath(request.getParameter("oldpic")));
                file.delete();
                int i=bookService.Delete(bid);
                session.setAttribute("del",i);
                response.sendRedirect("BookMangerServlet?action=Book");
            }
        }
        //修改图书
        if("ModifyBook".equals(action)) {
            request.setCharacterEncoding("UTF-8");

            Part part=request.getPart("img");
            String realFileName = part.getSubmittedFileName();
            // 1 获取文件拓展名
            String pic=request.getParameter("pic2");
            if(part.getSize()!=0) {
                String ext = realFileName.substring(realFileName.lastIndexOf("."),realFileName.length());
                // 2 生成唯一字符串拼接文件名
                pic = UUID.randomUUID().toString() + ext;
                File upPath = new File(getServletContext().getRealPath("/uploadFile/bookimg/"));
                upPath.mkdirs();
                String realPath = getServletContext().getRealPath("/uploadFile/bookimg/")+pic;
                part.write(realPath);

                File file=new File(getServletContext().getRealPath(request.getParameter("oldpic")));
                file.delete();
                pic="uploadFile/bookimg/"+pic;
            }
            System.out.println(pic);
            int bid=Integer.parseInt(request.getParameter("bid"));
            String bookname=(String) request.getParameter("bname");
            String publish=(String) request.getParameter("press");
            String state=(String) request.getParameter("state");
            int fid=Integer.parseInt(request.getParameter("fid"));
            int uid=Integer.parseInt(request.getParameter("uid"));
            double bookprice=Double.parseDouble(request.getParameter("price"));

            Book book = new Book(bid,fid,bookname,bookprice,publish, state,pic,uid);
            int i=bookService.updateBook(book);
            session.setAttribute("update",i);
            response.sendRedirect("BookMangerServlet?action=Book");
        }
        //查看所有图书
        if("ViewBook".equals(action)) {
            Map<Integer,String> map=bookService.fnameList();
            session.setAttribute("fnameMap",map);
            List<Book> list=bookService.SelectAll();
            request.setAttribute("list",list);

            request.getRequestDispatcher("ViewBook.jsp").forward(request, response);
        }
        //返回修改图书之前的数据
        if("ViewModifyBook".equals(action)) {
            int bid=Integer.parseInt(request.getParameter("id"));
            Book book=bookService.SelectById(bid);
            request.setAttribute("book",book);
            Map<Integer,String> unameMap=bookService.unameMap();
            request.setAttribute("uname",unameMap);
            request.getRequestDispatcher("ModifyBook.jsp").forward(request, response);
        }
        //导出图书信息
        if("DownBookExcel".equals(action)) {
            response.setContentType("application/vnd.ms-excel");
            String filename= DownUtils.filenameEncoding("图书信息表.xlsx", request);

            response.setHeader("Content-Disposition", "attachment;filename="+filename);
            BookService bs=new BookServiceImpl();
            List<Book> data=bs.SelectAll();

            ServletOutputStream so= response.getOutputStream();

            EasyExcel.write(so,Book.class).sheet("图书信息").doWrite(data);
        }
        //导出本页图书信息
        if("DownBenyeInfo".equals(action)) {
            response.setContentType("application/vnd.ms-excel");
            String pageNow=request.getParameter("pageNow");
            String filename=DownUtils.filenameEncoding("第"+pageNow+"页图书信息表.xlsx", request);

            response.setHeader("Content-Disposition", "attachment;filename="+filename);

            PageBean<Book> pb=(PageBean<Book>) request.getSession().getAttribute("pbb");


            List<Book> data=pb.getList();

            ServletOutputStream so= response.getOutputStream();

            EasyExcel.write(so,Book.class).sheet("第"+pageNow+"页图书信息").doWrite(data);
        }

        //分页+高级搜索
        if("Book".equals(action)) {
            if(_currentPage == null || "".equals(_currentPage)) {
                _currentPage = "1";
            }
            if(_rows == null || "".equals(_rows)) {
                _rows = "5";
            }
            Map<String, String[]> condition = request.getParameterMap();
            PageBean<Book> pbb = bookService.gaoJiView(_currentPage, _rows, condition);
            Map<Integer, String> fnameMap = bookService.fnameList();
            session.setAttribute("condition", condition);
            Set<String> s = condition.keySet();
            for(String s1 : s) {
                String value = condition.get(s1)[0];
                System.out.println(s1 + "==>" + value);
            }
            session.setAttribute("pbb", pbb);
            session.setAttribute("fNameMap", fnameMap);
            for(Book b : pbb.getList()) {
                System.out.println(b.toString());
            }
            request.getRequestDispatcher("/ViewBook3.jsp").forward(request,response);
        }
        //跳转到高级搜索
        if("jumpgaoji".equals(action)) {
            response.getWriter().append("Served at: ").append(request.getContextPath());
            String url=request.getParameter("url");
            String pageNow=request.getParameter("pageNow1");
            response.sendRedirect(url+"&pageNow="+pageNow);
        }
        //
        //添加分类
        if("AddSort".equals(action)) {
            Sort s = new Sort(fname);
            int i = sortService.addSort(s);
            if(i>0){
                //添加成功
                response.sendRedirect("BookMangerServlet?action=ViewSortPage");
            }else{

                response.sendRedirect("AddSort.jsp");
            }
        }
        //删除分类
        if("DelSort".equals(action)) {
            String fenleiid = request.getParameter("fid");
            int fid = Integer.parseInt(fenleiid);
            System.out.println(fid);
            System.out.println("Yrs::");
            if(bookService.findBookByFid(fid).isEmpty()) {
                sortService.delSort(fid);
                session.setAttribute("msg1", 0);
                response.sendRedirect("BookMangerServlet?action=ViewSortPage");
            } else {
                session.setAttribute("msg1", 1);
                response.sendRedirect("BookMangerServlet?action=ViewSortPage");
            }
        }
        //获取数据到修改分类界面
        if("ShowModifySort".equals(action)) {
            String fenleiid = request.getParameter("fid");
            int fid = Integer.parseInt(fenleiid);
            Sort sort = new Sort();
            sort.setFid(fid);
            sort.setFname(fname);
            System.out.println(fid+"-"+fname);
            session.setAttribute("modifysort", sort);
            response.sendRedirect("ModifySort.jsp");
        }
        //修改分类
        if("ModifySort".equals(action)) {
            int fid = Integer.parseInt(sortid);
            Sort sort = new Sort(fid, fname);
            sortService.modifySort(sort);
            response.sendRedirect("BookMangerServlet?action=ViewSortPage");
        }
        //校验分类是否存在
        if("verifyFname".equals(action)) {
            boolean flag = sortService.verifyFname(fname);
            if(flag) {
                response.getWriter().write( "{\"valid\":false}");
                return;
            } else {
                response.getWriter().write( "{\"valid\":true}");
                return;
            }
        }
        //查看所有分类
        if("ViewSort".equals(action)) {
            List<Sort> sortList = sortService.viewAll();
            session.setAttribute("sortList", sortList);
        }
        //分页查看分类
        if("ViewSortPage".equals(action)) {
            String currentPage = request.getParameter("currentPage");
            String rows = request.getParameter("rows");
            if(currentPage == null || "".equals(currentPage)) {
                currentPage = "1";
            }
            if(rows == null || "".equals(rows)) {
                rows = "6";
            }
            PageBean<Sort> pb = sortService.selectByPage(currentPage, rows);
            session.setAttribute("pb", pb);
            request.getRequestDispatcher("/ViewSortPage.jsp").forward(request, response);
        }
        //管理员登录
        if("AdminLogin".equals(action)) {
            String pwdMD5 = null;
            try {
                pwdMD5 = Md5Util.encodeByMd5(apassword);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String password = adminService.login(aacount);
            if(password == null) {
                session.setAttribute("msg", "用户名输入错误");
                response.sendRedirect("AdminLogin.jsp");
            } else if(!password.equals(pwdMD5)) {
                session.setAttribute("msg", "密码错误");
                response.sendRedirect("AdminLogin.jsp");
            } else {
                Admin admin = adminService.selectByAacount(aacount);
                session.setAttribute("admin", admin);
                System.out.println(admin);
                response.sendRedirect("index.jsp");
        }


    }
        //管理员注册
        if("AdminRegister".equals(action)) {
            Part adminPic = request.getPart("adminpic");
            String realFileName = adminPic.getSubmittedFileName();
            // 1 获取文件拓展名
            String ext = realFileName.substring(realFileName.lastIndexOf("."),realFileName.length());
            // 2 生成唯一字符串拼接文件名
            String pic = UUID.randomUUID().toString() + ext;
            File upPath = new File(getServletContext().getRealPath("/uploadFile/bookimg/"));
            upPath.mkdirs();
            String realPath = getServletContext().getRealPath("/uploadFile/bookimg/")+pic;
            adminPic.write(realPath);
            Admin admin = new Admin();
            admin.setAname(aname);
            admin.setAacount(aacount);
            admin.setAsphone(asphone);
            admin.setAdminPic("uploadFile/bookimg/"+pic);
            try {
                String password = Md5Util.encodeByMd5(apassword);
                admin.setApassword(password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            adminService.register(admin);
            response.sendRedirect("AdminLogin.jsp");
        }
        //管理员账号名校验
        if("verifyAacount".equals(action)) {
            boolean flag = adminService.verifyAacount(aacount);
            System.out.println("Yes!!");
            if(flag) {
                response.getWriter().write( "{\"valid\":false}");
                return;
            } else {
                response.getWriter().write( "{\"valid\":true}");
                return;
            }
        }
        //校验管理员密码
        if("verifyApassword".equals(action)) {
            Admin admin = (Admin) session.getAttribute("admin");
            String password = admin.getApassword();
            String oldPassword = null;
            try {
                oldPassword = Md5Util.encodeByMd5(request.getParameter("oldPassword"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(!oldPassword.equals(password)) {
                response.getWriter().write( "{\"valid\":false}");
                return;
            } else {
                response.getWriter().write( "{\"valid\":true}");
                return;
            }
        }
        //修改管理员密码
        if("ModifyApassword".equals(action)) {
            Admin admin = (Admin) session.getAttribute("admin");
            String account = admin.getAacount();
            String newPassword = request.getParameter("newPassword");
            String inpwd = null;
            try {
                inpwd = Md5Util.encodeByMd5(newPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int i = adminService.modifyPassword(account, inpwd);
            if(i > 0) {
                admin.setApassword(inpwd);
                session.setAttribute("admin", admin);
            }
            response.sendRedirect("right.jsp");
        }
        if("ExitSystem".equals(action)) {
            session.invalidate();
        }
    }

    private String getURL2(HttpServletRequest request) {
        String url=getURL(request);
        int index=url.lastIndexOf("&pageNow=");
        if(index==-1) {
            return url;
        }

        return url.substring(0, index);
    }

    private String getURL(HttpServletRequest request) {
        String path1=request.getContextPath();
        String path2=request.getServletPath();
        String path3=request.getQueryString();

        return path1+path2+"?"+path3;
    }

//    private Book getWhere(HttpServletRequest request) {
//        Integer fid=Integer.parseInt(request.getParameter("fid"));
//
//        String bname=request.getParameter("bname");
//
//        String uname=request.getParameter("uname");
//
//        String state=request.getParameter("state");
//        System.out.println(state);
//
//        Book book =new Book(fid, bname, state, uname);
//
//        return book;
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public int getPageNow(HttpServletRequest request) {

        String pageNow=request.getParameter("pageNow");

        if(pageNow==null || pageNow.trim().isEmpty()) {

            return 1;
        }

        return Integer.parseInt(pageNow);
    }
}
