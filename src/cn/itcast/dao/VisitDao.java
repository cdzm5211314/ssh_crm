package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.Visit;

public interface VisitDao {

	void save(Visit visit);

	List<Visit> findAll();

}
