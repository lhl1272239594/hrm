package com.jims.fbd.hrm.attendance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.attendance.api.OverTimeApi;
import com.jims.fbd.hrm.attendance.entity.OverTime;
import com.jims.fbd.hrm.attendance.vo.OverTimeVo;
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
@Path("overTime")
public class OverTimeRest {
    @Reference(version = "1.0.0")
    private OverTimeApi overTimeApi;
    /**
     * 业务处理：查询带分页
     *
     * @return
     */
    @GET
    @Path("over-time-list")
    public PageData findList(@QueryParam("orgId") String orgId,
                             @QueryParam("userId") String userId,
                             @QueryParam("month")String month,
                             @QueryParam("approveStatus") String approveStatus,
                             @QueryParam("deptIds")String deptIds,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String id=loginInfo.getPersionId();
        OverTime overTime = new OverTime();
        overTime.setOrgId(orgId);
        overTime.setUserId(userId);
        overTime.setApproveStatus(approveStatus);
        overTime.setDeptId(deptIds);
        overTime.setCreateBy(id);
        overTime.setStartDate(month);
        Page<OverTime> page = overTimeApi.findList(new Page<OverTime>(request, response), overTime);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 业务处理：“我的加班”查询
     *
     * @return
     */    @GET
    @Path("myovertime")
    public PageData myovertime(@QueryParam("orgId") String orgId,
                             @QueryParam("approveStatus") String approveStatus,
                             @QueryParam("month")String month,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String id=loginInfo.getPersionId();
        OverTime overTime = new OverTime();
        overTime.setOrgId(orgId);
        overTime.setUserId(id);
        overTime.setApproveStatus(approveStatus);
        overTime.setStartDate(month);
        Page<OverTime> page = overTimeApi.myovertime(new Page<OverTime>(request, response), overTime);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 业务处理：查询不带分页
     *
     * @return
     */
    @GET
    @Path("over-time-all-list")
    public List<OverTime> finAllList(@QueryParam("orgId") String orgId,
                                    @QueryParam("userId") String userId,
                                    @QueryParam("value") String value) {

        OverTime overTime = new OverTime();
        overTime.setOrgId(orgId);
        overTime.setUserId(userId);
        overTime.setOverTimeId(value);

        return overTimeApi.findAllList(overTime);
    }
    /**
     * 业务处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(OverTimeVo<OverTime> overTimeVo,@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        int num = 0;
        int count = 0;
        String id = overTimeVo.getOverTimeId();
        OverTime overTime = new OverTime();

        if (id.equals("999")) {

            overTime.setOverTimeId(IdGen.uuid());
            overTime.setStartDate(overTimeVo.getStartDate());
            overTime.setEndDate(overTimeVo.getEndDate());
            overTime.setOverTimeType(overTimeVo.getOverTimeType());
            overTime.setOverTimeReason(overTimeVo.getOverTimeReason());
            overTime.setOrgId(overTimeVo.getOrgId());
            overTime.setUserId(overTimeVo.getUserId());
            overTime.setDeptId(overTimeVo.getDeptId());
            overTime.setCreateBy(userId);
            overTime.setCreateDept(loginInfo.getDeptId());
            count = count + Integer.parseInt(overTimeApi.insertPrimary(overTime));


        } else {

            overTime.setOverTimeId(id);
            overTime.setStartDate(overTimeVo.getStartDate());
            overTime.setEndDate(overTimeVo.getEndDate());
            overTime.setOverTimeType(overTimeVo.getOverTimeType());
            overTime.setOverTimeReason(overTimeVo.getOverTimeReason());
            overTime.setOrgId(overTimeVo.getOrgId());
            overTime.setUserId(overTimeVo.getUserId());
            overTime.setDeptId(overTimeVo.getDeptId());
            count = count + Integer.parseInt(overTimeApi.updatePrimary(overTime));
        }

        StringData stringData = new StringData();
        if (count == 1) {
            stringData.setCode(String.valueOf(num));
            stringData.setData("success");
        }
        return stringData;
    }

    @Path("over-time-del")
    @POST
    public StringData dataDel(OverTimeVo<OverTime> overTimeVo) {

        String dataId=overTimeVo.getOverTimeId();
        String num = overTimeApi.delPrimary(dataId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}

