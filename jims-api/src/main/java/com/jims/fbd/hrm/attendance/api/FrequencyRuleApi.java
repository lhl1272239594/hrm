package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.FrequencyRule;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface FrequencyRuleApi {

    /**
     * 排班规则查询--无分页
     *
     * @return
     */
    public List<FrequencyRule> findFreRuleAllList(String orgId);
    /**
     * 排班规则详细信息查询--无分页
     *
     * @return
     */
    public List<FrequencyRule> findFreRuleDataAllList(String orgId,String freRuleId);
    /**
     * 排班规则查询--分页
     *
     * @return
     */

    public Page<FrequencyRule> findFreRuleList(Page<FrequencyRule> page, FrequencyRule frequencyRule);
    /**
     * 排班规则重复判断
     *
     * @return
     */
    public List<FrequencyRule> findFreRuleBoolean(String orgId, String freRuleDes,String freRuleId);
    /**
     * 排班规则详细信息查询--无分页
     *
     * @return
     */
    public List<FrequencyRule> findFreRuleDataList(String orgId,String freRuleId);

    /**
     * 排班规则业务处理：新增
     *
     * @return
     */
    public String insertPrimary(FrequencyRule frequencyRule);

    /**
     * 排班规则业务处理：修改
     *
     * @return
     */
    public String updatePrimary(FrequencyRule frequencyRule);

    /**
     * 排班规则业务处理：新增从表信息
     *
     * @return
     */
    public String insertForeign(FrequencyRule frequencyRule);

    /**
     * 排班规则业务处理：修改行表
     *
     * @return
     */
    public String updateForeign(FrequencyRule frequencyRule);
    /**
     * 删除占用判断
     * @author ZYG
     * @version 2016-08-22
     * @return
     */
    String findOccupy(FrequencyRule frequencyRule);
    /**
     * 排班规则业务处理：主表信息删除
     *
     * @return
     */
    public String delPrimary(String freRuleId,String flag);
    /**
     * 排班规则业务处理：更新主表循环周期
     *
     * @return
     */
    public String updateLoopDays(String freRuleId,String freLoopDays);
    /**
     * 排班规则业务处理：从表信息删除
     *
     * @return
     */
    public String delForeign(String freRuleDataId,String flag);
}
