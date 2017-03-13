/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;


import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 药品摆药分类定义Entity
 * @author zhangpeng
 * @version 2016-04-18
 */
public class DrugDispensProperty extends DataEntity<DrugDispensProperty> {
	
	private static final long serialVersionUID = 1L;
	private String hosId;		// 医院id
	private String dispensary;		// 调配药房
	private String drugCode;		// 药品代码
	private String dispensingProperty;		// 摆药类别
	private String drugSpec;		// 药品规格
	private String dispensingCumulate;		// 摆药累积
	private String separable;		// 可分割否
	private String virtualCabinet;		// 虚拟药柜
	
	public DrugDispensProperty() {
		super();
	}

	public DrugDispensProperty(String id){
		super(id);
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	@Length(min=0, max=8, message="调配药房长度必须介于 0 和 8 之间")
	public String getDispensary() {
		return dispensary;
	}

	public void setDispensary(String dispensary) {
		this.dispensary = dispensary;
	}
	
	@Length(min=0, max=20, message="药品代码长度必须介于 0 和 20 之间")
	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	
	@Length(min=0, max=10, message="摆药类别长度必须介于 0 和 10 之间")
	public String getDispensingProperty() {
		return dispensingProperty;
	}

	public void setDispensingProperty(String dispensingProperty) {
		this.dispensingProperty = dispensingProperty;
	}
	
	@Length(min=0, max=20, message="药品规格长度必须介于 0 和 20 之间")
	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}
	
	@Length(min=0, max=1, message="摆药累积长度必须介于 0 和 1 之间")
	public String getDispensingCumulate() {
		return dispensingCumulate;
	}

	public void setDispensingCumulate(String dispensingCumulate) {
		this.dispensingCumulate = dispensingCumulate;
	}
	
	@Length(min=0, max=1, message="可分割否长度必须介于 0 和 1 之间")
	public String getSeparable() {
		return separable;
	}

	public void setSeparable(String separable) {
		this.separable = separable;
	}
	
	@Length(min=0, max=1, message="虚拟药柜长度必须介于 0 和 1 之间")
	public String getVirtualCabinet() {
		return virtualCabinet;
	}

	public void setVirtualCabinet(String virtualCabinet) {
		this.virtualCabinet = virtualCabinet;
	}
	
}