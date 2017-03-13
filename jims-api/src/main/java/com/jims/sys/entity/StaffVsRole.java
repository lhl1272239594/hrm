/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 组织员工信息Entity
 * @author yangruidong
 * @version 2016-05-31
 */
public class StaffVsRole extends DataEntity<StaffVsRole> {

	private static final long serialVersionUID = 1L;
	private String staffId;		// 人员id
	private String roleId;		// 角色id

    public StaffVsRole() {
    }

    public StaffVsRole(String staffId, String roleId) {
        this.staffId = staffId;
        this.roleId = roleId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}