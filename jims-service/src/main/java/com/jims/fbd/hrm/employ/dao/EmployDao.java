package com.jims.fbd.hrm.employ.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.employ.entity.Employ;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招聘管理DAO接口
 * @author 
 * @version 2016-08-22
 */
@MyBatisHrmDao
public interface EmployDao extends CrudDao<Employ> {



    /**
     * 查询是否已存在

     * @return
     * @author 
     * @version 2016-09-22
     */
    public List<Employ> findEmploysame(@Param("orgId") String orgId, @Param("name") String name);
    /**
     * 查询招聘发布

     * @return
     * @author 
     * @version 2016-09-22
     */
    public List<Employ> employList(Employ employ);
    /**
     * 查询招聘信息

     * @return
     * @author 
     * @version 2016-09-22
     */
    public List<Employ> employSearch(Employ employ);

    /**
     * 批量删除
     *
     * @param employ
     * @return
     */
    public void deleteEmploy(@Param("Employ") Employ employ);
    /**
     * 发布招聘
     *
     * @param employ
     * @return
     */
    public void publish_employ(@Param("Employ") Employ employ, @Param("userName") String userName);
    /**
     * 结束招聘
     *
     * @param employ
     * @return
     */
    public void end_employ(@Param("Employ") Employ employ);
    /**
     * 重新发布
     *
     * @param employ
     * @return
     */
    public void redeal(@Param("Employ") Employ employ, @Param("userName") String userName);
    /**
     * 修改
     *
     * @param employ
     * @return
     */
    public void updateEmploy(@Param("Employ") Employ employ);
    /**
     * 新增
     *
     * @param employ
     * @return
     */
    public void insertEmploy(@Param("Employ") Employ employ);

}