package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.AdjustDay;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface AdjustDayApi {



    public Page<AdjustDay> findList(Page<AdjustDay> page, AdjustDay adjustDay);
    public List<AdjustDay> findAllList(AdjustDay adjustDay);

    //新增主表信息
    public String insertPrimary(AdjustDay adjustDay);

    //更新主表信息
    public String updatePrimary(AdjustDay adjustDay);

    public String delPrimary(String dataId);

    public Page<AdjustDay> myadjustday(Page<AdjustDay> adjustDayPage, AdjustDay adjustDay);
}
