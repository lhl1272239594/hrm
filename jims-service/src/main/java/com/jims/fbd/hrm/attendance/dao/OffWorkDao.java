package com.jims.fbd.hrm.attendance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.attendance.entity.OffWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface OffWorkDao extends CrudDao<OffWork>{

  /**
   * 业务处理：查询带分页
   *
   * @return
   */
  public List<OffWork> findList(OffWork offWork);
  /**
   * 业务处理：删除
   *
   * @return
   */
  public void delPrimary(@Param("dataId") String dataId) ;
  /**
   * 业务处理：“我的请假”查询
   *
   * @return
   */
  public  List<OffWork> myvacation(OffWork offWork);
}