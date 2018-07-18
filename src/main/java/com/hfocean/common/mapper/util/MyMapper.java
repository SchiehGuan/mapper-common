package com.hfocean.common.mapper.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by guan.sj on 2018/3/5
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
