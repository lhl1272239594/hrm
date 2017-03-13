package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.StaffGroupClassDictApi;
import com.jims.sys.bo.StaffGroupClassDictBo;
import com.jims.sys.dao.StaffGroupClassDictDao;
import com.jims.sys.entity.StaffGroupClassDict;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */

@Service(version = "1.0.0")

public class StaffGroupClassDictImpl extends CrudImplService<StaffGroupClassDictDao, StaffGroupClassDict> implements StaffGroupClassDictApi {

    @Autowired
    private StaffGroupClassDictBo staffGroupClassDictBo;

    public List<String> findTypeList() {
        return staffGroupClassDictBo.findTypeList(new StaffGroupClassDict());
    }
}
