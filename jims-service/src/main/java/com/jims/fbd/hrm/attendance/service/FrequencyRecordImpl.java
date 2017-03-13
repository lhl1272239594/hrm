package com.jims.fbd.hrm.attendance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.api.FrequencyRecordApi;
import com.jims.fbd.hrm.attendance.bo.FrequencyRecordBo;
import com.jims.fbd.hrm.attendance.dao.FrequencyRecordDao;
import com.jims.fbd.hrm.attendance.entity.FrequencyRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 排班设置imp层
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class FrequencyRecordImpl implements FrequencyRecordApi {
    @Autowired
    private FrequencyRecordBo frequencyRecordBo;
    @Autowired
    private FrequencyRecordDao frequencyRecordDao;

    /**
     * 排班记录信息明细查询-分页
     *
     * @return
     */
    @Override
    public List<FrequencyRecord> findAllDetailList(String orgId,String userId,String freRecordHeadId,String time) {

        return frequencyRecordBo.findAllDetailList(orgId,userId,freRecordHeadId,time);
    }
    /**
     * 排班记录信息汇总查询--按照人员汇总查询
     *
     * @return
     */
    @Override
    public Page<FrequencyRecord> findAllList(Page<FrequencyRecord> page, FrequencyRecord frequencyRecord) {
        return frequencyRecordBo.findAllList(page,frequencyRecord);
    }
    /**
     * 排班记录信息汇总查询--按照人员汇总查询
     *
     * @return
     */
    @Override
    public Page<FrequencyRecord> findHeadList(Page<FrequencyRecord> page, FrequencyRecord frequencyRecord) {
        return frequencyRecordBo.findHeadList(page,frequencyRecord);
    }
    /**
     * 排班记录信息明细查询-无分页
     *
     * @return
     */
    @Override
    public Page<FrequencyRecord> findDetailList(Page<FrequencyRecord> page, FrequencyRecord frequencyRecord) {
        return frequencyRecordBo.findDetailList(page,frequencyRecord);
    }
    /**
     * 排班记录业务处理：新增
     *
     * @return
     */
    @Override
    public String callProcedures(FrequencyRecord frequencyRecord) {

        return frequencyRecordBo.callProcedures(frequencyRecord);
    }
    /**
     * 排班记录业务处理：修改
     *
     * @return
     */
    @Override
    public String updatePrimary(FrequencyRecord frequencyRecord) {

        return frequencyRecordBo.updatePrimary(frequencyRecord);
    }
    /**
     * 排班记录业务处理：删除
     *
     * @return
     */
    @Override
    public String delPrimary(FrequencyRecord frequencyRecord) {
        try {
            frequencyRecordBo.delPrimary(frequencyRecord);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 删除头
     *
     * @param freRecordHeadId
     * @return
     * @author ZYG
     */
    @Override
    public String del_head(String freRecordHeadId) {
        try {
            frequencyRecordBo.del_head(freRecordHeadId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

}


