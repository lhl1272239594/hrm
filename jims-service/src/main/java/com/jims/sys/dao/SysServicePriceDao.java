
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.SysServicePrice;

import java.util.List;

/**
 * 服务价格明细表DAO接口
 * @author txb
 * @version 2016-05-31
 */
@MyBatisDao
public interface SysServicePriceDao extends CrudDao<SysServicePrice> {
    /**
     * 查询服务明细全部列表
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-01
     */
    public List<SysServicePrice> findListByServiceId(String serviceId);

    /**
     * 根据服务ID删除服务价格明细
     * @param serviceId 服务ID
     * @return
     * @author fengyuguang
     */
    public Integer deleteByServiceId(String serviceId);
	
}