package com.jims.template;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.template.api.TemplateMasterApi;
import com.jims.template.entity.OrgRoleTemplate;
import com.jims.template.api.OrgRoleTemplateApi;
import com.jims.template.entity.TemplateMaster;
import com.jims.template.vo.OrgRoleTemplateVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
* 角色模板rest
* @author lgx
* @version 2016-08-09
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("orgRoleTemplate")
public class OrgRoleTemplateRest {

    @Reference(version = "1.0.0")
    private OrgRoleTemplateApi api;

    /**
    * 检索
    * @return
    */
    @GET
    @Path("findList")
    public List<OrgRoleTemplate> findList(@QueryParam("area") String area) {
        OrgRoleTemplate entity = new OrgRoleTemplate();
        entity.setArea(area);
        return api.findList(entity);
    }

    /**
     * 检索
     * @return
     */
    @GET
    @Path("findListByMasterId")
    public List<OrgRoleTemplate> findListByMasterId(@QueryParam("masterId") String masterId) {
        OrgRoleTemplate entity = new OrgRoleTemplate();
        entity.setMasterId(masterId);
        return api.findAllList(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(OrgRoleTemplate entity) {
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<OrgRoleTemplate> list) {
        return api.save(list);
    }

    /**
     * 保存 （增删改）
     * @param
     * @return 0 失败，1成功
     */
    @POST
    @Path("saveAll")
    public StringData saveAll(OrgRoleTemplateVo<OrgRoleTemplate> orgRoleTemplateVo) {
        List<OrgRoleTemplate> newUpdateDict = new ArrayList<OrgRoleTemplate>();
        newUpdateDict = api.saveAll(orgRoleTemplateVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

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


    @GET
    @Path("saveTemplate")
    public String saveTemplate(@QueryParam("orgId") String orgId,@QueryParam("area") String area,@QueryParam("areaName")String areaName){

        String a= api.saveTemplate(orgId,area,areaName);
        System.out.print(a);
        return a;
    }
}