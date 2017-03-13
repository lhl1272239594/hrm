package com.jims.template;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.template.entity.TemplateMaster;
import com.jims.template.api.TemplateMasterApi;
import com.jims.template.vo.OrgRoleTemplateVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板信息rest
 *
 * @author lgx
 * @version 2016-08-09
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("templateMaster")
public class TemplateMasterRest {

    @Reference(version = "1.0.0")
    private TemplateMasterApi api;

    /**
     * 检索
     *
     * @return
     */
    @GET
    @Path("findList")
    public List<TemplateMaster> findList(@QueryParam("tableName") String tableName,@QueryParam("area") String area) {
        TemplateMaster entity = new TemplateMaster();
        entity.setTableName(tableName);
        entity.setArea(area);
        return api.findList(entity);
    }

    /**
     * 保存（插入或更新）
     *
     * @param entity
     * @return 0 失败，1成功
     */
    @POST
    @Path("save")
    public String save(TemplateMaster entity) {
        return api.save(entity);
    }

    /**
     * 批量保存（插入或更新）
     *
     * @param list
     * @return 0 失败，1成功
     */
    @POST
    @Path("saveList")
    public String saveList(List<TemplateMaster> list) {
        return api.save(list);
    }

    /**
     * 删除数据
     *
     * @param ids,多个id以逗号（,）隔开
     * @return 0 失败，1成功
     */
    @GET
    @Path("delete")
    public String delete(String ids) {
        return api.delete(ids);
    }


    /**
     * 保存 （增删改）
     *
     * @param
     * @return 0 失败，1成功
     */
    @POST
    @Path("saveAll")
    public StringData saveAll(OrgRoleTemplateVo<TemplateMaster> templateVo) {
        List<TemplateMaster> newUpdateDict = new ArrayList<TemplateMaster>();
        newUpdateDict = api.saveAll(templateVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }

    /**
     * 根据区域查询模板
     *
     * @param
     * @return
     */
    @GET
    @Path("findListByArea")
    public List<TemplateMaster> findListByArea(@QueryParam("area") String area) {
        TemplateMaster templateMaster = new TemplateMaster();
        templateMaster.setArea(area);
        return api.getArea(templateMaster);
    }
}