package com.guying.service;

import org.hibernate.criterion.DetachedCriteria;

import com.guying.domain.Linkman;
import com.guying.domain.PageBean;

public interface LinkmanService {

	PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

}
