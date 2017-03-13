package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.StaffGroupClassDictApi;
import com.jims.sys.entity.StaffGroupClassDict;
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
@Path("StaffGroupClassDict")
public class StaffGroupClassDictRest {

    @Reference(version = "1.0.0")
    private StaffGroupClassDictApi staffGroupClassDictApi;

    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<StaffGroupClassDict> page=staffGroupClassDictApi.findPage(new Page<StaffGroupClassDict>(request,response),new StaffGroupClassDict());
        PageData<StaffGroupClassDict> pageData=new PageData<StaffGroupClassDict>();
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
    public StaffGroupClassDict get(String id) {
        StaffGroupClassDict staffGroupClassDict = staffGroupClassDictApi.get(id);
        return staffGroupClassDict;
    }

    /**
     * 保存修改方法
     * @param staffGroupClassDict
     * @return
     */
    @Path("save")
    @POST
    public StringData save(StaffGroupClassDict staffGroupClassDict){
        String num=staffGroupClassDictApi.save(staffGroupClassDict);
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
        String num=staffGroupClassDictApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
