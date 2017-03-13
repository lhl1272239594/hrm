
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.ServiceVsMenu;

import java.util.List;

/**
 * 服务对应菜单表DAO接口
 * @author txb
 * @version 2016-05-31
 */
@MyBatisDao
public interface ServiceVsMenuDao extends CrudDao<ServiceVsMenu> {

    /**
     * 根据服务ID查询服务于菜单对照表
     * @param serviceId 服务ID
     * @return 服务于菜单对照实体对象
     * @author fengyuguang
     */
    public List<ServiceVsMenu> findByServiceId(String serviceId);
	
    /**
     * 查询服务全部菜单
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public List<ServiceVsMenu> serviceVsMenuList(String serviceId);
    /**
     * 删除服务全部菜单
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public Integer deleteByServiceId(String serviceId);
}