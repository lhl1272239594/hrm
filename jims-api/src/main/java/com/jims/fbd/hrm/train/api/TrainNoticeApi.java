package com.jims.fbd.hrm.train.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.train.entity.TrainNoticePerson;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface TrainNoticeApi {


    //查询培训通知列表

    public Page<TrainNoticePerson> findList(Page<TrainNoticePerson> page,
                                            TrainNoticePerson trainNoticePerson);
    //查询参与该培训通知的人

    public List<TrainNoticePerson> findNoticeToPerson(TrainNoticePerson trainNoticePerson);

    //新增主表
    public String insertPrimary(TrainNoticePerson trainNotice);
    //新增从表信息

    public String insertForeign(TrainNoticePerson trainNoticePerson);
    //修改主表信息

    public String updatePrimary(TrainNoticePerson trainNotice);
    //删除主表信息

    public String delPrimary(TrainNoticePerson trainNotice);
    //删除从表信息

    public String delForeign(TrainNoticePerson trainNoticePerson);
    //查询名称是否重复
    public int checkName(String id, String orgId, String name);
    //发布培训通知
    public void dataPublish(TrainNoticePerson trainNoticePerson);
    //我的培训通知列表
    public Page<TrainNoticePerson> mytrain(Page<TrainNoticePerson> trainNoticePersonPage,
                                           TrainNoticePerson trainNoticePerson);
}
