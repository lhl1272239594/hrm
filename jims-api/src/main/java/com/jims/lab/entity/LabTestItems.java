/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.lab.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 检验项目Entity
 * @author xueyx
 * @version 2016-05-04
 */
public class LabTestItems extends DataEntity<LabTestItems> {
	
	private static final long serialVersionUID = 1L;
	private String testNo;		// test_no
	private Integer itemNo;		// item_no
	private String itemName;		// item_name
	private String itemCode;		// item_code
	private Integer billingIndicator;		// billing_indicator
	private String testBy;		// test_by
	private String rcptNo;		// rcpt_no
	private String explanation;		// 退费说明
	private Double price;		// 费用
	private String parentId;
	private String labMaster; //主表id
	private String orgId;
	public LabTestItems() {
		super();
	}

	public LabTestItems(String id){
		super(id);
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getLabMaster() {
		return labMaster;
	}

	public void setLabMaster(String labMaster) {
		this.labMaster = labMaster;
	}

	@Length(min=0, max=20, message="test_no长度必须介于 0 和 20 之间")
	public String getTestNo() {
		return testNo;
	}

	public void setTestNo(String testNo) {
		this.testNo = testNo;
	}
	
	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	
	@Length(min=0, max=200, message="item_name长度必须介于 0 和 200 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=0, max=40, message="item_code长度必须介于 0 和 40 之间")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public Integer getBillingIndicator() {
		return billingIndicator;
	}

	public void setBillingIndicator(Integer billingIndicator) {
		this.billingIndicator = billingIndicator;
	}
	
	@Length(min=0, max=20, message="test_by长度必须介于 0 和 20 之间")
	public String getTestBy() {
		return testBy;
	}

	public void setTestBy(String testBy) {
		this.testBy = testBy;
	}
	
	@Length(min=0, max=40, message="rcpt_no长度必须介于 0 和 40 之间")
	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}
	
	@Length(min=0, max=200, message="退费说明长度必须介于 0 和 200 之间")
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}