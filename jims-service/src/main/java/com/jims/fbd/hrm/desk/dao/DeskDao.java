package com.jims.fbd.hrm.desk.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.desk.entity.MyData;
import com.jims.fbd.hrm.desk.entity.Schedule;
import com.jims.fbd.hrm.desk.entity.ShortCut;
import com.jims.fbd.hrm.notice.entity.Notice;
import com.jims.sys.entity.OrgStaff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface DeskDao extends CrudDao<Schedule>{

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
     * 默认快捷方式
     * @return
     */
    public List<ShortCut> getDefaultShortCut(OrgStaff orgStaff);
    /**
     * 查询请假时长
     * @return
     */
    public String getLeaveTime(OrgStaff orgStaff);
    /**
     * 查询请假次数
     * @return
     */
    public String getLeaveNums(OrgStaff orgStaff);
    /**
     * 查询加班时长
     * @return
     */
    public String getOverTime(OrgStaff orgStaff);
    /**
     * 查询加班次数
     * @return
     */
    public String getOverNums(OrgStaff orgStaff);
    /**
     * 查询调休时长
     * @return
     */
    public String getLieuTime(OrgStaff orgStaff);
    /**
     * 查询调休次数
     * @return
     */
    public String getLieuNums(OrgStaff orgStaff);
    /**
     * 查询公出时长
     * @return
     */
    public String getTripTime(OrgStaff orgStaff);
    /**
     * 查询公出次数
     * @return
     */
    public String getTripNums(OrgStaff orgStaff);
    /**
     * 查询公告
     * @return
     */
    public List<Notice> getNotice(OrgStaff orgStaff);
    /**
     * 查询公告内容
     * @return
     */
    public Notice getNoticeById(@Param("id") String id);
}
