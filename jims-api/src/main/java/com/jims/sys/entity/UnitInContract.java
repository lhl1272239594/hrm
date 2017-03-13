/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 合同单位记录Entity
 * @author zhangpeng
 * @version 2016-04-18
 */
public class UnitInContract extends DataEntity<UnitInContract> {

    private static final long serialVersionUID = 1L;
    private String unitName;		// 合同单位名称
    private String inputCode;		// 输入码
    private String address;		// 单位地址
    private String unitType;		// 单位性质
    private String subordinateTo;		// 隶属单位
    private String distanceToHospital;		// 就医距离
    private String regularNum;		// 在编人数
    private String tempNum;		// 非编人数
    private String retiredNum;		// 离退休人数
    private String inputCodeWb;		// 五笔码
    private String orgId;

    public UnitInContract() {
        super();
    }

    public UnitInContract(String id){
        super(id);
    }

    @Length(min=0, max=40, message="合同单位名称长度必须介于 0 和 40 之间")
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Length(min=0, max=8, message="输入码长度必须介于 0 和 8 之间")
    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    @Length(min=0, max=40, message="单位地址长度必须介于 0 和 40 之间")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Length(min=0, max=1, message="单位性质长度必须介于 0 和 1 之间")
    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    @Length(min=0, max=11, message="隶属单位长度必须介于 0 和 11 之间")
    public String getSubordinateTo() {
        return subordinateTo;
    }

    public void setSubordinateTo(String subordinateTo) {
        this.subordinateTo = subordinateTo;
    }

    public String getDistanceToHospital() {
        return distanceToHospital;
    }

    public void setDistanceToHospital(String distanceToHospital) {
        this.distanceToHospital = distanceToHospital;
    }

    @Length(min=0, max=4, message="在编人数长度必须介于 0 和 4 之间")
    public String getRegularNum() {
        return regularNum;
    }

    public void setRegularNum(String regularNum) {
        this.regularNum = regularNum;
    }

    @Length(min=0, max=4, message="非编人数长度必须介于 0 和 4 之间")
    public String getTempNum() {
        return tempNum;
    }

    public void setTempNum(String tempNum) {
        this.tempNum = tempNum;
    }

    @Length(min=0, max=4, message="离退休人数长度必须介于 0 和 4 之间")
    public String getRetiredNum() {
        return retiredNum;
    }

    public void setRetiredNum(String retiredNum) {
        this.retiredNum = retiredNum;
    }

    @Length(min=0, max=8, message="五笔码长度必须介于 0 和 8 之间")
    public String getInputCodeWb() {
        return inputCodeWb;
    }

    public void setInputCodeWb(String inputCodeWb) {
        this.inputCodeWb = inputCodeWb;
    }

    @Length(min = 0, max = 64, message = "所属组织结构长度必须介于 0 和 64 之间")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


}