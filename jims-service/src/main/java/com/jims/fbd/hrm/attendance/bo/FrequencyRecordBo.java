
package com.jims.fbd.hrm.attendance.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.attendance.dao.FrequencyRecordDao;
import com.jims.fbd.hrm.attendance.entity.FrequencyRecord;
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
public class FrequencyRecordBo extends CrudImplService<FrequencyRecordDao, FrequencyRecord>{

    /**
     * 排班记录信息汇总查询--按照业务创建时间汇总
     *
     * @return
     */
    public List<FrequencyRecord> findAllDetailList(String orgId,String userId,String freRecordHeadId,String time) {

        return dao.findAllDetailList(orgId,userId,freRecordHeadId,time);
    }
    /**
     * 排班记录信息汇总查询--按照人员汇总查询
     *
     * @return
     */
   public Page<FrequencyRecord> findAllList(Page<FrequencyRecord> page, FrequencyRecord frequencyRecord ) {
       frequencyRecord.setPage(page);
       page.setList(dao.findAllList(frequencyRecord));
       return page;
   }
    /**
     * 排班记录信息汇总查询--按照业务创建时间汇总
     *
     * @return
     */
    public Page<FrequencyRecord> findHeadList(Page<FrequencyRecord> page, FrequencyRecord frequencyRecord ) {
        frequencyRecord.setPage(page);
        page.setList(dao.findHeadList(frequencyRecord));
        return page;
    }
    /**
     * 排班记录信息明细查询-无分页
     *
     * @return
     */
    public Page<FrequencyRecord> findDetailList(Page<FrequencyRecord> page, FrequencyRecord frequencyRecord ) {
        frequencyRecord.setPage(page);
        page.setList(dao.findDetailList(frequencyRecord));
        return page;
    }
    /**
     * 排班记录业务处理：新增
     *
     * @return
     */
    public String callProcedures(FrequencyRecord frequencyRecord) {

        return dao.callProcedures(frequencyRecord)+"";
    }
    /**
     * 排班记录业务处理：修改
     *
     * @return
     */
    public String updatePrimary(FrequencyRecord frequencyRecord) {

        return dao.updatePrimary(frequencyRecord)+"";
    }
    /**
     * 排班记录业务处理：删除
     *
     * @return
     */
    public void delPrimary(FrequencyRecord frequencyRecord) {
        dao.delPrimary(frequencyRecord);
    }
    /**
     * 删除头
     *
     * @param freRecordHeadId
     * @return
     * @author ZYG
     */

    public void del_head(String freRecordHeadId) {
        dao.del_head(freRecordHeadId);
    }
}