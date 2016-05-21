package cn.iszt.ms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
	
	// 用户管理页面
	@RequestMapping("/sysuser")
	public String sysUser() {
		return "system/user";
	}
	
	// 角色管理页面
	@RequestMapping("/sysrole")
	public String sysRole() {
		return "system/role";
	}
	
	// 菜单管理页面
	@RequestMapping("/sysmenu")
	public String sysMenu() {
		return "system/menu";
	}
	
	// 菜单管理页面
	@RequestMapping("/sysgroup")
	public String sysGroup() {
		return "system/group";
	}
		
	// 修改密码页面
	@RequestMapping("/syspasswd")
	public String sysPasswd() {
		return "system/password";
	}
	
}
