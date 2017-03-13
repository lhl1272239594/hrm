package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
 * Created by zhuqi on 2016/8/9.
 */
public class InitProcess extends DataEntity<InitProcess> {
    private static final long serialVersionUID = 1L;
    private String orgId;  //组织机构Id
    private String initFlag; //初始化标志，1 已初始化，0 未初始化
    private Integer initSort; //初始化顺序
    private String menuId; //菜单ID
    private String menuName; //菜单名
    private String href;   //路径
    private String icon;    //图标

    public InitProcess() {
        super();
    }

    public InitProcess(String id){
        super(id);
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getInitFlag() {
        return initFlag;
    }

    public void setInitFlag(String initFlag) {
        this.initFlag = initFlag;
    }

    public Integer getInitSort() {
        return initSort;
    }

    public void setInitSort(Integer initSort) {
        this.initSort = initSort;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
