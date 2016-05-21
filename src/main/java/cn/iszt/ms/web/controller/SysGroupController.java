package cn.iszt.ms.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.iszt.ms.domain.SysGroup;
import cn.iszt.ms.domain.SysUser;
import cn.iszt.ms.service.SysGroupService;
import cn.iszt.ms.web.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.iszt.ms.App;

@Controller
@RequestMapping("/sys/group")
@SessionAttributes(App.USER_SESSION_KEY)
public class SysGroupController extends BaseController {
	
	@Resource
	private SysGroupService sysGroupService;
		
	@RequestMapping("/list")
	@ResponseBody
	public List<SysGroup> list() {
		return this.sysGroupService.findAll();
	}

	@RequestMapping("/add")
	@ResponseBody
	public Result add(SysGroup group) throws Exception {
		this.sysGroupService.save(group);
		return new Result();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Result update(SysGroup group) {
		this.sysGroupService.update(group);
		return new Result();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(@RequestParam(value="groupId", required=true) String groupId) {
		this.sysGroupService.delete(groupId);
		return new Result();
	}
	
	// 自己可操作的机构，默认返回自己所在机构，否则返回所有机构
	@RequestMapping("/ownGroups")
	@ResponseBody
	public List<SysGroup> ownGroups(
			@ModelAttribute(App.USER_SESSION_KEY) SysUser user) {

		List<SysGroup> temp = new ArrayList<SysGroup>(1);
		if (user == null) {
			return temp;
		}
		
		if (user.getGroupId() != null && ! user.getGroupId().isEmpty()) {
			temp.add(this.sysGroupService.findById(user.getGroupId()));
			return temp;
		} 
		
		return this.sysGroupService.findAll();
	}
}
