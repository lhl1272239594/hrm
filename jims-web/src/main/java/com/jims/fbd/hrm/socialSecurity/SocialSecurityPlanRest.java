package com.jims.fbd.hrm.socialSecurity;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.socialSecurity.api.SocialSecurityPlanApi;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityPlan;
import com.jims.fbd.hrm.socialSecurity.vo.SocialSecurityPlanVo;
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
@Path("socialSecurityPlan")
public class SocialSecurityPlanRest {
    @Reference(version = "1.0.0")
    private SocialSecurityPlanApi socialSecurityPlanApi;


    /**
     * 业务处理：社保方案查询-分页
     *
     * @return
     */
    @GET
    @Path("ss-plan-list")
    public PageData  findList(@QueryParam("orgId") String orgId,@QueryParam("name") String name,
                                               @Context HttpServletRequest request,
                                               @Context HttpServletResponse response){

        SocialSecurityPlan socialSecurityPlan = new SocialSecurityPlan();
        socialSecurityPlan.setOrgId(orgId);
        socialSecurityPlan.setSsPlanDes(name);
        Page<SocialSecurityPlan> page= socialSecurityPlanApi.findList(new Page<SocialSecurityPlan>(request, response),
                socialSecurityPlan);
        PageData pageData=new PageData();
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
    @Path("ss-plan-all-list")
    public List<SocialSecurityPlan> findAllList(@QueryParam("orgId") String orgId,@QueryParam("value") String value) {

        SocialSecurityPlan socialSecurityPlan = new SocialSecurityPlan();
        socialSecurityPlan.setOrgId(orgId);
        socialSecurityPlan.setSsPlanId(value);
        return socialSecurityPlanApi.findAllList(socialSecurityPlan);
    }
    /**
     * 业务处理：社保方案详细信息查询
     *
     * @return
     */
    @GET
    @Path("ss-plan-detail-list")
    public List<SocialSecurityPlan> findDetailList(@QueryParam("orgId") String orgId,@QueryParam("ssPlanId") String ssPlanId) {

        SocialSecurityPlan socialSecurityPlan = new SocialSecurityPlan();
        socialSecurityPlan.setOrgId(orgId);
        socialSecurityPlan.setSsPlanId(ssPlanId);
        return socialSecurityPlanApi.findDetailList(socialSecurityPlan);
    }
    /**
     * 新增重复判断
     *
     * @return
     */
    @GET
    @Path("if-exist")
    public List<SocialSecurityPlan> findSame(@QueryParam("orgId") String orgId, @QueryParam("ssPlanDes") String ssPlanDes, @QueryParam("id") String id) {
        return socialSecurityPlanApi.findSame(orgId,ssPlanDes,id);
    }

    /**
     * 业务处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(SocialSecurityPlanVo<SocialSecurityPlan> ssPlanVo,
                            @Context HttpServletRequest request) {
        int num = 0;
        int count = 0;
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        String orgId = loginInfo.getOrgId();
        String deptId=loginInfo.getDeptId();

        String id =ssPlanVo.getSsPlanId();
        SocialSecurityPlan socialSecurityPlan = new SocialSecurityPlan();

        if (id.equals("999")) {
            //保存头信息
            id=IdGen.uuid();
            socialSecurityPlan.setSsPlanId(id);
            socialSecurityPlan.setSsPlanDes(ssPlanVo.getSsPlanDes());
            socialSecurityPlan.setOrgId(orgId);
            socialSecurityPlan.setCreateBy(userId);
            socialSecurityPlan.setCreateDept(deptId);
            count = count + Integer.parseInt(socialSecurityPlanApi.insertPrimary(socialSecurityPlan));
            //保存行信息
            for (int i = 0; i < ssPlanVo.getInserted().size(); i++) {
                socialSecurityPlan = ssPlanVo.getInserted().get(i);
                socialSecurityPlan.setSsPlanId(id);
                socialSecurityPlan.setSsDetailId(IdGen.uuid());
                socialSecurityPlan.setPersonPay(socialSecurityPlan.getPersonPay());
                socialSecurityPlan.setCompanyPay(socialSecurityPlan.getCompanyPay());
                socialSecurityPlan.setOrgId(ssPlanVo.getOrgId());
                socialSecurityPlan.setCreateBy(userId);
                socialSecurityPlan.setCreateDept(deptId);
                count = count + Integer.parseInt(socialSecurityPlanApi.insertForeign(socialSecurityPlan));
            }

        } else {
            socialSecurityPlan.setSsPlanId(id);
            socialSecurityPlan.setSsPlanDes(ssPlanVo.getSsPlanDes());
            socialSecurityPlan.setOrgId(ssPlanVo.getOrgId());
            socialSecurityPlan.setUpdateBy(userId);
            count = count + Integer.parseInt(socialSecurityPlanApi.updatePrimary(socialSecurityPlan));

            for (int i = 0; i < ssPlanVo.getInserted().size(); i++) {
                socialSecurityPlan = ssPlanVo.getInserted().get(i);
                socialSecurityPlan.setSsPlanId(id);
                socialSecurityPlan.setSsDetailId(IdGen.uuid());
                socialSecurityPlan.setPersonPay(socialSecurityPlan.getPersonPay());
                socialSecurityPlan.setCompanyPay(socialSecurityPlan.getCompanyPay());
                socialSecurityPlan.setOrgId(ssPlanVo.getOrgId());
                socialSecurityPlan.setUpdateBy(userId);
                socialSecurityPlan.setCreateDept(deptId);
                count = count + Integer.parseInt(socialSecurityPlanApi.insertForeign(socialSecurityPlan));
            }

            for (int i = 0; i < ssPlanVo.getUpdated().size(); i++) {

                socialSecurityPlan = ssPlanVo.getUpdated().get(i);
                socialSecurityPlan.setSsPlanId(id);
                socialSecurityPlan.setSsDetailId(socialSecurityPlan.getSsDetailId());
                socialSecurityPlan.setPersonPay(socialSecurityPlan.getPersonPay());
                socialSecurityPlan.setCompanyPay(socialSecurityPlan.getCompanyPay());
                socialSecurityPlan.setOrgId(ssPlanVo.getOrgId());
                socialSecurityPlan.setUpdateBy(userId);
                count = count + Integer.parseInt(socialSecurityPlanApi.updateForeign(socialSecurityPlan));
            }

        }


        StringData stringData = new StringData();
        if (count == (ssPlanVo.getInserted().size() + ssPlanVo.getUpdated().size())) {
            num = 1;
        }
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }
    /**
     * 业务处理：社保方案删除
     *
     * @return
     */
    @Path("ss-plan-del")
    @POST
    public StringData mainDel(List<SocialSecurityPlan> plans) {
        String num = socialSecurityPlanApi.delPrimary(plans);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 删除占用判断
     *
     * @return
     */
    @POST
    @Path("if-occupy")
    public StringData findOccupy(List<SocialSecurityPlan> projects) {
        String num = socialSecurityPlanApi.findOccupy(projects);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (num != null&&num =="yes") {
            stringData.setData("success");
        }
        return stringData;
    }
    /**
     * 业务处理：社保方案详细信息删除
     *
     * @return
     */
    @Path("ss-detail-del")
    @GET
    public StringData detailDel(@QueryParam("id") String id) {
        String num = socialSecurityPlanApi.delForeign(id);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


}
