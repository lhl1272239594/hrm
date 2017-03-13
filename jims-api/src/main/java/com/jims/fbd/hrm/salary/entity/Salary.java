package com.jims.fbd.hrm.salary.entity;


import com.jims.common.persistence.DataEntity;

//import java.beans.Transient;


/**
 * 工资管理Entity
 * @author txb
 * @version 2016-05-31
 * update by chenxy
 * 将服务描述字段类型改为byte[]
 */
public class Salary extends DataEntity<Salary> {

	private static final long serialVersionUID = 1L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private String levelId;
	private String levelName;
	private String typeId;
	private String orgId;
	private String enableFlag;
	private String num;

	public Salary() {
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

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}









}