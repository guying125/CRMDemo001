package com.guying.service;

import org.hibernate.criterion.DetachedCriteria;

import com.guying.domain.PageBean;
import com.guying.domain.SaleVisit;

public interface SaleVisitService {

	void save(SaleVisit saleVisit);
	
	void saveOrUpdate(SaleVisit saleVisit);

	PageBean<SaleVisit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

	SaleVisit findById(String visit_id);

	void delete(SaleVisit saleVisit);

}
