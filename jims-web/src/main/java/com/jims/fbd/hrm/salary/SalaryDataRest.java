package com.jims.fbd.hrm.salary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.salary.api.SalaryDataApi;
import com.jims.fbd.hrm.salary.entity.SalaryData;
import com.jims.fbd.hrm.salary.vo.SalaryDataPersonVo;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 薪资档案rest层
 *
 * @author 
 * @version 2016-08-22
 */
@Component
@Produces("application/json")
@Path("salary-data")
public class SalaryDataRest {
    @Reference(version = "1.0.0")
    private SalaryDataApi salaryDataApi;

    /**
     * 异步加载表格
     *
     * @param request
     * @param response
     * @return
     */
    @Path("person-list")
    @GET
    public PageData list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        String orgId = request.getParameter("orgId");
        String deptId = request.getParameter("deptId");
        SalaryData salaryData = new SalaryData();
        salaryData.setOrgId(orgId);
        salaryData.setDeptId(deptId);
        Page<SalaryData> page = salaryDataApi.findPageByVo(new Page<SalaryData>(request, response), salaryData);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询档案信息
     *
     * @return
     */
    @Path("data-list")
    @GET
    public PageData dataList(@QueryParam("orgId")String orgId, @QueryParam("deptId")String deptId,
                             @QueryParam("personName")String personName,@QueryParam("typeId")String typeId, @QueryParam("sex")String sex,
                             @QueryParam("card")String card,@QueryParam("state")String state,
                             @QueryParam("partId")String partId, @QueryParam("deptIds")String deptIds,
                             @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryData salaryData = new SalaryData();
        PageData pageData=new PageData();
        if(!orgId.equals("")&&orgId!=null) {
            salaryData.setOrgId(orgId);
        }
        if(!typeId.equals("")&&typeId!=null){
            salaryData.setTypeId(typeId);
        }
        if(!deptId.equals("")&&deptId!=null){
            salaryData.setDeptId(deptId);
        }
        if(!personName.equals("")&&personName!=null){
            salaryData.setPersonName(personName);
        }
        if(!sex.equals("")&&sex!=null){
            salaryData.setSex(sex);
        }
        if(!card.equals("")&&card!=null){
            salaryData.setCard(card);
        }
        if(!state.equals("")&&state!=null){
            salaryData.setState(state);
        }
        if(!deptIds.equals("")&&deptIds!=null){
            salaryData.setDeptIds(deptIds);
        }
        Page<SalaryData> page= salaryDataApi.dataList(new Page<SalaryData>(request, response),salaryData);
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询职业信息
     *
     * @return
     */
    @Path("career-list")
    @GET
    public PageData careerList(@QueryParam("orgId")String orgId, @QueryParam("personId")String personId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryData salaryData = new SalaryData();
        PageData pageData=new PageData();
        if(!orgId.equals("")&&orgId!=null) {
            salaryData.setOrgId(orgId);
        }
        if(!personId.equals("")&&personId!=null){
            salaryData.setPersonId(personId);
        }
        Page<SalaryData> page= salaryDataApi.careerList(new Page<SalaryData>(request, response),salaryData);
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询职业变动报表
     *
     * @return
     */
    @Path("career-search")
    @GET
    public PageData careerSearch(@QueryParam("orgId")String orgId, @QueryParam("deptId")String deptId, @QueryParam("personId")String personId, @QueryParam("year")String year,@QueryParam("month")String month, @QueryParam("deptIds")String deptIds, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryData salaryData = new SalaryData();
        PageData pageData=new PageData();
        if(!orgId.equals("")&&orgId!=null) {
            salaryData.setOrgId(orgId);
        }
        if(!year.equals("")&&year!=null){
            salaryData.setYear(year);
        }
        if(!month.equals("")&&month!=null){
            salaryData.setMonth(month);
        }
        if(!deptId.equals("")&&deptId!=null){
            salaryData.setDeptId(deptId);
        }
        if(!personId.equals("")&&personId!=null){
            salaryData.setPersonId(personId);
        }
        if(!deptIds.equals("")&&deptIds!=null){
            salaryData.setDeptIds(deptIds);
        }
        Page<SalaryData> page= salaryDataApi.careerSearch(new Page<SalaryData>(request, response),salaryData);
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询职业信息历史
     *
     * @return
     */
    @Path("career-list-all")
    @GET
    public PageData careerListAll(@QueryParam("orgId")String orgId, @QueryParam("personId")String personId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryData salaryData = new SalaryData();
        PageData pageData=new PageData();
        if(!orgId.equals("")&&orgId!=null) {
            salaryData.setOrgId(orgId);
        }
        if(!personId.equals("")&&personId!=null){
            salaryData.setPersonId(personId);
        }
        Page<SalaryData> page= salaryDataApi.careerListAll(new Page<SalaryData>(request, response),salaryData);
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 新增薪资档案
     *
     * @param loop
     * @return
     */

    @Path("merge")
    @POST
    public StringData merge(List<SalaryDataPersonVo> loop) {
        String num = salaryDataApi.merge(loop);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 新增修改职业信息
     *
     * @param salaryData
     * @return
     */

    @Path("career-merge")
    @POST
    public StringData careerMerge(SalaryData salaryData, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        String num = salaryDataApi.careerMerge(salaryData,userName);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 起薪停薪
     * @param datas
     * @return
     * @author 
     */
    @Path("enableFlag")
    @POST
    public StringData enableFlag(List<SalaryData> datas) {

        String num = salaryDataApi.enableFlag(datas);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 变更类别
     * @param datas
     * @return
     * @author 
     */
    @Path("change")
    @POST
    public StringData change(List<SalaryData> datas, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String personId=p.getId();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String createDept = loginInfo.getDeptId();
        String num = salaryDataApi.change(datas,personId,createDept);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 删除职业信息
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("del-career")
    @POST
    public StringData del_career(List<SalaryData> salaryDatas) {
        String num = salaryDataApi.del_career(salaryDatas);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 查询科室信息(数据权限)
     *
     * @return
     */
    @Path("list")
    @GET
    public List<DeptDict> list(@QueryParam("orgId") String orgId, @QueryParam("deptIds")String deptIds) {

        //查询出所有的科室信息
        List<DeptDict> list = salaryDataApi.findAllList(orgId,deptIds);
        return list;
    }
}
