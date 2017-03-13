package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.Festival;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface FestivalApi {

    /**
     * 查询节日信息
     *
     * @return
     */
    public Page<Festival> findFesList  ( Page<Festival> page, Festival festival);

    /**
     * 查询节日日期信息
     *
     * @return
     */
    public List<Festival> findFesDateList(String orgId,String fesId);

    //根据参数查询新增数据是否重复
    public List<Festival> findFesBoolean(String orgId, String fesDesId, String yearId, String fesId);


    //新增头信息
    public String insertPrimary(Festival festival);


    //新增行信息
    public String insertForeign(Festival festival);

   //更新从表行信息
    public String updateForeign(Festival festival);

    public String delPrimary(Festival festival);

    public String delForeign(List<Festival> festival);
    public String delForeign1(List<Festival> festival);
    //删除从表行信息
    public void delFestival(String fesId);
}
