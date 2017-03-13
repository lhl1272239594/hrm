package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.api.InputSettingServiceApi;
import com.jims.sys.api.StaffGroupApi;
import com.jims.sys.bo.StaffGroupBo;
import com.jims.sys.dao.InputSettingDetailDao;
import com.jims.sys.dao.InputSettingMasterDao;
import com.jims.sys.dao.StaffGroupClassDictDao;
import com.jims.sys.dao.StaffGroupDictDao;
import com.jims.sys.entity.InputSettingDetail;
import com.jims.sys.entity.InputSettingMaster;
import com.jims.sys.entity.StaffGroupClassDict;
import com.jims.sys.entity.StaffGroupDict;
import com.jims.sys.vo.InputInfoVo;
import com.jims.sys.vo.InputParamVo;
import com.jims.sys.vo.InputSettingVo;
import com.jims.sys.vo.StaffGroupVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

/**
 *  用户工作组Service
 * Created by yangruidong on 2016/5/18 .
 */
@Service(version = "1.0.0")

public class StaffGroupImpl  implements StaffGroupApi {


    @Autowired
    private StaffGroupBo staffGroupBo;

    /**
     * 保存  增删改   工作组类
     * @param staffGroupClassDictVo
     * @return
     *  @author  yangruidong
     */
    @Override

    public List<StaffGroupClassDict> saveGroupClass(StaffGroupVo<StaffGroupClassDict> staffGroupClassDictVo) {
      return staffGroupBo.saveGroupClass(staffGroupClassDictVo);
    }


    /**
     * 保存  增删改   工作组
     * @param staffGroupDictVo
     * @return
     *  @author  yangruidong
     */
    @Override

    public List<StaffGroupDict> saveGroup(StaffGroupVo<StaffGroupDict> staffGroupDictVo) {
       return staffGroupBo.saveGroup(staffGroupDictVo);
    }

    /**
     * 根据组织机构id查询工作组类的全部信息
     * @param orgId 组织机构id
     * @return
     * @author yangruidong
     */
    @Override
    public List<StaffGroupClassDict> findAllListByOrgId(String orgId,String q) {
        return staffGroupBo.findAllListByOrgId(orgId,q);
    }

    /**
     * 根据工作组类的id查询工作组的信息
     * @param id
     * @return
     * @author yangruidong
     */
    @Override
    public List<StaffGroupDict> findListGroupDict(String id,String q) {
        return staffGroupBo.findListGroupDict(id,q);
    }



}
