
package com.jims.fbd.hrm.attendance.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.attendance.dao.FrequencyDao;
import com.jims.fbd.hrm.attendance.entity.Frequency;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AO层
 *
 * @author txb
 * @version 2016-08-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class FrequencyBo extends CrudImplService<FrequencyDao, Frequency>{

    /**
     * 班次项目查询--不带分页
     *
     * @return
     */
    public List<Frequency> findFreAllList(String orgId) {

        return dao.findFreAllList(orgId);
    }
    /**
     * 班次项目查询--分页
     *
     * @return
     */

   public Page<Frequency> findFreList(Page<Frequency> page, Frequency frequency ) {
       frequency.setPage(page);
       page.setList(dao.findFreList(frequency));
       return page;
   }

    /**
     * 新增班次重复判断
     *
     * @return
     */

    public List<Frequency> findFreBoolean(String orgId,String freItemId,String freItemDes){

        return dao.findFreBoolean(orgId,freItemId,freItemDes);
    }
    public String insertPrimary(Frequency frequency) {

        return dao.insertPrimary(frequency)+"";
    }


    public String updatePrimary(Frequency frequency) {

        return dao.updatePrimary(frequency)+"";
    }

    /**
     * 删除
     *
     * @return
     */


    public void delPrimary(String freItemId,String flag) {

        dao.delPrimary(freItemId,flag);
    }
    /**
     * 删除占用判断
     * @return
     * @author ZYG
     * @version 2016-08-23
     */
    public String findOccupy(Frequency frequency) {
        String result = "no";
        int sum = dao.findOccupy(frequency);
        if (sum>1||sum==1){
            result = "yes";
        }
        return result;
    }


}