package com.guying.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.guying.domain.Customer;
import com.guying.domain.PageBean;

/**
 * 以后所有的Dao的实现类，都可以继承该类
 * @author QJYang
 *
 * @param <T>
 */
@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	// 定义成员的属性
	private Class clazz;
	
	public BaseDaoImpl() {
		/**
		 * 步骤：
		 * 	1. 通过this，在此构造方法中获取子类的Class对象；
		 * 	2. 获取父类及填充的泛型
		 * 	3. 获取到泛型的具体类
		 */
		// this 表示的是子类，因为是通过子类才加载父类的，c表示的是继承BaseDaoImpl的子类的Class对象（如CustomerDaoImpl）
		// 1.
		Class c = this.getClass();
		// 获取父类，比如 CustomerDaoImpl extends BaseDaoImpl<Customer>
		// 2.获取 BaseDaoImpl<Customer>，Type是Class的接口
		Type type = c.getGenericSuperclass();
		// 把type的类型转换成子接口
		if(type instanceof ParameterizedType) {
			ParameterizedType ptype = (ParameterizedType) type;
			// 3. 获取到这个泛型的具体类（如 Customer）
			Type[] types = ptype.getActualTypeArguments();
			this.clazz = (Class) types[0];
		}
	}

	/**
	 * 添加
	 */
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	/**
	 * 修改/更新
	 */
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	/**
	 * 删除
	 */
	public boolean delete(T t) {
		this.getHibernateTemplate().delete(t);
		
		// 判断是否删除成功，若删去，则再次获取到的是null
		// 目前，我还无法解决！
		
		/**
		customer = this.findById(customer.getCust_id());
//		System.out.println("删除后的客户: "+customer);
		if(customer == null)
			return true;
		return false;
		 */
		return true;
	}

	/**
	 * 通过主键查找
	 */
	public T findById(Serializable id) {
		Object obj = this.getHibernateTemplate().get(clazz, id);
		return (T) obj;
	}
	
	/**
	 * 查询所有
	 */
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from " + clazz.getSimpleName());
	}

	/**
	 * 分页查找
	 */
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		// 创建分页的对象
		PageBean<T> page = new PageBean<T>();
		// 设置其属性
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0) {
			int totalCount = list.get(0).intValue();
			// 总的记录数
			page.setTotalCount(totalCount);
		}
		criteria.setProjection(null);	// select * from 
		List<T> beanList = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode - 1)*pageSize, pageSize);
		// 每页显示的数据
		page.setBeanList(beanList);
		
		return page;
	}


}
