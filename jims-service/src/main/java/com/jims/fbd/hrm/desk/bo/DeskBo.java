package com.jims.fbd.hrm.desk.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.desk.dao.DeskDao;
import com.jims.fbd.hrm.desk.entity.MyData;
import com.jims.fbd.hrm.desk.entity.Schedule;
import com.jims.fbd.hrm.desk.entity.ShortCut;
import com.jims.fbd.hrm.notice.entity.Notice;
import com.jims.sys.entity.OrgStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class DeskBo extends CrudImplService<DeskDao,Schedule> {


    @Autowired
    private DeskDao deskDao;
    /**
     * 查询代办
     * @return
     */
    public Schedule getSchedule(Schedule schedule) {

        return dao.getSchedule(schedule);
    }
    /**
     * 快捷方式
     * @return
     */
    public List<ShortCut> getShortCut(OrgStaff orgStaff) {
        List<ShortCut> shortCuts=dao.getShortCut(orgStaff);
        if(shortCuts.size()==0){
            shortCuts=dao.getDefaultShortCut(orgStaff);
        }
        return shortCuts;
    }
    /**
     * 查询信息统计
     * @return
     */
    public MyData getMyData(OrgStaff orgStaff) {
        MyData myData=new MyData();
        myData.setLeave_time(dao.getLeaveTime(orgStaff));
        myData.setLeave_nums(dao.getLeaveNums(orgStaff));
        myData.setOvertime_time(dao.getOverTime(orgStaff));
        myData.setOvertime_nums(dao.getOverNums(orgStaff));
        //myData.setLieu_time(dao.getLieuTime(orgStaff));
        //myData.setLieu_nums(dao.getLieuNums(orgStaff));
        myData.setTrip_time(dao.getTripTime(orgStaff));
        myData.setTrip_nums(dao.getTripNums(orgStaff));
        return myData;
    }
    /**
     * 查询公告
     * @return
     */
    public List<Notice> getNotice(OrgStaff orgStaff) {
        return dao.getNotice(orgStaff);
    }
    /**
     * 查询公告内容
     * @return
     */
    public Notice getNoticeById(String id) {
        return dao.getNoticeById(id);
    }
}
