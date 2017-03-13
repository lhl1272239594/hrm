package com.jims.fbd.hrm.attendance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.api.FrequencyApi;
import com.jims.fbd.hrm.attendance.bo.FrequencyBo;
import com.jims.fbd.hrm.attendance.dao.FrequencyDao;
import com.jims.fbd.hrm.attendance.entity.Frequency;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 班次设置imp层
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class FrequencyImpl implements FrequencyApi {
    @Autowired
    private FrequencyBo frequencyBo;
    @Autowired
    private FrequencyDao frequencyDao;

    /**
     * 班次项目查询--不带分页
     *
     * @return
     */
    @Override
    public List<Frequency> findFreAllList(String orgId) {

        return frequencyBo.findFreAllList(orgId);
    }
    /**
     * 班次项目查询--分页
     *
     * @return
     */
    @Override
    public Page<Frequency> findFreList(Page<Frequency> page, Frequency frequency) {
        return frequencyBo.findFreList(page,frequency);
    }


    /**
     * 新增班次重复判断
     *
     * @return
     */
    @Override
    public List<Frequency> findFreBoolean(String orgId, String freItemId,String freItemDes) {

        return frequencyBo.findFreBoolean(orgId,freItemId,freItemDes);
    }


    @Override
    public String insertPrimary(Frequency frequency) {

        return frequencyBo.insertPrimary(frequency);
    }


    /**
     * 班次项目处理：修改
     *
     * @return
     */
    @Override
    public String updatePrimary(Frequency frequency) {

        return frequencyBo.updatePrimary(frequency);
    }

    /**
     * 删除
     *
     * @return
     */
    @Override
    public String delPrimary(String freItemId,String flag) {
        try {
            frequencyBo.delPrimary(freItemId,flag);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    /**
     * 删除占用判断
     * @return
     * @author ZYG
     * @version 2016-08-23
     */
    @Override
    public String findOccupy(Frequency frequency) {
        return frequencyBo.findOccupy(frequency);
    }

}
