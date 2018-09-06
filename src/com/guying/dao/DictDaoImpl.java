package com.guying.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.guying.domain.Dict;

/**
 * 字典的持久层
 * @author QJYang
 *
 */
public class DictDaoImpl extends HibernateDaoSupport implements DictDao {

	/**
	 * 通过客户类别编码查询字典
	 */
	@SuppressWarnings("unchecked")
	public List<Dict> findByCode(String dict_type_code) {
		return (List<Dict>) this.getHibernateTemplate().find("from Dict where dict_type_code = ?", dict_type_code);
	}

}
