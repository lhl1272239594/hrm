package com.jims.fbd.hrm.attendance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.attendance.api.OffWorkApi;
import com.jims.fbd.hrm.attendance.entity.OffWork;
import com.jims.fbd.hrm.attendance.vo.OffWorkVo;
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
@Path("offWork")
public class OffWorkRest {
    @Reference(version = "1.0.0")
    private OffWorkApi offWorkApi;
    /**
     * 业务处理：查询带分页
     *
     * @return
     */
    @GET
    @Path("off-work-list")
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
        OffWork offWork = new OffWork();
        offWork.setOrgId(orgId);
        offWork.setUserId(userId);
        offWork.setApproveStatus(approveStatus);
        offWork.setDeptId(deptIds);
        offWork.setCreateBy(id);
        offWork.setStartDate(month);
        Page<OffWork> page = offWorkApi.findList(new Page<OffWork>(request, response), offWork);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 业务处理：“我的请假”查询
     *
     * @return
     */    @GET
    @Path("myvacation")
    public PageData myvacation(@QueryParam("orgId") String orgId,
                             @QueryParam("approveStatus") String approveStatus,
                             @QueryParam("month")String month,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String id=loginInfo.getPersionId();
        OffWork offWork = new OffWork();
        offWork.setOrgId(orgId);
        offWork.setUserId(id);
        offWork.setApproveStatus(approveStatus);
        offWork.setStartDate(month);
        Page<OffWork> page = offWorkApi.myvacation(new Page<OffWork>(request, response), offWork);
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
    @Path("off-work-all-list")
    public List<OffWork> finAllList(@QueryParam("orgId") String orgId,
                                      @QueryParam("userId") String userId,
                                      @QueryParam("value") String value) {

        OffWork offWork = new OffWork();
        offWork.setOrgId(orgId);
        offWork.setUserId(userId);
        offWork.setOffWorkId(value);

        return offWorkApi.findAllList(offWork);
    }
    /**
     * 业务处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(OffWorkVo<OffWork> offWorkVo,@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();

        int num = 0;
        int count = 0;
        String id = offWorkVo.getOffWorkId();
        OffWork offWork = new OffWork();

        if (id.equals("999")) {

            offWork.setOffWorkId(IdGen.uuid());
            offWork.setStartDate(offWorkVo.getStartDate());
            offWork.setEndDate(offWorkVo.getEndDate());
            offWork.setHolidayId(offWorkVo.getHolidayId());
            offWork.setOffWorkReason(offWorkVo.getOffWorkReason());
            offWork.setOrgId(offWorkVo.getOrgId());
            offWork.setUserId(offWorkVo.getUserId());
            offWork.setDeptId(offWorkVo.getDeptId());
            offWork.setCreateBy(userId);
            offWork.setCreateDept(loginInfo.getDeptId());
            count = count + Integer.parseInt(offWorkApi.insertPrimary(offWork));


        } else {

            offWork.setOffWorkId(id);
            offWork.setStartDate(offWorkVo.getStartDate());
            offWork.setEndDate(offWorkVo.getEndDate());
            offWork.setHolidayId(offWorkVo.getHolidayId());
            offWork.setOffWorkReason(offWorkVo.getOffWorkReason());
            offWork.setOrgId(offWorkVo.getOrgId());
            offWork.setUserId(offWorkVo.getUserId());
            offWork.setDeptId(offWorkVo.getDeptId());
            offWork.setUpdateBy(userId);
            count = count + Integer.parseInt(offWorkApi.updatePrimary(offWork));
        }

        StringData stringData = new StringData();
        if (count == 1) {
            stringData.setCode(String.valueOf(num));
            stringData.setData("success");
        }
        return stringData;
    }

    @Path("off-work-del")
    @POST
    public StringData dataDel(OffWorkVo<OffWork> offWorkVo) {

        String dataId=offWorkVo.getOffWorkId();
        String num = offWorkApi.delPrimary(dataId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}

