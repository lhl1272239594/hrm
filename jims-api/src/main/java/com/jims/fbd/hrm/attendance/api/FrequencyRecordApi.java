package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;

import com.jims.fbd.hrm.attendance.entity.FrequencyRecord;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface FrequencyRecordApi {

    /**
     * 排班记录信息明细查询-分页
     *
     * @return
     */
    public List<FrequencyRecord> findAllDetailList(String orgId,String userId,String freRecordHeadId,String time);
    /**
     * 排班记录信息汇总查询--按照人员汇总查询
     *
     * @return
     */
    public Page<FrequencyRecord> findAllList(Page<FrequencyRecord> page, FrequencyRecord frequencyRecord);

    /**
     * 排班记录信息汇总查询--按照业务创建时间汇总
     *
     * @return
     */
    public Page<FrequencyRecord> findHeadList(Page<FrequencyRecord> page, FrequencyRecord frequencyRecord);
    /**
     * 排班记录信息明细查询-无分页
     *
     * @return
     */
    public Page<FrequencyRecord> findDetailList(Page<FrequencyRecord> page, FrequencyRecord frequencyRecord);
    /**
     * 排班记录业务处理：新增
     *
     * @return
     */
    public String callProcedures(FrequencyRecord frequencyRecord);


    /**
     * 排班记录业务处理：修改
     *
     * @return
     */
    public String updatePrimary(FrequencyRecord frequencyRecord);
    /**
     * 排班记录业务处理：删除
     *
     * @return
     */
    public String delPrimary(FrequencyRecord frequencyRecord);
    /**
     * 删除头
     * @param freRecordHeadId
     */
    String del_head(String freRecordHeadId);
}
