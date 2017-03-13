package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.sys.api.ServiceParamApi;
import com.jims.sys.api.ServiceSelfVsSysApi;
import com.jims.sys.entity.ServiceSelfVsSys;
import com.jims.sys.entity.SysServiceParam;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heren on 2016/7/5.
 */
@Component
@Produces("application/json")
@Path("service-param")
public class ServiceParamRest {

    @Reference(version = "1.0.0")
    private ServiceParamApi serviceParamApi ;

    @Reference
    private ServiceSelfVsSysApi serviceSelfVsSysApi ;

    /**
     * 根据某一个服务查询其参数
     * @param serviceId
     * @return
     */
    @Path("list")
    @GET
    public List<SysServiceParam> listServiceParamByServiceId(@QueryParam("serviceId") String serviceId){
        return serviceParamApi.listSysServiceParam(serviceId) ;
    }


    /**
     * 保存相关的服务
     * @param sysServiceParams
     * @return
     */
    @Path("save")
    @POST
    public StringData saveServiceParam(List<SysServiceParam> sysServiceParams){

        int num=serviceParamApi.mergeSysServiceParam(sysServiceParams) ;
        StringData stringData =new StringData() ;
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData ;
    }

    @Path("merge")
    @POST
    public StringData mergeServiceParam(BeanChangeVo<SysServiceParam> sysServiceParamBeanChangeVo){
        int num=serviceParamApi.mergeSysServiceParam(sysServiceParamBeanChangeVo) ;
        StringData stringData =new StringData() ;
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData ;
    }


    /**
     * 删除相关的服务
     * @param sysServiceParams
     * @return
     */
    @Path("del")
    @POST
    public StringData delSysServiceParam(List<SysServiceParam> sysServiceParams){
        int num=serviceParamApi.deleteSysServiceParam(sysServiceParams) ;
        StringData stringData =new StringData() ;
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }

    @Path("find-by-self-service-id")
    @GET
    public List<SysServiceParam> findSysServiceParamBySelfServiceId(@QueryParam("selfServiceId")String selfServiceId,@QueryParam("orgId")String orgId){
        return serviceParamApi.findSysServiceParamBySelfServiceId(selfServiceId,orgId) ;
    }
}
