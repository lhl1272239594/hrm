package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
 * Created by heren on 2016/7/4.
 */
public class OrgServiceParam extends DataEntity<OrgServiceParam> {

    private String paramName ;//参数名称
    private String paramValue ;//参数值
    private String serviceId;//所属服务
    private String valueRange ;//值域
    private String parmDesp;//参数描述
    private String paramType ;//参数类型
    private String orgId ;//所属组织机构
    private String serviceType ;//服务类型

    public OrgServiceParam(String paramName, String paramValue, String serviceId, String valueRange, String parmDesp, String paramType, String orgId, String serviceType) {
        this.paramName = paramName;
        this.paramValue = paramValue;
        this.serviceId = serviceId;
        this.valueRange = valueRange;
        this.parmDesp = parmDesp;
        this.paramType = paramType;
        this.orgId = orgId;
        this.serviceType = serviceType;
    }

    public OrgServiceParam(String id) {
        super(id);
    }

    public OrgServiceParam() {
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getValueRange() {
        return valueRange;
    }

    public void setValueRange(String valueRange) {
        this.valueRange = valueRange;
    }

    public String getParmDesp() {
        return parmDesp;
    }

    public void setParmDesp(String parmDesp) {
        this.parmDesp = parmDesp;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
