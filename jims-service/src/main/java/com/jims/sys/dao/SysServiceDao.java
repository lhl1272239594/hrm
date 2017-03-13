package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.SysService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统服务DAO接口
 * @author txb
 * @version 2016-05-31
 */
@MyBatisDao
public interface SysServiceDao extends CrudDao<SysService> {
    /**
     * 通过服务类别类型查询服务列表
     * @param serviceType 服务类型
     * @param serviceClass 服务类别
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public List<SysService> serviceListByTC( String serviceType,String serviceClass);

    /**
     * 检索不同人群的服务
     * @param serviceClass 服务人群 1,个人服务，0机构服务
     * @param serviceType  服务类型
     * @param persionId  用户persionId(若persionId不为空，查询的是可以或者不可以定制的个人服务，具体决定是可以或是不可以是根据state决定的)
     * @param state  若state=0，表示查询个人已经定制的个人服务；若state=1，查询的是个人还可以定制的其他个人服务
     * @param id  sys_service.id
     * @return
     */
    public List<SysService> findServiceWithPrice(@Param("serviceClass")String serviceClass,@Param("serviceType")String serviceType,
                                                 @Param("persionId")String persionId,@Param("state")String state,@Param("id")String id);

    /**
     * 通过服务Id查询服务列表
     * @param serviceId 服务id
     * @return
     */
    public List<SysService> findServiceById(@Param("serviceId") String serviceId);
}