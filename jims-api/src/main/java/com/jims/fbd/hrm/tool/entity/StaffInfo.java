/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.tool.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 组织员工信息Entity
 * @author yangruidong
 * @version 2016-04-13
 */
public class StaffInfo extends DataEntity<StaffInfo> {

	private static final long serialVersionUID = 1L;
	private String deptId;		// 所属科室
	private String orgId;		// 所属组织结构
	private String title;		// 职称
    private String persionId;   //人员id

    public String getPersionId() {
        return persionId;
    }

    public void setPersionId(String persionId) {
        this.persionId = persionId;
    }

    public StaffInfo() {
		super();
	}

	public StaffInfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="所属科室长度必须介于 0 和 64 之间")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Length(min=0, max=64, message="所属组织结构长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Length(min=0, max=20, message="职称长度必须介于 0 和 20 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}