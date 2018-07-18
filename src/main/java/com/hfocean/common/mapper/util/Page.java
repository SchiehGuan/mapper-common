package com.hfocean.common.mapper.util;

import java.io.Serializable;

/**
 * Created by guan.sj on 2018/3/15
 */
public class Page implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer page = 1;
    private Integer size = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
