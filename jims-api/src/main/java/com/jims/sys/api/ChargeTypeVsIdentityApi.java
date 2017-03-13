package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.ChargeTypeVsIdentity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface ChargeTypeVsIdentityApi {
    /**
     * 查询字段列表
     * @param page
     * @param chargeTypeVsIdentity
     * @return
     */
    public Page<ChargeTypeVsIdentity> findPage(Page<ChargeTypeVsIdentity> page, ChargeTypeVsIdentity chargeTypeVsIdentity);

    /**
     * 保存修改数据
     * @param chargeTypeVsIdentity
     * @return
     */
    public String save(ChargeTypeVsIdentity chargeTypeVsIdentity);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 查询html类型列表
     * @return
     */
    public List<String> findTypeList();

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public ChargeTypeVsIdentity get(String id);
}
