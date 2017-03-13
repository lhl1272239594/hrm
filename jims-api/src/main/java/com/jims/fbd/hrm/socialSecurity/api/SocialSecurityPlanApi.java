package com.jims.fbd.hrm.socialSecurity.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityPlan;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface SocialSecurityPlanApi {
    /**
     * 业务处理：社保方案查询-分页
     *
     * @return
     */
    Page<SocialSecurityPlan> findList(Page<SocialSecurityPlan> page, SocialSecurityPlan socialSecurityPlan);

    /**
     * 业务处理：社保方案详细信息查询
     *
     * @return
     */
    List<SocialSecurityPlan> findDetailList(SocialSecurityPlan socialSecurityPlan);
    List<SocialSecurityPlan> findAllList(SocialSecurityPlan socialSecurityPlan);
    /**
     * 新增重复判断
     * @author
     * @version 2016-08-22
     * @return
     */
    List<SocialSecurityPlan> findSame(String orgId, String ssPlanDes, String id);
    /**
     * 业务处理：新增主表信息
     *
     * @return
     */
    String insertPrimary(SocialSecurityPlan socialSecurityPlan);


    /**
     * 业务处理：修改主表信息
     *
     * @return
     */
    String insertForeign(SocialSecurityPlan socialSecurityPlan);

    /**
     * 业务处理：修改从表信息
     *
     * @return
     */
    String updatePrimary(SocialSecurityPlan socialSecurityPlan);



    /**
     * 业务处理：修改从表信息
     *
     * @return
     */
    String updateForeign(SocialSecurityPlan socialSecurityPlan);

    /**
     * 删除占用判断
     * @author ZYG
     * @version 2016-08-22
     * @return
     */
    String findOccupy(List<SocialSecurityPlan> projects);
    /**
     * 删除
     * @param plans
     * @return
     * @author
     * @version 2016-08-31
     */
    String delPrimary(List<SocialSecurityPlan> plans);
    /**
     * 业务处理：社保方案详细信息删除
     *
     * @return
     */
    String delForeign(String fesDateId);
}
