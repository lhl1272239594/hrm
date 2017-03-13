package com.jims.fbd.hrm.attendance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.attendance.api.FrequencyApi;
import com.jims.fbd.hrm.attendance.entity.Frequency;
import com.jims.fbd.hrm.attendance.vo.FrequencyVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by yangchen on 2016/8/23 0.
 */
@Component
@Produces("application/json")
@Path("frequency")
public class FrequencyRest {
    @Reference(version = "1.0.0")
    private FrequencyApi frequencyApi;

    /**
     * 班次项目查询--不带分页
     *
     * @return
     */
    @GET
    @Path("frequency-all-list")
    public List<Frequency> findFreAllList(@QueryParam("orgId") String orgId) {

        return frequencyApi.findFreAllList(orgId);

    }
    /**
     * 班次项目查询--分页
     *
     * @return
     */
    @GET
    @Path("frequency-list")
    public PageData findFreList(@QueryParam("orgId") String orgId,
                                @QueryParam("freItemId") String freItemId,
                                @QueryParam("freItemDes") String freItemDes,
                                @QueryParam("freTypeId") String freTypeId,
                                @Context HttpServletRequest request,
                                @Context HttpServletResponse response) {

        Frequency frequency = new Frequency();
        frequency.setOrgId(orgId);
        frequency.setFreItemId(freItemId);
        frequency.setFreItemDes(freItemDes);
        frequency.setFreTypeId(freTypeId);

        Page<Frequency> page = frequencyApi.findFreList(new Page<Frequency>(request, response), frequency);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }

    /**
     * 新增班次重复判断
     *
     * @return
     */
    @GET
    @Path("frequency-boolean")
    public List<Frequency> findFreBoolean(@QueryParam("orgId") String orgId,
                                          @QueryParam("freItemId") String freItemId,
                                          @QueryParam("freItemDes") String freItemDes) {

        List<Frequency> list = frequencyApi.findFreBoolean(orgId, freItemId, freItemDes);
        return list;
    }
    /**
     * 班次项目处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(FrequencyVo<Frequency> frequencyVo, @Context HttpServletRequest request) {
        int num = 0;
        int count = 0;
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String freItemId = frequencyVo.getFreItemId();
        Frequency frequency = new Frequency();
        if (freItemId.equals("999")) {
            //保存班次头信息
            freItemId = IdGen.uuid();
            frequency.setFreItemId(freItemId);
            frequency.setFreItemDes(frequencyVo.getFreItemDes());
            frequency.setFreTypeId(frequencyVo.getFreTypeId());
            frequency.setStartTime(frequencyVo.getStartTime());
            frequency.setEndTime(frequencyVo.getEndTime());
            frequency.setOrgId(frequencyVo.getOrgId());
            frequency.setCreateBy(userId);
            count = count + Integer.parseInt(frequencyApi.insertPrimary(frequency));
        } else {
            frequency.setFreItemId(freItemId);
            frequency.setFreItemDes(frequencyVo.getFreItemDes());
            frequency.setFreTypeId(frequencyVo.getFreTypeId());
            frequency.setFreTimeId(frequencyVo.getFreTimeId());
            frequency.setStartTime(frequencyVo.getStartTime());
            frequency.setEndTime(frequencyVo.getEndTime());
            frequency.setOrgId(frequencyVo.getOrgId());
            frequency.setUpdateBy(userId);
            count = count + Integer.parseInt(frequencyApi.updatePrimary(frequency));
        }
        StringData stringData = new StringData();
        if (count == 1) {
            num = 1;
        }
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }

    @POST
    @Path("frequency-del")
    public StringData frequencyDel(FrequencyVo<Frequency> frequencyVo) {
        String freItemId = frequencyVo.getFreItemId();
        String flag = frequencyVo.getFlag();
        String num = "";
        num = frequencyApi.delPrimary(freItemId, flag);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }



    /**
     * 删除占用判断
     *
     * @return
     */
    @POST
    @Path("if-occupy")
    public StringData findOccupy(@QueryParam("freItemId") String freItemId, @QueryParam("orgId") String orgId) {
        Frequency frequency = new Frequency();
        frequency.setOrgId(orgId);
        frequency.setFreItemId(freItemId);
        String num = frequencyApi.findOccupy(frequency);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (num != null && num == "yes") {
            stringData.setData("success");
        }
        return stringData;
    }




}
