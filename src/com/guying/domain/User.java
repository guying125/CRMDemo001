package com.guying.domain;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User的模块
 * @author QJYang
 *
 */
public class User {

	/*
		`user_id` 		bigint(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  		`user_code` 	varchar(32) NOT NULL COMMENT '用户账号',
  		`user_name` 	varchar(64) NOT NULL COMMENT '用户名称',
  		
  		`user_password` varchar(32) NOT NULL COMMENT '用户密码',
  		`user_state` 	char(1) NOT NULL COMMENT '1:正常,0:暂停',
	 */
	
	// 主键
	private Long user_id;
	
	// 登录名称
	private String user_code;
	
	// 用户姓名
	private String user_name;

	// 用户密码，保存的时候，需要加密处理
	private String user_password;
	
	// 用户的状态	'1:正常,0:暂停'
	private String user_state;
	
	// 和客户拜访关系表配置一对多
	@JSONField(serialize=false)
	private Set<SaleVisit> saleVisits = new HashSet<SaleVisit>();

	public Set<SaleVisit> getSaleVisits() {
		return saleVisits;
	}

	public void setSaleVisits(Set<SaleVisit> saleVisits) {
		this.saleVisits = saleVisits;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_state() {
		return user_state;
	}

	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
	
}
