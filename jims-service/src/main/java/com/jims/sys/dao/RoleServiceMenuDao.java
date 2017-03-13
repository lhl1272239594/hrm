package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.RoleServiceMenu;

import java.util.List;

/**
 * 角色服务菜单DAO接口
 * @author luohk
 * @version 2016-06-01
 */
@MyBatisDao
public interface RoleServiceMenuDao extends CrudDao<RoleServiceMenu> {
    public List<RoleServiceMenu> findRoleServiceId(String roleServiceId);

    public RoleServiceMenu findRoleServiceIdAndMenuId(String roleServiceId, String menuId);

    public int deleteService(String roleServiceId);

    public int updateService(RoleServiceMenu roleServiceMenu);

    public int deleteServiceIdAndMenuId(String roleServiceId, String menuId);
}