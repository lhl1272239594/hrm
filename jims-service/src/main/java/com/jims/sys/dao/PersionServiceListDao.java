/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.PersionServiceList;
import com.jims.sys.entity.SysService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人员拥有服务DAO接口
 * @author yangruidong
 * @version 2016-04-13
 */
@MyBatisDao
public interface PersionServiceListDao extends CrudDao<PersionServiceList> {

    /**
     * 根据persionId查询服务
     * @param persionId
     * @return
     */
    public List<SysService> findListByFlag(@Param("persionId") String persionId);


    /**
     * 根据persionId查询服务
     * @param persionId
     * @return
     */
    public List<PersionServiceList> findServiceByPersionId(@Param("persionId") String persionId);
    /**
     * @auto louhuili
     * 查询的是可以或者不可以定制的个人服务
     * @param serviceClass 服务人群 1,个人服务，0机构服务
     * @param serviceType  服务类型
     * @param persionId  用户persionId(若persionId不为空，查询的是可以或者不可以定制的个人服务，具体决定是可以或是不可以是根据state决定的)
     * @param state  若state=0，表示查询个人已经定制的个人服务；若state=1，查询的是个人还可以定制的其他个人服务
     * @return
     */
    public List<PersionServiceList> findListByPersionId(@Param("serviceClass")String serviceClass,@Param("serviceType")String serviceType,@Param("persionId")String persionId,@Param("state")String state);
	
}