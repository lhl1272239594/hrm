package com.jims.fbd.hrm.attendance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.api.HolidayApi;
import com.jims.fbd.hrm.attendance.bo.HolidayBo;
import com.jims.fbd.hrm.attendance.dao.HolidayDao;
import com.jims.fbd.hrm.attendance.entity.Holiday;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 节日设置imp层
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class HolidayImpl implements HolidayApi {
    @Autowired
    private HolidayBo holidayBo;
    @Autowired
    private HolidayDao holidayDao;


    /**
     * 查询所有的假日信息--无分页
     *
     * @return
     */
    @Override
    public List<Holiday> findAllList(String orgId,String value,String dataId) {

        return holidayBo.findAllList(orgId,value,dataId);
    }
    /**
     * 查询所有的假日信息--分页
     *
     * @return
     */
    @Override
    public Page<Holiday> findList(Page<Holiday> page, Holiday holiday) {
        return holidayBo.findList(page,holiday);
    }

    /**
     * 查询假日信息是否重复
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public List<Holiday> findHolBoolean(String orgId,String holDes) {

        return holidayBo.findHolBoolean(orgId,holDes);
    }
    /**
     * 新增假日主表信息
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public String insertPrimary(Holiday holiday) {

        return holidayBo.insertPrimary(holiday);
    }
    /**
     * 更新假日主表信息
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public String updatePrimary(Holiday holiday) {

        return holidayBo.updatePrimary(holiday);
    }

    /**
     * 假日信息业务处理：删除
     *
     * @return
     */
    @Override
    public String delPrimary(String holId,String flag) {
        try {
            holidayBo.delPrimary(holId,flag);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    //查询名称是否重复
    @Override
    public int checkName(String holId, String orgId, String name) {
        return holidayBo.checkName(holId,orgId,name);
    }
    /**
     * 查看假日是否占用
     *
     * @param id
     * @return
     */
    @Override
    public String checkUsed(String id) {
        return holidayBo.checkUsed(id);
    }


}
