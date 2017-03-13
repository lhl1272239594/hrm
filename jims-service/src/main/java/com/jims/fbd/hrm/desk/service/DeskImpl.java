package com.jims.fbd.hrm.desk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.fbd.hrm.desk.bo.DeskBo;
import com.jims.fbd.hrm.desk.api.DeskApi;
import com.jims.fbd.hrm.desk.entity.MyData;
import com.jims.fbd.hrm.desk.entity.Schedule;
import com.jims.fbd.hrm.desk.entity.ShortCut;
import com.jims.fbd.hrm.notice.entity.Notice;
import com.jims.sys.entity.OrgStaff;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
@Service(version = "1.0.0")

public class DeskImpl implements DeskApi{

    @Autowired
    private DeskBo deskBo;
    /**
     * 查询代办
     * @return
     */
    @Override
    public Schedule getSchedule(Schedule schedule) {
        return deskBo.getSchedule(schedule);
    }
    /**
     * 快捷方式
     * @return
     */
    @Override
    public List<ShortCut> getShortCut(OrgStaff orgStaff) {
        return deskBo.getShortCut(orgStaff);
    }
    /**
     * 查询信息统计
     * @return
     */
    @Override
    public MyData getMyData(OrgStaff orgStaff) {
        return deskBo.getMyData(orgStaff);
    }
    /**
     * 查询公告
     * @return
     */
    @Override
    public List<Notice> getNotice(OrgStaff orgStaff) {
        return deskBo.getNotice(orgStaff);
    }
    /**
     * 查询公告内容
     * @return
     */
    @Override
    public Notice getNoticeById(String id) {
        return deskBo.getNoticeById(id);
    }
}
