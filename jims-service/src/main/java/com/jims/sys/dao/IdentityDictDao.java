package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.IdentityDict;

import java.util.List;

/**
 * 身份字典表dao层
 * Created by fyg on 2016/6/21.
 */
@MyBatisDao
public interface IdentityDictDao extends CrudDao<IdentityDict> {

    /**
     * 查询所有记录
     * @param orgId 所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<IdentityDict> findList(String orgId);

    /**
     * 根据身份名称模糊查询记录
     * @param identityName 身份名称
     * @param orgId        所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<IdentityDict> search(String identityName, String orgId);
}
