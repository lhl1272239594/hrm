
package com.jims.fbd.hrm.salary.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.dao.SalaryHumanTypeDao;
import com.jims.fbd.hrm.salary.entity.SalaryHumanType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 工资级别BO层
 * @author
 * @version 2016-08-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SalaryHumanTypeBo extends CrudImplService<SalaryHumanTypeDao, SalaryHumanType> {
    /**
     * 查询工资级别列表
     * @return
     * @author
     * @version 2016-08-22
     */

    public Page<SalaryHumanType> htList(Page<SalaryHumanType> page, SalaryHumanType salaryHumanType) {
        salaryHumanType.setPage(page);
        page.setList(dao.htList(salaryHumanType));
        return page;
    }
    /**
     * 查询工资级别下拉框
     * @return
     * @author
     * @version 2016-08-22
     */
    public List<SalaryHumanType> htList1(String orgId) {
        return dao.htList1(orgId);
    }
    /**
     *查询工资级别是否重复
     * @return
     * @author
     */
    public List<SalaryHumanType> findHtsame(String orgId, String typeName, String typeId){
        return dao.findHtsame(orgId,typeName,typeId);
    }
    /**
     *新增修改保存
     * @return
     * @author
     */
    public String merge(SalaryHumanType salaryHumanType, String userName, String createDept) {
        //判断主键Id是否为空，不为空则更新
        if (org.apache.commons.lang3.StringUtils.isNotBlank(salaryHumanType.getTypeId())){
            salaryHumanType.preUpdate();
            salaryHumanType.setUpdateBy(userName);
            dao.updateHt(salaryHumanType);
            return "edit";
        }
        else{
            salaryHumanType.preInsert();
            salaryHumanType.setTypeId(salaryHumanType.getTypeId());
            salaryHumanType.setCreateBy(userName);
            salaryHumanType.setUpdateBy(userName);
            salaryHumanType.setCreateDept(createDept);
            //dao.deleteAlltype(salaryHumanType);//新增之前先删除全部类别
            //dao.insertAlltype(salaryHumanType);//插入全部类别
            dao.insertHt(salaryHumanType);//新增类别
            return "add";
        }
    }
    /**
     *启用
     * @param typeId
     * @return
     * @author
     */
    public void changeup(String typeId) {
         dao.changeup(typeId);
    }
    /**
     *停用
     * @param typeId
     * @return
     * @author
     */
    public void changedown(String typeId) {
        dao.changedown(typeId);
    }
    /**
     * 停用启用
     * @param  humanTypes
     * @return
     */
    public String enableFlag(List<SalaryHumanType> humanTypes) {
        for (SalaryHumanType q : humanTypes) {
            q.preUpdate();
            dao.enableFlag(q);
        }
        return "sucsess";
    }
    /**
     * 删除数据
     * @param humanTypes
     * @return
     * @author 
     */
    public String delete(List<SalaryHumanType> humanTypes) {
        for (SalaryHumanType q : humanTypes) {
            q.preUpdate();
            dao.deleteHt(q);
        }
        return "sucsess";
    }
    /**
     * 删除占用判断
     * @return
     * @author 
     * @version 2016-08-23
     */
    public String findOccupy(List<SalaryHumanType> humanTypes) {
        String result = "no";
        for (SalaryHumanType q : humanTypes) {

            int sum = dao.findOccupy(q);
            if (sum>1||sum==1){
                result = "yes";
                break;
            }
        }
        return result;
    }


}