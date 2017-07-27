package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.LinkMan;

public interface LinkManDao {

	void save(LinkMan linkMan);

	List<LinkMan> findAll();

	LinkMan findById(Integer linkid);

	void update(LinkMan linkMan);

}
