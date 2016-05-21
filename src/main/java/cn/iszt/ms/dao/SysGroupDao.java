package cn.iszt.ms.dao;

import java.util.List;

import cn.iszt.ms.domain.SysGroup;

public interface SysGroupDao {
	
	void save(SysGroup group);
	
	void delete(String groupId);
	
	void update(SysGroup group);
	
	List<SysGroup> findAll();
	
	SysGroup findById(String groupId);
	
	SysGroup findByGroupName(String groupName);
	
}
