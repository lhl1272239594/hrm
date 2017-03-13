package com.jims.asepsis.entity;

import com.jims.common.persistence.DataEntity;


/**
* 包单位维护Entity
* @author yangruidong
* @version 2016-06-28
*/
public class OrgSysDict extends DataEntity<OrgSysDict> {

    private static final long serialVersionUID = 1L;
    private String label;  // 标签名
    private String value;  // 数据值
    private String type;  // 类型
    private String description;  // 描述
    private String inputCode;  // 
    private String orgId;  // 所属机构ID

    public OrgSysDict() {
        super();
    }

    public OrgSysDict(String id) {
        super(id);
    }

    public  String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public  String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public  String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public  String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  String getInputCode() {
        return this.inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public  String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


}