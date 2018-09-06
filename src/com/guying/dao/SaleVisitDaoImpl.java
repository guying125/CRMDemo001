package com.guying.dao;

import com.guying.domain.SaleVisit;

public class SaleVisitDaoImpl extends BaseDaoImpl<SaleVisit> implements SaleVisitDao {
	
	/**
	 * 按id进行查询
	 */
	public SaleVisit findById(String visit_id) {
		SaleVisit sv = (SaleVisit) this.getHibernateTemplate().find("from SaleVisit where visit_id = ?", visit_id);
		return sv;
	}

}
