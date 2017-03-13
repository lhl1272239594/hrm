package com.jims.sys.api;

import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.entity.ChargeTypeDict;
import com.jims.sys.vo.BeanChangeVo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface ChargeTypeDictApi {

    /**
     * 查询某个组织机构的费别列表
     * @param orgId  组织机构ID
     * @param page  分页
     * @param chargeTypeDict
     * @return
     * @author fengyuguang
     */
    public Page<ChargeTypeDict> findPage(String q,String orgId,Page<ChargeTypeDict> page,ChargeTypeDict chargeTypeDict);

    /**
     * 根据名称模糊查询数据
     * @param chargeTypeName 费别名称
     * @param orgId  组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<ChargeTypeDict> search(String chargeTypeName,String orgId);

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<ChargeTypeDict> beanChangeVo);

    /**
     * 查询某个组织机构的费别列表
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<ChargeTypeDict> listAll(String orgId);

    /**
     * 查询字段列表
     * @param page
     * @param chargeTypeDict
     * @return
     */
    public Page<ChargeTypeDict> findPage(Page<ChargeTypeDict> page, ChargeTypeDict chargeTypeDict);

    /**
     * 保存修改数据
     * @param chargeTypeDict
     * @return
     */
    public String save(ChargeTypeDict chargeTypeDict);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public ChargeTypeDict get(String id);

    /**
     * 查询html类型列表
     * @return
     */
    public List<String> findTypeList();


}
