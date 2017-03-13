package com.jims.fbd.hrm.attendance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.attendance.api.ApproveApi;
import com.jims.fbd.hrm.attendance.entity.Approve;
import com.jims.fbd.hrm.attendance.vo.ApproveVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

/**
 * Created by yangchen on 2016/8/23 0.
 */
@Component
@Produces("application/json")
@Path("approve")
public class ApproveRest {
    @Reference(version = "1.0.0")
    private ApproveApi approveApi;

    @GET
    @Path("approve-list")
    public PageData findList(@QueryParam("orgId") String orgId,
                             @QueryParam("userId") String userId,
                             @QueryParam("month")String month,
                             @QueryParam("attFunId") String attFunId,
                             @QueryParam("approveStatus") String approveStatus,
                             @QueryParam("deptIds") String deptIds,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {

        Approve approve = new Approve();
        approve.setOrgId(orgId);
        approve.setUserId(userId);
        approve.setAttFunId(attFunId);
        approve.setApproveStatus(approveStatus);
        approve.setDeptId(deptIds);
        approve.setStartDate(month);
        Page<Approve> page = approveApi.findList(new Page<Approve>(request, response), approve);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }



    @Path("approve-data")
    @POST
    public StringData approve(ApproveVo<Approve> approveVo, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String id=loginInfo.getPersionId();
        Approve approve = new Approve();
        approve.setDataId(approveVo.getDataId());
        approve.setAttFunId(approveVo.getAttFunId());
        approve.setFlag(approveVo.getFlag());
        approve.setApprovePersonId(id);
        String num = approveApi.approve(approve);

        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}

