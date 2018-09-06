package com.guying.web.action;

import java.io.File;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.guying.domain.Customer;
import com.guying.domain.PageBean;
import com.guying.domain.SaleVisit;
import com.guying.domain.User;
import com.guying.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends BaseAction implements ModelDriven<SaleVisit>  {

	private static final long serialVersionUID = 5916956284776745837L;

	private SaleVisit saleVisit = new SaleVisit();
	public SaleVisit getModel() {
		return saleVisit;
	}
	
	private SaleVisitService saleVisitService;
	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}


	/**
	 * 添加客户拜访记录
	 * @return
	 */
	public String save() {
		
		// 用户的id（可以 放在页面上的隐藏域中 或者 从 后台直接取出
		// 取出登录用户对象，放入SaleVisit实体，表达关系
		User user = (User) ActionContext.getContext().getSession().get("existUser");
		// 在没有登录校验的时候，需要对用户名进行校验
		// 校验代码，判断当前是否有用户登录
		if(user == null) {
			return "login";
		}
		
		saleVisit.setUser(user);
		
		// 调用service保存客户拜访记录
		saleVisitService.save(saleVisit);
		
		// 重定向至拜访列表Action
		return "toList";
	}
	
	/**
	 * 添加或修改客户拜访记录
	 * @return
	 */
	public String saveOrUpdate() {
		
		// 获取当前记录的uId
		Long uId = saleVisit.getUser().getUser_id();
		System.out.println("------X------X-------uId="+uId);
		// 直接获取用户
		User user = (User) ActionContext.getContext().getSession().get("existUser");
		
		// 校验代码，判断当前是否有用户登录
		if(user==null) {
			return "login";
		}
		
		if(uId == null) {
			System.out.println(uId+"-------为空---------------X---------X---------X------------");
			saleVisit.setUser(user);
		}else {
//			user = new UserDaoImpl().findById(uId);
			// 在没有登录校验的时候，需要对用户名进行校验
			System.out.println("`~~~~~~~~~~~~~~~`~~~~~~~~~~~`~~~~~~~~`~~~~~~~~:"+user.getUser_name());
			System.out.println("ok");
			
		}
		
		// 调用service保存客户拜访记录
		saleVisitService.saveOrUpdate(saleVisit);
		
		// 重定向至拜访列表Action
		return "toList";
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	public String findByPage() {
		// 在这里集齐调用Dao层findByPage()方法的三个参数
		DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);
		
		// 获取业务员的名称
		User user = saleVisit.getUser();
		if(user != null && user.getUser_id() != null) {
			// 拼接条件
			criteria.add(Restrictions.eq("user.user_id", user.getUser_id()));
		}
		
		// 获取客户
		Customer c = saleVisit.getCustomer();
		if(c != null && c.getCust_id() != null) {
			criteria.add(Restrictions.eq("customer.cust_id", c.getCust_id()));
		}
		
		// 调用业务层
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(this.getPageCode(),this.getPageSize(),criteria);
		// 压栈
		this.setVs("pageBean", pageBean);
		return "page";
	}
	
	public String initUpdate() {
//		System.out.println("~前~saleVisit:"+saleVisit);
		// 先获取要修改的记录的id
		String vid = saleVisit.getVisit_id();
		//System.out.println("~~vid~~~~~~~~~~~~~~~:"+vid);
		saleVisit = saleVisitService.findById(vid);
		// 2 将对象放入request域 
		//System.out.println("~后~saleVisit:"+saleVisit);
		ActionContext.getContext().put("saleVisit", saleVisit);
		return "initUpdate";
	}
	
	public String delete() {
		// 获取客户上传文件的路径		（已通过模型驱动封装了客户的id）
			// 先获取到客户信息
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		// 删除salvisit
		saleVisitService.delete(saleVisit);
		
		return "delete";
	}
	

}
