package com.guying.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.guying.domain.Customer;
import com.guying.domain.User;
import com.guying.service.UserService;
import com.guying.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author QJYang
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = -6553429619763943324L;

	// 使用模型驱动的方式封装数据
	private User user = new User();	
	public User getModel() {		
		return user;
	}
	
	// 业务层的代理对象
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 注册功能
	 * 	校验：登录名必须输入，且不能为空，而且判断是否已存在，使用异步请求 AJAX 
	 * 	加密：对密码进行加密存储（MD5）
	 * @return
	 */
	public String regist() {
//		System.out.println("注册");
		// 接收请求参数，模型驱动已经给封装好了，可以直接使用
		userService.save(user);
		
		return LOGIN;
	}
	
	/**
	 * 通过登录名，判断登录名是否已经存在
	 * @return
	 */
	public String checkCode() {
		// 获取传过来的user_code，其实使用模型驱动的方式，已经获取到了，并封装到user中了。
		// 直接向Service层传递
		User u = userService.checkCode(user.getUser_code());
		
		// 获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		try {
			// 获取输出流
			PrintWriter writer = response.getWriter();
			
			if(u != null) {
				// 说明登录名已存在，不能再注册了
				writer.print("no");
			} else {
				// 说明不存在，可以注册
				writer.print("yes");
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 登录功能
	 * @return
	 */
	public String login() {
		// 模型驱动封装了 用户
		User existUser = userService.login(user);
		
		if(existUser == null) {
			return LOGIN;
		} else {
			// 登陆成功
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginOK";
		}
	}
	
	/**
	 * 退出
	 */
	public String quit() {
		ServletActionContext.getRequest().getSession().removeAttribute("existUser");
		return LOGIN;
		
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	public String findAll() {
		List<User> list = userService.findAll();
		// 转换成json
		String jsonString = FastJsonUtil.toJSONString(list);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		
		return NONE;
	}
	

}
