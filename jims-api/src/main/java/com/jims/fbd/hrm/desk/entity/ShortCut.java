/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.desk.entity;

import com.jims.common.persistence.DataEntity;


public class ShortCut extends DataEntity<ShortCut> {

	private static final long serialVersionUID = 1L;
	private String menuName;		// 菜单名称
	private String personId;		// 人员ID
	private String orgId;	        // 机构ID
    private String deptId;   		// 部门ID
    private String iconUrl;   		// 图片地址

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
}