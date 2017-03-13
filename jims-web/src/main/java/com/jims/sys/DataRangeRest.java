package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.sys.api.DataRangeApi;
import com.jims.sys.api.OrgRoleVsServiceApi;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.OrgRoleVsService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Administrator /**
 * 数据权限控制Rest层

 *
 * @author yangchen
 * @version 2016-08-21
 * @uodateinfo:

 */
@Component
@Produces("application/json")
@Path("dataRange")
public class DataRangeRest {

    @Reference(version = "1.0.0")
    private OrgRoleVsServiceApi orgRoleVsServiceApi;
    private DataRangeApi orgDataRangeApi;
    /**
     * 根据角色ID和服务ID更改服务下的菜单状态
     * @param roleId  角色ID
     * @param serviceId  服务ID
     * @return
     * @author fengyuguang
     */

    @GET
    @Path("find-dept")
    public List<DeptDict> findDept(@QueryParam("orgId") String orgId, @QueryParam("serviceId") String serviceId, @QueryParam("menuId") String menuId){

        List<DeptDict> list = orgDataRangeApi.findList(orgId,serviceId,menuId);

        return list;
    }

    @Path("update-menu-operate")
    @GET
    public String updateMenuOperate(@QueryParam("roleId")String roleId,@QueryParam("serviceId")String serviceId,
                                    @QueryParam("operate")String operate){
        return orgRoleVsServiceApi.updateMenuOperate(roleId,serviceId,operate);
    }

    @Path("save")
    @POST
    public String save(List<OrgRoleVsService> orgRoleVsServices){
       return orgRoleVsServiceApi.OrgRoleVsServiceSave(orgRoleVsServices);
    }


}
