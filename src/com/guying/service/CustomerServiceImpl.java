package com.guying.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.guying.dao.CustomerDao;
import com.guying.domain.Customer;
import com.guying.domain.PageBean;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	/**
	 * 用来保存客户
	 */
	public void save(Customer customer) {
		System.out.println("业务层:保存客户~~~");
		customerDao.save(customer);
	}


	/**
	 * 分页查询
	 */
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		
		return customerDao.findByPage(pageCode, pageSize, criteria);
	}


	/**
	 * 通过客户id获取客户信息
	 */
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}


	/**
	 * 删除客户
	 */
	public boolean delete(Customer customer) {
		return customerDao.delete(customer);
	}


	/**
	 * 修改客户信息
	 */
	public void update(Customer customer) {
		customerDao.update(customer);
	}


	/**
	 * 查询所有
	 */
	public List<Customer> findAll() {
		return customerDao.findAll();
	}


	/**
	 * 按照行业统计客户数量
	 */
	public List<Object[]> getIndustryCount() {
		
		return customerDao.getIndustryCount();
	}


	/**
	 * 按照来源统计客户数量
	 */
	public List<Object[]> getSourceCount() {
		
		return customerDao.getSourceCount();
	}

}
