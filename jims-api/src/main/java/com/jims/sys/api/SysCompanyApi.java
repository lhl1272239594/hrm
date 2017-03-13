package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.SysCompany;

import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public interface SysCompanyApi {

    /**
     * 根据申请状态查询组织机构列表
     * @param applyStatus 申请状态
     * @return 组织机构list集合
     * @author fengyuguang
     */
    public List<SysCompany> findListByApplyStatus(String applyStatus);

    /**
     * 查询orgId和其子机构对应的机构信息
     * @param orgId
     * @return 组织机构list集合
     * @author zhuqi
     */
    public List<SysCompany> findListByParentId(String orgId);

    /**
     * 查询用户注册的DEL_FLAG='0'的所有机构List
     * @param owner
     * @return 组织机构list集合
     * @author 娄会丽
     */
    public List<SysCompany> findAllByOwner(String owner);
    /**
     * 根据机构所属者和组织机构名称查询信息
     * @param sysCompany
     * @author 娄会丽
     * @return
     */
    public List<SysCompany> findIsNoByOwner(SysCompany sysCompany);
    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public SysCompany get(String id);

    /**
     * 组织机构通过审核
     * @param sysCompany
     * @return
     * @author fengyuguang
     */
    public int update(SysCompany sysCompany);

    /**
     * 驳回组织机构审核
     * @param sysCompany
     * @return
     * @author fengyuguang
     */
    public int failPass(SysCompany sysCompany);

    /**
     * 查询组织机构列表
     *
     * @return
     */
    public Page<SysCompany> findPage(Page<SysCompany> page, SysCompany sysCompany);

    /**
     * 创建组织机构
     *
     * @param sysCompany
     * @return
     */
    public String save(SysCompany sysCompany);

    public String insertReturnId(SysCompany sysCompany);

    /**
     * 查询父机构
     * @return
     */
    public List<SysCompany> findListByName(String persionId);

    /**
     * 查询组织机构名称是否存在
     * @param orgName
     * @return
     */
    public SysCompany findByName(String orgName);

    /**
     * 根据创建人查询组织机构的名称
     * @return
     */
   // public SysCompany findNameByCreateBy();

    /**
     * 保存注册信息以及选择的服务
     * @param company
     * @return 1 成功 ,0 失败
     */
    public String saveCompanyAndService(SysCompany company);

    public List<SysCompany> findList(SysCompany company);
}
