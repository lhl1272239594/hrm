package com.jims.sys.api;

import com.jims.sys.entity.StaffGroupClassDict;
import com.jims.sys.entity.StaffGroupDict;
import com.jims.sys.entity.StaffVsGroup;
import com.jims.sys.vo.StaffGroupVo;
import com.jims.sys.vo.StaffVsGroupVo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface StaffVsGroupApi {
    /**
     *
      * @param staffVsGroupVo
     * @return
     */
    public List<StaffVsGroup> saveVsGroup(StaffVsGroupVo<StaffVsGroup> staffVsGroupVo) throws Exception;

    /**
     * 根据组织机构查询用户组的全部信息
     * @param orgId
     * @return
     * @author yangruidong
     */
    public List<StaffVsGroupVo> findAllListByOrgId(String orgId );

    /**
     *
     *根据组织机构id查询组织机构下的人员
     * @param orgId
     * @return
     */
    public List<StaffVsGroupVo> findOrgStaff(String orgId);

    /**
     *
     *根据组id查询组类名称
     * @param groupId
     * @return
     */
    public  List<StaffVsGroupVo> findGroupClass(String groupId);

    /**
     *
     *根据组id查询组下的人员
     * @param  groupId
     * @return
     */
    public List<StaffVsGroupVo> findStaffByGroupId(String groupId,String orgId);

    /**
     *
     *根据人员id查询此人员是否在此组中
     * @param  staffId
     * @return
     */
    public StaffVsGroup findStaffByStaffId(String staffId);
}
