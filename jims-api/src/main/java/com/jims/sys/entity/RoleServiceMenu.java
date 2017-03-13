package com.jims.sys.entity;



import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * 角色服务菜单Entity
 * @author luohk
 * @version 2016-06-01
 */
public class RoleServiceMenu extends DataEntity<RoleServiceMenu> {
	
	private static final long serialVersionUID = 1L;
	private String roleServiceId;		// 权限范围id
	private String menuId;		// 菜单id
	private String menuOperate;		// 菜单操作（0，view，1，edit）
    private String remark;
	
	public RoleServiceMenu() {
		super();
	}

	public RoleServiceMenu(String id){
		super(id);
	}


	public String getRoleServiceId() {
		return roleServiceId;
	}

	public void setRoleServiceId(String roleServiceId) {
		this.roleServiceId = roleServiceId;
	}


	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


	public String getMenuOperate() {
		return menuOperate;
	}

	public void setMenuOperate(String menuOperate) {
		this.menuOperate = menuOperate;
	}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}