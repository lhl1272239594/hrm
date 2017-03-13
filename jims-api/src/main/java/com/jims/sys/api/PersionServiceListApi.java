package com.jims.sys.api;


import com.jims.sys.entity.PersionServiceList;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangruidong on 2016/6/02
 */
public interface PersionServiceListApi {

    /**
     * 根据persionId查询免费的服务
     * @param persionId
     * @return
     */
    public List<SysService> findListByFlag(String persionId);

    /**
     * @auto louhuili
     * 查询的是可以或者不可以定制的个人服务
     * @param serviceClass 服务人群 1,个人服务，0机构服务
     * @param serviceType  服务类型
     * @param persionId  用户persionId(若persionId不为空，查询的是可以或者不可以定制的个人服务，具体决定是可以或是不可以是根据state决定的)
     * @param state  若state=0，表示查询个人已经定制的个人服务；若state=1，查询的是个人还可以定制的其他个人服务
     * @return
     */
    public List<PersionServiceList> findListByPersionId(String serviceClass,String serviceType ,String persionId, String state);
    /**
     * 保存个人购买的服务
     * @param persionServiceList
     *
     */
    public String saveService(PersionServiceList persionServiceList);

    /**
     * 根据组织机构名称查询信息
     * @param orgName
     * @return
     */
    public SysCompany getOrgName(String orgName);
}
