
package com.jims.fbd.hrm.salary.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.dao.SalaryLpsDao;
import com.jims.fbd.hrm.salary.entity.SalaryLps;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 工资级别项目金额BO层
 *
 * @author txb
 * @version 2016-05-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SalaryLpsBo extends CrudImplService<SalaryLpsDao, SalaryLps> {
    /**
     * 查询工资级别数据
     * @return
     * @author
     * @version 2016-08-22
     */

    public Page<SalaryLps> lpsList(Page<SalaryLps> page, SalaryLps salaryLps) {
        salaryLps.setPage(page);
        page.setList(dao.lpsList(salaryLps));
        return page;
    }
    // 查询节日是否重复

    public List<SalaryLps> findLpssame(String orgId, String levelId, String projectId){

        return dao.findLpssame(orgId,levelId,projectId);
    }
    /**
     *增加方法
     * @param salaryLps
     * @return
     * @author
     */
    public String save(SalaryLps salaryLps) {
        return dao.insert(salaryLps)+"";
    }

    /**
     *修改
     * @param salaryLps
     * @return
     * @author
     */
    public String update(SalaryLps salaryLps) {
        return dao.update(salaryLps)+"";
    }
    /**
     *启用
     * @param lpsId
     * @return
     * @author
     */
    public void changeup(String lpsId) {
        dao.changeup(lpsId);
    }
    /**
     *停用
     * @param lpsId
     * @return
     * @author
     */
    public void changedown(String lpsId) {
        dao.changedown(lpsId);
    }
    /**
     * 删除数据
     * @param ids 服务的ID集合
     * @return
     * @author
     */
    @Transactional(readOnly = false)
    public String delete(String ids){
        int i = 0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++) {
                dao.delete(id[j]);  //删除服务
               i++;
            }
        } catch (Exception e) {
            return i + "";
        }
        return i + "";
    }


}