/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 功能按钮菜单Entity
 * @author yangchen
 * @version 2016-08-17
 */
public class ButtonDict extends DataEntity<ButtonDict> {

	private static final long serialVersionUID = 1L;
	private String menuName;		// 菜单名称
	private String href;		// 链接地址
	private String icon;		// 图标
	private Long sort;		// 排序
	private String target;		// 打开方式
    private String pid; //父id
    private String menuLevel;//菜单级别
	private String type;//菜单按钮类型
	private String permission;//权限标识

    private List<ButtonDict> children;  // 子节点

    public List<ButtonDict> getChildren() {
        return children;
    }

    public void setChildren(List<ButtonDict> children) {
        this.children = children;
    }

    public ButtonDict() {
		super();
	}

	public ButtonDict(String id){
		super(id);
	}

	@Length(min=0, max=20, message="菜单名称长度必须介于 0 和 20 之间")
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	@Length(min=0, max=300, message="链接地址长度必须介于 0 和 300 之间")
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	@Length(min=0, max=100, message="图标长度必须介于 0 和 100 之间")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=100, message="打开方式长度必须介于 0 和 100 之间")
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}