
package com.jims.fbd.hrm.attendance.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.attendance.dao.FrequencyRuleDao;
import com.jims.fbd.hrm.attendance.entity.FrequencyRule;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AO层
 *
 * @author txb
 * @version 2016-08-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class FrequencyRuleBo extends CrudImplService<FrequencyRuleDao, FrequencyRule>{
    /**
     * 排班规则查询--无分页
     *
     * @return
     */
    public List<FrequencyRule> findFreRuleAllList(String orgId) {

        return dao.findFreRuleAllList(orgId);
    }
    /**
     * 排班规则详细信息查询--无分页
     *
     * @return
     */
    public List<FrequencyRule> findFreRuleDataAllList(String orgId,String freRuleId) {

        return dao.findFreRuleDataAllList(orgId,freRuleId);
    }
    /**
     * 排班规则查询--分页
     *
     * @return
     */

    public Page<FrequencyRule> findFreRuleList(Page<FrequencyRule> page, FrequencyRule frequencyRule ) {
       frequencyRule.setPage(page);
       page.setList(dao.findFreRuleList(frequencyRule));
       return page;
   }
    /**
     * 排班规则重复判断
     *
     * @return
     */
    public List<FrequencyRule> findFreRuleBoolean(String orgId,String freRuleDes,String freRuleId){

        return dao.findFreRuleBoolean(orgId,freRuleDes,freRuleId);
    }
    /**
     * 排班规则详细信息查询--无分页
     *
     * @return
     */
    public List<FrequencyRule> findFreRuleDataList(String orgId,String freRuleId) {

        return dao.findFreRuleDataList(orgId,freRuleId);
    }
    /**
     * 排班规则业务处理：新增
     *
     * @return
     */
    public String insertPrimary(FrequencyRule frequencyRule) {

        return dao.insertPrimary(frequencyRule)+"";
    }
    /**
     * 排班规则业务处理：新增从表信息
     *
     * @return
     */
    public String insertForeign(FrequencyRule frequencyRule) {

        return dao.insertForeign(frequencyRule)+"";
    }
    /**
     * 排班规则业务处理：修改
     *
     * @return
     */
    public String updatePrimary(FrequencyRule frequencyRule) {

        return dao.updatePrimary(frequencyRule)+"";
    }
    /**
     * 排班规则业务处理：修改行表
     *
     * @return
     */
    public String updateForeign(FrequencyRule frequencyRule) {

        return dao.updateForeign(frequencyRule)+"";
    }
    /**
     * 排班规则业务处理：更新主表循环周期
     *
     * @return
     */
    public void updateLoopDays(String freRuleId,String freLoopDays) {

        dao.updateLoopDays(freRuleId,freLoopDays);
    }

    /**
     * 删除占用判断
     * @return
     * @author ZYG
     * @version 2016-08-23
     */
    public String findOccupy(FrequencyRule frequencyRule) {
        String result = "no";
        int sum = dao.findOccupy(frequencyRule);
        if (sum>1||sum==1){
            result = "yes";
        }
        return result;
    }
    /**
     * 排班规则业务处理：主表信息删除
     *
     * @return
     */
    public void delPrimary(String freRuleId,String flag) {

        dao.delPrimary(freRuleId,flag);
    }
    /**
     * 排班规则业务处理：从表信息删除
     *
     * @return
     */
    public void delForeign(String freRuleDataId,String flag) {

        dao.delForeign(freRuleDataId,flag);
    }
}