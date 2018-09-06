package com.guying.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.guying.domain.Customer;
import com.guying.domain.PageBean;

/**
 * 持久层
 * @author QJYang
 *
 */
@SuppressWarnings("all")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	/**
	 * 按照行业统计客户数量
	 */
	public List<Object[]> getIndustryCount() {
		// 使用原生SQL查询
		List<Object[]> list = this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
			String sql = "SELECT bd.dict_item_name '客户行业', count(*) 'Total'\r\n" + 
					"FROM cst_customer c, base_dict bd\r\n" + 
					"WHERE c.cust_industry = bd.dict_id\r\n" + 
					"GROUP BY bd.dict_item_name;";

			@Override
			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				
				return query.list();
			}
		});
		
		return list;
	}

	/**
	 * 按照来源统计客户数量
	 */
	public List<Object[]> getSourceCount() {
		
		// 也是用原生SQL查询
		List<Object[]> list = this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {

			String sql = "SELECT bd.dict_item_name '客户来源', count(*) 'Total'\r\n" + 
					"FROM cst_customer c, base_dict bd\r\n" + 
					"WHERE c.cust_source = bd.dict_id\r\n" + 
					"GROUP BY bd.dict_item_name;";
			
			@Override
			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
			
		});
		
		return list;
	}

	/**
	 * 保存客户
	
	public void save(Customer customer) {
		System.out.println("持久层：保存客户~");
		// 数据往数据库中保存
//		HibernateTemplate hibernateTemplate = new HibernateTemplate();
//		hibernateTemplate.save(customer);
//		System.out.println(customer);
		this.getHibernateTemplate().save(customer); //extends HibernateDaoSupport
	} */

	/**
	 * 分页查询
	 
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		PageBean<Customer> page = new PageBean<Customer>();
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		// 先查询总记录数
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0) {
			int totalCount = list.get(0).intValue();
			
			page.setTotalCount(totalCount);
		}
		
		// 分页查询数据
		criteria.setProjection(null);
		List<Customer> beanList = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode-1)*pageSize, pageSize);
		page.setBeanList(beanList);
		
		return page;
	}*/

	/**
	 * 通过主键查询客户
	 
	public Customer findById(Long cust_id) {		
		return this.getHibernateTemplate().get(Customer.class, cust_id);
	}*/

	/**
	 * 删除客户
	 
	public boolean delete(Customer customer) {
//		System.out.println("删除前的客户名: "+customer);
		this.getHibernateTemplate().delete(customer);
		// 判断客户是否已被删除
		customer = this.findById(customer.getCust_id());
//		System.out.println("删除后的客户: "+customer);
		if(customer == null)
			return true;
		return false;
	}*/

	/**
	 * 修改客户信息
	 * 	更新客户
	 
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}*/

}
