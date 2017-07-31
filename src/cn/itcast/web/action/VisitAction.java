package cn.itcast.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.Customer;
import cn.itcast.entity.User;
import cn.itcast.entity.Visit;
import cn.itcast.service.CustomerService;
import cn.itcast.service.UserService;
import cn.itcast.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{
	
	private Visit visit = new Visit();
	@Override
	public Visit getModel() {
		return visit;
	}
	
	//注入service
	private VisitService visitService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//到新增页面
	public String toAddPage(){
		//查询所有客户
		List<Customer> listCustomer = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		//查询所有用户
		List<User> listUser = userService.findAll(); 
		ServletActionContext.getRequest().setAttribute("listUser", listUser);
		
		return "toAddPageSUCCESS";
	}
	
	//添加拜访信息
	public String addVisit(){
		
		visitService.addVisit(visit);
		
		return "addVisitSUCCESS";
	}

	//到拜访记录列表
	public String list(){
		List<Visit> list = visitService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "listSUCCESS";
	}
	
	
}
