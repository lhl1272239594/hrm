/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 角色表Entity
 * @author yangruidong
 * @version 2016-05-31
 */
public class OrgRole extends DataEntity<OrgRole> {

	private static final long serialVersionUID = 1L;
	private String orgId;		// 组织机构id
	private String roleName;		// 角色名称

    public OrgRole() {
    }

    public OrgRole(String orgId, String roleName) {
        this.orgId = orgId;
        this.roleName = roleName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}