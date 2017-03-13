
package com.jims.fbd.hrm.salary.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.dao.SalaryCalculateDao;
import com.jims.fbd.hrm.salary.entity.SalaryCalculate;
import com.jims.fbd.hrm.salary.entity.SalaryHumanType;
import com.jims.fbd.hrm.salary.entity.SalaryPart;
import com.jims.fbd.hrm.salary.entity.SalaryProjectMoney;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 工资计算公式BO层
 *
 * @author
 * @version 2016-05-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SalaryCalculateBo extends CrudImplService<SalaryCalculateDao, SalaryCalculate> {
    /**
     * 查询工资公式
     * @return
     * @author
     * @version 2016-08-22
     */

    public Page<SalaryCalculate> calList(Page<SalaryCalculate> page, SalaryCalculate salaryCalculate) {
        salaryCalculate.setPage(page);
        page.setList(dao.calList(salaryCalculate));
        return page;
    }
    /**
     *工资项目下拉框带回
     * @return
     * @author
     */
    public List<SalaryProjectMoney> projectList(String orgId, String typeId){

        return dao.projectList(orgId,typeId);
    }
    /**
     *人员类别下拉框带回
     * @return
     * @author
     */
    public List<SalaryHumanType> htList(String orgId){

        return dao.htList(orgId);
    }
    /**
     *工资组成部分下拉框带回
     * @return
     * @author
     */
    public List<SalaryPart> partList(String orgId){

        return dao.partList(orgId);
    }
    /**
     *绩效考评下拉框带回
     * @return
     * @author
     */
    public List<SalaryCalculate> performanceList(String orgId){

        return dao.performanceList(orgId);
    }

    /**
     *查询公式是否重复
     * @return
     * @author
     */
    public List<SalaryCalculate> findCalsame(String orgId, String content, String typeName, String partName){

        return dao.findCalsame(orgId,content, typeName,partName);
    }


    /**
     *增加方法
     * @param
     * @return
     * @author
     */
    public String merge(SalaryCalculate salaryCalculate, String userName, String createDept) {

        if (org.apache.commons.lang3.StringUtils.isNotBlank(salaryCalculate.getContentId())){
            salaryCalculate.preUpdate();
            salaryCalculate.setUpdateBy(userName);
            dao.updateCal(salaryCalculate);
            return "edit";
        }
        else{
            salaryCalculate.preInsert();
            salaryCalculate.setContentId(salaryCalculate.getContentId());
            salaryCalculate.setCreateBy(userName);
            salaryCalculate.setUpdateBy(userName);
            salaryCalculate.setCreateDept(createDept);
            dao.insertCal(salaryCalculate);
            return "add";
        }
    }
    /**
     *启用
     * @param contentId
     * @return
     * @author
     */
    public void changeup(String contentId) {
         dao.changeup(contentId);
    }
    /**
     *停用
     * @param contentId
     * @return
     * @author
     */
    public void changedown(String contentId) {
        dao.changedown(contentId);
    }
    /**
     * 停用启用
     * @param  calculates
     * @return
     */
    public String enableFlag(List<SalaryCalculate> calculates) {
        for (SalaryCalculate q : calculates) {
            q.preUpdate();
            dao.enableFlag(q);
        }
        return "sucsess";
    }
    /**
     * 删除数据
     * @param calculates
     * @return
     * @author
     */
    public String delete(List<SalaryCalculate> calculates) {
        for (SalaryCalculate q : calculates) {
            q.preUpdate();
            dao.deleteCal(q);
        }
        return "sucsess";
    }
    /**
     * 删除数据
     * @param ids
     * @return
     * @author
     */
    @Transactional(readOnly = false)
    public String delete(String ids){
        int i = 0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++) {
                dao.delete(id[j]);  //删除公式
               i++;
            }
        } catch (Exception e) {
            return i + "";
        }
        return i + "";
    }


}