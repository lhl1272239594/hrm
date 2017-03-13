package com.jims.fbd.hrm.train.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.train.entity.TrainPlan;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface TrainPlanApi {


    /**
     * 业务处理：查询--分页
     *
     * @return
     */
    public Page<TrainPlan> findList(Page<TrainPlan> page,
                                          TrainPlan trainPlan);
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    public List<TrainPlan> findAllList(TrainPlan trainPlan);
    /**
     * 业务处理：新增
     *
     * @return
     */
    public String insertPrimary(TrainPlan trainPlan);

    /**
     * 业务处理：修改
     *
     * @return
     */
    public String updatePrimary(TrainPlan trainPlan);
    /**
     * 业务处理：启用
     *
     * @return
     */
    public String change(List<TrainPlan> trainPlan, String flag );
    /**
     * 业务处理：删除
     *
     * @return
     */
    public String  trainPlanDel(List<TrainPlan> trainPlan, String flag );
    /**
     * 停用重复判断
     *
     * @return
     */
    public String findTrainBoolean(List<TrainPlan> trainPlan);
    //查询名称是否重复
    public int checkName(String id, String orgId, String name, String type);
}
