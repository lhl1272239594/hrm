package com.jims.sys.vo;

import com.jims.common.persistence.DataEntity;

import java.io.Serializable;

/**
 * Created by fyg on 2016/6/8.
 */
public class RoleServiceMenuVsMenuDictVo extends DataEntity<RoleServiceMenuVsMenuDictVo> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String roleServiceId;        // 权限范围id
    private String menuId;        // 菜单id
    private String menuName;        // 菜单名称
    private String menuOperate;        // 菜单操作（0，view，1，edit）
    private String href;        // 链接地址
    private Long sort;        // 排序
    private String pid; //父id
    private String menuLevel;//菜单级别

    public RoleServiceMenuVsMenuDictVo(){}
    public RoleServiceMenuVsMenuDictVo(String roleServiceId, String menuId, String menuName, String menuOperate, Long sort, String pid,
                                       String menuLevel) {
        this.roleServiceId = roleServiceId;
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuOperate = menuOperate;
        this.sort = sort;
        this.pid = pid;
        this.menuLevel = menuLevel;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }
}
