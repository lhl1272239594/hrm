/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.SpecimanDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标本字典DAO接口
 * @author xueyx
 * @version 2016-05-04
 */
@MyBatisDao
public interface SpecimanDictDao extends CrudDao<SpecimanDict> {
    /**
     * 查询科室代码下的检验标本
     * @return
     */
    public List<SpecimanDict> findListByDeptCode(@Param("deptCode")String deptCode);
}