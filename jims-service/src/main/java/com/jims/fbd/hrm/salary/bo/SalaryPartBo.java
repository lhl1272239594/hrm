
package com.jims.fbd.hrm.salary.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.dao.SalaryPartDao;
import com.jims.fbd.hrm.salary.dao.SalaryProjectDao;
import com.jims.fbd.hrm.salary.entity.SalaryPart;
import com.jims.fbd.hrm.salary.entity.SalaryProject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 工资组成BO层
 *
 * @author
 * @version 2016-08-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SalaryPartBo extends CrudImplService<SalaryPartDao, SalaryPart>{
    /**
     * 查询人员类别数据
     * @return
     * @author
     * @version 2016-08-22
     */

    public Page<SalaryPart> partList(Page<SalaryPart> page,SalaryPart salaryPart) {
        salaryPart.setPage(page);
        page.setList(dao.partList(salaryPart));
        return page;
    }
    /**
     *查询工资组成是否重复
     * @return
     * @author
     */
    public List<SalaryPart> findPartsame(String orgId,String partName,String partId){
        return dao.findPartsame(orgId,partName,partId);
    }
    /**
     *社保是否已存在
     * @return
     * @author
     */
    public List<SalaryPart> findSbsame(String orgId){
        return dao.findSbsame(orgId);
    }
    /**
     *新增修改保存
     * @return
     * @author
     */
    public String merge(SalaryPart salaryPart, String userName,String createDept) {
        //判断主键Id是否为空，不为空则更新
        if (org.apache.commons.lang3.StringUtils.isNotBlank(salaryPart.getPartId())){
            salaryPart.preUpdate();
            salaryPart.setUpdateBy(userName);
            dao.updatePart(salaryPart);
            return "edit";
        }
        else{
            salaryPart.preInsert();
            salaryPart.setPartId(salaryPart.getPartId());
            salaryPart.setCreateBy(userName);
            salaryPart.setUpdateBy(userName);
            salaryPart.setCreateDept(createDept);
            dao.insertPart(salaryPart);
            return "add";
        }
    }
    /**
     *启用
     * @param partId
     * @return
     * @author
     */
    public void changeup(String partId) {
        dao.changeup(partId);
    }
    /**
     *停用
     * @param partId
     * @return
     * @author
     */
    public void changedown(String partId) {
        dao.changedown(partId);
    }
    /**
     * 停用启用
     * @param  parts
     * @return
     */
    public String enableFlag(List<SalaryPart> parts) {
        for (SalaryPart q : parts) {
            q.preUpdate();
            dao.enableFlag(q);
        }
        return "sucsess";
    }
    /**
     * 恢复普通工资
     * @param  parts
     * @return
     */
    public String back(List<SalaryPart> parts,String userName) {
        for (SalaryPart q : parts) {
            q.setUpdateBy(userName);
            q.preUpdate();
            dao.back_cal(q);
            dao.back(q);
        }
        return "sucsess";
    }
    /**
     *匹配社保基数
     * @param parts
     * @return
     * @author
     */
    public String match_part(List<SalaryPart> parts,String userName) {
        for (SalaryPart q : parts) {
            q.setUpdateBy(userName);
            q.preUpdate();
            dao.match_part(q);//选中的置为1
            dao.match_part_cal(q);//联动将工资计算公式中该部分FLAG置为1
            dao.match_part_other(q);//为选中的置为0
        }
        return "sucsess";
    }
    /**
     * 删除数据
     * @param parts
     * @return
     * @author
     */
    public String delete(List<SalaryPart> parts) {
        for (SalaryPart q : parts) {
            q.preUpdate();
            dao.deletePart(q);
        }
        return "sucsess";
    }
    /**
     * 删除占用判断
     * @return
     * @author
     * @version 2016-08-23
     */
    public String findOccupy(List<SalaryPart> parts) {
        String result = "no";
        for (SalaryPart q : parts) {
            int sum = dao.findOccupy(q);
            if (sum>1||sum==1){
                result = "yes";
                break;
            }
        }
        return result;
    }

}