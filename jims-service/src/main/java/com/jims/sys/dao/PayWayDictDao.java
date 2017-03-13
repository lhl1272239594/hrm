package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.PayWayDict;

import java.util.List;

/**
 * 支付方式字典表dao层
 * Created by fyg on 2016/6/23.
 */
@MyBatisDao
public interface PayWayDictDao extends CrudDao<PayWayDict> {

    /**
     * 根据组织机构ID和住院病人适用标志查询数据
     * @param orgId  组织机构ID
     * @param inpIndicator 住院病人适用标志
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> getByInpIndicator(String orgId, Integer inpIndicator);

    /**
     * 查询所有支付方式
     * @param orgId 所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> findList(String orgId);

    /**
     * 查询医保类别为空的支付方式
     * @param orgId  组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> listByNullChargeType(String orgId);

    /**
     * 根据支付方式名称模糊查询记录
     * @param payWayName 支付方式名称
     * @param orgId  所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> search(String payWayName, String orgId);
}
