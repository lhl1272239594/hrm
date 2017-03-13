/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.beans.Transient;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 * 人员拥有服务Entity
 * @author yangruidong
 * @version 2016-04-13
 */
public class PersionServiceList extends DataEntity<PersionServiceList> {
	
	private static final long serialVersionUID = 1L;
	private String persionId;		// 人员
	private String serviceId;		// 菜单
	private String flag;		// 0默认服务1，增值服务
	private Date serviceStartDate;		// 服务开始时间
	private Date serviceEndDate;		// 服务结束时间
	private String serviceName;		// 服务名称
	private String serviceImage;		// 服务图片
	private String startDate;		// 服务开始时间
	private String endDate;		// 服务结束时间
	private String upFlag;		// 一个状态：当是0，表示新增，当是1，表示修改
	private byte[] serviceDescription;		// 服务描述
	private String tranServiceDescription;

    private List<PersionServiceList> serviceList;  // 机构定制的服务

    public List<PersionServiceList> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<PersionServiceList> serviceList) {
        this.serviceList = serviceList;
    }

    public PersionServiceList() {
		super();
	}

	public PersionServiceList(String id){
		super(id);
	}

	@Length(min=0, max=64, message="人员长度必须介于 0 和 64 之间")
	public String getPersionId() {
		return persionId;
	}

	public void setPersionId(String persionId) {
		this.persionId = persionId;
	}
	
	@Length(min=0, max=64, message="菜单长度必须介于 0 和 64 之间")
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	@Length(min=0, max=2, message="0默认服务1，增值服务长度必须介于 0 和 2 之间")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	public String getServiceImage() {
		return serviceImage;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getUpFlag() {
		return upFlag;
	}

	public void setUpFlag(String upFlag) {
		this.upFlag = upFlag;
	}

	public byte[] getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(byte[] serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	@Transient
	public String getTranServiceDescription() {
		return tranServiceDescription;
	}

	public void setTranServiceDescription(String tranServiceDescription) {
		this.tranServiceDescription = tranServiceDescription;
	}
}