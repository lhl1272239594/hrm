package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.DeptPropertyDictApi;
import com.jims.sys.api.SysCompanyApi;
import com.jims.sys.entity.*;
import com.jims.sys.vo.OrgDeptPropertyDictVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
@Component
@Produces("application/json")
@Path("dept-property")
public class DeptPropertyDictRest {

    @Reference(version = "1.0.0")
    private DeptPropertyDictApi deptPropertyDictApi;

    @Reference(version = "1.0.0")
    private SysCompanyApi sysCompanyApi;

    /**
     * 分页查询科室属性信息
     *
     * @param request
     * @param response
     * @return
     */
    @GET
    @Path("list")
    public PageData list(@QueryParam("orgId") String orgId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        OrgDeptPropertyDict orgDeptPropertyDict = new OrgDeptPropertyDict();
        orgDeptPropertyDict.setOrgId(orgId);
        Page<OrgDeptPropertyDict> page = deptPropertyDictApi.findPage(new Page<OrgDeptPropertyDict>(request, response), orgDeptPropertyDict);
        PageData<OrgDeptPropertyDict> pageData = new PageData<OrgDeptPropertyDict>();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 根据条件查询科室属性信息
     *
     * @param
     * @return
     */
    @GET
    @Path("findByCondition")
    public List<OrgDeptPropertyDict> findByCondition(@QueryParam("propertyType") String propertyType,
                                                     @QueryParam("propertyName") String propertyName,
                                                     @QueryParam("propertyValue") String propertyValue,
                                                     @QueryParam("orgId") String orgId) {
        OrgDeptPropertyDict orgDeptPropertyDict = new OrgDeptPropertyDict();
        orgDeptPropertyDict.setPropertyName(propertyName);
        orgDeptPropertyDict.setPropertyValue(propertyValue);
        orgDeptPropertyDict.setOrgId(orgId);
        orgDeptPropertyDict.setPropertyType(propertyType);
        List<OrgDeptPropertyDict> list = deptPropertyDictApi.findByCondition(orgDeptPropertyDict);
        return list;
    }


    /**
     * 查询属性信息并且排序
     *
     * @return
     */
    @Path("selectProperty")
    @GET
    public List<OrgDeptPropertyDict> findProperty(@QueryParam("orgId") String orgId) {
        List<OrgDeptPropertyDict> list = deptPropertyDictApi.findProperty(orgId);
        return list;
    }

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    @Path("get")
    @GET
    public OrgDeptPropertyDict get(String id) {
        OrgDeptPropertyDict orgDeptPropertyDict = deptPropertyDictApi.get(id);
        return orgDeptPropertyDict;
    }

    /**
     * 查询属性名称
     *
     * @return
     */
    @POST
    @Path("selectName/{deptPropertity}/{orgId}")
    public List<OrgDeptPropertyDict> findNameByType(@PathParam("deptPropertity") String deptPropertity, @PathParam("orgId") String orgId) {
        OrgDeptPropertyDict orgDeptPropertyDict=new OrgDeptPropertyDict();
        orgDeptPropertyDict.setPropertyType(deptPropertity);
        orgDeptPropertyDict.setOrgId(orgId);
        List<OrgDeptPropertyDict> list = deptPropertyDictApi.findByCondition(orgDeptPropertyDict);
        return list;
    }

    /**
     * 保存  增删改
     *
     * @param orgDeptPropertyDictVo
     * @return
     * @author yangruidong
     */
    @Path("saveAll")
    @POST
    public StringData saveAll(OrgDeptPropertyDictVo<OrgDeptPropertyDict> orgDeptPropertyDictVo) {
        List<OrgDeptPropertyDict> newUpdateDict = new ArrayList<OrgDeptPropertyDict>();
        newUpdateDict = deptPropertyDictApi.saveAll(orgDeptPropertyDictVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }

    /**
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = deptPropertyDictApi.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

}
