package com.jims.fbd.hrm.attendance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.api.FrequencyRuleApi;
import com.jims.fbd.hrm.attendance.bo.FrequencyRuleBo;
import com.jims.fbd.hrm.attendance.dao.FrequencyRuleDao;
import com.jims.fbd.hrm.attendance.entity.FrequencyRule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */


@Service(version = "1.0.0")
public class FrequencyRuleImpl implements FrequencyRuleApi {
    @Autowired
    private FrequencyRuleBo frequencyRuleBo;
    @Autowired
    private FrequencyRuleDao frequencyRuleDao;

    /**
     * 排班规则查询--无分页
     *
     * @return
     */
    @Override
    public List<FrequencyRule> findFreRuleAllList(String orgId) {

        return frequencyRuleBo.findFreRuleAllList(orgId);
    }
    /**
     * 排班规则详细信息查询--无分页
     *
     * @return
     */
    @Override
    public List<FrequencyRule> findFreRuleDataAllList(String orgId,String freRuleId) {

        return frequencyRuleBo.findFreRuleDataAllList(orgId,freRuleId);
    }
    /**
     * 排班规则查询--分页
     *
     * @return
     */

    @Override
    public Page<FrequencyRule> findFreRuleList(Page<FrequencyRule> page, FrequencyRule frequencyRule) {
        return frequencyRuleBo.findFreRuleList(page,frequencyRule);
    }
    /**
     * 排班规则重复判断
     *
     * @return
     */
    @Override
    public List<FrequencyRule> findFreRuleBoolean(String orgId, String freRuleDes,String freRuleId) {

        return frequencyRuleBo.findFreRuleBoolean(orgId,freRuleDes,freRuleId);
    }
    /**
     * 排班规则详细信息查询--无分页
     *
     * @return
     */
    @Override
    public List<FrequencyRule> findFreRuleDataList(String orgId,String freRuleId) {

        return frequencyRuleBo.findFreRuleDataList(orgId,freRuleId);
    }
    /**
     * 排班规则业务处理：新增
     *
     * @return
     */
    @Override
    public String insertPrimary(FrequencyRule frequencyRule) {

        return frequencyRuleBo.insertPrimary(frequencyRule);
    }
    /**
     * 排班规则业务处理：新增从表信息
     *
     * @return
     */
    @Override
    public String insertForeign(FrequencyRule frequencyRule) {

        return frequencyRuleBo.insertForeign(frequencyRule);
    }
    /**
     * 排班规则业务处理：修改
     *
     * @return
     */
    @Override
    public String updatePrimary(FrequencyRule frequencyRule) {

        return frequencyRuleBo.updatePrimary(frequencyRule);
    }
    /**
     * 排班规则业务处理：修改行表
     *
     * @return
     */
    @Override
    public String updateForeign(FrequencyRule frequencyRule) {

        return frequencyRuleBo.updateForeign(frequencyRule);
    }
    /**
     * 排班规则业务处理：更新主表循环周期
     *
     * @return
     */
    @Override
    public String updateLoopDays(String freRuleId,String freLoopDays) {
        try {
            frequencyRuleBo.updateLoopDays(freRuleId,freLoopDays);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 删除占用判断
     * @return
     * @author ZYG
     * @version 2016-08-23
     */
    @Override
    public String findOccupy(FrequencyRule frequencyRule) {

        return frequencyRuleBo.findOccupy(frequencyRule);
    }
    /**
     * 排班规则业务处理：主表信息删除
     *
     * @return
     */
    @Override
    public String delPrimary(String freRuleId,String flag) {
        try {
            frequencyRuleBo.delPrimary(freRuleId,flag);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 排班规则业务处理：从表信息删除
     *
     * @return
     */
    @Override
    public String delForeign(String fesRuleDataId,String flag) {
        try {
            frequencyRuleBo.delForeign(fesRuleDataId,flag);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
}
