package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.sun.org.apache.xpath.internal.operations.And;

import cn.itcast.entity.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

//	//添加
//	@Override
//	public void save(Customer customer) {
//		this.getHibernateTemplate().save(customer);
//	}
//
//	//查询所有
//	@Override
//	public List<Customer> findAll() {
//		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
//	}
//	
//	//根据id查询
//	@Override
//	public Customer findById(Integer cid) {
//		return this.getHibernateTemplate().get(Customer.class, cid);
//	}
//	
//	//删除客户
//	public void delete(Customer selectCustomer) {
//		this.getHibernateTemplate().delete(selectCustomer);
//	}
//
//	//修改
//	@Override
//	public void update(Customer customer) {
//		this.getHibernateTemplate().update(customer);
//	}
	
	//分页的总记录数
	@Override
	public int findCount() {
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		if (list != null && list.size() != 0) {
			Object ojb = list.get(0);
			//Object 变成int
			Long lo = (Long) ojb;
			int count = lo.intValue();
			return count;
		}
		return 0;
	}
	
	//分页中的每页记录数
	@Override
	public List<Customer> findPage(int begin, int pageSize) {
		//使用离线对象和模版中的方法
		//1.创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//2.调用模版中的方法实现
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		
		return list;
	}

	//条件查询
	public List<Customer> findcodition(Customer customer) {
		//方法一: 
//		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer where custName like ? ", "%"+customer.getCustName()+"%");
		//方法二
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.like("custName","%"+customer.getCustName()+"%"));
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		
		return list;
	}
	
}
