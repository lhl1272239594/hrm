
package com.jims.fbd.hrm.salary.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.dao.SalaryDao;
import com.jims.fbd.hrm.salary.entity.Salary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 工资管理BO层
 * @author
 * @version 2016-05-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SalaryBo extends CrudImplService<SalaryDao, Salary> {
    /**
     * 查询工资级别数据
     * @return
     * @author 
     * @version 2016-08-22
     */

    public Page<Salary> levelLists(Page<Salary> page, Salary salary) {
        salary.setPage(page);
        page.setList(dao.levelList(salary));
        return page;
    }

    /**
     *查询工资级别是否重复
     * @return
     * @author 
     */
    public List<Salary> findLevelsame(String orgId, String typeId, String levelName){

        return dao.findLevelsame(orgId,typeId,levelName);
    }

    /**
     *工资级别名称下拉框带回
     * @return
     * @author 
     */
    public List<Salary> levelDownlist(String orgId){

        return dao.levelDownlist(orgId);
    }
    /**
     *增加方法
     * @param salary
     * @return
     * @author 
     */
    public String save(Salary salary) {
        return dao.insert(salary)+"";
    }

    /**
     *修改
     * @param salary
     * @return
     * @author 
     */
    public String update(Salary salary) {
        return dao.update(salary)+"";
    }
    /**
     *启用
     * @param levelId
     * @return
     * @author 
     */
    public void changeup(String levelId) {
         dao.changeup(levelId);
    }
    /**
     *停用
     * @param levelId
     * @return
     * @author 
     */
    public void changedown(String levelId) {
        dao.changedown(levelId);
    }
    /**
     * 删除数据
     * @param ids 服务的ID集合
     * @return
     * @author fengyuguang
     */
    @Transactional(readOnly = false)
    public String delete(String ids){
        int i = 0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++) {
                dao.delete(id[j]);  //删除服务
               // priceDao.deleteByServiceId(id[j]);  //删除服务价格明细(联动删除)
               i++;
            }
        } catch (Exception e) {
            return i + "";
        }
        return i + "";
    }


}