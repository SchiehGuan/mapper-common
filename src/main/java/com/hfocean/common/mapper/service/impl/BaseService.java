package com.hfocean.common.mapper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hfocean.common.mapper.service.IBaseService;
import com.hfocean.common.mapper.util.Page;
import com.hfocean.common.mapper.util.PageExcute;
import com.hfocean.common.mapper.util.Pager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by guan.sj on 2018/3/5
 */
public abstract class BaseService<T> implements IBaseService<T> {

    @Autowired
    protected Mapper<T> mapper;

    @Override
    public T selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int selectCount(T t) {
        return mapper.selectCount(t);
    }

    @Override
    public List<T> select(T t) {
        return mapper.select(t);
    }

    @Override
    public int insert(T t) {
        return mapper.insert(t);
    }

    @Override
    public int insertSelective(T t) {
        return mapper.insertSelective(t);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return mapper.updateByPrimaryKey(t);
    }

    @Override
    public int updateByPrimaryKeySelective(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int delete(T t) {
        return mapper.delete(t);
    }

    @Override
    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int selectCountByExample(Example example) {
        return mapper.selectCountByExample(example);
    }

    @Override
    public List<T> selectByExample(Example example) {
        return mapper.selectByExample(example);
    }

    @Override
    public int deleteByExample(Example example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public int updateByExample(T t, Example example) {
        return mapper.updateByExample(t,example);
    }

    @Override
    public int updateByExampleSelective(T t, Example example) {
        return mapper.updateByExampleSelective(t,example);
    }

    @Override
    public int batchUpdateSelective(T t,Object[] ids) {
        if(null==ids||0==ids.length) throw new RuntimeException("请选择要操作的目标！");
        int result=0;
        if(null!=ids&&ids.length>0){
            Example example = new Example(t.getClass());
            example.createCriteria().andIn("id", Arrays.asList(ids));
            result= mapper.updateByExampleSelective(t, example);
            if(result!=ids.length)throw new RuntimeException("操作失败！");
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public int batchDelete(Class clazz,Object[] ids) {
        if(null==ids||0==ids.length) throw new RuntimeException("请选择要删除的目标！");
        Example example = new Example(clazz);
        example.createCriteria().andIn("id",Arrays.asList(ids));
        return mapper.deleteByExample(example);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public <F> Pager<F> selectByPage(PageExcute<F> pageExcute, Page page) {
        int pageNumber = page.getPage();
        int pageSize = page.getSize();
        PageHelper.startPage(pageNumber,pageSize,true);
        PageInfo<F> pageInfo = new PageInfo(pageExcute.excute());
        Pager<F> pager = new Pager<>();
        pager.setContent(pageInfo.getList());
        pager.setPage(pageInfo.getPageNum());
        pager.setSize(pageInfo.getPageSize());
        pager.setTotalElements(pageInfo.getTotal());
        pager.setTotalPages(pageInfo.getPages());
        return pager;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public <F,R> Pager<R> selectByPage(PageExcute<F> pageExcute, Page page, Function<List<F>,List<R>> fun) {
        Pager<F> fPager = selectByPage(pageExcute, page);
        List content = fPager.getContent();
        List apply = fun.apply(content);
        Pager<R> pager = new Pager<>();
        pager.setContent(apply);
        pager.setPage(fPager.getPage());
        pager.setSize(fPager.getSize());
        pager.setTotalElements(fPager.getTotalElements());
        pager.setTotalPages(fPager.getTotalPages());
        return pager;
    }

    @Override
    public <F, R> Pager<R> selectByPage(PageExcute<F> pageExcute, Page page, Class<R> clazz) {
        Pager<F> fPager = selectByPage(pageExcute, page);
        List<F> content = fPager.getContent();
        List<R> rcontent = new ArrayList<>();
        for(F f : content){
            try {
                R r = clazz.newInstance();
                BeanUtils.copyProperties(f,r);
                rcontent.add(r);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Pager<R> pager = new Pager<>();
        pager.setContent(rcontent);
        pager.setPage(fPager.getPage());
        pager.setSize(fPager.getSize());
        pager.setTotalElements(fPager.getTotalElements());
        pager.setTotalPages(fPager.getTotalPages());
        return pager;
    }
}
