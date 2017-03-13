/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 身份与费别对照Entity
 * @author zhangpeng
 * @version 2016-04-18
 */
public class ChargeTypeVsIdentity extends DataEntity<ChargeTypeVsIdentity> {
	
	private static final long serialVersionUID = 1L;
	private String hosId;		// 医院id
	private String chargeType;		// 费别
	private String identitySerialNo;		// 身份
	private String identity;		// 身份序号
	
	public ChargeTypeVsIdentity() {
		super();
	}

	public ChargeTypeVsIdentity(String id){
		super(id);
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	@Length(min=0, max=8, message="费别长度必须介于 0 和 8 之间")
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	
	@Length(min=0, max=2, message="身份长度必须介于 0 和 2 之间")
	public String getIdentitySerialNo() {
		return identitySerialNo;
	}

	public void setIdentitySerialNo(String identitySerialNo) {
		this.identitySerialNo = identitySerialNo;
	}
	
	@Length(min=0, max=10, message="身份序号长度必须介于 0 和 10 之间")
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
}