package com.jims.fbd.hrm.attendance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.attendance.api.HolidayApi;
import com.jims.fbd.hrm.attendance.entity.Holiday;
import com.jims.fbd.hrm.attendance.vo.HolidayVo;
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
@Path("holiday")
public class HolidayRest {
    @Reference(version = "1.0.0")
    private HolidayApi holidayApi;


    /**
     * 查询所有的假日信息--无分页
     *
     * @return
     */
    @GET
    @Path("holiday-all-list")
    public List<Holiday> findAllList(@QueryParam("orgId") String orgId,
                                     @QueryParam("value") String value,
                                     @QueryParam("dataId") String dataId) {

        return holidayApi.findAllList(orgId,value,dataId);
    }
    /**
     * 查询所有的假日信息--分页
     *
     * @return
     */
    @GET
    @Path("holiday-list")
    public PageData findList(@QueryParam("orgId") String orgId,
                                @QueryParam("holDes") String holDes,
                                @QueryParam("holPayTypeId") String holPayTypeId,
                                @Context HttpServletRequest request,
                                @Context HttpServletResponse response){

        Holiday holiday = new Holiday();
        holiday.setOrgId(orgId);
        holiday.setHolDes(holDes);
        holiday.setHolPayTypeId(holPayTypeId);

        Page<Holiday> page= holidayApi.findList(new Page<Holiday>(request, response), holiday);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }

    /**
     * 新增假日重复判断
     *
     * @return
     */
    @GET
    @Path("holiday-boolean")
    public List<Holiday>  findHolBoolean(@QueryParam("orgId") String orgId,@QueryParam("holDes") String holDes) {

        return holidayApi.findHolBoolean(orgId,holDes);
    }
    /**
     * 假日信息业务处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(HolidayVo<Holiday> holidayVo,@Context HttpServletRequest request) {
        int num = 0;
        int count = 0;
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        String holId =holidayVo.getHolId();
        String orgId=holidayVo.getOrgId();
        Holiday holiday = new Holiday();
        String name=holidayVo.getHolDes();
        StringData stringData = new StringData();
        String type=holidayVo.getHolPayTypeId();
        int hasName =  holidayApi.checkName(holId,orgId,name);
        if(hasName>0){
            stringData.setData("hasName");
            return stringData;
        }
        if ( holId.equals("999")) {
            //保存节日设置头信息
            holiday.setHolId(IdGen.uuid());
            holiday.setHolDes(name);
            holiday.setHolPayTypeId(holidayVo.getHolPayTypeId());
            holiday.setCreateBy(userId);
            holiday.setOrgId(orgId);
            count = count + Integer.parseInt(holidayApi.insertPrimary(holiday));
        } else {

            holiday.setHolId(holId);
            holiday.setHolDes(name);
            holiday.setHolPayTypeId(holidayVo.getHolPayTypeId());
            holiday.setUpdateBy(userId);
            holiday.setOrgId(orgId);
            count = count + Integer.parseInt(holidayApi.updatePrimary(holiday));
         }

        if (count == 1)
        {
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        }
        return stringData;
    }
    /**
     * 假日信息业务处理：删除
     *
     * @return
     */
    @Path("holiday-del")
    @POST
    public StringData holidayDel(HolidayVo<Holiday> holidayVo) {
        String holId =holidayVo.getHolId();
        String flag =holidayVo.getFlag();
        String num = holidayApi.delPrimary(holId,flag);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 查看假日是否占用
     *
     * @param id
     * @return
     */

    @Path("checkUsed")
    @GET
    public StringData checkUsed(@QueryParam("id") String id, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        String num = holidayApi.checkUsed(id);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }



}
