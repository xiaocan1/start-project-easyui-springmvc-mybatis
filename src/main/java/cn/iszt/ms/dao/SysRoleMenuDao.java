package cn.iszt.ms.dao;

import java.util.List;

import cn.iszt.ms.domain.SysRoleMenu;

public interface SysRoleMenuDao {
	
	void save(SysRoleMenu srm);
	
	void deleteByRoleId(String roleId);
	
	List<String> findMenuIdByRoleId(String roleId);
	
	void deleteByMenuId(String menuId);
}
