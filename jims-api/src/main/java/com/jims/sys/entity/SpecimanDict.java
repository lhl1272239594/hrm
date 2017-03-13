/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 标本字典Entity
 * @author xueyx
 * @version 2016-05-04
 */
public class SpecimanDict extends DataEntity<SpecimanDict> {
	
	private static final long serialVersionUID = 1L;
	private Integer serialNo;		// 序号
	private String specimanCode;		// 标本代码
	private String specimanName;		// 标本名称
	private String inputCode;		// 输入码
	private String deptCode;		// 科室代码
	
	public SpecimanDict() {
		super();
	}

	public SpecimanDict(String id){
		super(id);
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	
	@Length(min=1, max=4, message="标本代码长度必须介于 1 和 4 之间")
	public String getSpecimanCode() {
		return specimanCode;
	}

	public void setSpecimanCode(String specimanCode) {
		this.specimanCode = specimanCode;
	}
	
	@Length(min=0, max=16, message="标本名称长度必须介于 0 和 16 之间")
	public String getSpecimanName() {
		return specimanName;
	}

	public void setSpecimanName(String specimanName) {
		this.specimanName = specimanName;
	}
	
	@Length(min=0, max=16, message="输入码长度必须介于 0 和 16 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
	@Length(min=0, max=16, message="科室代码长度必须介于 0 和 16 之间")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
}