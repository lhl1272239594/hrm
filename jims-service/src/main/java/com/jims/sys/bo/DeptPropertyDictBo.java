/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.bo;

import com.jims.common.data.StringData;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import com.jims.sys.dao.OrgDeptPropertyDictDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.OrgDeptPropertyDict;
import com.jims.sys.vo.OrgDeptPropertyDictVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangruidong
 * @version 2016-06-16
 */
@Service
@Component
@Transactional(readOnly = false)
public class DeptPropertyDictBo extends CrudImplService<OrgDeptPropertyDictDao, OrgDeptPropertyDict> {

    @Autowired
    private OrgDeptPropertyDictDao orgDeptPropertyDictDao;


    /**
     * 保存  增删改
     *
     * @param orgDeptPropertyDictVo
     * @return
     * @author yangruidong
     */
    public List<OrgDeptPropertyDict> saveAll(OrgDeptPropertyDictVo<OrgDeptPropertyDict> orgDeptPropertyDictVo) {
        List<OrgDeptPropertyDict> newUpdateDict = new ArrayList<OrgDeptPropertyDict>();
        List<OrgDeptPropertyDict> inserted = orgDeptPropertyDictVo.getInserted();
        List<OrgDeptPropertyDict> updated = orgDeptPropertyDictVo.getUpdated();
        List<OrgDeptPropertyDict> deleted = orgDeptPropertyDictVo.getDeleted();
        //插入
        for (OrgDeptPropertyDict orgDeptPropertyDict : inserted) {

            OrgDeptPropertyDict orgDeptPropertyDict1=new OrgDeptPropertyDict();
            orgDeptPropertyDict1.setPropertyType(orgDeptPropertyDict.getPropertyType());
            orgDeptPropertyDict1.setOrgId(orgDeptPropertyDict.getOrgId());

            List<OrgDeptPropertyDict> list = orgDeptPropertyDictDao.findByCondition(orgDeptPropertyDict1);
            //给插入的科室属性进行排序
            OrgDeptPropertyDict sort = orgDeptPropertyDictDao.findSort(orgDeptPropertyDict.getOrgId());
            if (list.size() > 0) {
                orgDeptPropertyDict.setSort(list.get(0).getSort());
            } else {
                if (sort.getSort() == null) {
                    orgDeptPropertyDict.setSort(0L);
                } else {
                    orgDeptPropertyDict.setSort(sort.getSort() + 1);
                }
            }
            orgDeptPropertyDict.preInsert();
            int num = orgDeptPropertyDictDao.insert(orgDeptPropertyDict);
        }
        //更新
        for (OrgDeptPropertyDict orgDeptPropertyDict : updated) {
            orgDeptPropertyDict.preUpdate();
            int num = orgDeptPropertyDictDao.update(orgDeptPropertyDict);
        }
        //删除
        List<String> ids = new ArrayList<String>();
        for (OrgDeptPropertyDict orgDeptPropertyDict : deleted) {
            ids.add(orgDeptPropertyDict.getId());
        }
        for (String id : ids) {
            dao.delete(id);
        }
        return newUpdateDict;
    }

    /**
     * 查询所有的属性类型
     *
     * @return
     */
    public List<OrgDeptPropertyDict> findProperty(String orgId) {
        return dao.findProperty(orgId);
    }

    /**
     * 根据条件查询所有的属性信息
     *
     * @param orgDeptPropertyDict
     * @return
     */
    public List<OrgDeptPropertyDict> findByCondition(OrgDeptPropertyDict orgDeptPropertyDict) {

        return dao.findByCondition(orgDeptPropertyDict);
    }

    /**
     * 查询最大的排序值
     *
     * @return
     */
    public OrgDeptPropertyDict findSort(String orgId) {
        return dao.findSort(orgId);
    }


}