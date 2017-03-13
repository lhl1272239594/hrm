package com.jims.fbd.hrm.attendance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.api.FestivalApi;
import com.jims.fbd.hrm.attendance.bo.FestivalBo;
import com.jims.fbd.hrm.attendance.dao.FestivalDao;
import com.jims.fbd.hrm.attendance.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 节日设置imp层
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class FestivalImpl implements FestivalApi {
    @Autowired
    private FestivalBo festivalBo;
    @Autowired
    private FestivalDao festivalDao;


    /**
     * 查询节日信息
     * @return
     * @author yangchen
     * @version 2016-08-23
     */

    @Override
    public Page<Festival> findFesList(Page<Festival> page, Festival festival) {

        return festivalBo.findFesList(page,festival);
    }


    /**
     * 查询节日日期信息
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public List<Festival> findFesDateList(String orgId,String fesDesId) {
        return festivalBo.findFesDateList(orgId,fesDesId);
    }
    /**
     * 查询节日设置是否重复
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public List<Festival> findFesBoolean(String orgId, String fesDesId, String yearId, String fesId) {
        return festivalBo.findFesBoolean(orgId,fesDesId,yearId,fesId);
    }
    /**
     * 新增节日头信息
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public String insertPrimary(Festival festival) {

        return festivalBo.insertPrimary(festival);
    }
    /**
     * 新增节日行信息
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Override
    public String insertForeign(Festival festival) {

        return festivalBo.insertForeign(festival);
    }
    @Override
    public String updateForeign(Festival festival) {

        return festivalBo.updateForeign(festival);
    }

    @Override
    public String delPrimary(Festival festival) {
        try {
            festivalBo.delPrimary(festival);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    @Override
    public String delForeign(List<Festival> festival) {
        try {
            festivalBo.delForeign(festival);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    @Override
    public String delForeign1(List<Festival> festival) {
        try {
            festivalBo.delForeign1(festival);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    //删除从表行信息
    @Override
    public void delFestival(String fesId) {
        festivalBo.delFestival(fesId);
    }

}
