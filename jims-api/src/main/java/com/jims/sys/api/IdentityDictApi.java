package com.jims.sys.api;

import com.jims.common.data.StringData;
import com.jims.sys.entity.IdentityDict;
import com.jims.sys.vo.BeanChangeVo;

import java.util.List;

/**
 * 身份字典表api接口
 * Created by fyg on 2016/6/21.
 */
public interface IdentityDictApi {

    /**
     * 查询所有记录
     * @param orgId 所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<IdentityDict> findList(String orgId);

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<IdentityDict> beanChangeVo);

    /**
     * 根据名称查询记录
     * @param identityName 身份名称
     * @param orgId        所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<IdentityDict> search(String identityName, String orgId);
}
