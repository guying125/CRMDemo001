package com.guying.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action的一个父类
 * @author QJYang
 * 
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 3409352955529389370L;

	// 属性驱动的方式，封装当前页
	private Integer pageCode = 1;
	
	public void setPageCode(Integer pageCode) {
		if(pageCode==null) {
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}
	
	public Integer getPageCode() {
		return pageCode;
	}

	// 每页显示的条数
	private Integer pageSize = 2;
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	
	/**
	 * 调用值栈对象的set方法
	 */
	public void setVs(String key, Object obj) {
		ActionContext.getContext().getValueStack().set(key, obj);
	}
	
	/**
	 * 调用值栈对象的push方法
	 */
	public void pushVs(Object obj) {
		ActionContext.getContext().getValueStack().push(obj);
	}
}



















