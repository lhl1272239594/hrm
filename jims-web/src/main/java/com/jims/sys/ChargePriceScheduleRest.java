package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.ChargePriceScheduleApi;
import com.jims.sys.entity.ChargePriceSchedule;
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
@Path("ChargePriceSchedule")
public class ChargePriceScheduleRest {

    @Reference(version = "1.0.0")
    private ChargePriceScheduleApi chargePriceScheduleApi;

    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Page<ChargePriceSchedule> page = chargePriceScheduleApi.findPage(new Page<ChargePriceSchedule>(request, response), new ChargePriceSchedule());
        PageData<ChargePriceSchedule> pageData = new PageData<ChargePriceSchedule>();
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
    public ChargePriceSchedule get(String id) {
        ChargePriceSchedule chargePriceSchedule = chargePriceScheduleApi.get(id);
        return chargePriceSchedule;
    }

    /**
     * 保存修改方法
     * @param chargePriceSchedule
     * @return
     */
    @Path("save")
    @POST
    public StringData save(ChargePriceSchedule chargePriceSchedule){
        String num=chargePriceScheduleApi.save(chargePriceSchedule);
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
        String num=chargePriceScheduleApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}