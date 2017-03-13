package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.register.api.OrgServiceManagerApi;
import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;
import com.jims.sys.entity.ServiceSelfVsSys;
import com.jims.sys.vo.MenuDictVo;
import com.jims.sys.vo.OrgSelfServiceVsMenuVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 机构服务Rest层
 * @author lgx
 * @version 2016-05-31
 */
@Component
@Produces("application/json")
@Path("org-service")
public class OrgServiceManagerRest {

    @Reference(version = "1.0.0")
    private OrgServiceManagerApi api;

    /**
     * 保存定制的服务
     * @param serviceList
     * @return
     */
    @POST
    @Path("save-service")
    public String saveService(List<OrgServiceList> serviceList){
        return api.saveService(serviceList);
    }

    /**
     * 保存机构自定义的服务
     * @param selfServiceList 自定义服务以及菜单,
     *                        参数OrgSelfServiceList属性中
     *                        delFlag 为 1 时，属性id为药删除的自定义服务id,多个以‘,’隔开，
     *                        id不为空，orgId为空时，属性menus为服务(id)对应的菜单数据(树形结构)
     *                                              servicesVs 为对应平台服务数据
     *
     *                        其他值时，为修改的自定义服务，当为添加的自定义服务时，包含所有需添加信息。
     * @return 0保存失败，1保存成功
     */
    @POST
    @Path("save-self-service")
    public String saveSelfService(List<OrgSelfServiceList> selfServiceList){
        return api.saveSelfService(selfServiceList);
    }

    /**
     * 检索机构购买的服务、菜单
     * @param orgId 机构Id
     * @return
     */
    @GET
    @Path("find-service")
    public List<OrgServiceList> findService(@QueryParam("orgId")String orgId){
        return api.findService(orgId);
    }

    /**
     * 检索自定义服务
     * @param orgId
     * @return
     */
    @GET
    @Path("find-self-service")
    public List<OrgSelfServiceList> findSelfService(@QueryParam("orgId") String orgId){
        return api.findSelfService(orgId);
    }

    /**
     * 检索自定义服务
     * @param personId  人员ID
     * @param orgId   机构ID
     * @return  自定义服务
     * @author fengyuguang
     */
    @GET
    @Path("find-selfServiceList-by-orgId-personId")
    public List<OrgSelfServiceList> findSelfServiceByOrgIdPersonId(@QueryParam("personId")String personId,@QueryParam("orgId")String orgId){
        return api.findSelfServiceByOrgIdPersonId(personId, orgId);
    }

    /**
     * 检索自定义服务对应菜单
     * @param selfServiceId
     * @return
     */
    @GET
    @Path("find-self-service-menu")
    public List<OrgSelfServiceVsMenu> findSelfServiceMenu(@QueryParam("selfServiceId") String selfServiceId,
                                                          @QueryParam("isTree") boolean isTree){
        return api.findSelfServiceVsMenu(selfServiceId,isTree);
    }

    @GET
    @Path("find-menu")
    public List<OrgSelfServiceVsMenu> findSelfServiceVsMenu(@QueryParam("serviceId") String serviceId, @QueryParam("roleId") String
            roleId,@QueryParam("isTree") boolean isTree) {
        List<OrgSelfServiceVsMenu> menuDictVos = api.findSelfServiceMenu(serviceId, roleId,isTree);
        return menuDictVos;
    }

    /**
     * 检索自定义服务对应平台服务
     * @param selfServiceId
     * @return
     */
    @GET
    @Path("findVsSys")
    public List<ServiceSelfVsSys> findVsSys(@QueryParam("selfServiceId") String selfServiceId){
        return api.findVsSys(selfServiceId);
    }
    /**
     * 检索自定义服务对应功能按钮
     * @param
     * @return
     */

    @GET
    @Path("find-button")
    public List<OrgSelfServiceVsMenu> findSelfServiceVsButton(@QueryParam("serviceId") String serviceId, @QueryParam("roleId") String
            roleId,@QueryParam("isTree") boolean isTree) {
        List<OrgSelfServiceVsMenu> menuDictVos = api.findSelfServiceVsButton(serviceId, roleId,isTree);
        return menuDictVos;
    }
}
