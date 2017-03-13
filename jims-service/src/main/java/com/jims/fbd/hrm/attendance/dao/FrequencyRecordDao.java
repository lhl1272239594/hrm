package com.jims.fbd.hrm.attendance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.attendance.entity.FrequencyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface FrequencyRecordDao extends CrudDao<FrequencyRecord>{
  /**
   * 排班记录信息汇总查询--按照业务创建时间汇总
   *
   * @return
   */
  public List<FrequencyRecord> findAllDetailList(@Param("orgId") String orgId,@Param("userId") String userId,@Param("freRecordHeadId") String freRecordHeadId,@Param("time") String time);
  /**
   * 排班记录信息汇总查询--按照人员汇总查询
   *
   * @return
   */
  public List<FrequencyRecord> findAllList(FrequencyRecord frequencyRecord);
  /**
   * 排班记录信息汇总查询--按照业务创建时间汇总
   *
   * @return
   */
  public List<FrequencyRecord> findHeadList(FrequencyRecord frequencyRecord);
  /**
   * 排班记录信息明细查询-无分页
   *
   * @return
   */
  public List<FrequencyRecord> findDetailList(FrequencyRecord frequencyRecord);
  /**
   * 排班记录业务处理：新增
   *
   * @return
   */
  public String callProcedures (FrequencyRecord frequencyRecord);
  /**
   * 排班记录业务处理：删除
   *
   * @return
   */
  public void delPrimary(FrequencyRecord frequencyRecord) ;

  /* *
     * 删除头
     * @param ids
     * @return
     **/
  void del_head(@Param("freRecordHeadId") String freRecordHeadId) ;
}