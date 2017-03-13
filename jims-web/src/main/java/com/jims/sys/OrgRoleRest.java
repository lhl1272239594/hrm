package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.OrgRoleApi;
import com.jims.sys.api.OrgStaffApi;
import com.jims.sys.api.PersionInfoApi;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by yangruidong on 2016/5/31 0024.
 */
@Component
@Produces("application/json")
@Path("org-role")
public class OrgRoleRest {
    @Reference(version = "1.0.0")
    private OrgRoleApi orgRoleApi;

    @GET
    @Path("findAllListByOrgId")
    public List<OrgRole> findAllListByOrgId(@QueryParam("orgId") String orgId)
    {
        return orgRoleApi.findAllList(orgId);
    }
    @GET
    @Path("findAllListByOrgId1")
    public List<OrgRole> findAllListByOrgId1(@QueryParam("orgId") String orgId)
    {
        return orgRoleApi.findAllList1(orgId);
    }
    /**
     * 根据角色名称模糊查询数据
     * @param roleName  角色名称
     * @param orgId  组织机构ID
     * @return
     * @author fengyuguang
     */
    @GET
    @Path("find-by-name")
    public List<OrgRole> findByRoleName(@QueryParam("roleName")String roleName,@QueryParam("orgId")String orgId){
        return orgRoleApi.findByRoleName(roleName,orgId);
    }



    /**
     * 保存增删改
     * @param beanChangeVo 角色增删改集合
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("merge")
    public StringData merge(BeanChangeVo<OrgRole> beanChangeVo) {
        String num = orgRoleApi.merge(beanChangeVo);
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
     * 导入模板数据
     * @param orgId
     * @param
     * @return
     */
    @GET
    @Path("insertTemplate")
    public StringData saveAll(@QueryParam("orgId") String orgId,@QueryParam("masterId") String masterId) {
        OrgRole orgRole=new OrgRole();
        orgRole.setOrgId(orgId);
        String num=orgRoleApi.insertByTemplate(orgRole,masterId);
        StringData stringData = new StringData();
        if(num !=""){
            stringData.setData("success");
        }
        return stringData;
    }
}
