package com.jims.sys.entity;


import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 服务对应菜单表Entity
 * @author txb
 * @version 2016-05-31
 */
public class ServiceVsMenu extends DataEntity<ServiceVsMenu> {
	
	private static final long serialVersionUID = 1L;
	private String serviceId;		// 服务id
	private String menuId;		// 菜单id
	private String menuSort;		// 菜单排序
	
	public ServiceVsMenu() {
		super();
	}

	public ServiceVsMenu(String id){
		super(id);
	}

	@Length(min=0, max=64, message="服务id长度必须介于 0 和 64 之间")
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	@Length(min=0, max=64, message="菜单id长度必须介于 0 和 64 之间")
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	@Length(min=0, max=10, message="菜单排序长度必须介于 0 和 10 之间")
	public String getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(String menuSort) {
		this.menuSort = menuSort;
	}

}