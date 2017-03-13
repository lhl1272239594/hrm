
package com.jims.fbd.hrm.train.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.train.dao.TrainNoticeDao;
import com.jims.fbd.hrm.train.entity.TrainNoticePerson;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AO层
 *
 * @version 2016-09-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class TrainNoticeBo extends CrudImplService<TrainNoticeDao, TrainNoticePerson>{

    //查询培训通知列表

    public Page<TrainNoticePerson> findList(Page<TrainNoticePerson> page,
                                         TrainNoticePerson trainNoticePerson ) {
       trainNoticePerson.setPage(page);
       page.setList(dao.findList(trainNoticePerson));
       return page;
   }
    //查询参与该培训通知的人

    public List<TrainNoticePerson> findNoticeToPerson(TrainNoticePerson trainPlanPerson){

        return dao.findNoticeToPerson(trainPlanPerson);
    }
    //新增主表

    public String insertPrimary(TrainNoticePerson trainNotice) {

        return dao.insertPrimary(trainNotice)+"";
    }
    //新增从表信息

    public String insertForeign(TrainNoticePerson trainPlanPerson) {

        return dao.insertForeign(trainPlanPerson)+"";
    }
    //修改主表信息

    public String updatePrimary(TrainNoticePerson trainNotice) {

        return dao.updatePrimary(trainNotice)+"";
    }
    //删除主表信息

    public void delPrimary(TrainNoticePerson trainNotice) {

        dao.delPrimary(trainNotice);
    }
    //删除从表信息

    public void delForeign(TrainNoticePerson trainPlanPerson) {

        dao.delForeign(trainPlanPerson);
    }
    //查询名称是否重复
    public int checkName(String id, String orgId, String name) {
        return dao.checkName(id,orgId,name);
    }
    //发布培训通知
    public void dataPublish(TrainNoticePerson trainNoticePerson) {
        dao.dataPublish(trainNoticePerson.getIds());
    }
    //我的培训通知列表
    public Page<TrainNoticePerson> mytrain(Page<TrainNoticePerson> page, TrainNoticePerson trainNoticePerson) {
        trainNoticePerson.setPage(page);
        page.setList(dao.mytrain(trainNoticePerson));
        return page;
    }
}