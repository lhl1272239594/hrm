package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.api.OrgRoleApi;
import com.jims.sys.api.PersionServiceListApi;
import com.jims.sys.bo.OrgRoleBo;
import com.jims.sys.bo.PersionServiceListBo;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.PersionServiceList;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by yangruidong on 2016/6/02 0024.
 */
@Service(version = "1.0.0")
public class PersionServiceListImpl implements PersionServiceListApi {

    @Autowired
    private PersionServiceListBo persionServiceListBo;


    /**
     * 根据persionId查询服务
     *
     * @param persionId
     * @return
     */
    public List<SysService> findListByFlag(String persionId) {
        List<SysService> services = persionServiceListBo.findListByFlag(persionId);

        return services;
    }

    /**
     * @auto louhuili
     * 查询的是可以或者不可以定制的个人服务
     * @param serviceClass 服务人群 1,个人服务，0机构服务
     * @param serviceType  服务类型
     * @param persionId  用户persionId(若persionId不为空，查询的是可以或者不可以定制的个人服务，具体决定是可以或是不可以是根据state决定的)
     * @param state  若state=0，表示查询个人已经定制的个人服务；若state=1，查询的是个人还可以定制的其他个人服务
     * @return
     */
    public List<PersionServiceList> findListByPersionId(String serviceClass,String serviceType,String persionId, String state){
        return persionServiceListBo.findListByPersionId(serviceClass, serviceType, persionId, state);
    }
    /**
     * 保存个人购买的服务
     *
     * @param persionServiceList
     */
    public String saveService(PersionServiceList persionServiceList) {
        String result = "0";
        try {
            persionServiceListBo.saveService(persionServiceList);
            result = "1";
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 根据组织机构名称查询信息
     * @param orgName
     * @return
     */
    @Override
    public SysCompany getOrgName(String orgName) {
        return persionServiceListBo.getOrgName(orgName);
    }
}
