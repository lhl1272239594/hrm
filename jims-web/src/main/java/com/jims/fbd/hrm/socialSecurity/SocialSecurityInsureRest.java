package com.jims.fbd.hrm.socialSecurity;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.socialSecurity.api.SocialSecurityInsureApi;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityInsure;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityInsurePerson;
import com.jims.fbd.hrm.socialSecurity.vo.SocialSecurityInsureVo;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by yangchen on 2016/8/23 0.
 */
@Component
@Produces("application/json")
@Path("socialSecurityInsure")
public class SocialSecurityInsureRest {
    @Reference(version = "1.0.0")
    private SocialSecurityInsureApi socialSecurityInsureApi;
    /**
     * 业务处理：查询-分页
     *
     * @return
     */
    @GET
    @Path("ss-insure-list")
    public PageData findList(@QueryParam("orgId") String orgId,
                             @QueryParam("userId") String userId,@QueryParam("deptId") String deptId,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {

        SocialSecurityInsurePerson socialSecurityInsurePerson = new SocialSecurityInsurePerson();
        socialSecurityInsurePerson.setOrgId(orgId);
        socialSecurityInsurePerson.setUserId(userId);
        socialSecurityInsurePerson.setDeptId(deptId);
        Page<SocialSecurityInsurePerson> page = socialSecurityInsureApi.findList(
                new Page<SocialSecurityInsurePerson>(request, response), socialSecurityInsurePerson);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    @GET
    @Path("ss-insure-all-list")
    public List<SocialSecurityInsurePerson> findAllList(@QueryParam("orgId") String orgId) {


        return socialSecurityInsureApi.findAllList(orgId);
    }
    /**
     * 业务处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(SocialSecurityInsureVo<SocialSecurityInsure> socialSecurityInsureVo,
                            @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String personId=p.getId();
        String createDept = loginInfo.getDeptId();
        int num = 0;
        int count = 0;
        String id = socialSecurityInsureVo.getSsInsureId();

        if (id.equals("999")) {
            List<SocialSecurityInsurePerson> ssip=socialSecurityInsureVo.getSocialSecurityInsurePerson();
            for(SocialSecurityInsurePerson socialSecurityInsurePerson:ssip){
                socialSecurityInsurePerson.setSsInsureId(IdGen.uuid());
                socialSecurityInsurePerson.setSsInsureTypeId(socialSecurityInsureVo.getSsInsureTypeId());
                socialSecurityInsurePerson.setSsPlanId(socialSecurityInsureVo.getSsPlanId());
                socialSecurityInsurePerson.setOrgId(socialSecurityInsureVo.getOrgId());
                socialSecurityInsurePerson.setCreateBy(personId);
                socialSecurityInsurePerson.setCreateDept(createDept);
                socialSecurityInsureApi.deletePrimary(socialSecurityInsurePerson);//新增前先删除该人员的社保
                socialSecurityInsureApi.insertPrimary(socialSecurityInsurePerson);
            }

        } else {
            SocialSecurityInsurePerson socialSecurityInsurePerson = new SocialSecurityInsurePerson();
            socialSecurityInsurePerson.setSsInsureId(id);
            socialSecurityInsurePerson.setSsInsureTypeId(socialSecurityInsureVo.getSsInsureTypeId());
            socialSecurityInsurePerson.setSsPlanId(socialSecurityInsureVo.getSsPlanId());
            socialSecurityInsurePerson.setOrgId(socialSecurityInsureVo.getOrgId());
            socialSecurityInsurePerson.setUpdateBy(personId);
            socialSecurityInsureApi.updatePrimary(socialSecurityInsurePerson);
        }

        StringData stringData = new StringData();
        if (count == 1) {
            stringData.setCode(String.valueOf(num));
            stringData.setData("success");
        }
        return stringData;
    }

    @Path("ss-insure-del")
    @POST
    /*public StringData dataDel(SocialSecurityInsureVo<SocialSecurityInsure> socialSecurityInsureVo) {

        String dataId=socialSecurityInsureVo.getSsInsureId();
        String num = socialSecurityInsureApi.delPrimary(dataId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }*/
    public StringData mainDel(List<SocialSecurityInsure> plans) {
        String num = socialSecurityInsureApi.delPrimary(plans);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}

