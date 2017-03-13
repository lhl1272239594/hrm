package com.jims.fbd.hrm.salary.entity;

import com.jims.common.persistence.DataEntity;
import com.jims.fbd.hrm.tool.vo.PersonVo;

import java.util.List;


/**
 * 创建工资Entity
 * @author
 * @version 2016-09-22
 */
public class SalaryTmp extends DataEntity<SalaryTmp> {

	private static final long serialVersionUID = 1L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private String tmpId;
	private String projectName;
	private String orgId;
	private String orgName;
	private String item;
	private String typeName;
	private String deptIds;
	private String createDept;
	private String createDeptname;
	private List<PersonVo> personVos;

	public List<PersonVo> getPersonVos() {
		return personVos;
	}

	public void setPersonVos(List<PersonVo> personVos) {
		this.personVos = personVos;
	}

	public List<SalaryTmp> getSalaryTmps() {
		return salaryTmps;
	}

	public void setSalaryTmps(List<SalaryTmp> salaryTmps) {
		this.salaryTmps = salaryTmps;
	}

	private List<SalaryTmp> salaryTmps;
	@Override
	public String getCreateDept() {
		return createDept;
	}

	@Override
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	public String getCreateDeptname() {
		return createDeptname;
	}

	public void setCreateDeptname(String createDeptname) {
		this.createDeptname = createDeptname;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	private String money;
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	private String flag;
	private String salaryMonth;
	private String personId;
	private String personName;
	private String deptId;
	private String typeId;
	private String state;
	private String socialSecuritybase;
	private String socialSecuritycompany;
	private String salaryBefore;
	private String socialSecuritypersonal;
	private String salaryTax;
	private String adjustMoney;
	private String salaryAfter;
	private String adjustReason;
	private String adjustBy;

	public String getAdjustBy() {
		return adjustBy;
	}

	public void setAdjustBy(String adjustBy) {
		this.adjustBy = adjustBy;
	}

	public String getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(String adjustDate) {
		this.adjustDate = adjustDate;
	}

	private String adjustDate;
	private String confirmMan;
	private String comfirmDate;
	private String delFlag;
	@Override
	public String getDelFlag() {
		return delFlag;
	}

	@Override
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}



	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public SalaryTmp() {
	}

	public String getTmpId() {
		return tmpId;
	}

	public void setTmpId(String tmpId) {
		this.tmpId = tmpId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSalaryMonth() {
		return salaryMonth;
	}

	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSocialSecuritybase() {
		return socialSecuritybase;
	}

	public void setSocialSecuritybase(String socialSecuritybase) {
		this.socialSecuritybase = socialSecuritybase;
	}

	public String getSocialSecuritycompany() {
		return socialSecuritycompany;
	}

	public void setSocialSecuritycompany(String socialSecuritycompany) {
		this.socialSecuritycompany = socialSecuritycompany;
	}

	public String getSalaryBefore() {
		return salaryBefore;
	}

	public void setSalaryBefore(String salaryBefore) {
		this.salaryBefore = salaryBefore;
	}

	public String getSocialSecuritypersonal() {
		return socialSecuritypersonal;
	}

	public void setSocialSecuritypersonal(String socialSecuritypersonal) {
		this.socialSecuritypersonal = socialSecuritypersonal;
	}

	public String getSalaryTax() {
		return salaryTax;
	}

	public void setSalaryTax(String salaryTax) {
		this.salaryTax = salaryTax;
	}

	public String getAdjustMoney() {
		return adjustMoney;
	}

	public void setAdjustMoney(String adjustMoney) {
		this.adjustMoney = adjustMoney;
	}

	public String getSalaryAfter() {
		return salaryAfter;
	}

	public void setSalaryAfter(String salaryAfter) {
		this.salaryAfter = salaryAfter;
	}

	public String getAdjustReason() {
		return adjustReason;
	}

	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}



	public String getConfirmMan() {
		return confirmMan;
	}

	public void setConfirmMan(String confirmMan) {
		this.confirmMan = confirmMan;
	}

	public String getComfirmDate() {
		return comfirmDate;
	}

	public void setComfirmDate(String comfirmDate) {
		this.comfirmDate = comfirmDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}










}