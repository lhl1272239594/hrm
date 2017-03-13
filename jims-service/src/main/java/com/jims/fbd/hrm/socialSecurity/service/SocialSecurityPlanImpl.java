package com.jims.fbd.hrm.socialSecurity.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.socialSecurity.api.SocialSecurityPlanApi;
import com.jims.fbd.hrm.socialSecurity.bo.SocialSecurityPlanBo;
import com.jims.fbd.hrm.socialSecurity.dao.SocialSecurityPlanDao;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityPlan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class SocialSecurityPlanImpl implements SocialSecurityPlanApi {
    @Autowired
    private SocialSecurityPlanBo socialSecurityPlanBo;
    @Autowired
    private SocialSecurityPlanDao socialSecurityPlanDao;


    /**
     * 业务处理：社保方案查询-分页
     *
     * @return
     */
    @Override
    public Page<SocialSecurityPlan> findList(Page<SocialSecurityPlan> page, SocialSecurityPlan socialSecurityPlan) {

        return socialSecurityPlanBo.findList(page,socialSecurityPlan);
    }

    /**
     * 业务处理：社保方案详细信息查询
     *
     * @return
     */
    @Override
    public List<SocialSecurityPlan> findDetailList(SocialSecurityPlan socialSecurityPlan) {
        return socialSecurityPlanBo.findDetailList(socialSecurityPlan);
    }
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    @Override
    public List<SocialSecurityPlan> findAllList(SocialSecurityPlan socialSecurityPlan) {
        return socialSecurityPlanBo.findAllList(socialSecurityPlan);
    }
    /**
     * 查询是否重复
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public List<SocialSecurityPlan> findSame(String orgId,String ssPlanDes,String id) {
        return socialSecurityPlanBo.findSame(orgId,ssPlanDes,id);
    }
    /**
     * 新头信息
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public String insertPrimary(SocialSecurityPlan socialSecurityPlan) {

        return socialSecurityPlanBo.insertPrimary(socialSecurityPlan);
    }


    /**
     * 新增行信息
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public String insertForeign(SocialSecurityPlan socialSecurityPlan) {

        return socialSecurityPlanBo.insertForeign(socialSecurityPlan);
    }
    /**
     * 业务处理：修改主表信息
     *
     * @return
     */
    @Override
    public String updatePrimary(SocialSecurityPlan socialSecurityPlan) {

        return socialSecurityPlanBo.updatePrimary(socialSecurityPlan);
    }

    /**
     * 业务处理：修改从表信息
     *
     * @return
     */
    @Override
    public String updateForeign(SocialSecurityPlan socialSecurityPlan) {

        return socialSecurityPlanBo.updateForeign(socialSecurityPlan);
    }

    /**
     * 删除占用判断
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public String findOccupy(List<SocialSecurityPlan> projects) {
        return socialSecurityPlanBo.findOccupy(projects);
    }
    /**
     * 批量删除
     * @param plans
     * @return
     * @author
     * @version 2016-08-22
     */
    @Override
    public String delPrimary(List<SocialSecurityPlan> plans) {
        return socialSecurityPlanBo.delPrimary(plans);
    }
    /**
     * 业务处理：社保方案详细信息删除
     *
     * @return
     */
    @Override
    public String delForeign(String dataId) {
        try {
            socialSecurityPlanBo.delForeign(dataId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

}
