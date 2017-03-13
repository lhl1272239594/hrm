package com.jims.fbd.hrm.attendance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.attendance.entity.FrequencyRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 节日设置DAO接口
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface FrequencyRuleDao extends CrudDao<FrequencyRule>{


  /**
   * 排班规则查询--无分页
   *
   * @return
   */
  public List<FrequencyRule> findFreRuleAllList(@Param("orgId") String orgId);
  /**
   * 排班规则详细信息查询--无分页
   *
   * @return
   */
  public List<FrequencyRule> findFreRuleDataAllList(@Param("orgId") String orgId,
                                                    @Param("freRuleId") String freRuleId);
  /**
   * 排班规则查询--分页
   *
   * @return
   */

  public List<FrequencyRule> findFreRuleList(FrequencyRule frequencyRule);
  /**
   * 排班规则重复判断
   *
   * @return
   */
  public List<FrequencyRule> findFreRuleBoolean(@Param("orgId") String orgId,
                                        @Param("freRuleDes") String freRuleDes,
                                                @Param("freRuleId") String freRuleId);
  /**
   * 排班规则详细信息查询--无分页
   *
   * @return
   */
  public List<FrequencyRule> findFreRuleDataList(@Param("orgId") String orgId,
                                                 @Param("freRuleId") String freRuleId);
  /**
   * 排班规则业务处理：主表信息删除
   *
   * @return
   */
  public void delPrimary(@Param("freRuleId") String freRuleId,@Param("flag") String flag) ;
  /**
   * 排班规则业务处理：更新主表循环周期
   *
   * @return
   */
  public void updateLoopDays(@Param("freRuleId") String freRuleId,@Param("freLoopDays") String freLoopDays) ;

  /**
   * 删除占用判断
   *
   * @param frequencyRule
   * @return
   */
  public int findOccupy(@Param("FrequencyRule" ) FrequencyRule frequencyRule);
  /**
   * 排班规则业务处理：从表信息删除
   *
   * @return
   */
  public void delForeign(@Param("freRuleDataId") String freRuleDataId,@Param("flag") String flag) ;
}