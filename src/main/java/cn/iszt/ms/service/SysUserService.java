package cn.iszt.ms.service;

import java.util.List;
import java.util.Map;

import cn.iszt.ms.domain.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SysUserService {
	
	/**
	 * 保存用户
	 * @param user
	 * @throws UserExistsException 用户名存在
	 * @throws IllegalArgumentException
	 */
	void save(SysUser user) throws UserExistsException;
	
	/**
	 * 保存用户
	 * @param user
	 * @throws UserExistsException 用户名存在
	 */
	void update(SysUser user);
	
	void delete(String userId);
	
	SysUser findByUserId(String userId);
	
	List<SysUser> findAll();
	
	Page<SysUser> findPage(Pageable pageRequest);
	
	Page<SysUser> findPage(Map<String, Object> params, Pageable pageRequest);
	
	void updatePassword(String userId, String newPassword);
	
	void resetPassword(String userId);
	
	SysUser loadUserByUsernameAndPassword(String username, String password);
}
