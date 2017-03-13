package com.jims.fbd.hrm.salary.vo;

import com.jims.common.persistence.DataEntity;

import java.util.List;

/**
 * Created by  on 2016/9/21.
 */
public class SalaryDataPersonVo extends DataEntity<SalaryDataPersonVo> {
    public SalaryDataPersonVo(){}
    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    private String dataId;         //标准ID
    private String text;               //姓名
    private String depId;             //部门ID
    private String orgId;              //组织机构ID
    private String userId;              //组织机构ID
    private String typeId;
    private List<SalaryDataPersonVo> salaryDataPersonVos;

    public List<SalaryDataPersonVo> getSalaryDataPersonVos() {
        return salaryDataPersonVos;
    }

    public void setSalaryDataPersonVos(List<SalaryDataPersonVo> salaryDataPersonVos) {
        this.salaryDataPersonVos = salaryDataPersonVos;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

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
}
