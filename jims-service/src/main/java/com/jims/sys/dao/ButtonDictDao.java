/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.entity.ButtonDict;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.OrgDeptPropertyDict;
import org.apache.ibatis.annotations.Param;
import com.jims.sys.entity.MENU_DICT_BUTTON;
import java.util.List;

/**
 * 系统菜单DAO接口
 * @author yangchen
 * @version 2016-08-17
 */
@MyBatisDao
public interface ButtonDictDao extends CrudDao<ButtonDict> {
    /**
     * 保存方法（返回id）
     * @param buttonDict
     */
    public Integer insertReturnObject(ButtonDict buttonDict);
    /**
     * 修改方法（返回id）
     * @param buttonDict
     */
    public Integer updateReturnObject(ButtonDict buttonDict);
    /**
     * 根据ID 查询
     * @param mid
     */
    public List<MENU_DICT_BUTTON> getALLbut(String mid);
    /**
     * 根据ID 删除按钮
     * @param id
     */
    public void delbt(String id);
    /**
     * 查找菜单和按钮
     * @param
     */
    public List<OrgSelfServiceVsMenu> findSelfServiceId(String serviceId, String roleId,String orgid);
    /**
     * 返回条数
     * @param
     */
    public Integer getscmbr(String orgid, String roleid, String serviceid, String menuid, String btname);
    /**
     * 新增数据
     * @param
     */
    public void inscmbr(String orgid, String roleid, String permission, String serviceid, String target, String menuid, String btname, String icon);
    /**
     * 更新数据
     * @param
     */
    public void upscmbr(String orgid, String roleid, String serviceid, String menuid, String btname, String icon);
    /**
     * 查询所有的科室信息
     * @return
     */
    public List<DeptDict> findAllData(String orgId, String roleid, String serid, String mid);
    /**
     * 查询所有的属性类型
     * @return
     */
    public List<OrgDeptPropertyDict> findProperty(@Param("orgId") String orgId);
    /**
     * 根据条件查询所有的属性信息
     * @return
     */
    public List<OrgDeptPropertyDict> findByCondition(OrgDeptPropertyDict orgDeptPropertyDict);
    /**
     * 更新数据权限
     *
     * @return
     */
    public void upmdata(@Param("buttonDict") ButtonDict buttonDict);
    /**
     * 删除指定数据权限
     *
     * @return
     */
    public void delmdata(@Param("buttonDict") ButtonDict buttonDict);
    /**
     * 查找按钮
     * @param
     */
    public List<OrgSelfServiceVsMenu> findSelfbtns(String serviceId, String roleId, String orgid, String meid);
}