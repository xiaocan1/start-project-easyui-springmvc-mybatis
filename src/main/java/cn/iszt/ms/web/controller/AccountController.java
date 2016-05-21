package cn.iszt.ms.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cn.iszt.ms.domain.SysMenu;
import cn.iszt.ms.domain.SysUser;
import cn.iszt.ms.service.SysMenuService;
import cn.iszt.ms.service.SysRoleMenuService;
import cn.iszt.ms.service.SysUserService;
import cn.iszt.ms.web.util.EasyuiTreeNode;
import cn.iszt.ms.web.util.Result;
import cn.iszt.ms.web.util.WebFrontHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.iszt.ms.App;

import com.google.gson.Gson;

@Controller
public class AccountController {
	
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysMenuService sysMenuService;
	@Resource
	private SysRoleMenuService sysRoleMenuService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginPage(HttpSession session, Model model) {
		SysUser user = (SysUser) session.getAttribute(App.USER_SESSION_KEY);
		
		if (user == null) {			
			return "login";
		}
		
		String roleId = user.getRoleId();
		List<String> roleMenuIds = this.sysRoleMenuService.findMenuIdsByRoleId(roleId);
		
		if (roleMenuIds == null || roleMenuIds.size() == 0) {
			session.invalidate();
			return "grant-tips";
		}
		
		List<SysMenu> allMenus = this.sysMenuService.findByChildId(roleMenuIds);
		
		EasyuiTreeNode root = WebFrontHelper.buildTreeForEasyuiTree(allMenus);
		
		model.addAttribute("treeJson", new Gson().toJson(root.getChildren()));
		
		return "main";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public Result login(
			@RequestParam(value = "username", required=true) String username,
			@RequestParam(value = "password", required=true) String password,
			HttpSession session) {
		
		
		SysUser user = this.sysUserService.loadUserByUsernameAndPassword(username, password);
		
		if (user != null) {
			logger.info("登陆成功：{}", user);
			session.setAttribute(App.USER_SESSION_KEY, user);
			return new Result();
		} else {
			return new Result("用户名密码不匹配");
		}
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("checkSession")
	@ResponseBody
	public Result checkSession(HttpSession session) {
		
		if (session.getAttribute(App.USER_SESSION_KEY) != null) {
			return new Result();
		}
		
		return new Result(false);
	}
	
}
