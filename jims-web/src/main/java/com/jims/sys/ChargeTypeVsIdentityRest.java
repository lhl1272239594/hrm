package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.ChargeTypeVsIdentityApi;
import com.jims.sys.entity.ChargeTypeVsIdentity;
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
@Path("ChargeTypeVsIdentity")
public class ChargeTypeVsIdentityRest {

    @Reference(version = "1.0.0")
    private ChargeTypeVsIdentityApi chargeTypeVsIdentityApi;

    @Path("findList")
    @GET
    public PageData findList(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<ChargeTypeVsIdentity> page=chargeTypeVsIdentityApi.findPage(new Page<ChargeTypeVsIdentity>(request,response),new ChargeTypeVsIdentity());
        PageData<ChargeTypeVsIdentity> pageData=new PageData<ChargeTypeVsIdentity>();
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
    public ChargeTypeVsIdentity get(String id) {
        ChargeTypeVsIdentity chargeTypeVsIdentity = chargeTypeVsIdentityApi.get(id);
        return chargeTypeVsIdentity;
    }

    /**
     * 保存修改方法
     * @param chargeTypeVsIdentity
     * @return
     */
    @Path("save")
    @POST
    public StringData save(ChargeTypeVsIdentity chargeTypeVsIdentity){
        String num=chargeTypeVsIdentityApi.save(chargeTypeVsIdentity);
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
        String num=chargeTypeVsIdentityApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
