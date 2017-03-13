package com.jims.asepsis.api;

import com.jims.asepsis.entity.StaffDict;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 * 员工(用户)表Api接口（查对者和操作员）
 * @author louhuili
 * @version 2016-06-27
 */
public interface StaffDictServiceApi {

    /**
     * 获取员工(用户)表
     * @author louhuili
     * @version 2016/6/27
     */
    public List<StaffDict> listStaffDict();
}
