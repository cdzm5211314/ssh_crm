package cn.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.LinkManDao;
import cn.itcast.entity.LinkMan;

@Transactional
public class LinkManService {
	
	//注入dao
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	public void addLinkMan(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}
	public List<LinkMan> listLinkMan() {
		// TODO Auto-generated method stub
		return linkManDao.findAll();
	}
	public LinkMan findById(Integer linkid) {
		return linkManDao.findById(linkid);
	}
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}
	public List<LinkMan> moreCodition(LinkMan linkMan) {
		return linkManDao.moreCodition(linkMan);
	}

	
	
	
}
