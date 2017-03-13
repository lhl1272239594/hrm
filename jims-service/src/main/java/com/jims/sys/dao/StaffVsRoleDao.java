/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.OrgRoleVsService;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.entity.StaffVsRole;
import com.jims.sys.vo.OrgStaffVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人员角色表DAO接口
 * @author yangruidong
 * @version 2016-05-31
 */
@MyBatisDao
public interface StaffVsRoleDao extends CrudDao<StaffVsRole> {


    /**
     * 查询人员角色信息
     *
     * @return
     */
    public List<OrgRole> getRole(@Param("staffId") String staffId);

    /**
     * 根据员工ID查询员工拥有的角色下所有的服务
     * @param staffId 员工ID
     * @return 角色对应服务的list集合
     * @author fengyuguang
     */
    public List<OrgRoleVsService> findServiceId(String staffId);

    /**
     * 查询人员角色信息
     *
     * @return
     */
    public List<StaffVsRole> findRole(@Param("staffId") String staffId);

    /**
     * 根据id删除StaffVsRole数据
     * zhu
     * @return
     */
    public int deleteStaffRole(@Param("id") String id);
	
}