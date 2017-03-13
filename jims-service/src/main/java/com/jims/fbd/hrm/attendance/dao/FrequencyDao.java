package com.jims.fbd.hrm.attendance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.attendance.entity.Frequency;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 节日设置DAO接口
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface FrequencyDao extends CrudDao<Frequency>{
  /**
   * 班次项目查询--不带分页
   *
   * @return
   */

  public List<Frequency> findFreAllList(@Param("orgId") String orgId);
  /**
   * 班次项目查询--分页
   *
   * @return
   */
  public List<Frequency> findFreList(Frequency frequency);


  /**
   * 新增班次重复判断
   *
   * @return
   */
  public List<Frequency> findFreBoolean(@Param("orgId") String orgId,
                                        @Param("freItemId") String freItemId,
                                        @Param("freItemDes") String freItemDes);
  /**
   * 删除
   *
   * @return
   */
  public void delPrimary(@Param("freItemId") String freItemId,@Param("flag") String flag) ;
  /**
   * 删除占用判断
   *
   * @param frequency
   * @return
   */
  public int findOccupy(@Param("Frequency" ) Frequency frequency);
}