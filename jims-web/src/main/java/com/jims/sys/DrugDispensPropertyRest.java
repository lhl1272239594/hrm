package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.DrugDispensPropertyApi;
import com.jims.sys.entity.DrugDispensProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Created by Administrator on 2016/4/20.
 */

@Component
@Produces("application/json")
@Path("DrugDispensProperty")
public class DrugDispensPropertyRest {

    @Reference(version = "1.0.0")
    private DrugDispensPropertyApi drugDispensPropertyApi;

    @GET
    @Path("list")
    public PageData list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Page<DrugDispensProperty> page = drugDispensPropertyApi.findPage(new Page<DrugDispensProperty>(request, response), new DrugDispensProperty());
        PageData<DrugDispensProperty> pageData = new PageData<DrugDispensProperty>();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public DrugDispensProperty get(String id) {
        DrugDispensProperty drugDispensProperty = drugDispensPropertyApi.get(id);
        return drugDispensProperty;
    }

    /**
     * 保存修改方法
     * @param drugDispensProperty
     * @return
     */
    @Path("save")
    @POST
    public StringData save(DrugDispensProperty drugDispensProperty){
        String num=drugDispensPropertyApi.save(drugDispensProperty);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids){
        String num=drugDispensPropertyApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}