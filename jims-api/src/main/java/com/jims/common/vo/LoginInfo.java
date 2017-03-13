package com.jims.common.vo;

import com.jims.sys.entity.MenuDict;

import java.io.Serializable;
import java.util.List;

/**
 * Created by heren on 2016/7/13.
 */
public class LoginInfo implements Serializable {
    private String persionId ;
    private String staffId ;
    private String orgId ;
    private String userName ;

    private String deptId ;
    private String deptName ;
    private String deptCode ;
    private String roleId ;
    private String roleName ;
    private List<MenuDict> menuList;
    public LoginInfo() {
    }

    public LoginInfo(String persionId, String staffId, String orgId, String userName, String deptId, String deptName, String deptCode,List<MenuDict>menuList) {
        this.persionId = persionId;
        this.staffId = staffId;
        this.orgId = orgId;
        this.userName = userName;
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptCode = deptCode;
        this.menuList=menuList; //有权限访问的菜单列表
    }

    public List<MenuDict> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuDict> menuList) {
        this.menuList = menuList;
    }

    public String getPersionId() {
        return persionId;
    }

    public void setPersionId(String persionId) {
        this.persionId = persionId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
