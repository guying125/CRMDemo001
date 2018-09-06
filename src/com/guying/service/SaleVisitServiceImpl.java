package com.guying.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.guying.dao.SaleVisitDao;
import com.guying.domain.Linkman;
import com.guying.domain.PageBean;
import com.guying.domain.SaleVisit;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {

	private SaleVisitDao saleVisitDao;
	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}


	/**
	 * 保存客户拜访记录
	 */
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}


	/**
	 * 保存或更新客户拜访记录
	 */
	public void saveOrUpdate(SaleVisit saleVisit) {
		saleVisitDao.saveOrUpdate(saleVisit);
	}


	/**
	 * 分页查询
	 */
	public PageBean<SaleVisit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		
		return saleVisitDao.findByPage(pageCode, pageSize, criteria);
	}


	/**
	 * 按id进行查询
	 */
	public SaleVisit findById(String visit_id) {
		SaleVisit sv = saleVisitDao.findById(visit_id);
		return sv;
	}


	/**
	 * 删除
	 */
	public void delete(SaleVisit saleVisit) {
		saleVisitDao.delete(saleVisit);
	}
	
}
