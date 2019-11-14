package com.mos.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

public class Book {
	
	@ExcelProperty(value="图书编号",index=0)
	private int bid;
	@ExcelIgnore
	private int fid;
	@ExcelProperty(value="分类名称",index=1)
	private String fname;
	@ExcelProperty(value="图书名称",index=2)
	private String bname;
	@ExcelProperty(value="图书价格",index=3)
	private double price;
	@ExcelProperty(value="出版社",index=4)
	private String press;
	@ExcelProperty(value="图书状态",index=5)
	private String state;
	@ExcelProperty(value="封面地址",index=7)
	private String pic;
	@ExcelIgnore
	private int uid;
	@ExcelProperty(value="借书人",index=6)
	private String uname;

    public Book(Integer fid, String bname, String state, String uname) {
    }


    public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Book(int bid, int fid, String bname, double price, String press, String state) {
		super();
		this.bid = bid;
		this.fid = fid;
		this.bname = bname;
		this.price = price;
		this.press = press;
		this.state = state;
	}
	public Book(int fid, String bname, double price, String press, String state) {
		super();
		this.fid = fid;
		this.bname = bname;
		this.price = price;
		this.press = press;
		this.state = state;
	}
	
	public Book() {
		super();
	}
	
	

	public Book(int fid, String bname, double price, String press, String state,  String pic) {
		super();
		this.fid = fid;
		this.bname = bname;
		this.price = price;
		this.press = press;
		this.state = state;
		this.pic = pic;
	}


	public Book(int fid, String bname, double price, String press, String state,  String pic, int uid) {
		super();
		this.fid = fid;
		this.bname = bname;
		this.price = price;
		this.press = press;
		this.state = state;
		this.pic = pic;
		this.uid = uid;
	}
	public Book(int bid, int fid, String bname, double price, String press, String state, int uid) {
		super();
		this.bid = bid;
		this.fid = fid;
		this.bname = bname;
		this.price = price;
		this.press = press;
		this.state = state;
		this.uid = uid;
	}
	public Book(int bid, int fid, String bname, double price, String press, String state, String pic, int uid) {
		super();
		this.bid = bid;
		this.fid = fid;
		this.bname = bname;
		this.price = price;
		this.press = press;
		this.state = state;
		this.pic = pic;
		this.uid = uid;
	}
	public Book(int bid, String fname, String bname, double price, String press, String state, String pic,
			String uname) {
		super();
		this.bid = bid;
		this.fname = fname;
		this.bname = bname;
		this.price = price;
		this.press = press;
		this.state = state;
		this.pic = pic;
		this.uname = uname;
	}


	@Override
	public String toString() {
		return "Book{" +
				"bid=" + bid +
				", fid=" + fid +
				", fname='" + fname + '\'' +
				", bname='" + bname + '\'' +
				", price=" + price +
				", press='" + press + '\'' +
				", state='" + state + '\'' +
				", pic='" + pic + '\'' +
				", uid=" + uid +
				", uname='" + uname + '\'' +
				'}';
	}
}
