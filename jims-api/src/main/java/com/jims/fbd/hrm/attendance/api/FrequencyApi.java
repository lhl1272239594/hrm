package com.jims.fbd.hrm.attendance.api;



import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.Frequency;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface FrequencyApi {


    /**
     * 班次项目查询--不带分页
     *
     * @return
     */

    public List<Frequency> findFreAllList(String orgId);
    /**
     * 班次项目查询--分页
     *
     * @return
     */
    public Page<Frequency> findFreList(Page<Frequency> page, Frequency frequency);


    /**
     * 新增班次重复判断
     *
     * @return
     */
    public List<Frequency> findFreBoolean(String orgId, String freItemId,String freItemDes);
    /**
     * 班次项目处理：新增
     *
     * @return
     */
    public String insertPrimary(Frequency frequency);

    /**
     * 班次项目处理：修改
     *
     * @return
     */
    public String updatePrimary(Frequency frequency);


    /**
     * 删除占用判断
     * @author ZYG
     * @version 2016-08-22
     * @return
     */
    String findOccupy(Frequency frequency);
    /**
     * 删除
     *
     * @return
     */
    public String delPrimary(String freItemId,String flag);
}
