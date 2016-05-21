package cn.iszt.ms.service;

import java.util.List;

import cn.iszt.ms.domain.SysRoleMenu;

public interface SysRoleMenuService {
	
	void save(SysRoleMenu srm);
	
	void save(String roleId, String menuId);
	
	void save(String roleId, String[] menuIds);
	
	List<String> findMenuIdsByRoleId(String roleId);
	
}
