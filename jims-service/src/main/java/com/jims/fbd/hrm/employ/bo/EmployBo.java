
package com.jims.fbd.hrm.employ.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.employ.dao.EmployDao;
import com.jims.fbd.hrm.employ.entity.Employ;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 招聘管理BO层
 *
 * @author 
 * @version 2016-09-22
 */
@Service
@Component
@Transactional(readOnly = false)
public class EmployBo extends CrudImplService<EmployDao, Employ> {
    /**
     * 查询招聘发布数据
     * @return
     * @author 
     * @version 2016-08-22
     */
    public Page<Employ> employList(Page<Employ> page, Employ employ) {
        employ.setPage(page);
        page.setList(dao.employList(employ));
        return page;
    }
    /**
     * 查询招聘信息数据
     * @return
     * @author 
     * @version 2016-08-22
     */
    public Page<Employ> employSearch(Page<Employ> page, Employ employ) {
        employ.setPage(page);
        page.setList(dao.employSearch(employ));
        return page;
    }
    /**
     *查询招聘信息是否重复
     * @return
     * @author 
     */
    public List<Employ> findEmploysame(String orgId, String name){
        return dao.findEmploysame(orgId,name);
    }
    /**
     *新增修改保存
     * @return
     * @author 
     */
    public String merge(Employ employ, String createDept) {
        //判断主键id是否为空，不为空则执行修改方法
        if (org.apache.commons.lang3.StringUtils.isNotBlank(employ.getEmployId())){
            employ.preUpdate();
            employ.setUpdateBy(employ.getUpdateBy());
            dao.updateEmploy(employ);
            return "edit";
        }
        else{
            employ.preInsert();
            employ.setEmployId(employ.getEmployId());
            employ.setCreateBy(employ.getCreateBy());
            employ.setUpdateBy(employ.getUpdateBy());
            employ.setCreateDept(createDept);
            dao.insertEmploy(employ);
            return "add";
        }
    }
    /**
     * 删除数据
     * @param employs
     * @return
     * @author 
     */
    public String delete(List<Employ> employs) {
        for (Employ q : employs) {
            q.preUpdate();
            dao.deleteEmploy(q);
        }
        return "sucsess";
    }
    /**
     * 发布招聘
     * @param employs
     * @return
     * @author 
     */
    public String publish_employ(List<Employ> employs, String userName) {
        for (Employ q : employs) {
            q.preUpdate();
            dao.publish_employ(q,userName);
        }
        return "sucsess";
    }
    /**
     * 结束招聘
     * @param employs
     * @return
     * @author 
     */
    public String end_employ(List<Employ> employs) {
        for (Employ q : employs) {
            q.preUpdate();
            dao.end_employ(q);
        }
        return "sucsess";
    }
    /**
     * 重新发布
     * @param employs
     * @return
     * @author 
     */
    public String redeal(List<Employ> employs, String userName) {
        for (Employ q : employs) {
            q.preUpdate();
            dao.redeal(q,userName);
        }
        return "sucsess";
    }

}