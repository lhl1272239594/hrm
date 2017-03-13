package com.jims.fbd.hrm.salary.entity;

import com.jims.common.persistence.DataEntity;
import com.jims.fbd.hrm.evaluation.vo.StandardPersonVo;

import java.util.List;


/**
 * 薪资档案Entity
 * @author 
 * @version 2016-08-31
 */
public class SalaryData extends DataEntity<SalaryData> {

	private static final long serialVersionUID = 1L;

	private String dataId ;
	private String personId ;
	private String personName ;
	private String card ;

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	private String deptId ;
	private String deptName;
	private String cardNo;
	private String sex;
	private String careerId;
	private String inDate;
	private String remark;
	private String orgName;
	private String deptIds;
	private String createDeptname;
	private String createDept;
	private String date;
	private String year;
	private String month;
	private String title;
	private String titleLevel;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleLevel() {
		return titleLevel;
	}

	public void setTitleLevel(String titleLevel) {
		this.titleLevel = titleLevel;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCareerId() {
		return careerId;
	}

	public void setCareerId(String careerId) {
		this.careerId = careerId;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	private String outDate;
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	private String typeId ;
	private String state ;
	private String num ;
	private String orgId ;
	private List<StandardPersonVo> standardPersonVos;
	public List<StandardPersonVo> getStandardPersonVos() {
		return standardPersonVos;
	}

	public void setStandardPersonVos(List<StandardPersonVo> standardPersonVos) {
		this.standardPersonVos = standardPersonVos;
	}
	public List<SalaryData> getUserData() {
		return userData;
	}

	public void setUserData(List<SalaryData> userData) {
		this.userData = userData;
	}

	private List<SalaryData> userData;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
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

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}












}