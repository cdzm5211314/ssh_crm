package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.Customer;

public interface CustomerDao {

	void save(Customer customer);

	List<Customer> findAll();

	Customer findById(Integer cid);

	void delete(Customer selectCustomer);

	void update(Customer customer);

	int findCount();

	List<Customer> findPage(int begin, int pageSize);

	List<Customer> findcodition(Customer customer);

}
