package cn.itcast.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.annotations.Where;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.sun.org.apache.bcel.internal.generic.Select;
import com.sun.org.apache.xpath.internal.operations.And;

import cn.itcast.entity.Customer;
import cn.itcast.entity.Dict;

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
	
	//多条件查询
	@Override
	public List<Customer> findMoreCodition(Customer customer) {
		//创建离线对象，指定对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//判断条件值是否为空
		if(customer.getCustName()!=null && !"".equals(customer.getCustName())) {
			//设置对属性，设置值
			criteria.add(Restrictions.eq("custName", customer.getCustName()));
		}
//		if(customer.getCustLevel()!=null && !"".equals(customer.getCustLevel())) {
//			criteria.add(Restrictions.eq("custLevel", customer.getCustLevel()));
//		}
		if(customer.getCustSource()!=null && !"".equals(customer.getCustSource())) {
			criteria.add(Restrictions.eq("custSource", customer.getCustSource()));
		}
		
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	//查询所有客户级别
	@Override
	public List<Dict> findAllDictLevel() {
		return (List<Dict>) this.getHibernateTemplate().find("from Dict");
	}
	
	//客户来源统计
	@Override
	public List findCountSource() {
		
		//调用底层sql实现
		//1.获取session
//		this.getHibernateTemplate().getSessionFactory();
		Session session = this.getSessionFactory().getCurrentSession();
		
		//2. 得到SQLQuery
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) num,custSource from t_customer group by custSource");
		
		//改变默认结果中的数组结构为map结构
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		
		//3. 查询结果
		List list =sqlQuery.list();
		
		return list;
	}

	@Override
	public List findCountLevel() {
		
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("select c.num ,d.dname from (select count(*) num,custlevel from t_customer group by custlevel) c,t_dict d where c.custLevel = d.did");
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		
		return sqlQuery.list();
	}
	
}
