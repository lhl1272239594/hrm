/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 科室属性Entity
 * @author yangruidong
 * @version 2016-04-13
 */
public class OrgDeptPropertyDict extends DataEntity<OrgDeptPropertyDict> {
	
	private static final long serialVersionUID = 1L;
	private String propertyType;		// 属性类型
	private String propertyName;		// 属性名称
	private String propertyValue;		// 属性值
	private String orgId;		// 所属组织
	private Long sort;		// sort
	
	public OrgDeptPropertyDict() {
		super();
	}

	public OrgDeptPropertyDict(String id){
		super(id);
	}

	@Length(min=0, max=100, message="属性类型长度必须介于 0 和 100 之间")
	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	
	@Length(min=0, max=100, message="属性名称长度必须介于 0 和 100 之间")
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	@Length(min=0, max=100, message="属性值长度必须介于 0 和 100 之间")
	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	@Length(min=0, max=64, message="所属组织长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
}