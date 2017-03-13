package com.jims.fbd.hrm.desk.api;

import com.jims.fbd.hrm.desk.entity.MyData;
import com.jims.fbd.hrm.desk.entity.Schedule;
import com.jims.fbd.hrm.desk.entity.ShortCut;
import com.jims.fbd.hrm.notice.entity.Notice;
import com.jims.sys.entity.OrgStaff;

import java.util.List;

public interface DeskApi {
    /**
     * 查询代办
     * @return
     */
    public Schedule getSchedule(Schedule schedule);
    /**
     * 快捷方式
     * @return
     */
    public List<ShortCut> getShortCut(OrgStaff orgStaff);
    /**
     * 查询信息统计
     * @return
     */
    public MyData getMyData(OrgStaff orgStaff);
    /**
     * 查询公告
     * @return
     */
    public List<Notice> getNotice(OrgStaff orgStaff);
    /**
     * 查询公告内容
     * @return
     */
    public Notice getNoticeById(String id);
}