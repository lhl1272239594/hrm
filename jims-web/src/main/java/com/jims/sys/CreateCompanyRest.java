package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jims.common.data.StringData;
import com.jims.common.utils.CookieUtils;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.SysCompanyApi;
import com.jims.sys.api.SysUserApi;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysUser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
@Component
@Produces("application/json")
@Path("sys-company")
public class CreateCompanyRest {


    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Reference(version = "1.0.0")
    private SysCompanyApi sysCompanyApi;

    @Reference(version = "1.0.0")
    private SysUserApi sysUserApi;

    /**
     * 保存修改方法
     *
     * @param
     * @return
     */
    @Path("add")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public StringData save(SysCompany sysCompany) {
        if (StringUtils.isNotBlank(sysCompany.getOrgName()) && StringUtils.isNotBlank(sysCompany.getOrgCode()) &&
                StringUtils.isNotBlank(sysCompany.getLinkPhoneNum()) && StringUtils.isNotBlank(sysCompany.getAddress()) && StringUtils.isNotBlank(sysCompany.getEmail())) {
            sysCompany.setApplyStatus("0");

            if (StringUtils.equalsIgnoreCase(sysCompany.getParentId(), " ")) {
                sysCompany.setParentId(null);
            }
            String num = sysCompanyApi.save(sysCompany);
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }

        return null;
    }

    //存储插入成功后返回的id
    String id;
    SysUser user = null;


    /**
     * 保存组织机构并返回id
     *
     * @param sysCompany
     * @param request
     * @return
     */
    @Path("insertReturnId")
    @POST
    public StringData insertReturnId(SysCompany sysCompany, @Context HttpServletRequest request) {
        if (StringUtils.isNotBlank(sysCompany.getOrgName()) && StringUtils.isNotBlank(sysCompany.getOrgCode()) &&
                StringUtils.isNotBlank(sysCompany.getLinkPhoneNum()) && StringUtils.isNotBlank(sysCompany.getAddress()) && StringUtils.isNotBlank(sysCompany.getEmail())) {
            sysCompany.setApplyStatus("1");

            //如果没有父机构则向数据库中存入空
            String parent=sysCompany.getParentId();
            if (StringUtils.equalsIgnoreCase(parent,"请选择")) {
                sysCompany.setParentId(null);
            }
            id = sysCompanyApi.insertReturnId(sysCompany);
            if (id != null) {
                StringData stringData = new StringData();
                stringData.setData("success");
                return stringData;
            }
            //保存

        }
        StringData stringData = new StringData();
        return stringData;
    }


    /**
     * 查询父机构
     *
     * @return
     */
    @GET
    @Path("select")
    public List<SysCompany> findAllByName(@QueryParam("persionId") String persionId) {

        List<SysCompany> list = sysCompanyApi.findListByName(persionId);

        return list;
    }

    /**
     * 根据id查询组织机构信息
     *
     * @param
     * @return
     */
    @GET
    @Path("get")
    public SysCompany get() {
        return sysCompanyApi.get(id);
    }

    /**
     * 修改组织机构信息
     *
     * @param
     * @return
     */
    @POST
    @Path("update")
    public StringData update(SysCompany sysCompany) {
        System.out.print(sysCompany.getId());
        int num = sysCompanyApi.update(sysCompany);
        if (num != 0) {
            StringData stringData = new StringData();
            stringData.setData("success");
            return stringData;
        }
        return null;

    }

    /**
     * 保存注册信息以及选择的服务
     * @param sysCompany
     * @return 1 成功 ,0 失败
     */
    @Path("saveCompanyAndService")
    @POST
    public String saveCompanyAndService(SysCompany sysCompany) {
        if (StringUtils.isNotBlank(sysCompany.getOrgName()) && StringUtils.isNotBlank(sysCompany.getOrgCode()) &&
                StringUtils.isNotBlank(sysCompany.getLinkPhoneNum()) && StringUtils.isNotBlank(sysCompany.getAddress()) && StringUtils.isNotBlank(sysCompany.getEmail())) {
            sysCompany.setApplyStatus("1");

            //如果没有父机构则向数据库中存入空
            String parent=sysCompany.getParentId();
            if (StringUtils.equalsIgnoreCase(parent,"请选择")) {
                sysCompany.setParentId(null);
            }
            return sysCompanyApi.saveCompanyAndService(sysCompany);
        }
        return "0";
    }
}
