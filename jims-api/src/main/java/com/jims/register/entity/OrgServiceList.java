package com.jims.register.entity;

import com.jims.common.persistence.DataEntity;
import com.jims.sys.entity.MenuDict;

import java.util.Date;
import java.util.List;

/**
 * 机构选择服务Entity
 * @author lgx
 * @version 2016-05-31
 */
public class OrgServiceList extends DataEntity<OrgServiceList> {
	
	private static final long serialVersionUID = 1L;
	private String serviceId;		// 所购买的服务
	private Date serviceStartDate;		// 服务开始使用时间
	private Date serviceEndDate;		// 服务结束使用时间
	private String orgId;		// 购买服务单位

    private String serviceType;  // // 1,有偿服务,0无偿服务
    private String serviceClass;  // 3,机构管理服务，2,所有服务，1,个人服务，0机构服务
    private String serviceName;  // 服务名称
    private List<MenuDict> menus;  // 菜单

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<MenuDict> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDict> menus) {
        this.menus = menus;
    }

    public OrgServiceList() {
		super();
	}

	public OrgServiceList(String id){
		super(id);
	}


	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}


	public Date getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}


	public Date getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
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

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }
}