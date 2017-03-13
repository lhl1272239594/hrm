package com.jims.register.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.OrgSelfServiceVsMenu;

import java.util.List;

/**
 * 机构自定义服务对应菜单DAO接口
 * @author lgx
 * @version 2016-05-31
 */
@MyBatisDao
public interface OrgSelfServiceVsMenuDao extends CrudDao<OrgSelfServiceVsMenu> {

    /**
     * 根据自定义服务主键删除
     * @param selfServiceId
     * @return
     */
    public int deleteByServiceId(String selfServiceId);

    public List<OrgSelfServiceVsMenu> findSelfServiceId(String serviceId, String roleId);

    public List<OrgSelfServiceVsMenu> findServiceId(String selfServiceId);

    /**
     * 单表查询
     * @param entity
     * @return
     */
    public List<OrgSelfServiceVsMenu> findListNoJoin(OrgSelfServiceVsMenu entity);

    /**
     * @param menuId
     * @param sysServiceId
     * @return
     */
    public int deleteByMenuIdAndSysServiceId(String menuId,String sysServiceId);

    /**
     * 获取最大的排序
     * @param entity
     * @return
     */
    public Integer findMaxSort(OrgSelfServiceVsMenu entity);

    public List<OrgSelfServiceVsMenu> findSelfServiceVsButton(String serviceId, String roleId);
}