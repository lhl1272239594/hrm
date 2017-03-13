package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.OrgRoleApi;
import com.jims.sys.api.PersionServiceListApi;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.PersionServiceList;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * Created by yangruidong on 2016/5/31 0024.
 */
@Component
@Produces("application/json")
@Path("persion-service-list")
public class PersionServiceListRest {
    @Reference(version = "1.0.0")
    private PersionServiceListApi persionServiceListApi;

    @GET
    @Path("findListByFlag")
    public List<SysService> findAllListByOrgId(@QueryParam("persionId") String persionId)
    {
        List<SysService> list=persionServiceListApi.findListByFlag(persionId);
        if(list!=null&&!list.isEmpty()){
            for(SysService sysService:list){
                try {
                    if(sysService.getServiceDescription()!=null){
                        sysService.setTranServiceDescription(new String(sysService.getServiceDescription(), "utf-8"));
                    }
                } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
            }
        }
        return list;
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
    @GET
    @Path("findListByPersionId")
    public List<PersionServiceList> findListByPersionId(@QueryParam("serviceClass")String serviceClass,@QueryParam("serviceType")String serviceType ,@QueryParam("persionId")String persionId ,@QueryParam("state")String state)
    {
        List<PersionServiceList> list=persionServiceListApi.findListByPersionId(serviceClass, serviceType, persionId, state);
        if(list!=null&&!list.isEmpty()){
            for(PersionServiceList pService:list){
                try {
                    if(pService.getServiceDescription()!=null){
                        pService.setTranServiceDescription(new String(pService.getServiceDescription(),"utf-8"));
                    }
                } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
            }
        }
        return list;
    }

    /**
     * 保存注册信息以及选择的服务
     * @param persionServiceList
     * @return 1 成功 ,0 失败
     */
    @Path("saveService")
    @POST
    public String saveCompanyAndService(PersionServiceList persionServiceList) {
        if (persionServiceList!=null) {
            persionServiceListApi.saveService(persionServiceList);
            return "1";
        }
        return "0";
    }

    /**
     * 根据组织机构名称查询组织机构id
     * @param orgName
     * @return
     */
    @GET
    @Path("getOrgName")
    public SysCompany getOrgName(@QueryParam("orgName") String orgName)
    {
        return persionServiceListApi.getOrgName(orgName);
    }
}
