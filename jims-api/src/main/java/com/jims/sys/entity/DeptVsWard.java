/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;


import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 临床科室与病房（区）对照Entity
 * @author zhangpeng
 * @version 2016-04-18
 */
public class DeptVsWard extends DataEntity<DeptVsWard> {
	
	private static final long serialVersionUID = 1L;
	private String hosId;		// 医院id
	private String deptCode;		// 科室代码
	private String wardCode;		// 病房代码
	
	public DeptVsWard() {
		super();
	}

	public DeptVsWard(String id){
		super(id);
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	@Length(min=0, max=8, message="科室代码长度必须介于 0 和 8 之间")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	@Length(min=0, max=8, message="病房代码长度必须介于 0 和 8 之间")
	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	
}