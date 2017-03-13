package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.StaffGroupDict;
import com.jims.sys.entity.StaffVsGroup;
import com.jims.sys.vo.StaffVsGroupVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangruidong on 2016/5/23.
 */
@MyBatisDao
public interface StaffVsGroupDao extends CrudDao<StaffVsGroup>{
   /* *
     * 根据工作组类的id查询工作组的信息
     * @param orgId
     * @return
     * @author yangruidong
     *
    public List<StaffGroupDict> findListGroupDict(String id) ;*/

    /**
     * 根据组织机构查询用户组的全部信息
     * @param orgId
     * @return
     */
    public List<StaffVsGroupVo> findAllListByOrgId(@Param("orgId") String orgId);

    /**
     *
     *根据组织机构id查询组织机构下的人员
     * @param orgId
     * @return
     */
    public List<StaffVsGroupVo> findOrgStaff(@Param("orgId") String orgId);

    /**
     *
     *根据组id查询组类名称
     * @param groupId
     * @return
     */
    public List<StaffVsGroupVo> findGroupClass(@Param("groupId") String groupId);

    /**
     *
     *根据组id查询组下的人员
     * @param  groupId
     * @return
     */
    public List<StaffVsGroupVo> findStaffByGroupId(@Param("groupId") String groupId,@Param("orgId") String orgId);

    /**
     *
     *根据人员id查询此人员是否在此组中
     * @param  staffId
     * @return
     */
    public StaffVsGroup findStaffByStaffId(@Param("staffId") String staffId);
}
