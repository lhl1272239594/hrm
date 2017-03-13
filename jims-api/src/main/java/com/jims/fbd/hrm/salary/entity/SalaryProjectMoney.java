package com.jims.fbd.hrm.salary.entity;

import com.jims.common.persistence.DataEntity;


/**
 * 工资项目金额Entity
 * @author
 * @version 2016-08-31
 */
public class SalaryProjectMoney extends DataEntity<SalaryProjectMoney> {

	private static final long serialVersionUID = 1L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private String projectId;
	private String projectName;
	private String orgId;
	private String enableFlag;
	private String num;
	private String delFlag;

	private String money;
	private String typeName;
	private String typeId;
	private String createDeptname;
	private String createDept;

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

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}


	@Override
	public String getDelFlag() {
		return delFlag;
	}

	@Override
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	private String psId;

	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}

/*	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}*/

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public SalaryProjectMoney() {
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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










}