package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.sys.entity.MenuUpdateExplain;
import com.jims.sys.api.MenuUpdateExplainApi;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
* 服务菜单更新说明rest
* @author lgx
* @version 2016-07-19
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("menuUpdateExplain")
public class MenuUpdateExplainRest {

    @Reference(version = "1.0.0")
    private MenuUpdateExplainApi api;

    /**
    * 检索
    * @param serviceId
    * @return
    */
    @GET
    @Path("findList")
    public List<MenuUpdateExplain> findList(@QueryParam("serviceId")String serviceId) {
        MenuUpdateExplain entity = new MenuUpdateExplain();
        entity.setServiceId(serviceId);
        return api.findList(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(MenuUpdateExplain entity) {
        return api.save(entity);
    }

    /**
     * 保存（插入或更新）
     * @param
     * @return 0 失败，1成功
     */
    @GET
    @Path("saveExplain")
    public String saveExplain(@QueryParam("id")String id,
                              @QueryParam("explain")String explain,
                              @QueryParam("title")String title,
                              @QueryParam("serviceId")String serviceId){
        MenuUpdateExplain entity = new MenuUpdateExplain();
        entity.setId(id);
        entity.setExplain(explain.getBytes());
        entity.setServiceId(serviceId);
        entity.setTitle(title);
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<MenuUpdateExplain> list) {
        return api.save(list);
    }

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    @GET
    @Path("delete")
    public String delete(@QueryParam("ids")String ids) {
        return api.delete(ids);
    }
}