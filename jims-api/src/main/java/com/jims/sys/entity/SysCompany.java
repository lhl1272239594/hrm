/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import com.jims.register.entity.OrgServiceList;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 组织结构Entity
 * @author yangruidong
 * @version 2016-04-13
 */
public class SysCompany extends DataEntity<SysCompany> {
	
	private static final long serialVersionUID = 1L;
	private String parentId;		// 父机构ID
	private String orgName;		// 组织结构名称
	private String orgCode;		// 组织结构代码
	private String zipCode;		// zip_code
	private String linkMan;		// 联系人
	private String linkPhoneNum;		// 联系电话
	private String applyStatus;		// 申请状态：0，暂存；1，提交待审核，2，通过 ，-1 审核失败
	private String address;		// 组织结构地址
	private String email;		// EMAIL地址
	private String owner;		// 超级管理员

	private List<OrgServiceList> serviceList;  // 机构定制的服务

	private List<SysService> sysServiceList;//基础服务表
	private List<List<SysServicePrice>> sysServicePriceList;  // 服务价格

	public List<List<SysServicePrice>> getSysServicePriceList() {
		return sysServicePriceList;
	}

	public void setSysServicePriceList(List<List<SysServicePrice>> sysServicePriceList) {
		this.sysServicePriceList = sysServicePriceList;
	}

	public List<SysService> getSysServiceList() {
		return sysServiceList;
	}

	public void setSysServiceList(List<SysService> sysServiceList) {
		this.sysServiceList = sysServiceList;
	}
    public List<OrgServiceList> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<OrgServiceList> serviceList) {
        this.serviceList = serviceList;
    }

    public SysCompany() {
		super();
	}

	public SysCompany(String id){
		super(id);
	}

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Length(min=0, max=50, message="组织结构名称长度必须介于 0 和 50 之间")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@Length(min=0, max=20, message="组织结构代码长度必须介于 0 和 20 之间")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Length(min=0, max=20, message="zip_code长度必须介于 0 和 20 之间")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	
	@Length(min=0, max=11, message="联系电话长度必须介于 0 和 11 之间")
	public String getLinkPhoneNum() {
		return linkPhoneNum;
	}

	public void setLinkPhoneNum(String linkPhoneNum) {
		this.linkPhoneNum = linkPhoneNum;
	}
	
	@Length(min=0, max=4, message="申请状态：0，暂存；1，提交待审核，2，通过 ，-1 审核失败长度必须介于 0 和 4 之间")
	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	@Length(min=0, max=1000, message="组织结构地址长度必须介于 0 和 1000 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=50, message="EMAIL地址长度必须介于 0 和 50 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=64, message="超级管理员长度必须介于 0 和 64 之间")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}