/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 费别字典Entity
 * @author zhangpeng
 * @version 2016-04-18
 */
public class ChargeTypeDict extends DataEntity<ChargeTypeDict> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 医院id
	private String chargeTypeCode;		// 费别代码
	private String chargeTypeName;		// 费别名称
	private String chargePriceIndicator;		// 享受优惠价格标志    1—享受 0不享受
	private String inputCodeWb;		// 五笔码
	private String isInsur;		// 是否是医保类别   0－非医保 1－医保
	private String groupNo;		// 费别分组号 其中需要强制定义的有:01自费  02医保 03合作医疗  其他可根据需要定义
	private String groupName;		// 费别分组名称
	private String insuranceTypeInq;		// 院长查询用的医保类别  0:自费 1:医保  2:合作医疗
	private Date createDate;		// 创建时间
	
	public ChargeTypeDict() {
		super();
	}

	public ChargeTypeDict(String id){
		super(id);
	}

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Length(min=0, max=1, message="费别代码长度必须介于 0 和 1 之间")
	public String getChargeTypeCode() {
		return chargeTypeCode;
	}

	public void setChargeTypeCode(String chargeTypeCode) {
		this.chargeTypeCode = chargeTypeCode;
	}
	
	@Length(min=0, max=8, message="费别名称长度必须介于 0 和 8 之间")
	public String getChargeTypeName() {
		return chargeTypeName;
	}

	public void setChargeTypeName(String chargeTypeName) {
		this.chargeTypeName = chargeTypeName;
	}
	
	@Length(min=0, max=1, message="享受优惠价格标志长度必须介于 0 和 1 之间")
	public String getChargePriceIndicator() {
		return chargePriceIndicator;
	}

	public void setChargePriceIndicator(String chargePriceIndicator) {
		this.chargePriceIndicator = chargePriceIndicator;
	}
	
	@Length(min=0, max=8, message="五笔码长度必须介于 0 和 8 之间")
	public String getInputCodeWb() {
		return inputCodeWb;
	}

	public void setInputCodeWb(String inputCodeWb) {
		this.inputCodeWb = inputCodeWb;
	}
	
	@Length(min=0, max=1, message="是否是医保类别长度必须介于 0 和 1 之间")
	public String getIsInsur() {
		return isInsur;
	}

	public void setIsInsur(String isInsur) {
		this.isInsur = isInsur;
	}
	
	@Length(min=0, max=1, message="费别分组号长度必须介于 0 和 1 之间")
	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	
	@Length(min=0, max=64, message="费别分组名称长度必须介于 0 和 64 之间")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Length(min=0, max=1, message="院长查询用的医保类别长度必须介于 0 和 1 之间")
	public String getInsuranceTypeInq() {
		return insuranceTypeInq;
	}

	public void setInsuranceTypeInq(String insuranceTypeInq) {
		this.insuranceTypeInq = insuranceTypeInq;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}