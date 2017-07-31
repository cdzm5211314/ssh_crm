package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {
	
	//添加联系人信息
	@Override
	public void save(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}
	
	//查询联系人列表
	@SuppressWarnings("all")
	@Override
	public List<LinkMan> findAll() {
		return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
	}
	//根据id 查询联系人信息
	@Override
	public LinkMan findById(Integer linkid) {
		return this.getHibernateTemplate().get(LinkMan.class, linkid);
	}
	
	//修改联系数据
	@Override
	public void update(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);
	}
	
	//联系人多条件查询
	@Override
	public List<LinkMan> moreCodition(LinkMan linkMan) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		if(linkMan.getLkmName() != null && !"".equals(linkMan.getLkmName())){
			criteria.add(Restrictions.eq("lkmName",linkMan.getLkmName()));
		}
		//判断是否选择客户
		if(linkMan.getCustomer().getCid() != null && linkMan.getCustomer().getCid()>0){
			criteria.add(Restrictions.eq("customer.cid",linkMan.getCustomer().getCid()));
			
		}
		
			 return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	
	
	
}
