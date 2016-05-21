package cn.iszt.ms.service;

import java.util.List;

import cn.iszt.ms.domain.SysGroup;

public interface SysGroupService {
	
	/**
	 * 增加分组，若分组名已经存在则抛出异常
	 * @param group
	 * @throws DataExistsException
	 * @throws IllegalArgumentException
	 */
	void save(SysGroup group) throws DataExistsException;
	
	/**
	 * 更新
	 * @param group
	 * @throws IllegalArgumentException
	 */
	void update(SysGroup group);
	
	void delete(String groupId);
	
	List<SysGroup> findAll();
	
	SysGroup findById(String groupId);
}
