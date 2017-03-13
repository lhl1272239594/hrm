package com.jims.template.entity;

import com.jims.common.persistence.DataEntity;


/**
* 角色模板Entity
* @author lgx
* @version 2016-08-09
*/
public class OrgRoleTemplate extends DataEntity<OrgRoleTemplate> {

    private static final long serialVersionUID = 1L;
    private String roleName;  // 角色名称
    private String masterId;  // 模板信息表ID

    private String area ;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public OrgRoleTemplate() {
        super();
    }

    public OrgRoleTemplate(String id) {
        super(id);
    }

    public  String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public  String getMasterId() {
        return this.masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }


}