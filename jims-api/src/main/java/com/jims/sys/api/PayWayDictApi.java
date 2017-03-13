package com.jims.sys.api;

import com.jims.sys.entity.PayWayDict;
import com.jims.sys.vo.BeanChangeVo;

import java.util.List;

/**
 * 支付方式字典表Api接口
 * Created by fyg on 2016/6/23.
 */
public interface PayWayDictApi {

    /**
     * 根据组织机构ID和住院病人适用标志查询数据
     * @param orgId 组织机构ID
     * @param inpIndicator 住院病人适用标志
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> getByInpIndicator(String orgId,Integer inpIndicator);

    /**
     * 查询所有支付方式
     * @param orgId 所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> findList(String orgId);

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<PayWayDict> beanChangeVo);

    /**
     * 根据名称查询记录
     * @param payWayName 支付方式名称
     * @param orgId  所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> search(String payWayName, String orgId);
}
