package com.jims.register.entity;

import com.jims.common.persistence.DataEntity;
import com.jims.sys.entity.ServiceSelfVsSys;

import java.util.Date;
import java.util.List;

/**
 * 机构自定义服务Entity
 * @author lgx
 * @version 2016-05-31
 */
public class OrgSelfServiceList extends DataEntity<OrgSelfServiceList> {
	
	private static final long serialVersionUID = 1L;
	private String serviceName;		// 自定义服务名字
    private String menuPosition;    // 自定义服务中菜单界面显示位置
    private String menuStyle ;   // 自定义服务中菜单显示样式
	private String orgId;		// 机构ID

    private List<OrgSelfServiceVsMenu> menus;   // 自定义服务对应的菜单
    private List<ServiceSelfVsSys> serviceVs;   // 服务与平台服务对照

    public List<OrgSelfServiceVsMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<OrgSelfServiceVsMenu> menus) {
        this.menus = menus;
    }

    public OrgSelfServiceList() {
		super();
	}

	public OrgSelfServiceList(String id){
		super(id);
	}


	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

    public String getMenuStyle() {
        return menuStyle;
    }

    public void setMenuStyle(String menuStyle) {
        this.menuStyle = menuStyle;
    }

    public String getMenuPosition() {
        return menuPosition;
    }

    public void setMenuPosition(String menuPosition) {
        this.menuPosition = menuPosition;
    }

    public List<ServiceSelfVsSys> getServiceVs() {
        return serviceVs;
    }

    public void setServiceVs(List<ServiceSelfVsSys> serviceVs) {
        this.serviceVs = serviceVs;
    }
}