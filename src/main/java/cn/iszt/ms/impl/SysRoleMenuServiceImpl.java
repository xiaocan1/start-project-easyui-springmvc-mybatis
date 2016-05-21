package cn.iszt.ms.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.iszt.ms.dao.SysRoleMenuDao;
import cn.iszt.ms.domain.SysRoleMenu;
import cn.iszt.ms.service.SysRoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	
	private static Logger logger = LoggerFactory.getLogger(SysRoleMenuServiceImpl.class);
	
	@Resource
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Override
	public void save(SysRoleMenu srm) {
		this.sysRoleMenuDao.save(srm);
	}

	@Override
	public void save(String roleId, String menuId) {
		SysRoleMenu srm = new SysRoleMenu();
		srm.setRoleId(roleId);
		srm.setMenuId(menuId);
		
		this.save(srm);
	}


	/*
	 * 保存时先删除用户的权限菜单，再重新插入
	 * 这是要给完整的事务控制
	 */
	@Override
	@Transactional
	public void save(String roleId, String[] menuIds) {
		if (roleId == null) {
			return;
		}
				
		this.sysRoleMenuDao.deleteByRoleId(roleId);
		
		for (String menuId : menuIds) {
			this.save(roleId, menuId);
		}
	}

	@Override
	public List<String> findMenuIdsByRoleId(String roleId) {
		return this.sysRoleMenuDao.findMenuIdByRoleId(roleId);
	}
	
	

}
