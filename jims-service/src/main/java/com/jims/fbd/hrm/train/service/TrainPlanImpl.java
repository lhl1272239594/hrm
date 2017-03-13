package com.jims.fbd.hrm.train.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.train.api.TrainPlanApi;
import com.jims.fbd.hrm.train.bo.TrainPlanBo;
import com.jims.fbd.hrm.train.dao.TrainPlanDao;
import com.jims.fbd.hrm.train.entity.TrainPlan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class TrainPlanImpl implements TrainPlanApi {
    @Autowired
    private TrainPlanBo trainPlanBo;
    @Autowired
    private TrainPlanDao trainPlanDao;


    /**
     * 业务处理：查询--分页
     *
     * @return
     */
    @Override
    public Page<TrainPlan> findList(Page<TrainPlan> page,
                                          TrainPlan trainPlan) {

        return trainPlanBo.findList(page,trainPlan);
    }
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    @Override
    public List<TrainPlan> findAllList(TrainPlan trainPlan)
    {

        return trainPlanBo.findAllList(trainPlan);
    }
    /**
     * 业务处理：新增
     *
     * @return
     */
    @Override
    public String insertPrimary(TrainPlan trainPlan) {

        return trainPlanBo.insertPrimary(trainPlan);
    }
    /**
     * 业务处理：修改
     *
     * @return
     */
    @Override
    public String updatePrimary(TrainPlan trainPlan) {

        return trainPlanBo.updatePrimary(trainPlan);
    }
    /**
     * 业务处理：启用
     *
     * @return
     */
    @Override
    public String change(List<TrainPlan> trainPlan, String flag ) {

        return trainPlanBo.change(trainPlan,flag);

    }
    /**
     * 业务处理：删除
     *
     * @return
     */
    @Override
    public String trainPlanDel(List<TrainPlan> trainPlan, String flag ) {

        return trainPlanBo.trainPlanDel(trainPlan,flag);

    }
    /**
     * 停用重复判断
     *
     * @return
     */
    @Override
    public String findTrainBoolean(List<TrainPlan> trainPlan) {
        return trainPlanBo.findTrainBoolean(trainPlan);
    }
    //查询名称是否重复
    @Override
    public int checkName(String id, String orgId, String name, String type) {
        return trainPlanBo.checkName(id,orgId,name,type);
    }


}


