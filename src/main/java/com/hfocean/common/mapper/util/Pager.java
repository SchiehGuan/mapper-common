package com.hfocean.common.mapper.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by guan.sj on 2018/3/5
 */
public class Pager<T> implements Serializable{
	private static final long serialVersionUID = 1L;

	private int page;//页数

    private long totalElements;//总条数

    private long totalPages;//总页数

    private int size;//页大小

    private int numberOfElements;//当前页条数

    private List<T> content;
    
    public Pager() {}
    
	public int getNumberOfElements() {
        return null==content?numberOfElements:content.size();
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    @SuppressWarnings("rawtypes")
	public List getContent() {
        return content;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void setContent(List content) {
        this.content = content;
    }

    @SuppressWarnings("rawtypes")
	public static Pager getEmptyPager(){
        Pager<Object> pager = new Pager<>();
        pager.setPage(1);
        pager.setTotalPages(0);
        pager.setTotalElements(0);
        pager.setSize(10);
        pager.setNumberOfElements(0);
        pager.setContent(Collections.emptyList());
        return pager;
    }

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
