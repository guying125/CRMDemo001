package com.guying.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.guying.dao.LinkmanDao;
import com.guying.domain.Linkman;
import com.guying.domain.PageBean;

@Transactional
public class LinkmanServiceImpl implements LinkmanService {
	
	private LinkmanDao linkmanDao;

	public void setLinkmanDao(LinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}

	/**
	 * 分页查询
	 */
	public PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		
		return linkmanDao.findByPage(pageCode, pageSize, criteria);
	}
	

}
