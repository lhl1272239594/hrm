package com.jims.fbd.hrm.attendance;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.attendance.api.TempAttendanceApi;
import com.jims.fbd.hrm.attendance.entity.TempAttendancePerson;
import com.jims.fbd.hrm.attendance.vo.TempAttendancePersonVo;
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
@Path("tempAttendance")
public class TempAttendanceRest {
    @Reference(version = "1.0.0")
    private TempAttendanceApi tempAttendanceApi;

    /**
     * 业务处理：查询临时考勤
     *
     * @return
     */

    @GET
    @Path("temp-attendance-list")
    public PageData findAttList(@QueryParam("orgId") String orgId,
                                @QueryParam("tempAttendanceName") String tempAttendanceName,
                                @Context HttpServletRequest request,
                                @Context HttpServletResponse response){


        TempAttendancePerson tempAttendancePerson = new TempAttendancePerson();
        tempAttendancePerson.setCreateOrg(orgId);
        tempAttendancePerson.setTempAttName(tempAttendanceName);

        Page<TempAttendancePerson> page= tempAttendanceApi.findList(
                new Page<TempAttendancePerson>(request, response), tempAttendancePerson);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 业务处理:查询临时考勤人员
     *
     * @return
     */
    @GET
    @Path("temp-attendance-person-list")
    public List<TempAttendancePerson> findPerson(@QueryParam("orgId") String orgId,
                                                      @QueryParam("tempAttId") String tempAttId) {


        TempAttendancePerson tempAttendancePerson = new TempAttendancePerson();
        tempAttendancePerson.setCreateOrg(orgId);
        tempAttendancePerson.setTempAttId(tempAttId);

        List<TempAttendancePerson> list = tempAttendanceApi.findPerson(tempAttendancePerson);
        return list;

    }
    /**
     * 业务处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(TempAttendancePersonVo<TempAttendancePerson> tempAttendancePersonVo,
                            @Context HttpServletRequest request, @Context HttpServletResponse response){
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        int num = 0;
        int count = 0;
        StringData stringData = new StringData();
        String id = tempAttendancePersonVo.getTempAttId();

        String userId=loginInfo.getPersionId();
        String deptId = loginInfo.getDeptId();
        String orgId = loginInfo.getOrgId();
        if (id.equals("999")) {
            String tampAttId=IdGen.uuid();
            //插入临时考勤信息
            TempAttendancePerson tempAttendance= new TempAttendancePerson();
            tempAttendance.setTempAttId(tampAttId);
            tempAttendance.setTempAttDate(tempAttendancePersonVo.getTempAttDate());
            tempAttendance.setTempAttName(tempAttendancePersonVo.getTempAttName());
            tempAttendance.setTempAttPlace(tempAttendancePersonVo.getTempAttPlace());
            tempAttendance.setAdjustStartTime(tempAttendancePersonVo.getAdjustStartTime());
            tempAttendance.setAdjustEndTime(tempAttendancePersonVo.getAdjustEndTime());
            tempAttendance.setStartTime(tempAttendancePersonVo.getStartTime());
            tempAttendance.setEndTime(tempAttendancePersonVo.getEndTime());
            tempAttendance.setCreateOrg(orgId);
            tempAttendance.setCreateBy(userId);
            tempAttendance.setCreateDept(userId);
            tempAttendanceApi.insertPrimary(tempAttendance);

            //插入临时考勤人员
            List<TempAttendancePerson> tap=tempAttendancePersonVo.getTempAttendancePerson();
            for(TempAttendancePerson tempAttendancePerson:tap){
                tempAttendancePerson.setTempAttId(tampAttId);
                tempAttendancePerson.setTempAttPersonId(IdGen.uuid());
                tempAttendancePerson.setCreateOrg(orgId);
                tempAttendancePerson.setCreateBy(userId);
                tempAttendancePerson.setCreateDept(userId);
                tempAttendanceApi.insertForeign(tempAttendancePerson);
            }
            stringData.setData("success");

        } else {

            //更新临时考勤信息
            TempAttendancePerson tempAttendance = new TempAttendancePerson();
            tempAttendance.setTempAttDate(tempAttendancePersonVo.getTempAttDate());
            tempAttendance.setTempAttId(tempAttendancePersonVo.getTempAttId());
            tempAttendance.setTempAttName(tempAttendancePersonVo.getTempAttName());
            tempAttendance.setTempAttPlace(tempAttendancePersonVo.getTempAttPlace());
            tempAttendance.setAdjustStartTime(tempAttendancePersonVo.getAdjustStartTime());
            tempAttendance.setAdjustEndTime(tempAttendancePersonVo.getAdjustEndTime());
            tempAttendance.setStartTime(tempAttendancePersonVo.getStartTime());
            tempAttendance.setEndTime(tempAttendancePersonVo.getEndTime());
            tempAttendance.setCreateOrg(orgId);
            tempAttendance.setCreateBy(userId);
            tempAttendance.setCreateDept(userId);
            tempAttendanceApi.updatePrimary(tempAttendance);

            tempAttendanceApi.delForeign(tempAttendance);
            //更新新的考勤参加人信息
            List<TempAttendancePerson> tap=tempAttendancePersonVo.getTempAttendancePerson();
            for(TempAttendancePerson tempAttendancePerson:tap){
                tempAttendancePerson.setTempAttId(tempAttendancePersonVo.getTempAttId());
                tempAttendancePerson.setTempAttPersonId(IdGen.uuid());
                tempAttendancePerson.setCreateOrg(orgId);
                tempAttendancePerson.setCreateBy(userId);
                tempAttendancePerson.setCreateDept(userId);
                tempAttendanceApi.insertForeign(tempAttendancePerson);
            }
            stringData.setData("success");
        }
        if (count == 1) {
            stringData.setCode(String.valueOf(num));
            stringData.setData("success");
        }
        return stringData;
    }

    /**
     * 业务处理：删除信息
     *
     * @return
     */

    @Path("temp-attendance-del")
    @POST
    public StringData tempAttDel(TempAttendancePersonVo<TempAttendancePerson> tempAttendancePersonVo) {

        TempAttendancePerson tempAttendancePerson= new TempAttendancePerson();
        tempAttendancePerson.setTempAttId(tempAttendancePersonVo.getTempAttId());
        String num = tempAttendanceApi.delPrimary(tempAttendancePerson);
        tempAttendanceApi.delForeign(tempAttendancePerson);

        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 业务处理：临时考勤重复判断
     *
     * @return
     */
    @GET
    @Path("temp-attendance-boolean")
    public List<TempAttendancePerson> findBoolean(@QueryParam("orgId") String orgId,
                                                  @QueryParam("tempAttId") String tempAttId,
                                                  @QueryParam("editStartDate") String editStartDate,
                                                  @QueryParam("editEndDate") String editEndDate,
                                                  @QueryParam("tempAttDate") String tempAttDate,
                                                  @QueryParam("tempAttPlace") String tempAttPlace) {

        TempAttendancePerson tempAttendancePerson = new TempAttendancePerson();

        tempAttendancePerson.setTempAttId(tempAttId);
        tempAttendancePerson.setStartTime(editStartDate);
        tempAttendancePerson.setEndTime(editEndDate);
        tempAttendancePerson.setCreateOrg(orgId);
        tempAttendancePerson.setTempAttPlace(tempAttPlace);
        tempAttendancePerson.setTempAttDate(tempAttDate);

        List<TempAttendancePerson> list= tempAttendanceApi.findBoolean(tempAttendancePerson);
        return list;
    }

}
