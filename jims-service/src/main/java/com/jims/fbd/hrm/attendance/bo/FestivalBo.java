
package com.jims.fbd.hrm.attendance.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.attendance.dao.FestivalDao;
import com.jims.fbd.hrm.attendance.entity.Festival;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AO层
 *
 * @author txb
 * @version 2016-08-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class FestivalBo extends CrudImplService<FestivalDao, Festival>{


    public Page<Festival> findFesList(Page<Festival> page, Festival festival ) {

        festival.setPage(page);
        page.setList(dao.findFesList(festival));
        return page;
    }


    //节日日期信息
    public List<Festival> findFesDateList(String orgId,String fesId){

        return dao.findFesDateList(orgId,fesId);
    }



        // 查询节日是否重复

    public List<Festival> findFesBoolean(String orgId, String fesDesId, String yearId, String fesId){

        return dao.findFesBoolean(orgId,fesDesId,yearId,fesId);
    }

    /**
     *新增节日信息：主
     * @param festival
     * @return
     * @author wei
     */
    public String insertPrimary(Festival festival) {

        return dao.insertPrimary(festival)+"";
    }

    /**
     *新增节日信息：从
     * @param festival
     * @return
     * @author wei
     */
    public String insertForeign(Festival festival) {

        return dao.insertForeign(festival)+"";
    }

    public String updateForeign(Festival festival) {

        return dao.updateForeign(festival)+"";
    }


    public void delPrimary(Festival festival) {

            dao.delPrimary(festival);

    }

    /*删除行*/
    public String delForeign(List<Festival> festival) {

        for (Festival q : festival) {

            dao.delForeign(q);
        }
        return "sucsess";

    }
    /*删除行同时删除关联头*/
    public String delForeign1(List<Festival> festival) {

        for (Festival q : festival) {

            dao.delForeign(q);
            dao.delForeign1(q);
        }
        return "sucsess";

    }
    //删除从表行信息
    public void delFestival(String fesId) {
        dao.delFestival(fesId);
    }
}