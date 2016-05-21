package cn.iszt.ms.domain;

import java.sql.Date;

/**
 * 系统用户
 * @author Adam
 *
 */
public class SysUser {
	
	private String userId;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String roleId;
	
	private String roleName;
	
	private String groupId;
	
	private String groupName;
	
	private Date addTime;
		
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "SysUser [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", name=" + name + ", roleId="
				+ roleId + ", groupId=" + groupId + ", addTime=" + addTime
				+ ", roleName=" + roleName + "]";
	}
	
	
	
}
