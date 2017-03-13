package com.jims.sys.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public class MenuDictVo implements Serializable{

    private String id;
    private String pid; //父id
    private String serviceId; //服务ID
    private String serviceName; //服务名称
    private String menuId;
    private String menuName;        // 菜单名称
    private String menuOperate;

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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMenuOperate() {
        return menuOperate;
    }

    public void setMenuOperate(String menuOperate) {
        this.menuOperate = menuOperate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
