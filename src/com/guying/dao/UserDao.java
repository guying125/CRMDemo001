package com.guying.dao;

import com.guying.domain.User;

public interface UserDao extends BaseDao<User> {

	User checkCode(String user_code);

	void save(User user);

	User login(User user);

}
