package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.StaffGroupApi;
import com.jims.sys.api.StaffVsGroupApi;
import com.jims.sys.bo.StaffVsGroupBo;
import com.jims.sys.dao.StaffGroupClassDictDao;
import com.jims.sys.dao.StaffGroupDictDao;
import com.jims.sys.dao.StaffVsGroupDao;
import com.jims.sys.entity.StaffGroupClassDict;
import com.jims.sys.entity.StaffGroupDict;
import com.jims.sys.entity.StaffVsGroup;
import com.jims.sys.vo.StaffGroupVo;
import com.jims.sys.vo.StaffVsGroupVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

/**
 * 科室分组人员维护Service
 * Created by yangruidong on 2016/5/25 .
 */
@Service(version = "1.0.0")

public class StaffVsGroupImpl extends CrudImplService<StaffVsGroupDao, StaffVsGroup> implements StaffVsGroupApi {

    @Autowired
    private StaffVsGroupBo staffVsGroupBo;
    @Override
    public List<StaffVsGroup> saveVsGroup(StaffVsGroupVo<StaffVsGroup> staffVsGroupVo) throws Exception {

        return staffVsGroupBo.saveVsGroup(staffVsGroupVo);
    }

    /**
     * 根据组织机构查询用户组的全部信息
     *
     * @param orgId
     * @return
     * @author yangruidong
     */
    @Override
    public List<StaffVsGroupVo> findAllListByOrgId(String orgId) {
        return staffVsGroupBo.findAllListByOrgId(orgId);
    }

    /**
     *
     *根据组织机构id查询组织机构下的人员
     * @param orgId
     * @return
     */
    @Override
    public List<StaffVsGroupVo> findOrgStaff(String orgId) {
        return staffVsGroupBo.findOrgStaff(orgId);
    }

    /**
     *
     *根据组id查询组类名称
     * @param id
     * @return
     */
    @Override
    public List<StaffVsGroupVo> findGroupClass(String id) {
        return staffVsGroupBo.findGroupClass(id);
    }

    /**
     *
     *根据组id查询组下的人员
     * @param  groupId
     * @return
     */
    @Override
    public List<StaffVsGroupVo> findStaffByGroupId(String groupId,String orgId){
        return staffVsGroupBo.findStaffByGroupId(groupId,orgId);
    }

    /**
     *
     *根据人员id查询此人员是否在此组中
     * @param  staffId
     * @return
     */
    @Override
    public StaffVsGroup findStaffByStaffId(String staffId) {
        return staffVsGroupBo.findStaffByStaffId(staffId);
    }

}
