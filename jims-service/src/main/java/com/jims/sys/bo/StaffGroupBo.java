package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.StaffGroupClassDictDao;
import com.jims.sys.dao.StaffGroupDictDao;
import com.jims.sys.dao.StaffVsGroupDao;
import com.jims.sys.entity.StaffGroupClassDict;
import com.jims.sys.entity.StaffGroupDict;
import com.jims.sys.entity.StaffVsGroup;
import com.jims.sys.vo.StaffGroupVo;
import com.jims.sys.vo.StaffVsGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangruidong on 2016-07-19.
 */
@Service
@Component
@Transactional(readOnly = false)
public class StaffGroupBo extends CrudImplService<StaffGroupDictDao,StaffGroupDict> {



    @Autowired
    private StaffGroupClassDictDao staffGroupClassDictDao;

    /**
     * 保存  增删改   工作组类
     * @param staffGroupClassDictVo
     * @return
     *  @author  yangruidong
     */

    public List<StaffGroupClassDict> saveGroupClass(StaffGroupVo<StaffGroupClassDict> staffGroupClassDictVo) {
        List<StaffGroupClassDict> newUpdateDict = new ArrayList<StaffGroupClassDict>();
        List<StaffGroupClassDict> inserted = staffGroupClassDictVo.getInserted();
        List<StaffGroupClassDict> updated = staffGroupClassDictVo.getUpdated();
        List<StaffGroupClassDict> deleted = staffGroupClassDictVo.getDeleted();
        //插入
        for (StaffGroupClassDict staffGroupClassDict : inserted) {
            staffGroupClassDict.preInsert();
            staffGroupClassDict.setOrgId(staffGroupClassDictVo.getOrgId());
            int num = staffGroupClassDictDao.insert(staffGroupClassDict);
        }
        //更新
        for (StaffGroupClassDict staffGroupClassDict : updated) {
            staffGroupClassDict.preUpdate();
            int num = staffGroupClassDictDao.update(staffGroupClassDict);
        }
        //删除
        List<String> ids = new ArrayList<String>();

        for (StaffGroupClassDict staffGroupClassDict : deleted) {
            ids.add(staffGroupClassDict.getId());
        }
        for (String id : ids) {
            staffGroupClassDictDao.delete(id);
        }
        return newUpdateDict;
    }


    /**
     * 保存  增删改   工作组
     * @param staffGroupDictVo
     * @return
     *  @author  yangruidong
     */

    public List<StaffGroupDict> saveGroup(StaffGroupVo<StaffGroupDict> staffGroupDictVo) {
        List<StaffGroupDict> newUpdateDict = new ArrayList<StaffGroupDict>();
        List<StaffGroupDict> inserted = staffGroupDictVo.getInserted();
        List<StaffGroupDict> updated = staffGroupDictVo.getUpdated();
        List<StaffGroupDict> deleted = staffGroupDictVo.getDeleted();
        //插入
        for (StaffGroupDict staffGroupDict : inserted) {
            staffGroupDict.preInsert();
            staffGroupDict.setDeptCode(staffGroupDict.getGroupCode());
            staffGroupDict.setDeptName(staffGroupDict.getGroupName());
            int num = dao.insert(staffGroupDict);
        }
        //更新
        for (StaffGroupDict staffGroupDict : updated) {
            staffGroupDict.preUpdate();
            staffGroupDict.setDeptCode(staffGroupDict.getGroupCode());
            staffGroupDict.setDeptName(staffGroupDict.getGroupName());
            int num = dao.update(staffGroupDict);
        }
        //删除
        List<String> ids = new ArrayList<String>();

        for (StaffGroupDict staffGroupDict: deleted) {
            ids.add(staffGroupDict.getId());
        }
        for (String id : ids) {
            dao.delete(id);
        }
        return newUpdateDict;
    }

    /**
     * 根据组织机构id查询工作组类的全部信息
     * @param orgId 组织机构id
     * @return
     * @author yangruidong
     */
    public List<StaffGroupClassDict> findAllListByOrgId(String orgId,String q) {
        return staffGroupClassDictDao.findAllListByOrgId(orgId,q);
    }

    /**
     * 根据工作组类的id查询工作组的信息
     * @param id
     * @return
     * @author yangruidong
     */
    public List<StaffGroupDict> findListGroupDict(String id,String q) {
        return dao.findListGroupDict(id,q);
    }




}
