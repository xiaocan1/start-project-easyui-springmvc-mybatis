package cn.iszt.ms.dao;

import java.util.List;

import cn.iszt.ms.domain.SysMenu;

public interface SysMenuDao {
	
	List<SysMenu> findAll();
	
	void save(SysMenu menu);
	
	void update(SysMenu menu);
	
	void delete(String menuId);
	
	List<SysMenu> findByChildId(List<String> menuId);
}
