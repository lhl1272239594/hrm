package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 身份字典表Entity
 * Created by fyg on 2016/6/21.
 */
public class IdentityDict extends DataEntity<IdentityDict> {

    private static final long serialVersionUID = 1L;
    private String identityCode;       //身份代码
    private String identityName;       //身份名称
    private String inputCode;          //输入码
    private String priorityIndicator;       //优先标志 combobox(1优先，0不优先)
    private String militaryIndicator;       //军人标志 combobox(1是，0否)
    private String inputCodeWb;
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Length(min = 0, max = 1, message = "身份代码只能是一位字符或数字")
    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    @Length(min = 0, max = 10, message = "身份名称长度必须介于 0 和 10 之间")
    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public String getPriorityIndicator() {
        return priorityIndicator;
    }

    public void setPriorityIndicator(String priorityIndicator) {
        this.priorityIndicator = priorityIndicator;
    }

    public String getMilitaryIndicator() {
        return militaryIndicator;
    }

    public void setMilitaryIndicator(String militaryIndicator) {
        this.militaryIndicator = militaryIndicator;
    }

    public String getInputCodeWb() {
        return inputCodeWb;
    }

    public void setInputCodeWb(String inputCodeWb) {
        this.inputCodeWb = inputCodeWb;
    }
}
