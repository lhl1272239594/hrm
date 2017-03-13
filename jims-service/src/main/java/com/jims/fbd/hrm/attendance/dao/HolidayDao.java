package com.jims.fbd.hrm.attendance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.attendance.entity.Holiday;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 节日设置DAO接口
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface HolidayDao extends CrudDao<Holiday>{
  /**
   * 查询所有的假日信息--无分页
   *
   * @return
   */
  public List<Holiday> findAllList(@Param("orgId") String orgId,@Param("value") String value,@Param("dataId") String dataId);
  /**
   * 查询所有的假日信息--分页
   *
   * @return
   */
  public List<Holiday> findList(Holiday holiday);
  /**
   * 新增假日重复判断
   *
   * @return
   */
  public List<Holiday> findHolBoolean(@Param("orgId") String orgId, @Param("holDes") String holDes);
  /**
   * 假日信息业务处理：删除
   *
   * @return
   */
  public void delPrimary(@Param("holId") String holId, @Param("flag") String flag) ;

  /**
   * 查看假日是否重复
   *
   * @param
   * @return
   */  public int checkName(@Param("holId") String holId, @Param("orgId") String orgId, @Param("name") String name);
  /**
   * 查看假日是否占用
   *
   * @param id
   * @return
   */
  public int checkUsed(@Param("id") String id);
}