package com.jims.fbd.hrm.salary.entity;


import com.jims.common.persistence.DataEntity;

/**
 * 工资计算公式Entity
 * @author 
 * @version 2016-08-31
 */
public class SalaryCalculate extends DataEntity<SalaryCalculate> {

	private static final long serialVersionUID = 1L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private String contentId;
	private String code;
	private String content;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	private String typeName;
	private String partName;
	public String getPartName() {
		return partName;
	}
	private String partId;

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	private String typeId;
	private String delFlag;
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

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	private String deptId;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	private String deptName;
	private String orgId;
	private String enableFlag;
	private String num;

	public SalaryCalculate() {
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

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}