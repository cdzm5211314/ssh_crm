package cn.itcast.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
	
	//1.上传文件:变量的名称是表单里面文件上传项的name值:如upload
	private File upload;
	//2.上传文件名称:变量的名称是表单里面文件上传项name值+FileName
	private String uploadFileName;
	
	//生成get/set方法
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
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
	public String  addLinkMan() throws Exception{
		//判断是否需要上传文件
		if (upload != null) {
			//需要上传文件--->在服务器上的指定位置创建文件
			File serverFile = new File("F:\\我的下载"+"\\"+uploadFileName);
			//把上传文件内容复制到服务器创建的文件里面
			FileUtils.copyFile(upload, serverFile);
		}
		linkManService.addLinkMan(linkMan);
		return "addLinkManSUCCESS";
	}
	
	//联系人列表
	public String list(){
		//查询所有联系人
		List<LinkMan> list = linkManService.listLinkMan();
		//保存查询结果到域对象
		ServletActionContext.getRequest().setAttribute("list", list);
		return "listSUCCESS";
	}
	
	//到联系人修改页面
	public String showLinkMan(){
		
		LinkMan link = linkManService.findById(linkMan.getLinkid());
		ServletActionContext.getRequest().setAttribute("linkman", link);
		//还需要传所有客户的list集合
		List<Customer> listCustomer = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer",listCustomer);
		
		return "showLinkManSUCCESS";
	}
	//修改联系人
	public String update(){
		
		linkManService.update(linkMan);
		
		return "updateSUCCESS";
	}
	
}
