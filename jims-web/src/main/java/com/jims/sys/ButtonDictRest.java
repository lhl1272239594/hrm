package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.api.ButtonDictApi;
import com.jims.sys.entity.ButtonDict;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.MENU_DICT_BUTTON;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单
 *
 * @author yangchen
 * @version 2016-08-17
 */
@Component
@Produces("application/json")
@Path("buttonDict")
public class ButtonDictRest {

    @Reference(version = "1.0.0")
    private ButtonDictApi buttonDictApi;

    /**
     * 异步加载表格
     *
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public List<ButtonDict> list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<ButtonDict> list = new ArrayList<ButtonDict>();
        list = buttonDictApi.findAll();
        return list;
    }

    /**
     * 获取单条数据
     *
     * @param mid
     * @return
     */
    @Path("get")
    @GET
    public List<MENU_DICT_BUTTON> get(@QueryParam("mid") String mid) {
        List<MENU_DICT_BUTTON> list = buttonDictApi.getALLbut(mid);
        return list;
    }

    /**
     * 保存修改方法
     *
     * @param buttonDict
     * @return
     */
    @Path("save")
    @POST
    public StringData save(ButtonDict buttonDict) {
        String num = buttonDictApi.save(buttonDict);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 保存方法(返回对象)
     *
     * @param buttonDict
     * @return
     */
    @Path("insertReturnObject")
    @POST
    public ButtonDict insertReturnObject(ButtonDict buttonDict) {
        buttonDict = buttonDictApi.get(buttonDictApi.insertReturnObject(buttonDict));
        return buttonDict;
    }
    /**
     * 修改方法(返回对象)
     *
     * @param buttonDict
     * @return
     */
    @Path("updateReturnObject")
    @POST
    public ButtonDict updateReturnObject(ButtonDict buttonDict) {
        buttonDict = buttonDictApi.get(buttonDictApi.updateReturnObject(buttonDict));
        return buttonDict;
    }

    /**
     * 批量id删除
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public void del(String ids) {
        buttonDictApi.delbt(ids);
    }
    @GET
    @Path("find-menu")
    public List<OrgSelfServiceVsMenu> findSelfServiceVsMenu(@QueryParam("serviceId") String serviceId, @QueryParam("roleId") String
            roleId, @QueryParam("isTree") boolean isTree,@QueryParam("orgid") String orgid) {
        List<OrgSelfServiceVsMenu> menuDictVos = buttonDictApi.findSelfServiceMenu(serviceId, roleId, isTree,orgid);
        return menuDictVos;
    }
    /**
     * 查询所有的科室信息
     *
     * @return
     * + "/buttonDict/getlist?orgId=" + orgId+"&roleid="+rolerow.id+"&serid="+serrow.serviceId+"&mid="+cdrow.menuId
     */
    @Path("getlist")
    @GET
    public List<DeptDict> getlist(@QueryParam("orgId") String orgId, @QueryParam("roleid") String roleid, @QueryParam("serid") String serid, @QueryParam("mid") String mid) {

        //查询出所有的科室信息
        List<DeptDict> list = buttonDictApi.findAllList(orgId,roleid,serid,mid);
        return list;
    }
    /**
     * 更新数据权限
     *
     * @return
     */
    @Path("upmdata")
    @POST
    public ButtonDict upmdata(ButtonDict buttonDict) {

        //查询出所有的科室信息
        buttonDictApi.upmdata(buttonDict);
        return null;
    }

    /**
     * 修改方法(返回对象)
     *
     * @param buttonDict
     * @return  String ORGID,String ROLEID,String ROLENAME,String SERVICEID,String SERVICENAME,String infos
     * 借用 ButtonDict 向后台传递数据
     */
    @Path("upbtnrole")
    @POST
    public ButtonDict upbtnrole(List<ButtonDict> buttonDict) {
        buttonDictApi.upbtnrole(buttonDict);
        return null;
    }
    @GET
    @Path("find-menu-btn")
    public List<OrgSelfServiceVsMenu> findSelfServiceVsMenuBtn(@QueryParam("serviceId") String serviceId, @QueryParam("roleId") String
            roleId, @QueryParam("isTree") boolean isTree, @QueryParam("orgid") String orgid, @QueryParam("meid") String meid) {
        List<OrgSelfServiceVsMenu> menuDictVos = buttonDictApi.findSelfServiceVsMenuBtn(serviceId, roleId, isTree,orgid,meid);
        return menuDictVos;
    }
}
