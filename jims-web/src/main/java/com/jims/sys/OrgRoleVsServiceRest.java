package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.sys.api.OrgRoleVsServiceApi;
import com.jims.sys.entity.OrgRoleVsService;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.OrgSelfServiceVsMenuVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
@Component
@Produces("application/json")
@Path("roleVs")
public class OrgRoleVsServiceRest {

    @Reference(version = "1.0.0")
    private OrgRoleVsServiceApi orgRoleVsServiceApi;

    /**
     * 根据角色ID和服务ID更改服务下的菜单状态
     * @param roleId  角色ID
     * @param serviceId  服务ID
     * @return
     * @author fengyuguang
     */
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

    /**
     * 保存角色新添加的服务
     * @param orgRoleVsServices
     * @return
     * @author fengyuguang
     */
    @Path("saveService")
    @POST
    public String saveService(List<OrgRoleVsService> orgRoleVsServices) {
        return orgRoleVsServiceApi.saveService(orgRoleVsServices);
    }

    @Path("find")
    @GET
    public List<RoleServiceMenu> find(@QueryParam("id") String id){
        List<RoleServiceMenu>  orgSelfServiceVsMenuVos= orgRoleVsServiceApi.find(id);
        return orgSelfServiceVsMenuVos;
    }

    @Path("findrole")
    @GET
    public List<OrgRoleVsService> findRole(@QueryParam("roleId") String roleId,@QueryParam("q") String q) {
        List<OrgRoleVsService> lists = orgRoleVsServiceApi.findRole(roleId,q);
        return lists;
    }

    /**
     * 删除角色自定义服务
     * @param orgRoleVsServices
     * @return
     * @author fengyuguang
     */
    @Path("delete")
    @POST
    public StringData delete(List<OrgRoleVsService> orgRoleVsServices){
        for (OrgRoleVsService orgRoleVsService : orgRoleVsServices) {
            orgRoleVsServiceApi.delete(orgRoleVsService.getServiceId(), orgRoleVsService.getRoleId());
        }
        StringData stringData = new StringData();
        stringData.setData("success");
        stringData.setCode("1");
        return  stringData;
    }

    /**
     * 删除
     * @param
     * @return
     */
    @Path("delete-orgRole")
    @POST
    public StringData del(String ids) {
        String num = orgRoleVsServiceApi.del(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
