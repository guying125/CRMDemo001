package com.guying.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.guying.domain.User;

/**
 * 用户的持久层，继承HibernateDaoSupport类，该类提供了模板的属性，就不需要手动编写
 * @author QJYang
 *
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	/**
	 * 通过登录名进行校验
	 */
	public User checkCode(String user_code) {
		// 使用模板类进行查询
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ?", user_code);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 保存注册的用户
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 登录功能
	 * 	通过用户名、密码和状态
	 */
	public User login(User user) {
		// 使用 QBC 查询
		// 离线条件查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		// 拼接条件
		criteria.add(Restrictions.eq("user_code", user.getUser_code()));
		criteria.add(Restrictions.eq("user_password", user.getUser_password()));
		criteria.add(Restrictions.eq("user_state", "1"));
		
		// 开始查询
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
