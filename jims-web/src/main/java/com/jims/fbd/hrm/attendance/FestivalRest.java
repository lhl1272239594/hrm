package com.jims.fbd.hrm.attendance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.attendance.api.FestivalApi;
import com.jims.fbd.hrm.attendance.entity.Festival;
import com.jims.fbd.hrm.attendance.vo.FestivalVo;
import com.jims.fbd.hrm.tool.entity.Tool;
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
@Path("festival")
public class FestivalRest {
    @Reference(version = "1.0.0")
    private FestivalApi festivalApi;


    /**
     * 查询所有的节日信息
     *
     * @return
     */
    @GET
    @Path("festival-list")
    public PageData  findFesList(@QueryParam("orgId") String orgId,
                                               @QueryParam("fesDesId") String fesDesId,
                                               @QueryParam("yearId") String yearId,
                                               @Context HttpServletRequest request,
                                               @Context HttpServletResponse response){

        Festival festival = new Festival();
        festival.setOrgId(orgId);
        festival.setFesDesId(fesDesId);
        festival.setYearId(yearId);

        Page<Festival> page= festivalApi.findFesList(new Page<Festival>(request, response), festival);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 查询所有的节日日期信息
     *
     * @return
     */
    @GET
    @Path("festival-date-list")
    public List<Festival> findFesDateList(@QueryParam("orgId") String orgId,@QueryParam("fesId") String fesId) {


        return festivalApi.findFesDateList(orgId,fesId);
    }

    /**
     * 新增节日重复判断
     *
     * @return
     */
    @GET
    @Path("fes-boolean")
    public List<Festival>  findFesBoolean(@QueryParam("orgId") String orgId,
                                          @QueryParam("fesDesId") String fesDesId,
                                          @QueryParam("fesId") String fesId,
                                          @QueryParam("yearId") String yearId) {

        return festivalApi.findFesBoolean(orgId,fesDesId,yearId,fesId);
    }


    /**
     * 节日信息处理
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(FestivalVo festivalVo,@Context HttpServletRequest request) {
        int num = 0;
        int count = 0;
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        //String orgId=tool.getOrgId();


        String fesId =festivalVo.getFesId();
        Festival festival = new Festival();
        List<Festival>  festivals=festivalVo.getFestivals();
        if ( fesId.equals("999")) {
            //保存节日设置头信息
            fesId=IdGen.uuid();
            festival.setFesId(fesId);
            festival.setFesDesId(festivalVo.getFesDesId());
            festival.setYearId(festivalVo.getYearId());
            festival.setOrgId(festivalVo.getOrgId());
            festival.setCreateBy(userId);
            count = count + Integer.parseInt(festivalApi.insertPrimary(festival));


        }
        if ( !fesId.equals("999")) {
            festivalApi.delFestival(fesId);
        }
        //保存节日设置行信息
        for(Festival f:festivals){
            f.setFesId(fesId);
            f.setFesDateId(IdGen.uuid());
            f.setOrgId(festivalVo.getOrgId());
            f.setCreateBy(userId);
            festivalApi.insertForeign(f);
        }
        StringData stringData = new StringData();
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }
    /**
     * 节日信息删除
     *
     * @return
     */
    @Path("festival-del")
    @POST
    public StringData festivalDel(FestivalVo<Festival> festivalVo,@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        Tool tool = (Tool) session.getAttribute("SysUser");
        Festival festival = new Festival();
        festival.setFesId(festivalVo.getFesId());

        String num = festivalApi.delPrimary(festival);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
//删除行
    @Path("festival-date-del")
    @POST
    public StringData festivalDateDel(List<Festival> festival,@Context HttpServletRequest request){

        String num = festivalApi.delForeign(festival);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
//删除行同时删除头
    @Path("festival-date-del1")
    @POST
    public StringData festivalDateDel1(List<Festival> festival,@Context HttpServletRequest request){

        String num = festivalApi.delForeign1(festival);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


}
