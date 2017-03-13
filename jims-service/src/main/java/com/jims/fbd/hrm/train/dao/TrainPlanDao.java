package com.jims.fbd.hrm.train.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import org.apache.ibatis.annotations.Param;
import com.jims.fbd.hrm.train.entity.TrainPlan;


import java.util.List;

/**
 *
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface TrainPlanDao extends CrudDao<TrainPlan>{
  /**
   * 业务处理：查询--分页
   *
   * @return
   */

  public List<TrainPlan> findList(TrainPlan trainPlan);
  /**
   * 业务处理：查询-无分页
   *
   * @return
   */
  public List<TrainPlan> findAllList(TrainPlan trainPlan);
  /**
   * 停用重复判断
   *
   * @return
   */
  public int findTrainBoolean(TrainPlan trainPlan);
  /**
   * 业务处理：启用
   *
   * @return
   */
  public void change(@Param("TrainPlan") TrainPlan trainPlan, @Param("flag") String flag) ;
  /**
   * 业务处理：删除
   *
   * @return
   */
  public void trainPlanDel(@Param("TrainPlan") TrainPlan trainPlan, @Param("flag") String flag) ;
  //查询名称是否重复
  public int checkName(@Param("id")String id,@Param("orgId") String orgId,@Param("name") String name,@Param("type") String type);
}