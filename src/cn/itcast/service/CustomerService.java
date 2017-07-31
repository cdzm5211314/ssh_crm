package cn.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.CustomerDao;
import cn.itcast.entity.Customer;
import cn.itcast.utils.PageBean;

@Transactional
public class CustomerService {
	
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	public void addCustomer(Customer customer) {
		customerDao.save(customer);
	}
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	public Customer findById(Integer cid) {
		return customerDao.findById(cid);
	}
	public void delete(Customer selectCustomer) {
		customerDao.delete(selectCustomer);
	}
	public void update(Customer customer) {
		customerDao.update(customer);
	}
	
	//封装分页数据到pagebean对象中
	public PageBean listpage(Integer currentPage) {
		PageBean pageBean = new PageBean();
		//封装当前页
		pageBean.setCurrentPage(currentPage);
		//封装总记录数
		int totalCount = customerDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		//每页显示记录数
		int pageSize = 5;
		int totalPage = 0;
		//总页数
		if (totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		pageBean.setTotalPage(totalPage);
		
		 //开始位置
		int begin = (currentPage-1)*pageSize;
		//每页记录的list集合
		List<Customer> list = customerDao.findPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	
	//条件查询
	public List<Customer> findCodition(Customer customer) {
		return customerDao.findcodition(customer);
	}
	//多条件查询
	public List<Customer> findMoreCodition(Customer customer) {
		return customerDao.findMoreCodition(customer);
	}
	
}
