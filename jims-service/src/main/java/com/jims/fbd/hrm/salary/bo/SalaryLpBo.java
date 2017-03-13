
package com.jims.fbd.hrm.salary.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.dao.SalaryLpDao;
import com.jims.fbd.hrm.salary.entity.SalaryLp;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 工资级别项目BO层
 *
 * @author
 * @version 2016-08-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SalaryLpBo extends CrudImplService<SalaryLpDao, SalaryLp> {
    /**
     * 查询数据
     * @return
     * @author
     * @version 2016-08-22
     */

    public Page<SalaryLp> lpList(Page<SalaryLp> page, SalaryLp salaryLp) {
        salaryLp.setPage(page);
        page.setList(dao.lpList(salaryLp));
        return page;
    }
    // 查询是否重复

    public List<SalaryLp> findLpsame(String orgId, String levelId, String projectId){

        return dao.findLpsame(orgId,levelId,projectId);
    }
    /**
     *增加方法
     * @param salaryLp
     * @return
     * @author 
     */
    public String save(SalaryLp salaryLp) {
        return dao.insert(salaryLp)+"";
    }

    /**
     *修改
     * @param salaryLp
     * @return
     * @author 
     */
    public String update(SalaryLp salaryLp) {
        return dao.update(salaryLp)+"";
    }
    /**
     *启用
     * @param lpId
     * @return
     * @author 
     */
    public void changeup(String lpId) {
        dao.changeup(lpId);
    }
    /**
     *停用
     * @param lpId
     * @return
     * @author 
     */
    public void changedown(String lpId) {
        dao.changedown(lpId);
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
                dao.delete(id[j]);  //删除
               i++;
            }
        } catch (Exception e) {
            return i + "";
        }
        return i + "";
    }


}