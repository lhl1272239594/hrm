package com.jims.sys.entity;


import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.beans.Transient;
import java.util.List;

/**
 * 系统服务Entity
 * @author txb
 * @version 2016-05-31
 * update by chenxy
 * 将服务描述字段类型改为byte[]
 */
public class SysService extends DataEntity<SysService> {
	
	private static final long serialVersionUID = 1L;
	private String serviceName;		// 系统服务名称
//	private String serviceDescription;		// 服务描述
    private byte[] serviceDescription;		// 服务描述
	private String tranServiceDescription;// 服务描述转化为String

    private String serviceType;		// 1,有偿服务,0无偿服务
	private String serviceClass;		// 2,所有服务，1,个人服务，0机构服务
	private String serviceImage;		// 服务图片
	private String startDate;		// 服务开始时间（从persion_service_list/org_service_list中查询to_char(service_start_date,'yyyy-mm-dd')得来）
	private String endDate;		// 服务结束时间（从persion_service_list/org_service_list中查询to_char(service_end_date,'yyyy-mm-dd')得来）


	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	private List<MenuDict> menuDictList;
    private List<SysServicePrice> sysServicePriceList;

    public List<MenuDict> getMenuDictList() {
        return menuDictList;
    }

    public void setMenuDictList(List<MenuDict> menuDictList) {
        this.menuDictList = menuDictList;
    }

    public List<SysServicePrice> getSysServicePriceList() {
        return sysServicePriceList;
    }

    public void setSysServicePriceList(List<SysServicePrice> sysServicePriceList) {
        this.sysServicePriceList = sysServicePriceList;
    }

    public SysService() {
		super();
	}

	public SysService(String id){
		super(id);
	}

	@Length(min=0, max=100, message="系统服务名称长度必须介于 0 和 100 之间")
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
 	public byte[] getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(byte[] serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	
	@Length(min=0, max=2, message="1,有偿服务,0无偿服务长度必须介于 0 和 2 之间")
	public String getServiceType() {
		return serviceType;
	}
    @Length(min=0, max=2, message="2,所有服务，1,个人服务，0机构服务长度必须介于 0 和 2 之间")
	public String getServiceClass() {
		return serviceClass;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	public String getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}

    @Transient
    public String getTranServiceDescription() {
        return tranServiceDescription;
    }

    public void setTranServiceDescription(String tranServiceDescription) {
        this.tranServiceDescription = tranServiceDescription;
    }
}