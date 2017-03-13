/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.attendance.entity;

import com.jims.common.persistence.DataEntity;

/**
 *排班规则管理Entity
 * @author
 * @version 2016-09-21
 */
public class FrequencyRule extends DataEntity<FrequencyRule> {

    private static final long serialVersionUID = 1L;


    private String freRuleId;        //id
    private String freRuleDes;
    private String freLoopDays;        //id
    private String freRuleDataId;
    private String freItemId;
    private String freItemDes;
    private String freItemOrder;
    private String createDept;            //
    private String createOrg;            //
    private String orgId;
    private String createRole;
    private String statusType;
    private String num;
    private String flag;
    private String value;
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFreRuleId() {
        return freRuleId;
    }

    public void setFreRuleId(String freRuleId) {
        this.freRuleId = freRuleId;
    }

    public String getFreRuleDes() {
        return freRuleDes;
    }

    public void setFreRuleDes(String freRuleDes) {
        this.freRuleDes = freRuleDes;
    }

    public String getFreLoopDays() {
        return freLoopDays;
    }

    public void setFreLoopDays(String freLoopDays) {
        this.freLoopDays = freLoopDays;
    }

    public String getFreRuleDataId() {
        return freRuleDataId;
    }

    public void setFreRuleDataId(String freRuleDataId) {
        this.freRuleDataId = freRuleDataId;
    }

    public String getFreItemId() {
        return freItemId;
    }

    public void setFreItemId(String freItemId) {
        this.freItemId = freItemId;
    }

    public String getFreItemDes() {
        return freItemDes;
    }

    public void setFreItemDes(String freItemDes) {
        this.freItemDes = freItemDes;
    }

    public String getFreItemOrder() {
        return freItemOrder;
    }

    public void setFreItemOrder(String freItemOrder) {
        this.freItemOrder = freItemOrder;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getCreateOrg() {
        return createOrg;
    }

    public void setCreateOrg(String createOrg) {
        this.createOrg = createOrg;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCreateRole() {
        return createRole;
    }

    public void setCreateRole(String createRole) {
        this.createRole = createRole;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}