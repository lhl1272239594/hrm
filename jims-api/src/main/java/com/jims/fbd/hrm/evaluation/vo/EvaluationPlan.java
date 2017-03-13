package com.jims.fbd.hrm.evaluation.vo;

import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;


/**
 * 工资人员类别Entity
 * @author ZYG
 * @version 2016-08-31
 */
public class EvaluationPlan extends DataEntity<EvaluationPlan> {

	private static final long serialVersionUID = 1L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private String name;			//计划名称
	private String templetId;		//计划ID
	private String typeId;			//计划类型ID
	private String typeName;		//计划类型名称
	private String self;			//是否自评（0否，1是）
	private String obj;				//对象（1部门2人员3评分人员）
	private String score;			//总分
	private Date startDate;			//开始日期
	private Date endDate;			//结束日期
	private String expiryDate;		//有效期
	private String orgId;			//机构ID
	private String submit;			//已提交数
	private String unsubmit;		//总数
	private String state;			//状态
	private String startType;		//启动类型（1手动2自动）
	private String type;			//1自评，2考评，3普通考评
	private String submit1;			//考评已提交数
	private String unsubmit1;		//考评总数
	private String deptId;			//科室ID

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTempletId() {
		return templetId;
	}

	public void setTempletId(String templetId) {
		this.templetId = templetId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

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

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public String getUnsubmit() {
		return unsubmit;
	}

	public void setUnsubmit(String unsubmit) {
		this.unsubmit = unsubmit;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStartType() {
		return startType;
	}

	public void setStartType(String startType) {
		this.startType = startType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubmit1() {
		return submit1;
	}

	public void setSubmit1(String submit1) {
		this.submit1 = submit1;
	}

	public String getUnsubmit1() {
		return unsubmit1;
	}

	public void setUnsubmit1(String unsubmit1) {
		this.unsubmit1 = unsubmit1;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}