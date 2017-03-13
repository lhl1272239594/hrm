package com.jims.fbd.hrm.evaluation.vo;

import com.jims.common.persistence.DataEntity;


/**
 * 工资人员类别Entity
 * @author ZYG
 * @version 2016-08-31
 */
public class EvaluationType extends DataEntity<EvaluationType> {

	private static final long serialVersionUID = 1L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private String typeName;
	private String orgId;
	private String state;
	private String type;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}