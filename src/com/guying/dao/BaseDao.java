package com.guying.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.guying.domain.PageBean;
import com.guying.domain.SaleVisit;

/**
 * 定义自己以后需要编写DAO的父类（基类）
 * 	自定义泛型接口
 * @author QJYang
 *
 */
public interface BaseDao<T> {

	public void save(T t);
	
	public void saveOrUpdate(T t);

	public void update(T t);
	
	// 可以创建一个saveOrUpdate()的方法
	
	public boolean delete(T t);
	
	public T findById(Serializable id);
	
	
	public List<T> findAll();
	
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
	
}
