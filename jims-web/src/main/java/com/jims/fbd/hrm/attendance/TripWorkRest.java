package com.jims.fbd.hrm.attendance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.attendance.api.TripWorkApi;
import com.jims.fbd.hrm.attendance.entity.TripWork;
import com.jims.fbd.hrm.attendance.vo.TripWorkVo;
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
@Path("tripWork")
public class TripWorkRest {
    @Reference(version = "1.0.0")
    private TripWorkApi tripWorkApi;
    /**
     * 业务处理：查询带分页
     *
     * @return
     */
    @GET
    @Path("trip-work-list")
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
        TripWork tripWork = new TripWork();
        tripWork.setOrgId(orgId);
        tripWork.setUserId(userId);
        tripWork.setApproveStatus(approveStatus);
        tripWork.setDeptId(deptIds);
        tripWork.setCreateBy(id);
        tripWork.setStartDate(month);
        Page<TripWork> page = tripWorkApi.findList(new Page<TripWork>(request, response), tripWork);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 业务处理：“我的公出”查询
     *
     * @return
     */    @GET
    @Path("mytrip")
    public PageData mytrip(@QueryParam("orgId") String orgId,
                             @QueryParam("approveStatus") String approveStatus,
                             @QueryParam("month")String month,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String id=loginInfo.getPersionId();
        TripWork tripWork = new TripWork();
        tripWork.setOrgId(orgId);
        tripWork.setUserId(id);
        tripWork.setApproveStatus(approveStatus);
        tripWork.setStartDate(month);
        Page<TripWork> page = tripWorkApi.mytrip(new Page<TripWork>(request, response), tripWork);
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
    @Path("trip-work-all-list")
    public List<TripWork> finAllList(@QueryParam("orgId") String orgId,
                                     @QueryParam("userId") String userId,
                                     @QueryParam("value") String value) {

        TripWork tripWork = new TripWork();
        tripWork.setOrgId(orgId);
        tripWork.setUserId(userId);
        tripWork.setTripWorkId(value);

        return tripWorkApi.findAllList(tripWork);
    }
    /**
     * 业务处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(TripWorkVo<TripWork> tripWorkVo,@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        int num = 0;
        int count = 0;
        String id = tripWorkVo.getTripWorkId();
        TripWork tripWork = new TripWork();

        if (id.equals("999")) {

            tripWork.setTripWorkId(IdGen.uuid());
            tripWork.setStartDate(tripWorkVo.getStartDate());
            tripWork.setEndDate(tripWorkVo.getEndDate());
            tripWork.setTripWorkDestination(tripWorkVo.getTripWorkDestination());
            tripWork.setTripWorkPlace(tripWorkVo.getTripWorkPlace());
            tripWork.setTripWorkReason(tripWorkVo.getTripWorkReason());
            tripWork.setOrgId(tripWorkVo.getOrgId());
            tripWork.setUserId(tripWorkVo.getUserId());
            tripWork.setCreateDept(loginInfo.getDeptId());
            tripWork.setDeptId(tripWorkVo.getDeptId());
            tripWork.setCreateBy(userId);
            count = count + Integer.parseInt(tripWorkApi.insertPrimary(tripWork));


        } else {

            tripWork.setTripWorkId(id);
            tripWork.setStartDate(tripWorkVo.getStartDate());
            tripWork.setEndDate(tripWorkVo.getEndDate());
            tripWork.setTripWorkDestination(tripWorkVo.getTripWorkDestination());
            tripWork.setTripWorkPlace(tripWorkVo.getTripWorkPlace());
            tripWork.setTripWorkReason(tripWorkVo.getTripWorkReason());
            tripWork.setOrgId(tripWorkVo.getOrgId());
            tripWork.setUserId(tripWorkVo.getUserId());
            tripWork.setDeptId(tripWorkVo.getDeptId());
            count = count + Integer.parseInt(tripWorkApi.updatePrimary(tripWork));
        }

        StringData stringData = new StringData();
        if (count == 1) {
            stringData.setCode(String.valueOf(num));
            stringData.setData("success");
        }
        return stringData;
    }

    @Path("trip-work-del")
    @POST
    public StringData dataDel(TripWorkVo<TripWork> tripWorkVo) {

        String dataId=tripWorkVo.getTripWorkId();
        String num = tripWorkApi.delPrimary(dataId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}

