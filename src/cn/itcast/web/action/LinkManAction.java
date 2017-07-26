package cn.itcast.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{

	//注入service
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	//注入customerService
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	//到添加联系人页面
	public String toAddPage(){
		//查询所有的数据显示在页面的下拉列表中
		List<Customer> listCustomer = customerService.findAll();
		//保存到域对象中
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		
		return "toAddPageSUCCESS";
	}
	
	//添加联系人信息
	public String  addLinkMan(){
		
		linkManService.addLinkMan(linkMan);
		
		return "addLinkManSUCCESS";
	}


}
