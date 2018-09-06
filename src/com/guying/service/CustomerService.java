package com.guying.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.guying.domain.Customer;
import com.guying.domain.PageBean;

public interface CustomerService {
	
	public void save(Customer customer);

	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

	public Customer findById(Long cust_id);

	public boolean delete(Customer customer);

	public void update(Customer customer);

	public List<Customer> findAll();
	
	// 按照行业统计客户数量
	List<Object[]> getIndustryCount();

	// 按照来源统计客户数量
	List<Object[]> getSourceCount();
	
}
