package com.guying.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.guying.dao.DictDao;
import com.guying.domain.Dict;
import com.guying.domain.PageBean;
import com.guying.domain.SaleVisit;

/**
 * 字典
 * @author QJYang
 *
 */
@Transactional
public class DictServiceImpl implements DictService {

	private DictDao dictDao;

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}

	/**
	 * 通过客户类别的编码 查询字典
	 */
	public List<Dict> findByCode(String dict_type_code) {
		return dictDao.findByCode(dict_type_code);
	}
	
	/**
	 * 分页查询
	 * @param pageCode
	 * @param pageSize
	 * @param criteria
	 * @return
	 */
	public PageBean<SaleVisit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria){
		
		return null;
	}
	
}
