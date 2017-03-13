package com.jims.template;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.template.entity.DrugPriceListTemplate;
import com.jims.template.api.DrugPriceListTemplateApi;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
* 药品价格模板rest
* @author lgx
* @version 2016-08-12
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("drugPriceListTemplate")
public class DrugPriceListTemplateRest {

    @Reference(version = "1.0.0")
    private DrugPriceListTemplateApi api;

    /**
    * 检索
    * @param orgId
    * @return
    */
    @GET
    @Path("findList")
    public List<DrugPriceListTemplate> findList(@QueryParam("orgId")String orgId) {
        DrugPriceListTemplate entity = new DrugPriceListTemplate();
        return api.findList(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(DrugPriceListTemplate entity) {
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<DrugPriceListTemplate> list) {
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