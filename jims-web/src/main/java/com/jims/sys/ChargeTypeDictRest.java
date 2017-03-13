package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.ChargeTypeDictApi;
import com.jims.sys.entity.ChargeTypeDict;
import com.jims.sys.vo.BeanChangeVo;
import org.apache.commons.fileupload.util.LimitedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20.
 */
@Component
@Produces("application/json")
@Path("ChargeTypeDict")
public class ChargeTypeDictRest {

    @Reference(version = "1.0.0")
    private ChargeTypeDictApi chargeTypeDictApi;

    /**
     * 查询某个组织机构的费别列表
     * @param orgId  组织机构ID
     * @param request
     * @param response
     * @return
     * @author fengyuguang
     */
    @Path("list-by-orgId")
    @GET
    public PageData list(@QueryParam("q")String q,@QueryParam("orgId") String orgId, @Context HttpServletRequest request, @Context
    HttpServletResponse
            response) {
        Page<ChargeTypeDict> page = chargeTypeDictApi.findPage(q,orgId,new Page<ChargeTypeDict>(request, response), new ChargeTypeDict());
        PageData<ChargeTypeDict> pageData = new PageData<ChargeTypeDict>();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("merge")
    public StringData merge(BeanChangeVo<ChargeTypeDict> beanChangeVo) {
        String num = chargeTypeDictApi.merge(beanChangeVo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }

    /**
     * 根据名称模糊查询数据
     * @param chargeTypeName 费别名称
     * @param orgId  组织机构ID
     * @return
     * @author fengyuguang
     */
    @Path("search")
    @GET
    public List<ChargeTypeDict> search(@QueryParam("chargeTypeName")String chargeTypeName,@QueryParam("orgId")String orgId){
        return chargeTypeDictApi.search(chargeTypeName,orgId);
    }

    /**
     * 查询某个组织机构的费别列表
     * @param orgId  组织机构ID
     * @return
     * @author fengyuguang
     */
    @Path("list-all")
    @GET
    public List<ChargeTypeDict> listAll(@QueryParam("orgId")String orgId){
        return chargeTypeDictApi.listAll(orgId);
    }

    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<ChargeTypeDict> page=chargeTypeDictApi.findPage(new Page<ChargeTypeDict>(request,response),new ChargeTypeDict());
        PageData<ChargeTypeDict> pageData=new PageData<ChargeTypeDict>();
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
    public ChargeTypeDict get(String id) {
        ChargeTypeDict chargeTypeDict = chargeTypeDictApi.get(id);
        return chargeTypeDict;
    }

    /**
     * 保存修改方法
     * @param chargeTypeDict
     * @return
     */
    @Path("save")
    @POST
    public StringData save(ChargeTypeDict chargeTypeDict){
        String num=chargeTypeDictApi.save(chargeTypeDict);
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
        String num=chargeTypeDictApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
