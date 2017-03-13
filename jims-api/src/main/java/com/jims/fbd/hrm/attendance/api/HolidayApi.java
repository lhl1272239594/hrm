package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.Holiday;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface HolidayApi {

    /**
     * 查询所有的假日信息--无分页
     *
     * @return
     */
    public List<Holiday> findAllList(String orgId,String value,String dataId);
    /**
     * 查询所有的假日信息--分页
     *
     * @return
     */
    public Page<Holiday> findList(Page<Holiday> page, Holiday holiday);

    /**
     * 新增假日重复判断
     *
     * @return
     */    public List<Holiday> findHolBoolean(String orgId, String holDes);

    /**
     * 假日信息业务处理：新增
     *
     * @return
     */
    public String insertPrimary(Holiday holiday);

    /**
     * 假日信息业务处理：修改
     *
     * @return
     */    public String updatePrimary(Holiday holiday);


    /**
     * 假日信息业务处理：删除
     *
     * @return
     */
    public String delPrimary(String holId, String flag);
    //查询名称是否重复
    public int checkName(String holId, String orgId, String name);
    /**
     * 查看假日是否占用
     *
     * @param id
     * @return
     */

    public String checkUsed(String id);
}
