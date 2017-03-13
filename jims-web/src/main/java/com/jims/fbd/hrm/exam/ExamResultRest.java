package com.jims.fbd.hrm.exam;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.exam.api.ExamApi;
import com.jims.fbd.hrm.exam.api.ExamResultApi;
import com.jims.fbd.hrm.exam.entity.*;
import com.jims.fbd.hrm.exam.vo.*;
import com.jims.sys.entity.SysUser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangzhiming on 2016/8/18 0024.
 */
@Component
@Produces("application/json")
@Path("examResult")
public class ExamResultRest {

    @Reference(version = "1.0.0")
    private ExamResultApi examResultApi;

    /**
     * 查询考试结果
     *
     * @param request
     * @param response
     * @return
     */

    @Path("resultList")
    @GET
    public PageData resultList(@QueryParam("orgId")String orgId,@QueryParam("deptIds")String deptIds,@QueryParam("type")String type,@QueryParam("planName")String planName,@QueryParam("userName")String userName,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        ExamResultVo examResultVo = new ExamResultVo();
        if(!orgId.equals("")&&orgId!=null){
            examResultVo.setOrgId(orgId);
        }
        if(!type.equals("")&&type!=null){
            examResultVo.setTypeId(type);
        }
        if(!planName.equals("")&&planName!=null){
            examResultVo.setPlanName(planName);
        }
        if(!userName.equals("")&&userName!=null){
            examResultVo.setUserName(userName);
        }
        if(!deptIds.equals("")&&deptIds!=null){
            examResultVo.setCreateDept(deptIds);
        }
        Page<ExamResultVo> page= examResultApi.resultList(new Page<ExamResultVo>(request, response), examResultVo);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询考试结果
     *我的考试使用
     * @param request
     * @param response
     * @return
     */

    @Path("getmyexam")
    @GET
    public PageData getmyexam(@QueryParam("pid")String pid,@QueryParam("orgId")String orgId,@QueryParam("type")String type,@QueryParam("planName")String planName,@QueryParam("month")String month,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        ExamResultVo examResultVo = new ExamResultVo();
        if(!orgId.equals("")&&orgId!=null){
            examResultVo.setOrgId(orgId);
        }
        if(!type.equals("")&&type!=null){
            examResultVo.setTypeId(type);
        }
        if(!planName.equals("")&&planName!=null){
            examResultVo.setPlanName(planName);
        }
        if(!month.equals("")&&month!=null){
            examResultVo.setTime(month);
        }
        examResultVo.setDepId(pid);
        Page<ExamResultVo> page= examResultApi.getmyexam(new Page<ExamResultVo>(request, response), examResultVo);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
}
