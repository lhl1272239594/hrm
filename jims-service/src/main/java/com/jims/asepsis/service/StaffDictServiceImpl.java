/**
 * Created by Administrator on 2016/6/25.
 */
package com.jims.asepsis.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.asepsis.api.StaffDictServiceApi;
import com.jims.asepsis.bo.StaffDictBo;
import com.jims.asepsis.dao.StaffDictDao;
import com.jims.asepsis.entity.StaffDict;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 员工(用户)表管理
* @author louhuili
* @version 2016-06-27
*/
@Service(version = "1.0.0")

public class StaffDictServiceImpl extends CrudImplService<StaffDictDao, StaffDict> implements StaffDictServiceApi {

    @Autowired
    private StaffDictBo bo;

    /**
     * 员工(用户)表管理（操作员，查对者）
     *
     * @author louhuili
     * @version 2016/6/27
     */
    public List<StaffDict> listStaffDict() {
        return bo.listStaffDict();
    }
}