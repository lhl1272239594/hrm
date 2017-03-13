package com.jims.asepsis.bo;

import com.jims.asepsis.dao.StaffDictDao;
import com.jims.asepsis.entity.StaffDict;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Auser on
 * 员工(用户)表Service（操作员，查对者）
 *
 * @author louhuili
 * @version 2016/6/27.
 */
@Service
@Transactional(readOnly = false)
public class StaffDictBo extends CrudImplService<StaffDictDao, StaffDict> {

    /**
     * 查询员工(用户)表
     * @return
     * @author louhuili
     */
    public List<StaffDict> listStaffDict() {
        return dao.listStaffDict();
    }

}

