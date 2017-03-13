package com.jims.fbd.hrm.train;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.train.api.TrainNoticeApi;
import com.jims.fbd.hrm.train.entity.TrainNoticePerson;
import com.jims.fbd.hrm.train.vo.TrainNoticeVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by yangchen on 2016/9/23 0.
 */
@Component
@Produces("application/json")
@Path("trainNotice")
public class TrainNoticeRest {
    @Reference(version = "1.0.0")
    private TrainNoticeApi trainNoticeApi;

    //查询培训通知列表
    @GET
    @Path("train-notice-list")
    public PageData findList(@QueryParam("orgId") String orgId,
                             @QueryParam("trainPlanTittle") String trainPlanTittle,
                             @QueryParam("type") String type,
                             @QueryParam("month") String month,
                             @QueryParam("state") String state,
                             @QueryParam("deptIds") String deptIds,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {

        TrainNoticePerson trainNoticePerson = new TrainNoticePerson();
        trainNoticePerson.setOrgId(orgId);
        trainNoticePerson.setTrainPlanTittle(trainPlanTittle);
        if(!type.equals("")&&type!=null){
            trainNoticePerson.setTrainPlanType(type);
        }
        if(!state.equals("")&&state!=null){
            trainNoticePerson.setState(state);
        }
        if(!month.equals("")&&month!=null){
            trainNoticePerson.setStartDate(month);
        }
        if(!deptIds.equals("")&&deptIds!=null){
            trainNoticePerson.setDeptId(deptIds);
        }
        Page<TrainNoticePerson> page = trainNoticeApi.findList(
                new Page<TrainNoticePerson>(request, response), trainNoticePerson);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    //我的培训通知列表
    @GET
    @Path("mytrain")
    public PageData mytrain(@QueryParam("orgId") String orgId,
                             @QueryParam("trainPlanTittle") String trainPlanTittle,
                             @QueryParam("type") String type,
                             @QueryParam("month") String month,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

        String userId=loginInfo.getPersionId();
        String deptId = loginInfo.getDeptId();
        TrainNoticePerson trainNoticePerson = new TrainNoticePerson();
        trainNoticePerson.setOrgId(orgId);
        trainNoticePerson.setTrainPlanTittle(trainPlanTittle);
        if(!type.equals("")&&type!=null){
            trainNoticePerson.setTrainPlanType(type);
        }
        if(!month.equals("")&&month!=null){
            trainNoticePerson.setStartDate(month);
        }
        if(!userId.equals("")&&userId!=null){
            trainNoticePerson.setUserId(userId);
        }
        Page<TrainNoticePerson> page = trainNoticeApi.mytrain(
                new Page<TrainNoticePerson>(request, response), trainNoticePerson);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    //查询参与该培训通知的人
    @GET
    @Path("train-notice-to-person")
    public List<TrainNoticePerson> findNoticeToPerson(@QueryParam("orgId") String orgId,
                                                      @QueryParam("trainNoticeId") String trainNoticeId) {


        TrainNoticePerson trainNoticePerson = new TrainNoticePerson();
        trainNoticePerson.setOrgId(orgId);
        trainNoticePerson.setTrainNoticeId(trainNoticeId);

        List<TrainNoticePerson> list = trainNoticeApi.findNoticeToPerson(trainNoticePerson);
        return list;

    }
    //新增、保存方法。
    @POST
    @Path("merge")
    public StringData merge(TrainNoticeVo<TrainNoticePerson> trainNoticeVo,
                            @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

        String userId=loginInfo.getPersionId();
        String orgId = loginInfo.getOrgId();
        String deptId=loginInfo.getDeptId();
        int num = 0;
        int count = 0;
        StringData stringData = new StringData();
        String name=trainNoticeVo.getName();
        String id = trainNoticeVo.getTrainNoticeId();
        int hasName =  trainNoticeApi.checkName(id,orgId,name);
        if(hasName>0){
            stringData.setData("hasName");
            return stringData;
        }
        if (id.equals("999")) {

            String noticeId=IdGen.uuid();

            //插入通知记录
            TrainNoticePerson trainNotice = new TrainNoticePerson();
            trainNotice.setTrainNoticeId(noticeId);
            trainNotice.setTrainPlanId(trainNoticeVo.getTrainPlanId());
            trainNotice.setTrainPlace(trainNoticeVo.getTrainPlace());
            trainNotice.setStartDate(trainNoticeVo.getStartDate());
            trainNotice.setEndDate(trainNoticeVo.getEndDate());
            trainNotice.setTrainTeacher(trainNoticeVo.getTrainTeacher());
            trainNotice.setOrgId(trainNoticeVo.getOrgId());
            trainNotice.setCreateBy(userId);
            trainNotice.setDeptId(deptId);
            trainNotice.setName(name);
            trainNoticeApi.insertPrimary(trainNotice);

            //插入通知接收人记录
            List<TrainNoticePerson> tpp=trainNoticeVo.getTrainNoticePerson();
            for(TrainNoticePerson trainNoticePerson:tpp){
                trainNoticePerson.setTrainNoticeId(noticeId);
                trainNoticePerson.setTrainNoticeToPersonId(IdGen.uuid());
                trainNoticePerson.setOrgId(trainNoticeVo.getOrgId());
                trainNoticePerson.setCreateBy(userId);
                trainNoticeApi.insertForeign(trainNoticePerson);
            }
            stringData.setData("success");

        } else {

            //更新通知记录
            TrainNoticePerson trainNotice = new TrainNoticePerson();
            trainNotice.setTrainNoticeId(trainNoticeVo.getTrainNoticeId());
            trainNotice.setTrainPlanId(trainNoticeVo.getTrainPlanId());
            trainNotice.setTrainPlace(trainNoticeVo.getTrainPlace());
            trainNotice.setTrainTeacher(trainNoticeVo.getTrainTeacher());
            trainNotice.setStartDate(trainNoticeVo.getStartDate());
            trainNotice.setEndDate(trainNoticeVo.getEndDate());
            trainNotice.setUpdateBy(userId);
            trainNotice.setName(name);
            trainNoticeApi.updatePrimary(trainNotice);

            trainNoticeApi.delForeign(trainNotice);
            //更新新的接收人数据
            List<TrainNoticePerson> tpp=trainNoticeVo.getTrainNoticePerson();
            for(TrainNoticePerson trainNoticePerson:tpp){
                trainNoticePerson.setTrainNoticeToPersonId(IdGen.uuid());
                trainNoticePerson.setTrainNoticeId(trainNoticeVo.getTrainNoticeId());
                trainNoticePerson.setCreateOrg(trainNoticeVo.getOrgId());
                trainNoticePerson.setCreateBy(userId);
                trainNoticeApi.insertForeign(trainNoticePerson);
            }
            stringData.setData("success");
        }


        return stringData;
    }
    //删除培训通知
    @Path("train-notice-del")
    @POST
    public StringData dataDel(TrainNoticeVo<TrainNoticePerson> trainNoticeVo) {

        //删除培训通知记录

        //删除培训通知接收人数据
        TrainNoticePerson trainNoticePerson = new TrainNoticePerson();
        trainNoticePerson.setIds(trainNoticeVo.getTrainNoticeId());

        trainNoticeApi.delPrimary(trainNoticePerson);
        trainNoticeApi.delForeign(trainNoticePerson);

        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    //发布培训通知
    @Path("train-notice-publish")
    @POST
    public StringData dataPublish(TrainNoticeVo<TrainNoticePerson> trainNoticeVo) {

        //删除培训通知记录

        //删除培训通知接收人数据
        TrainNoticePerson trainNoticePerson = new TrainNoticePerson();
        trainNoticePerson.setIds(trainNoticeVo.getTrainNoticeId());

        trainNoticeApi.dataPublish(trainNoticePerson);

        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
}

