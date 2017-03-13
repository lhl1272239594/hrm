package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.StaffVsGroupDao;
import com.jims.sys.entity.StaffVsGroup;
import com.jims.sys.vo.StaffVsGroupVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txb on 2016-06-17.
 */
@Service
@Component
@Transactional(readOnly = false)
public class StaffVsGroupBo extends CrudImplService<StaffVsGroupDao,StaffVsGroup> {


    public List<StaffVsGroup> saveVsGroup(StaffVsGroupVo<StaffVsGroup> staffVsGroupVo) throws Exception{
        List<StaffVsGroup> newUpdateDict = new ArrayList<StaffVsGroup>();
        List<StaffVsGroup> inserted = staffVsGroupVo.getInserted();
        List<StaffVsGroup> updated = staffVsGroupVo.getUpdated();
        List<StaffVsGroup> deleted = staffVsGroupVo.getDeleted();
        //插入
        for (StaffVsGroup staffVsGroup : inserted) {
            staffVsGroup.preInsert();
            staffVsGroup.setGroupClass(staffVsGroupVo.getGroupClass());
            staffVsGroup.setGroupCode(staffVsGroupVo.getGroupCode());
            staffVsGroup.setGroupId(staffVsGroupVo.getGroupId());
            int num = dao.insert(staffVsGroup);
        }
        //更新
        for (StaffVsGroup staffVsGroup : updated) {
            staffVsGroup.preUpdate();
            staffVsGroup.setGroupClass(staffVsGroupVo.getGroupClass());
            staffVsGroup.setGroupCode(staffVsGroupVo.getGroupCode());
            staffVsGroup.setGroupId(staffVsGroupVo.getGroupId());
            int num = dao.update(staffVsGroup);
        }
        //删除
        List<String> ids = new ArrayList<String>();

        for (StaffVsGroup staffVsGroup: deleted) {
            ids.add(staffVsGroup.getId());
        }
        for (String id : ids) {
            dao.delete(id);
        }
        return newUpdateDict;
    }

    /**
     * 根据组织机构查询用户组的全部信息
     *
     * @param orgId
     * @return
     * @author yangruidong
     */

    public List<StaffVsGroupVo> findAllListByOrgId(String orgId) {
        return dao.findAllListByOrgId(orgId);
    }

    /**
     *
     *根据组织机构id查询组织机构下的人员
     * @param orgId
     * @return
     */

    public List<StaffVsGroupVo> findOrgStaff(String orgId) {
        return dao.findOrgStaff(orgId);
    }

    /**
     *
     *根据组id查询组类名称
     * @param id
     * @return
     */

    public List<StaffVsGroupVo> findGroupClass(String id) {
        return dao.findGroupClass(id);
    }

    /**
     *
     *根据组id查询组下的人员
     * @param  groupId
     * @return
     */

    public List<StaffVsGroupVo> findStaffByGroupId(String groupId,String orgId){
        return dao.findStaffByGroupId(groupId,orgId);
    }

    /**
     *
     *根据人员id查询此人员是否在此组中
     * @param  staffId
     * @return
     */

    public StaffVsGroup findStaffByStaffId(String staffId) {
        return dao.findStaffByStaffId(staffId);
    }

}
