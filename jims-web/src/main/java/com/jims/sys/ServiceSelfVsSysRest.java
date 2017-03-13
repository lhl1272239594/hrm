package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.sys.entity.ServiceSelfVsSys;
import com.jims.sys.api.ServiceSelfVsSysApi;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
* 机构服务自定义对应系统服务rest
* @author lgx
* @version 2016-07-11
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("serviceSelfVsSys")
public class ServiceSelfVsSysRest {

    @Reference(version = "1.0.0")
    private ServiceSelfVsSysApi api;

    /**
    * 检索
    * @param selfServiceId
    * @return
    */
    @GET
    @Path("findList")
    public List<ServiceSelfVsSys> findList(@QueryParam("selfServiceId")String selfServiceId) {
        ServiceSelfVsSys entity = new ServiceSelfVsSys();
        entity.setSelfServiceId(selfServiceId);
        return api.findList(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(ServiceSelfVsSys entity) {
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<ServiceSelfVsSys> list) {
        return api.save(list);
    }

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    @GET
    @Path("delete")
    public String delete(String ids) {
        return api.delete(ids);
    }
}