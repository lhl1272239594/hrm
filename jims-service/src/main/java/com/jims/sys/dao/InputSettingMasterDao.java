/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.InputSettingMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 输入法字典主记录DAO接口
 * @author yangruidong
 * @version 2016-05-118
 */
@MyBatisDao
public interface InputSettingMasterDao extends CrudDao<InputSettingMaster> {
    /**
     * 根据组织机构id查询输入法字典的全部信息
     * @param orgId 组织机构id
     * @return
     * @author yangruidong
     */
    public List<InputSettingMaster> findAllListByOrgId(String orgId);
}