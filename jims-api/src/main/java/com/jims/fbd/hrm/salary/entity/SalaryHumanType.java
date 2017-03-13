package com.jims.fbd.hrm.salary.entity;

import com.jims.common.persistence.DataEntity;


/**
 * 工资级别Entity
 * @author
 * @version 2016-08-31
 */
public class SalaryHumanType extends DataEntity<SalaryHumanType> {

	private static final long serialVersionUID = 1L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private String typeId;
	private String typeName;
	private String orgId;
	private String enableFlag;
	private String num;
	private String parentId;
	private String delFlag;
	private String projectId;
	private String createDeptname;
	private String createDept;
	private String deptId;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCreateDeptname() {
		return createDeptname;
	}

	public void setCreateDeptname(String createDeptname) {
		this.createDeptname = createDeptname;
	}

	@Override
	public String getCreateDept() {
		return createDept;
	}

	@Override
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String getDelFlag() {
		return delFlag;
	}

	@Override
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public SalaryHumanType() {
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}









}