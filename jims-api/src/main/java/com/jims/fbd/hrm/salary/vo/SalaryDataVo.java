package com.jims.fbd.hrm.salary.vo;

import com.jims.common.persistence.DataEntity;

import java.util.List;

/**
 * Created by  on 2016/9/21.
 */
public class SalaryDataVo extends DataEntity<SalaryDataVo> {
    public SalaryDataVo(){}
    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    private List<SalaryDataPersonVo> salaryDataPersonVos;

    private String dataId ;
    private String personId ;
    private String personName ;
    private String deptId ;
    private String typeId ;
    private String state ;
    private String num ;
    private String orgId;              //组织机构ID

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    private String userId;              //人员ID
    private String depId;              //部门ID
    private String roleId;

    public List<SalaryDataPersonVo> getSalaryDataPersonVos() {
        return salaryDataPersonVos;
    }

    public void setSalaryDataPersonVos(List<SalaryDataPersonVo> salaryDataPersonVos) {
        this.salaryDataPersonVos = salaryDataPersonVos;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
