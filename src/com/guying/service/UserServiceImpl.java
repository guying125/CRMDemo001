package com.guying.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.guying.dao.UserDao;
import com.guying.domain.User;
import com.guying.utils.MD5Utils;

/**
 * 用户的业务层
 * @author QJYang
 *
 */
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 通过登录名进行校验
	 */
	public User checkCode(String user_code) {
		return userDao.checkCode(user_code);
	}

	/**
	 * 保存用户，密码需要进行加密
	 */
	public void save(User user) {
		// 密码加密
		String pwd = user.getUser_password();
		String md5Pwd = MD5Utils.md5(pwd);
		user.setUser_password(md5Pwd);
		// 设置用户的状态
		user.setUser_state("1");
		
		// 调用Dao层的保存方法
		userDao.save(user);
		
	}

	/**
	 * 登录 通过用户名和密码做校验
	 * 	需要先把密码加密，再查询
	 */
	public User login(User user) {
		// 密码加密
		String pwd = user.getUser_password();
		String md5Pwd = MD5Utils.md5(pwd);
		user.setUser_password(md5Pwd);
		
		// 查询
		return userDao.login(user);
	}

	/**
	 * 查询所有
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

	/**
	 * 按主键查找
	 */
	public User findById(Long uId) {
		
		return userDao.findById(uId);
	}
	

}
