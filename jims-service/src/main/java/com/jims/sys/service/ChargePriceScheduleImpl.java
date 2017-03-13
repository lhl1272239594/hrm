package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.ChargePriceScheduleApi;
import com.jims.sys.dao.ChargePriceScheduleDao;
import com.jims.sys.entity.ChargePriceSchedule;


import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@Service(version = "1.0.0")

public class ChargePriceScheduleImpl extends CrudImplService<ChargePriceScheduleDao,ChargePriceSchedule> implements ChargePriceScheduleApi{

    @Override
    public List<String> findTypeList() {
        return dao.findTypeList(new ChargePriceSchedule());
    }
}
