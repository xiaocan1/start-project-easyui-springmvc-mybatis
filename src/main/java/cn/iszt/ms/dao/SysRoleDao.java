package cn.iszt.ms.dao;

import java.util.List;

import cn.iszt.ms.domain.SysRole;

public interface SysRoleDao {
	
	List<SysRole> findAll();
	
	void save(SysRole role);
	
	void update(SysRole role);
	
	void delete(String roleId);
	
}
