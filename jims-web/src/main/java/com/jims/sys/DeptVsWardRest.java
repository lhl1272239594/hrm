package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.DeptVsWardApi;
import com.jims.sys.entity.DeptVsWard;
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
@Path("DeptVsWard")
public class DeptVsWardRest {

    @Reference(version = "1.0.0")
    private DeptVsWardApi deptVsWardApi;

    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<DeptVsWard> page=deptVsWardApi.findPage(new Page<DeptVsWard>(request,response),new DeptVsWard());
        PageData<DeptVsWard> pageData=new PageData<DeptVsWard>();
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
    public DeptVsWard get(String id) {
        DeptVsWard deptVsWard = deptVsWardApi.get(id);
        return deptVsWard;
    }

    /**
     * 保存修改方法
     * @param deptVsWard
     * @return
     */
    @Path("save")
    @POST
    public StringData save(DeptVsWard deptVsWard){
        String num=deptVsWardApi.save(deptVsWard);
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
        String num=deptVsWardApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
