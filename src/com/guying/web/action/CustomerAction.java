package com.guying.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.guying.domain.Customer;
import com.guying.domain.Dict;
import com.guying.domain.PageBean;
import com.guying.service.CustomerService;
import com.guying.utils.FastJsonUtil;
import com.guying.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 客户端的控制层
 * @author QJYang
 *
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private static final long serialVersionUID = 3955765460191134035L;
	
	// 使用模型驱动,封装JavaBean数据,需要手动new
	private Customer customer = new Customer();
	// model是CustomerAction类的属性
	public Customer getModel() {
		return customer;
	}
	
	// 提供service的成员属性,提供setter方法
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	// 属性驱动的方式，封装当前页
	private Integer pageCode = 1;
	
	public void setPageCode(Integer pageCode) {
		if(pageCode==null) {
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}
	
	// 每页显示的条数
	private Integer pageSize = 2;

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 分页查询，列表显示数据
	 * @return
	 */
	public String findByPage() {
		// 调用业务层
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		// 拼接查询的条件
		String cust_name = customer.getCust_name();
		if(cust_name != null && !cust_name.trim().isEmpty()) {
			// 说明客户的名称输入值了
			criteria.add(Restrictions.like("cust_name", "%"+cust_name+"%"));
		}
		
		// 拼接客户的级别
		Dict level = customer.getLevel();
		if(level != null && !level.getDict_id().trim().isEmpty()) {
			// 说明客户的级别选择值了
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
		}
		
		// 拼接客户的来源
		Dict source = customer.getSource();
		if(source != null && !source.getDict_id().trim().isEmpty()) {
			// 说明客户的级别选择值了
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
		}
		
		// 查询
		PageBean<Customer> pageBean = customerService.findByPage(pageCode, pageSize, criteria);
		
		// 压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		// set方法，栈顶是Map集合
		vs.set("pageBean",pageBean);
		
		return "page";
	}
	
	/**
	 * 	初始化到添加页面
	 * @return
	 */
	public String initAddUI() {
		
		return "initAddUI";
	}
	
	/**
	 * 文件的上传，需要在CustomerAction类中定义成员的属性，命名是有规则的！！
	 * 	private File upload;		// 表示要上传的文件
	 * 	private String uploadFileName;	表示是上传文件的名称（没有中文乱码）
	 * 	private String uploadContentType;	表示上传文件的MIME类型
	 * 	提供set方法，拦截器就注入值了
	 */

	// 上传的文件
	private File upload;
	// 上传文件的名称
	private String uploadFileName;
	// 上传文件的MIME类型
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String save() throws IOException {
		System.out.println("WEB层: 保存客户...");
		// uploadFileName不为空，说明有上传文件
		if(uploadFileName != null) {
			System.out.println("文件类型: "+uploadContentType);
			
			// 文件的名称处理一下
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			
			// 把文件上传到 D:\\LearningSoftware\\apache-tomcat-8.5.24\\webapps\\upload\\
			String pathname = "D:\\LearningSoftware\\apache-tomcat-8.5.24\\webapps\\upload\\";
			File file = new File(pathname+uuidName);	// 文件的路径+文件名
			FileUtils.copyFile(upload, file);
			
			// 把上传的文件路径保存到客户表中
			customer.setFilepath(pathname+uuidName);
			
		}
		// WEB的工厂
		/*WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		CustomerService cs = (CustomerService) context.getBean("customerService");
		cs.save(customer);*/
		customerService.save(customer);
		
		return "save";
	}
	
	public String delete() {
		// 获取客户上传文件的路径		（已通过模型驱动封装了客户的id）
			// 先获取到客户信息
		customer = customerService.findById(customer.getCust_id());
			// 获取客户上传文件的路径
		String filepath = customer.getFilepath();
		// 删除客户
		boolean isDelSuccess = customerService.delete(customer);
		
		// 通过文件路径去删除文件
		if(isDelSuccess) {
			File file = new File(filepath);
			if(file.exists()) {
				file.delete();
			}
		}
		return "delete";
	}
	
	/**
	 * 跳转到初始化修改的页面
	 * @return
	 */
	public String initUpdate() {
		// 通过id获取到客户信息
		customer = customerService.findById(customer.getCust_id());
		
		// 压栈，默认customer是压栈的，因为Action默认压栈，model是Action类的属性 getModel() 返回customer对象
		
		return "initUpdate";
	}
	
	/**
	 * 更新客户
	 * @return
	 * @throws IOException
	 */
	public String update() throws IOException {
		// customer已经封装，现在就要判断是否上传了新的文件（如果有，文件的名称、路径和类型也都封装到了Action类的bean属性中）
		if(uploadFileName != null) {
			// 先删除旧得上传文件
			String oldFilepath = customer.getFilepath();
			if(oldFilepath != null && !oldFilepath.trim().isEmpty()) {
				// 有旧文件，删除
				File f = new File(oldFilepath);
				f.delete();
			}
			
			// 上传新的文件
				// 文件名称替换惟一的
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			String pathName = "D:\\LearningSoftware\\apache-tomcat-8.5.24\\webapps\\upload\\";
			File file = new File(pathName+uuidName);
			FileUtils.copyFile(upload, file);
			
			// 把客户新图片的路径更新到数据库中
			customer.setFilepath(pathName+uuidName);
		}
		
		// 调用service层，更新客户信息
		customerService.update(customer);
		
		return "update";
	}
	
	public String findAll() {
		List<Customer> list = customerService.findAll();
		// 转换成json
		String jsonString = FastJsonUtil.toJSONString(list);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		
		return NONE;
	}
	
	public String industryCount() {
		// 调用业务层进行查询
		List<Object[]> industryCount = customerService.getIndustryCount();
		// 把查询结果放到context域中
		ActionContext.getContext().put("list", industryCount);
		ActionContext.getContext().put("flag", "industry");
		
		return "count";
		
	}
	
	public String sourceCount() {
		// 调用业务层进行查询
		List<Object[]> sourceCount = customerService.getSourceCount();
		// 把查询结果放到context域中
		ActionContext.getContext().put("list", sourceCount);
		ActionContext.getContext().put("flag", "source");
		
		return "count";
		
	}
	
}
