package com.jims.fbd.hrm.attendance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.attendance.entity.TripWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface TripWorkDao extends CrudDao<TripWork>{

  /**
   * 业务处理：查询带分页
   *
   * @return
   */
  public List<TripWork> findList(TripWork tripWork);
  /**
   * 业务处理：删除
   *
   * @return
   */
  public void delPrimary(@Param("dataId") String dataId) ;
  /**
   * 业务处理：“我的公出”查询
   *
   * @return
   */  public  List<TripWork> mytrip(TripWork tripWork);
}