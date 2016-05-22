package cn.iszt.ms.web.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.iszt.ms.domain.SysMenu;

public class WebFrontHelper {
	
	public static SysMenu buildMenuTree(List<SysMenu> menus) {
		
		Map<String, SysMenu> menuMap = new LinkedHashMap<String, SysMenu>();
		SysMenu rootMenu = new SysMenu();
		rootMenu.setMenuId("root");
		rootMenu.setMenuName("Root");
		menuMap.put("root", rootMenu);
		
		for (SysMenu menu : menus) {
			menuMap.put(menu.getMenuId(), menu);
		}
		
		for (SysMenu menu : menus) {
			String parentMenuId = menu.getParentMenuId();
			if (parentMenuId == null || "".equals(parentMenuId)) {
				parentMenuId = "root";
			} 
			
			menuMap.get(parentMenuId).addChild(menu);
		}

		return rootMenu;
	}

	public static EasyuiTreeNode buildTreeForEasyuiTree(List<SysMenu> menus, List<String> menuIdsForChecked) {
		
		Map<String, EasyuiTreeNode> map = new LinkedHashMap<String, EasyuiTreeNode>();
		EasyuiTreeNode root = new EasyuiTreeNode();
		try {
			root.setId("root");
			root.setText("Root");
			map.put("root", root);
			
			for (SysMenu menu : menus) {
				EasyuiTreeNode node = new EasyuiTreeNode();
				node.setId(menu.getMenuId());
				node.setText(menu.getMenuName());
				node.setParentId(menu.getParentMenuId());
				node.setAttributes(menu);
				
				map.put(node.getId(), node);
			}
			
			for (Map.Entry<String, EasyuiTreeNode> entry : map.entrySet()) {
				EasyuiTreeNode node = entry.getValue();
				
				if ("root".equals(node.getId())) {
					continue;
				}
				
				String parentId = node.getParentId();
				
				if (parentId == null || "".equals(parentId)) {
					parentId = "root";
				}
				
				EasyuiTreeNode esyNode = map.get(parentId);
				esyNode.addChild(node);
			}
			
					
			if (menuIdsForChecked != null && menuIdsForChecked.size() > 0) {
				for (Map.Entry<String, EasyuiTreeNode> entry : map.entrySet()) {
					EasyuiTreeNode node = entry.getValue();
				
					if (menuIdsForChecked.contains(node.getId())) {
						node.setChecked(true);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return root;
	}
	
	public static EasyuiTreeNode buildTreeForEasyuiTree(List<SysMenu> menus) {
		return buildTreeForEasyuiTree(menus, null);
	}
	
}
