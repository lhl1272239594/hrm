
package com.jims.fbd.hrm.salary.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.dao.SalaryTmpDao;
import com.jims.fbd.hrm.salary.entity.SalaryTmp;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 创建工资BO层
 *
 * @author
 * @version 2016-08-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SalaryTmpBo extends CrudImplService<SalaryTmpDao, SalaryTmp> {
    /**
     * 查询创建工资列表
     * @return
     * @author
     * @version 2016-08-22
     */

    public Page<SalaryTmp> salaryList(Page<SalaryTmp> page, SalaryTmp salaryTmp) {
        salaryTmp.setPage(page);
        page.setList(dao.salaryList(salaryTmp));
        return page;
    }
    /**
     * 查询待发放工资列表
     * @return
     * @author
     * @version 2016-08-22
     */

    public Page<SalaryTmp> salarytodoList(Page<SalaryTmp> page, SalaryTmp salaryTmp) {
        salaryTmp.setPage(page);
        page.setList(dao.salarytodoList(salaryTmp));
        return page;
    }
    /**
     * 查询待发放工资列表
     * @return
     * @author
     * @version 2016-08-22
     */

    public Page<SalaryTmp> salarytodoList1(Page<SalaryTmp> page, SalaryTmp salaryTmp) {
        salaryTmp.setPage(page);
        page.setList(dao.salarytodoList1(salaryTmp));
        return page;
    }
    /**
     * 变更类别
     * @param  tmps
     * @return
     */
    public String change(List<SalaryTmp> tmps, String userName) {
        for (SalaryTmp q : tmps) {
            q.preUpdate();
            dao.change(q,userName);
        }
        return "sucsess";
    }
    /**
     * 创建工资
     * @param  salaryTmp
     * @return
     */
    public String create(SalaryTmp salaryTmp) {
        List<PersonVo> personVos=salaryTmp.getPersonVos();
        for(PersonVo o:personVos){
            o.setOrgId(salaryTmp.getOrgId());
            o.setName(salaryTmp.getCreateBy());
            dao.deleteSalary(o);//创建之前先删除
            dao.create(o);
        }
        return "sucsess";
    }

    /**
     * 重新计算
     * @param  tmps
     * @return
     */
    public String recal(List<SalaryTmp> tmps) {
        for (SalaryTmp q : tmps) {
            q.preUpdate();
            dao.recal(q);
        }
        return "sucsess";
    }

    /**
     * 下发工资(全部)
     * @param  salaryTmp
     * @return
     */
    public String deal(SalaryTmp salaryTmp) {
        dao.insertHistory(salaryTmp);
        dao.updateState(salaryTmp);
        dao.updateConfirm(salaryTmp);
        return "sucsess";
    }
    /**
     * 下发工资(按部门)
     * @param  salaryTmp
     * @return
     */
    public String dealbyDept(SalaryTmp salaryTmp) {
        List<PersonVo> personVos=salaryTmp.getPersonVos();
        for(PersonVo o:personVos){
            o.setOrgId(salaryTmp.getOrgId());
            o.setName(salaryTmp.getConfirmMan());
            dao.insertHistoryByDept(o);
            dao.updateStateByDept(o);
            dao.updateConfirmDept(o);
        }
        return "sucsess";
    }
    /**
     * 查询工资历史列表
     * @return
     * @author
     * @version 2016-08-22
     */
    public Page<SalaryTmp> salaryHistorylist(Page<SalaryTmp> page, SalaryTmp salaryTmp) {
        salaryTmp.setPage(page);
        page.setList(dao.salaryHistorylist(salaryTmp));
        return page;
    }
    /**
     * 查询历史信息
     * @return
     * @author
     * @version 2016-08-22
     */
    public Page<SalaryTmp> salaryHistoryall(Page<SalaryTmp> page, SalaryTmp salaryTmp) {
        salaryTmp.setPage(page);
        page.setList(dao.salaryHistoryall(salaryTmp));
        return page;
    }

    /**
     * 我的工资
     * @return
     * @author
     * @version 2016-08-22
     */

    public Page<SalaryTmp> getmypay(Page<SalaryTmp> page, SalaryTmp salaryTmp) {
        salaryTmp.setPage(page);
        page.setList(dao.getmypay(salaryTmp));
        return page;
    }
    /**
     *判断工资是否已下发
     * @return
     * @author 
     */
    public List<SalaryTmp> findSalarysame(String orgId){
        return dao.findSalarysame(orgId);
    }
    /**
     * 工资详单
     * @param salaryTmp
     * @return
     */
    public List<SalaryTmp> salaryInfo(SalaryTmp salaryTmp) {

        return dao.salaryInfo(salaryTmp);

    }
    /**
     * 工资详单
     * @param salaryTmp
     * @return
     */
    public List<SalaryTmp> salaryDetail(SalaryTmp salaryTmp) {

        return dao.salaryDetail(salaryTmp);

    }

}