package com.guying.service;

import java.util.List;

import com.guying.domain.Customer;
import com.guying.domain.User;

public interface UserService {

	User checkCode(String user_code);

	void save(User user);

	User login(User user);

	List<User> findAll();

	User findById(Long uId);

}
