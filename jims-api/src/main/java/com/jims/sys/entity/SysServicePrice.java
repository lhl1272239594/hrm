package com.jims.sys.entity;


import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 服务价格明细表Entity
 * @author txb
 * @version 2016-05-31
 */
public class SysServicePrice extends DataEntity<SysServicePrice> {
	
	private static final long serialVersionUID = 1L;
	private String serviceId;		// 服务ID
	private Double servicePrice;		// 服务价格
	private String serviceTimeLimit;		// 服务时间限制
	
	public SysServicePrice() {
		super();
	}

	public SysServicePrice(String id){
		super(id);
	}

	@Length(min=0, max=64, message="服务ID长度必须介于 0 和 64 之间")
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	public Double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Double servicePrice) {
		this.servicePrice = servicePrice;
	}
	
	@Length(min=0, max=10, message="服务时间限制长度必须介于 0 和 10 之间")
	public String getServiceTimeLimit() {
		return serviceTimeLimit;
	}

	public void setServiceTimeLimit(String serviceTimeLimit) {
		this.serviceTimeLimit = serviceTimeLimit;
	}
	
}