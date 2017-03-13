package com.jims.fbd.hrm.train;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.train.api.TrainPlanApi;
import com.jims.fbd.hrm.train.entity.TrainPlan;
import com.jims.fbd.hrm.train.vo.TrainPlanVo;
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
@Path("trainPlan")
public class TrainPlanRest {
    @Reference(version = "1.0.0")
    private TrainPlanApi trainPlanApi;
    /**
     * 业务处理：查询--分页
     *
     * @return
     */
    @GET
    @Path("train-plan-list")
    public PageData findList(@QueryParam("orgId") String orgId,
                             @QueryParam("trainPlanTittle") String trainPlanTittle,
                             @QueryParam("type") String type,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {

        TrainPlan trainPlan = new TrainPlan();
        trainPlan.setOrgId(orgId);
        trainPlan.setTrainPlanType(type);
        trainPlan.setTrainPlanTittle(trainPlanTittle);

        Page<TrainPlan> page = trainPlanApi.findList(
                new Page<TrainPlan>(request, response), trainPlan);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    @GET
    @Path("train-plan-all-list")
    public List<TrainPlan> findAllList(@QueryParam("orgId") String orgId,
                                       @QueryParam("trainPlanType") String trainPlanType,
                                       @QueryParam("trainPlanId") String trainPlanId) {


        TrainPlan trainPlan = new TrainPlan();
        trainPlan.setOrgId(orgId);
        trainPlan.setTrainPlanId(trainPlanId);
        trainPlan.setTrainPlanType(trainPlanType);

        List<TrainPlan> list = trainPlanApi.findAllList(trainPlan);
        return list;

    }
    /**
     * 业务处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(TrainPlanVo<TrainPlan> trainPlanVo,
                            @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        String orgId = loginInfo.getOrgId();
        String deptId=loginInfo.getDeptId();

        int num = 0;
        int count = 0;
        StringData stringData = new StringData();
        String id = trainPlanVo.getTrainPlanId();
        String name=trainPlanVo.getTrainPlanTittle();
        String type=trainPlanVo.getTrainPlanType();
        TrainPlan trainPlan = new TrainPlan();
        int hasName =  trainPlanApi.checkName(id,orgId,name,type);
        if(hasName>0){
            stringData.setData("hasName");
            return stringData;
        }
        if (id.equals("999")) {

            trainPlan.setTrainPlanId(IdGen.uuid());
            trainPlan.setTrainPlanContent(trainPlanVo.getTrainPlanContent());
            trainPlan.setTrainPlanTittle(trainPlanVo.getTrainPlanTittle());
            trainPlan.setTrainPlanType(trainPlanVo.getTrainPlanType());
            trainPlan.setTrainTeacher(trainPlanVo.getTrainTeacher());
            trainPlan.setOrgId(trainPlanVo.getOrgId());
            trainPlan.setCreateBy(userId);
            trainPlan.setCreateDept(deptId);
            trainPlan.setCreateOrg(trainPlanVo.getOrgId());
            count = count + Integer.parseInt(trainPlanApi.insertPrimary(trainPlan));


        } else {

            trainPlan.setTrainPlanId(id);
            trainPlan.setTrainPlanContent(trainPlanVo.getTrainPlanContent());
            trainPlan.setTrainPlanTittle(trainPlanVo.getTrainPlanTittle());
            trainPlan.setTrainPlanType(trainPlanVo.getTrainPlanType());
            trainPlan.setTrainTeacher(trainPlanVo.getTrainTeacher());
            trainPlan.setUpdateBy(userId);
            count = count + Integer.parseInt(trainPlanApi.updatePrimary(trainPlan));
        }

        if (count == 1) {
            stringData.setCode(String.valueOf(num));
            stringData.setData("success");
        }
        return stringData;
    }

    /**
     * 业务处理：启用
     *
     * @return
     */
    @Path("train-plan-ok-change")
    @POST
    public StringData okChange(List<TrainPlan> trainPlan) {

        String flag="1";
        String num = trainPlanApi.change(trainPlan,flag);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 停用重复判断
     *
     * @return
     */
    @POST
    @Path("train-boolean")
    public StringData checkTypeIsUsed(List<TrainPlan> trainPlan, @Context HttpServletRequest request) {
        String num = trainPlanApi.findTrainBoolean(trainPlan);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 业务处理：停用
     *
     * @return
     */
    @Path("train-plan-no-change")
    @POST
    public StringData noChange(List<TrainPlan> trainPlan) {

        String flag="2";
        String num = trainPlanApi.change(trainPlan,flag);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 业务处理：删除
     *
     * @return
     */
    @Path("train-plan-del")
    @POST
    public StringData trainPlanDel(List<TrainPlan> trainPlan) {

        String flag="2";
        String num = trainPlanApi.trainPlanDel(trainPlan,flag);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}

