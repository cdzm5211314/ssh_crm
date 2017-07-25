package cn.itcast.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.usertype.CompositeUserType;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.Customer;
import cn.itcast.service.CustomerService;
import cn.itcast.utils.PageBean;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//属性封装
	private Integer currentPage;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	//分页查询
	public String listpage(){
		
		PageBean pageBean = customerService.listpage(currentPage);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listpageSUCCESS";
	}
	
	//条件查询
																																																																																				
	

	//到添加页面
	public String toAddPage(){
		
		return "toAddPageSUCCESS";
	}
	
	//添加方法
	public String addCustomer(){
		
		customerService.addCustomer(customer);
		//保存数据到值栈
//		ActionContext.getContext().getValueStack().push(customer);
		return "addCustomerSUCCESS";
	}
	
	//客户列表
	public String list(){
		
		List<Customer> list = customerService.findAll();
		//保存查询数据到域对象中
		ServletActionContext.getRequest().setAttribute("list", list);
		return "listSUCCESS";
	}
	
	//删除客户
	public String delete(){
		
		//删除的规范,先根据id查询,然后在删除
		Customer selectCustomer = customerService.findById(customer.getCid());
		if( selectCustomer != null){
			//在根据查询的结果删除客户
			customerService.delete(selectCustomer);
		}
		
		return "deleteSUCCESS";
	}
	
	//跳转到修改页面
	public String showCustomer(){
		
		//先根据id查询
		Customer c = customerService.findById(customer.getCid());
		
		//保存查询的数据到域对象
		ServletActionContext.getRequest().setAttribute("customer", c);
		
		return "showCustomerSUCCESS";
	}
	
	//修改
	public String editCustomer(){
		
		customerService.update(customer);
		
		return "editCustomerSUCCESS";
	}

}
