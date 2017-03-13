package com.jims.fbd.hrm.employ.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.employ.entity.Employ;

import java.util.List;

/**
 * 招聘管理api层
 * @author 
 * @version 2016-08-22
 */
public interface EmployApi {


    /**
     * 根据参数查询新增数据是否重复
     * @author 
     * @version 2016-09-22
     * @return
     */
    List<Employ> findEmploysame(String orgId, String name);
    /**
     * 保存或修改
     * @author 
     * @version 2016-08-31
     * @return
     */
    String merge(Employ employ, String createDept);
    /**
     * 删除
     * @param employs
     * @return
     * @author 
     * @version 2016-08-31
     */
    String del_employ(List<Employ> employs);
    /**
     * 发布
     * @param employs
     * @return
     * @author 
     * @version 2016-08-31
     */
    String publish_employ(List<Employ> employs, String userName);
    /**
     * 结束
     * @param employs
     * @return
     * @author 
     * @version 2016-08-31
     */
    String end_employ(List<Employ> employs);
    /**
     * 重新发布
     * @param employs
     * @return
     * @author 
     * @version 2016-08-31
     */
    String redeal(List<Employ> employs, String userName);

    /**
     * 查询发布招聘
     * @return
     * @author 
     * @version 2016-09-20
     */
    Page<Employ> employList(Page<Employ> page, Employ employ);

    /**
     * 查询招聘信息
     * @return
     * @author 
     * @version 2016-09-20
     */
    Page<Employ> employSearch(Page<Employ> page, Employ employ);

}
