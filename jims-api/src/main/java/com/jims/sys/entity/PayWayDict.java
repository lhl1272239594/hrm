package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 支付方式字典表Entity
 * Created by fyg on 2016/6/23.
 */
public class PayWayDict extends DataEntity<PayWayDict> {

    private static final long serialVersionUID = 1L;
    private String payWayCode;      //支付方式代码
    private String payWayName;      //支付方式名称
    private Integer acctingIndicator;   //记账标志  0，不记账，1记账
    private Integer outpIndicator;      //门诊病人适用标志  0，否，1是
    private Integer inpIndicator;       //住院病人适用标志  0，否，1是
    private String inputCode;       //输入码
    private Integer registIndicator;    //注册标志  0，否，1是
    private String chargeType;      //医保类别
    private String orgId;           //所属组织机构
    private String chargeTypeName;

    public String getChargeTypeName() {
        return chargeTypeName;
    }

    public void setChargeTypeName(String chargeTypeName) {
        this.chargeTypeName = chargeTypeName;
    }

    public String getPayWayCode() {
        return payWayCode;
    }

    public void setPayWayCode(String payWayCode) {
        this.payWayCode = payWayCode;
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    public Integer getAcctingIndicator() {
        return acctingIndicator;
    }

    public void setAcctingIndicator(Integer acctingIndicator) {
        this.acctingIndicator = acctingIndicator;
    }

    public Integer getOutpIndicator() {
        return outpIndicator;
    }

    public void setOutpIndicator(Integer outpIndicator) {
        this.outpIndicator = outpIndicator;
    }

    public Integer getInpIndicator() {
        return inpIndicator;
    }

    public void setInpIndicator(Integer inpIndicator) {
        this.inpIndicator = inpIndicator;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public Integer getRegistIndicator() {
        return registIndicator;
    }

    public void setRegistIndicator(Integer registIndicator) {
        this.registIndicator = registIndicator;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
