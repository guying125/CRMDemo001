package com.guying.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.guying.domain.Customer;

public class Demo1 {
	
	@Test
	public void r1() {
		Customer c = new Customer();
		c.setCust_name("华安");
		c.setCust_id(9527L);
		c.setCust_phone("14138");
		
		String jsonString = JSON.toJSONString(c);
		System.out.println(jsonString);
	}
	
	@Test
	public void r2() {
		List<Customer> list = new ArrayList<Customer>();
		
		Customer c = new Customer();
		c.setCust_name("华安");
		c.setCust_id(9527L);
		c.setCust_phone("14138");
		list.add(c);
		
		Customer c2 = new Customer();
		c2.setCust_name("秋香");
		c2.setCust_id(2183L);
		c2.setCust_phone("8123812");
		list.add(c2);
		
		String jsonString = JSON.toJSONString(list);
		System.out.println(jsonString);
	}
	/**
	 * 禁止循环引用
	 */
	@Test
	public void r3() {
		List<Customer> list = new ArrayList<Customer>();
		
		Customer c = new Customer();
		c.setCust_name("华安");
		c.setCust_id(9527L);
		c.setCust_phone("14138");
		list.add(c);
		
		list.add(c);
		
		// 禁止循环引用
		String jsonString = JSON.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(jsonString);
	}
	
	/**
	 * 死循环的问题
	 */
	@Test
	public void r4() {

		Person p = new Person();
		p.setPname("阿花");
		
		Role r = new Role();
		r.setRname("管理员");
		
		p.setRole(r);
		r.setPerson(p);
		
		// 禁止循环引用
		String jsonString = JSON.toJSONString(p,SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(jsonString);
	}
	
	

}
