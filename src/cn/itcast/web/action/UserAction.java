package cn.itcast.web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.entity.User;
import cn.itcast.service.UserService;

public class UserAction extends ActionSupport {
	
//	注入
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	//用户登录
	public String login(){
		
		System.err.println("登录操作....");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User userlogin = userService.login(user);
		if(userlogin != null){
			//保存用户到session中
			ServletActionContext.getRequest().getSession().setAttribute("user", userlogin);
			
			return "loginSUCCESS";
		}else{
			
			return "loginINPUT";
		}
		
	}
	
}
