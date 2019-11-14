package com.mos.domain;

import java.util.List;

public class BookBean<T> {

	private int pageNow;
	private int counts;
	private int pageSize;
	private List<T> beanList;
	private String url;
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getPages() {
		
		int pages=this.counts/this.pageSize;
		
		return this.counts%this.pageSize==0?pages:pages+1;
	}
	
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}


	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
