package com.jims.fbd.hrm.attendance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.attendance.api.FrequencyRecordApi;
import com.jims.fbd.hrm.attendance.entity.FrequencyRecord;
import com.jims.fbd.hrm.attendance.vo.FrequencyRecordVo;
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
@Path("frequencyRecord")
public class FrequencyRecordRest {
    @Reference(version = "1.0.0")
    private FrequencyRecordApi frequencyRecordApi;

    /**
     * 排班记录信息汇总查询--按照业务创建时间汇总
     *
     * @return
     */

    @GET
    @Path("frequency-head-list")
    public PageData findHeadList(@QueryParam("orgId") String orgId,
                                         @QueryParam("userId") String userId,
                                         @QueryParam("freTimeMonth") String freTimeMonth,
                                         @Context HttpServletRequest request,
                                         @Context HttpServletResponse response){

        FrequencyRecord frequencyRecord = new FrequencyRecord();
        frequencyRecord.setOrgId(orgId);
        frequencyRecord.setUserId(userId);
        frequencyRecord.setFreTimeMonth(freTimeMonth);

        Page<FrequencyRecord> page= frequencyRecordApi.findHeadList(new Page<FrequencyRecord>(request, response), frequencyRecord);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 排班记录信息汇总查询--按照人员汇总查询
     *
     * @return
     */
    @GET
    @Path("frequency-record-all-list")
    public PageData findFreRecordAllList(@QueryParam("orgId") String orgId,
                                         @QueryParam("userId") String userId,
                                         @QueryParam("freRecordHeadId") String freRecordHeadId,
                                         @Context HttpServletRequest request,
                                         @Context HttpServletResponse response){

        FrequencyRecord frequencyRecord = new FrequencyRecord();
        frequencyRecord.setOrgId(orgId);
        frequencyRecord.setUserId(userId);
        frequencyRecord.setFreRecordHeadId(freRecordHeadId);

        Page<FrequencyRecord> page= frequencyRecordApi.findAllList(new Page<FrequencyRecord>(request, response), frequencyRecord);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 排班记录信息明细查询-无分页
     *
     * @return
     */
    @GET
    @Path("frequency-record-detail-all-list")
    public List<FrequencyRecord> findAllDetailList(@QueryParam("orgId") String orgId,@QueryParam("userId") String userId,@QueryParam("freRecordHeadId") String freRecordHeadId,@QueryParam("time") String time) {

        return frequencyRecordApi.findAllDetailList(orgId,userId,freRecordHeadId,time);

    }
    @GET
    @Path("frequency-record-detail-list")
    public PageData findDetailList(@QueryParam("orgId") String orgId,
                                    @QueryParam("userId") String userId,
                                    @QueryParam("freTimeMonth") String freTimeMonth,
                                    @QueryParam("freTimeDay") String freTimeDay,
                                    @Context HttpServletRequest request,
                                    @Context HttpServletResponse response){

        FrequencyRecord frequencyRecord = new FrequencyRecord();
        frequencyRecord.setOrgId(orgId);
        frequencyRecord.setUserId(userId);
        frequencyRecord.setFreTimeMonth(freTimeMonth);
        frequencyRecord.setFreTimeDay(freTimeDay);

        Page<FrequencyRecord> page= frequencyRecordApi.findDetailList(new Page<FrequencyRecord>(request, response), frequencyRecord);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 排班记录业务处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(FrequencyRecordVo<FrequencyRecord> frequencyRecordVo,@Context HttpServletRequest request) {
        int num = 0;
        int count = 0;
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        String deptId = loginInfo.getDeptId();
        String id =frequencyRecordVo.getId();
        FrequencyRecord frequencyRecord = new FrequencyRecord();

        if ( id.equals("999")) {
            frequencyRecord.setFreRecordHeadId(IdGen.uuid());
            frequencyRecord.setFreRecordId(IdGen.uuid());
            frequencyRecord.setStartDate(frequencyRecordVo.getStartDate());
            frequencyRecord.setEndDate(frequencyRecordVo.getEndDate());
            frequencyRecord.setFreFirstItemOrder(frequencyRecordVo.getFreFirstItemOrder());
            frequencyRecord.setFreRuleId(frequencyRecordVo.getFreRuleId());
            frequencyRecord.setOrgId(frequencyRecordVo.getOrgId());
            frequencyRecord.setUserId(frequencyRecordVo.getUserId());
            frequencyRecord.setDeptId(frequencyRecordVo.getDeptId());
            frequencyRecord.setCreateBy(userId);
            frequencyRecord.setCreateDept(deptId);
            frequencyRecordApi.callProcedures(frequencyRecord);


        } else {
            for (int i = 0; i < frequencyRecordVo.getUpdated().size(); i++) {
                frequencyRecord = (FrequencyRecord) frequencyRecordVo.getUpdated().get(i);
                frequencyRecord.setOrgId(frequencyRecordVo.getOrgId());
                frequencyRecord.setUpdateBy(userId);
                frequencyRecord.setFreRuleId(frequencyRecordVo.getFreRuleId());
                count = count + Integer.parseInt(frequencyRecordApi.updatePrimary(frequencyRecord));
            }

        }


        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    /**
     * 排班记录业务处理：删除
     *
     * @return
     */
    @Path("frequency-record-del")
    @POST
    public StringData Del(FrequencyRecordVo<FrequencyRecord> frequencyRecordVo) {


        FrequencyRecord frequencyRecord = new FrequencyRecord();
        frequencyRecord.setOrgId(frequencyRecordVo.getOrgId());
        frequencyRecord.setStartDate(frequencyRecordVo.getStartDate());
        frequencyRecord.setEndDate(frequencyRecordVo.getEndDate());
        frequencyRecord.setUserId(frequencyRecordVo.getUserId());

        String num = frequencyRecordApi.delPrimary(frequencyRecord);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 删除头
     *
     * @param freRecordHeadId
     * @return
     * @author ZYG
     */
    @Path("frequency-head-del")
    @POST
    public StringData del_head(String freRecordHeadId) {
        String num = frequencyRecordApi.del_head(freRecordHeadId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

}
