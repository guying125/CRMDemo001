package com.guying.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.guying.domain.Customer;
import com.guying.domain.Linkman;
import com.guying.domain.PageBean;
import com.guying.service.LinkmanService;
import com.opensymphony.xwork2.ModelDriven;

public class LinkmanAction extends BaseAction implements ModelDriven<Linkman> {

	private static final long serialVersionUID = -7074628056506845947L;

	private Linkman linkman = new Linkman();
	public Linkman getModel() {
		return linkman;
	}
	
	private LinkmanService linkmanService;
	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	public String findByPage() {
		// 在这里集齐调用Dao层findByPage()方法的三个参数
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		
		// 获取联系人的名称
		String lkm_name = linkman.getLkm_name();
		if(lkm_name != null && !lkm_name.trim().isEmpty()) {
			// 拼接条件
			criteria.add(Restrictions.like("lkm_name", "%"+lkm_name+"%"));
		}
		
		// 获取客户
		Customer c = linkman.getCustomer();
		if(c != null && c.getCust_id() != null) {
			criteria.add(Restrictions.eq("customer.cust_id", c.getCust_id()));
		}
		
		// 调用业务层
		PageBean<Linkman> pageBean = linkmanService.findByPage(this.getPageCode(),this.getPageSize(),criteria);
		// 压栈
		this.setVs("pageBean", pageBean);
		
		return "page";
	}
	

}
