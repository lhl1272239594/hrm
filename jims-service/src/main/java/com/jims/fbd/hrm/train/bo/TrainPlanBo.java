
package com.jims.fbd.hrm.train.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.train.entity.TrainPlan;
import com.jims.fbd.hrm.train.dao.TrainPlanDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AO层
 *
 * @version 2016-09-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class TrainPlanBo extends CrudImplService<TrainPlanDao, TrainPlan>{

    /**
     * 业务处理：查询--分页
     *
     * @return
     */
   public Page<TrainPlan> findList(Page<TrainPlan> page,
                                         TrainPlan trainPlan ) {
       trainPlan.setPage(page);
       page.setList(dao.findList(trainPlan));
       return page;
   }
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    public List<TrainPlan> findAllList(TrainPlan trainPlan){

        return dao.findAllList(trainPlan);
    }
    /**
     * 业务处理：新增
     *
     * @return
     */
    public String insertPrimary(TrainPlan trainPlan) {

        return dao.insertPrimary(trainPlan)+"";
    }


    /**
     * 业务处理：修改
     *
     * @return
     */
    public String updatePrimary(TrainPlan trainPlan) {

        return dao.updatePrimary(trainPlan)+"";
    }
    /**
     * 停用重复判断
     *
     * @return
     */
    public String findTrainBoolean(List<TrainPlan> trainPlan){
        for (TrainPlan q : trainPlan) {
            int num=dao.findTrainBoolean(q);
            if(num>0){
                return "isUsed";
            }
        }
        return "success";
    }
    /**
     * 业务处理：启用
     *
     * @return
     */
    public String  change(List<TrainPlan> trainPlan, String flag) {
        for (TrainPlan q : trainPlan) {
            dao.change(q,flag);
        }
        return "sucsess";
    }
    /**
     * 业务处理：删除
     *
     * @return
     */
    public String  trainPlanDel(List<TrainPlan> trainPlan, String flag) {
        for (TrainPlan q : trainPlan) {
            dao.trainPlanDel(q,flag);
        }
        return "sucsess";
    }
    //查询名称是否重复
    public int checkName(String id, String orgId, String name, String type) {
        return dao.checkName(id,orgId,name,type);
    }
}