package cn.iszt.ms.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.iszt.ms.dao.SysMenuDao;
import cn.iszt.ms.dao.SysRoleMenuDao;
import cn.iszt.ms.domain.SysMenu;
import cn.iszt.ms.service.SysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysMenuServiceImpl implements SysMenuService {
	
	@Resource
	private SysMenuDao sysMenuDao;
	
	@Resource
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	public List<SysMenu> findAll() {
		return this.sysMenuDao.findAll();
	}

	@Override
	public void save(SysMenu menu) {
		if (menu == null) {
			return;
		}
		
		if (menu.getMenuName() == null || menu.getMenuName().isEmpty()) {
			throw new IllegalArgumentException("menu.name.required");
		}
		
		if (menu.getParentMenuId() != null && 
				"".equals(menu.getParentMenuId().trim())) {
			menu.setParentMenuId(null);
		}
		
		this.sysMenuDao.save(menu);
	}

	@Override
	public void update(SysMenu menu) {
		if (menu == null) {
			return;
		}
		
		if (menu.getMenuId() == null) {
			throw new IllegalArgumentException("menu.id.required");
		}
		
		if (menu.getMenuName() == null || menu.getMenuName().isEmpty()) {
			throw new IllegalArgumentException("menu.name.required");
		}
		
		this.sysMenuDao.update(menu);
	}

	@Override
	@Transactional
	public void delete(String menuId) {
		if (menuId == null) {
			return;
		}
		
		this.sysMenuDao.delete(menuId);
		
		// 删除菜单后再删除角色赋予的菜单
		this.sysRoleMenuDao.deleteByMenuId(menuId);
	}

	@Override
	public List<SysMenu> findByChildId(List<String> menuIds) {
		return this.sysMenuDao.findByChildId(menuIds);
	}

}
