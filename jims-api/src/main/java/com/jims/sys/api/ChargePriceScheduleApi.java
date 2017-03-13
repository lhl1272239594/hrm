package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.ChargePriceSchedule;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface ChargePriceScheduleApi {
    /**
     * 查询字段列表
     * @param page
     * @param chargePriceSchedule
     * @return
     */
    public Page<ChargePriceSchedule> findPage(Page<ChargePriceSchedule> page, ChargePriceSchedule chargePriceSchedule);

    /**
     * 保存修改数据
     * @param chargePriceSchedule
     * @return
     */
    public String save(ChargePriceSchedule chargePriceSchedule);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public ChargePriceSchedule get(String id);

    /**
     * 查询html类型列表
     * @return
     */
    public List<String> findTypeList();

}
