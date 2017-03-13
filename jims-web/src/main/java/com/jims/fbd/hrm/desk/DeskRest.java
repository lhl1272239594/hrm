package com.jims.fbd.hrm.desk;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.fbd.hrm.desk.api.DeskApi;
import com.jims.fbd.hrm.desk.entity.MyData;
import com.jims.fbd.hrm.desk.entity.Schedule;
import com.jims.fbd.hrm.desk.entity.ShortCut;
import com.jims.fbd.hrm.notice.entity.Notice;
import com.jims.sys.api.ButtonDictApi;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.OrgStaff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.*;


/**
 * Created by wangzhiming on 2016/8/18 0024.
 */
@Component
@Produces("application/json")
@Path("desk")
public class DeskRest {

    @Reference(version = "1.0.0")
    private DeskApi deskApi;
    @Reference(version = "1.0.0")
    private ButtonDictApi buttonDictApi;
    /**
     * 查询代办
     * @return
     */
    @Path("getSchedule")
    @POST
    public Schedule getSchedule(Schedule schedule,@Context HttpServletRequest request, @Context HttpServletResponse response){
        HttpSession session = request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        //查询出所有的科室信息
        String orgId=schedule.getOrgId();
        String roleid=schedule.getRoleId();
        String serviceId=schedule.getServiceId();
        String contract=schedule.getContract();
        String examGrade=schedule.getExamGrade();
        //获取合同页数据权限
        List<DeptDict> contractList = buttonDictApi.findAllList(orgId,roleid,serviceId,contract);
        String contractDep=null;
        for(DeptDict d: contractList){
            if(d.getRemarks().equals("1")){
                contractDep+="'"+d.getId()+"',";
            }
        }
        if(contractDep!=null){
            contractDep=contractDep.substring(0,contractDep.length()-1);
        }
        schedule.setContract(contractDep);
        //获取评分页数据权限
        List<DeptDict> gradeList = buttonDictApi.findAllList(orgId,roleid,serviceId,examGrade);
        String gradeDep=null;
        for(DeptDict d: gradeList){
            if(d.getRemarks().equals("1")){
                gradeDep+="'"+d.getId()+"',";
            }
        }
        if(gradeDep!=null){
            gradeDep=gradeDep.substring(0,gradeDep.length()-1);
        }
        schedule.setExamGrade(gradeDep);
        schedule.setPersionId(orgStaff.getPersionId());
        return deskApi.getSchedule(schedule);
    }
    /**
     * 查询公告
     * @return
     */
    @Path("getNotice")
    @GET
    public List<Notice> getNotice(@Context HttpServletRequest request, @Context HttpServletResponse response){
        HttpSession session = request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        return deskApi.getNotice(orgStaff);
    }
    /**
     * 查询公告内容
     * @return
     */
    @Path("getNoticeById")
    @GET
    public Notice getNotice(@QueryParam("id")String id,@Context HttpServletRequest request, @Context HttpServletResponse response){
        return deskApi.getNoticeById(id);
    }
    /**
     * 快捷方式
     * @return
     */
    @Path("getShortCut")
    @GET
    public List<ShortCut> getShortCut(@Context HttpServletRequest request, @Context HttpServletResponse response){
        HttpSession session = request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        return deskApi.getShortCut(orgStaff);
    }
    /**
     * 查询信息统计
     * @return
     */
    @Path("getMyData")
    @GET
    public MyData getMyData(@Context HttpServletRequest request, @Context HttpServletResponse response){
        HttpSession session = request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        MyData myData=deskApi.getMyData(orgStaff);
        return myData;
    }
}
