package com.jims.sys.vo;

/**
 * Created by Administrator on 2016/6/6.
 */
public class OrgRoleVsServiceVo {
    private String serviceId;        // 服务id
    private String roleId;        // 角色id
    private String serviceName; //服务名称

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
