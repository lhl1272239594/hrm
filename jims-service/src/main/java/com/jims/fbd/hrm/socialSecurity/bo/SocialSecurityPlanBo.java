
package com.jims.fbd.hrm.socialSecurity.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.socialSecurity.dao.SocialSecurityPlanDao;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityPlan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AO层
 *
 * @author
 * @version 2016-08-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class SocialSecurityPlanBo extends CrudImplService<SocialSecurityPlanDao, SocialSecurityPlan>{

    /**
     * 业务处理：社保方案查询-分页
     *
     * @return
     */
    public Page<SocialSecurityPlan> findList(Page<SocialSecurityPlan> page, SocialSecurityPlan socialSecurityPlan ) {

        socialSecurityPlan.setPage(page);
        page.setList(dao.findList(socialSecurityPlan));
        return page;
    }
    /**
     * 业务处理：社保方案详细信息查询
     *
     * @return
     */
    public List<SocialSecurityPlan> findDetailList(SocialSecurityPlan socialSecurityPlan){

        return dao.findDetailList(socialSecurityPlan);
    }
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    public List<SocialSecurityPlan> findAllList(SocialSecurityPlan socialSecurityPlan){

        return dao.findAllList(socialSecurityPlan);
    }
    /**
     *新增重复判断
     * @return
     * @author
     */
    public List<SocialSecurityPlan> findSame(String orgId,String ssPlanDes,String id){
        return dao.findSame(orgId,ssPlanDes,id);
    }

    /**
     *新增信息：主
     * @param socialSecurityPlan
     * @return
     * @author
     */
    public String insertPrimary(SocialSecurityPlan socialSecurityPlan) {

        return dao.insertPrimary(socialSecurityPlan)+"";
    }

    /**
     *新增信息：从
     * @param socialSecurityPlan
     * @return
     * @author
     */
    public String insertForeign(SocialSecurityPlan socialSecurityPlan) {

        return dao.insertForeign(socialSecurityPlan)+"";
    }
    /**
     * 业务处理：修改主表信息
     *
     * @return
     */
    public String updatePrimary(SocialSecurityPlan socialSecurityPlan) {

        return dao.updatePrimary(socialSecurityPlan)+"";
    }

    /**
     * 业务处理：修改从表信息
     *
     * @return
     */
    public String updateForeign(SocialSecurityPlan socialSecurityPlan) {

        return dao.updateForeign(socialSecurityPlan)+"";
    }


    /**
     * 删除占用判断
     * @return
     * @author 
     * @version 2016-08-23
     */
    public String findOccupy(List<SocialSecurityPlan> projects) {
        String result = "no";
        for (SocialSecurityPlan q : projects) {
            int sum = dao.findOccupy(q);
            if (sum>1||sum==1){
                result = "yes";
                break;
            }

        }
        return result;
    }
    /**
     * 批量删除数据
     * @param plans
     * @return
     * @author 
     */
    public String delPrimary(List<SocialSecurityPlan> plans) {
        for (SocialSecurityPlan q : plans) {
            q.preUpdate();
            dao.delPrimary(q);
        }
        return "sucsess";
    }
    /**
     * 业务处理：社保方案详细信息删除
     *
     * @return
     */
    public void delForeign(String dataId) {

        dao.delForeign(dataId);
    }
}