package cn.iszt.ms.web.util;

import java.util.ArrayList;
import java.util.List;

import cn.iszt.ms.domain.SysMenu;

public class EasyuiTreeNode {
	
	private String id;
	private String text;
	private String iconCls;
	private String parentId;
	private List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
	private boolean checked;
	
	private SysMenu attributes;
	
	public void addChild(EasyuiTreeNode node) {
		if (! this.children.contains(node)) {
			this.children.add(node);
		}
	}
	
	public boolean isLeaf() {
		return this.children.isEmpty();
	}
	
	public SysMenu getAttributes() {
		return attributes;
	}

	public void setAttributes(SysMenu attributes) {
		this.attributes = attributes;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public List<EasyuiTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<EasyuiTreeNode> children) {
		this.children = children;
	}
	
	
	
}
