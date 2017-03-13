package com.jims.register.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * 机构自定义服务对应菜单Entity
 * @author lgx
 * @version 2016-05-31
 */
public class OrgSelfServiceVsMenu extends DataEntity<OrgSelfServiceVsMenu> {
	
    private static final long serialVersionUID = 1L;
    private String selfServiceId;		// 服务ID
    private String sysServiceId;		// 所属平台服务ID
    private String menuId;		// 菜单ID
    private Integer menuSort;		// 菜单排序
    private String pid ;     // 父ID
    private Date menuEndDate;    //菜单结束时间
    private String menuOperate;
    private String href;

    private List<OrgSelfServiceVsMenu> children ; // 子节点
    private String menuName;  // 菜单名称

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<OrgSelfServiceVsMenu> getChildren() {
        return children;
    }

    public void setChildren(List<OrgSelfServiceVsMenu> children) {
        this.children = children;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public OrgSelfServiceVsMenu() {
		super();
	}

	public OrgSelfServiceVsMenu(String id){
		super(id);
	}

	public String getSelfServiceId() {
		return selfServiceId;
	}

	public void setSelfServiceId(String selfServiceId) {
		this.selfServiceId = selfServiceId;
	}


	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


	public Integer getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}

    public Date getMenuEndDate() {
        return menuEndDate;
    }

    public void setMenuEndDate(Date menuEndDate) {
        this.menuEndDate = menuEndDate;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuOperate() {
        return menuOperate;
    }

    public void setMenuOperate(String menuOperate) {
        this.menuOperate = menuOperate;
    }

    public String getSysServiceId() {
        return sysServiceId;
    }

    public void setSysServiceId(String sysServiceId) {
        this.sysServiceId = sysServiceId;
    }
}