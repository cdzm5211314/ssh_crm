package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.Customer;
import cn.itcast.entity.Dict;

public interface CustomerDao extends BaseDao<Customer>{

//	void save(Customer customer);
//
//	List<Customer> findAll();
//
//	Customer findById(Integer cid);
//
//	void delete(Customer selectCustomer);
//
//	void update(Customer customer);

	int findCount();

	List<Customer> findPage(int begin, int pageSize);

	List<Customer> findcodition(Customer customer);

	List<Customer> findMoreCodition(Customer customer);

	List<Dict> findAllDictLevel();

	List findCountSource();

	List findCountLevel();


}
