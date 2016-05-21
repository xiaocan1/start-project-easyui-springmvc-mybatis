package cn.iszt.ms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.iszt.ms.domain.SysUser;
import cn.iszt.ms.service.SysUserService;
import cn.iszt.ms.util.AppHelper;
import cn.iszt.ms.web.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.iszt.ms.App;

@Controller
@RequestMapping("/sys/user")
@SessionAttributes(App.USER_SESSION_KEY)
public class SysUserController extends BaseController {
	
	@Resource
	private SysUserService sysUserService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<SysUser> list() {
		return this.sysUserService.findAll();		
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Map<String, Object> page(
			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="20") int pageSize,
			@ModelAttribute(App.USER_SESSION_KEY) SysUser user) {
		
		pageNo = pageNo > 0 ? pageNo - 1 : pageNo;
		Pageable pageRequest = new PageRequest(pageNo, pageSize);
		
		Page<SysUser> page = null;
		
		if (user == null) {
			return null;
		}
		
		// 超级管理员才能查全部用户，其他只能查自己机构内部的用户
		if (App.SUPER_ROLE_ID.equals(user.getRoleId())) {
			page = this.sysUserService.findPage(pageRequest);
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("groupId", user.getGroupId());
			page = this.sysUserService.findPage(params, pageRequest);
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("total", page.getTotalElements());
		data.put("rows", page.getContent());
		
		return data;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Result add(SysUser user) throws Exception {
		this.sysUserService.save(user);
		return new Result();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Result update(SysUser user) {
		this.sysUserService.update(user);
		return new Result();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(@RequestParam("userId") String userId) {
		this.sysUserService.delete(userId);
		return new Result();
	}
	
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Result updatePassword(
			@RequestParam("userId") String userId,
			@RequestParam(value = "oldPassword", defaultValue = "") String oldPassword,
			@RequestParam(value = "newPassword", defaultValue = "") String newPassword) {
		
		oldPassword = oldPassword.trim();
		newPassword = newPassword.trim();
		
		if (oldPassword.isEmpty()) {
			throw new IllegalArgumentException("缺少旧密码");
		}
		
		if (newPassword.isEmpty()) {
			throw new IllegalArgumentException("缺少新密码");
		}
		
		if (newPassword.equals(oldPassword)) {
			throw new IllegalArgumentException("新旧密码不能相同");
		}
		
		SysUser user = this.sysUserService.findByUserId(userId);
		if (user != null && AppHelper.encryptPassword(oldPassword).equals(user.getPassword())) {
			this.sysUserService.updatePassword(userId, newPassword.trim());
			return new Result();
		}
		
		return new Result("原密码输入错误");
	}
	
	@RequestMapping("/resetPassword")
	@ResponseBody
	public Result resetPassword(@RequestParam("userId") String userId) {
		
		this.sysUserService.resetPassword(userId);
		return new Result();
	}
	
}
