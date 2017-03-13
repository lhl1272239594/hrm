package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
* 机构服务自定义对应系统服务Entity
* @author lgx
* @version 2016-07-11
*/
public class ServiceSelfVsSys extends DataEntity<ServiceSelfVsSys> {

    private static final long serialVersionUID = 1L;
    private String selfServiceId;  // 自定义服务ID
    private String sysServiceId;  // 菜单所属原平台服务ID

    public ServiceSelfVsSys() {
        super();
    }

    public ServiceSelfVsSys(String id) {
        super(id);
    }

    public  String getSelfServiceId() {
        return this.selfServiceId;
    }

    public void setSelfServiceId(String selfServiceId) {
        this.selfServiceId = selfServiceId;
    }

    public  String getSysServiceId() {
        return this.sysServiceId;
    }

    public void setSysServiceId(String sysServiceId) {
        this.sysServiceId = sysServiceId;
    }


}