
package com.jims.fbd.hrm.attendance.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.attendance.dao.HolidayDao;
import com.jims.fbd.hrm.attendance.entity.Holiday;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AO层
 *
 * @author yangchen
 * @version 2016-08-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class HolidayBo extends CrudImplService<HolidayDao, Holiday>{
    /**
     * 查询所有的假日信息--无分页
     *
     * @return
     */
    public List<Holiday> findAllList(String orgId,String value,String dataId){

        return dao.findAllList(orgId,value,dataId);

    }
    /**
     * 查询所有的假日信息--分页
     *
     * @return
     */
    public Page<Holiday> findList(Page<Holiday> page, Holiday holiday ) {
        holiday.setPage(page);
        page.setList(dao.findList(holiday));
        return page;
    }

    /**
     * 新增假日重复判断
     *
     * @return
     */
    public List<Holiday> findHolBoolean(String orgId,String holDes){

        return dao.findHolBoolean(orgId,holDes);
    }

    /**
     *新增假日信息：主
     * @param holiday
     * @return
     * @author wei
     */
    public String insertPrimary(Holiday holiday) {

        return dao.insertPrimary(holiday)+"";
    }

    /**
     *修改信息：主
     * @param holiday
     * @return
     * @author wei
     */
    public String updatePrimary(Holiday holiday) {

        return dao.updatePrimary(holiday)+"";
    }

    /**
     * 假日信息业务处理：删除
     *
     * @return
     */

    public void delPrimary(String holId,String flag) {

        dao.delPrimary(holId,flag);
    }

    //查询名称是否重复
    public int checkName(String holId, String orgId, String name) {
        return dao.checkName(holId,orgId,name);
    }
    /**
     * 查看假日是否占用
     *
     * @param id
     * @return
     */
    public String checkUsed(String id) {
        int num=dao.checkUsed(id);
        if(num>0){
            return "hasUsed";
        }
        return "success";
    }
}