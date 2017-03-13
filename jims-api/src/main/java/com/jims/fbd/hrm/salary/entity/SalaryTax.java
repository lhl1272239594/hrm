package com.jims.fbd.hrm.salary.entity;


import com.jims.common.persistence.DataEntity;


/**
 * 个人所得税税率Entity
 * @author 
 * @version 2016-08-22
 */
public class SalaryTax extends DataEntity<SalaryTax> {

	private static final long serialVersionUID = 1L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	private String taxId;
	private String baseNum;
	private String lowLimit;
	private String highLimit;
	private String rate;
	private String decuteNum;
	private String delFlag;
	private String createDept;
	private String createDeptname;

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

	@Override
	public String getDelFlag() {
		return delFlag;
	}

	@Override
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public SalaryTax(){

	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String orgId;
	private String userName;
	private String enableFlag;
	private String num;

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

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getBaseNum() {
		return baseNum;
	}

	public void setBaseNum(String baseNum) {
		this.baseNum = baseNum;
	}

	public String getLowLimit() {
		return lowLimit;
	}

	public void setLowLimit(String lowLimit) {
		this.lowLimit = lowLimit;
	}

	public String getHighLimit() {
		return highLimit;
	}

	public void setHighLimit(String highLimit) {
		this.highLimit = highLimit;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDecuteNum() {
		return decuteNum;
	}

	public void setDecuteNum(String decuteNum) {
		this.decuteNum = decuteNum;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


}