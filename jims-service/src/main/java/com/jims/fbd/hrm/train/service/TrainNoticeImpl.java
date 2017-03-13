package com.jims.fbd.hrm.train.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.train.api.TrainNoticeApi;
import com.jims.fbd.hrm.train.bo.TrainNoticeBo;
import com.jims.fbd.hrm.train.dao.TrainNoticeDao;
import com.jims.fbd.hrm.train.entity.TrainNoticePerson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class TrainNoticeImpl implements TrainNoticeApi {
    @Autowired
    private TrainNoticeBo trainNoticeBo;
    @Autowired
    private TrainNoticeDao trainNoticeDao;


    //查询培训通知列表

    @Override
    public Page<TrainNoticePerson> findList(Page<TrainNoticePerson> page,
                                            TrainNoticePerson trainNoticePerson) {

        return trainNoticeBo.findList(page,trainNoticePerson);
    }
    //查询参与该培训通知的人

    @Override
    public List<TrainNoticePerson> findNoticeToPerson(TrainNoticePerson trainNoticePerson)
    {
        return trainNoticeBo.findNoticeToPerson(trainNoticePerson);
    }
    //新增主表

    @Override
    public String insertPrimary(TrainNoticePerson trainNotice) {

        return trainNoticeBo.insertPrimary(trainNotice);
    }
    //新增从表信息

    @Override
    public String insertForeign(TrainNoticePerson trainNoticePerson) {

        return trainNoticeBo.insertForeign(trainNoticePerson);
    }
    //修改主表信息

    @Override
    public String updatePrimary(TrainNoticePerson trainNotice) {

        return trainNoticeBo.updatePrimary(trainNotice);
    }
    //删除主表信息

    @Override
    public String delPrimary(TrainNoticePerson trainNotice) {
        try {
            trainNoticeBo.delPrimary(trainNotice);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    //删除从表信息

    @Override
    public String delForeign(TrainNoticePerson trainNoticePerson) {
        try {
            trainNoticeBo.delForeign(trainNoticePerson);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    //查询名称是否重复
    @Override
    public int checkName(String id, String orgId, String name) {
        return trainNoticeBo.checkName(id,orgId,name);
    }
    //发布培训通知
    @Override
    public void dataPublish(TrainNoticePerson trainNoticePerson) {
        trainNoticeBo.dataPublish(trainNoticePerson);
    }
    //我的培训通知列表
    @Override
    public Page<TrainNoticePerson> mytrain(Page<TrainNoticePerson> page, TrainNoticePerson trainNoticePerson) {
        return trainNoticeBo.mytrain(page,trainNoticePerson);
    }
}


