package com.guying.dao;

import java.util.List;

import com.guying.domain.Customer;

public interface CustomerDao extends BaseDao<Customer> {
//	public void save(Customer customer);

//	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

//	public Customer findById(Long cust_id);

//	public boolean delete(Customer customer);

//	public void update(Customer customer);
	
	// 按照行业统计客户数量
	List<Object[]> getIndustryCount();

	// 按照来源统计客户数量
	List<Object[]> getSourceCount();

}
