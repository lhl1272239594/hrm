package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.dao.OrgSelfServiceListDao;
import com.jims.register.dao.OrgSelfServiceVsMenuDao;
import com.jims.register.dao.OrgServiceListDao;
import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;
import com.jims.sys.api.SysCompanyApi;
import com.jims.sys.dao.ServiceVsMenuDao;
import com.jims.sys.bo.SysCompanyBo;
import com.jims.sys.dao.SysCompanyDao;
import com.jims.sys.dao.SysServiceDao;
import com.jims.sys.entity.ServiceVsMenu;
import com.jims.sys.entity.SysCompany;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
@Service(version = "1.0.0")
public class SysCompanyImpl extends CrudImplService<SysCompanyDao, SysCompany> implements SysCompanyApi {
    @Autowired
    private SysCompanyDao sysCompanyDao;
    @Autowired
    private SysCompanyBo bo;
    /**
     * 根据申请状态查询组织机构列表
     * @param applyStatus 申请状态
     * @return 组织机构list集合
     * @author fengyuguang
     */
    public List<SysCompany> findListByApplyStatus(String applyStatus){
        return dao.findListByApplyStatus(applyStatus);
    }



    /**
     * 查询orgId和其子机构对应的机构信息
     * @param orgId
     * @return 组织机构list集合
     * @author zhuqi
     */
    public List<SysCompany> findListByParentId(String orgId){
        return bo.findListByParentId(orgId);
    }

    /**
     * 查询用户注册的DEL_FLAG='0'的所有机构List
     * @param owner
     * @return 组织机构list集合
     * @author 娄会丽
     */
    public List<SysCompany> findAllByOwner(String owner){
        return bo.findAllByOwner(owner);
    }
    /**
     * 根据机构所属者和组织机构名称查询信息
     * @param sysCompany
     * @author 娄会丽
     * @return
     */
    public List<SysCompany> findIsNoByOwner(SysCompany sysCompany){
        return bo.findIsNoByOwner(sysCompany);
    }

    /**
     * 查询父机构名称
     * @return
     */
    @Override
    public List<SysCompany> findListByName(String persionId) {
        return dao.findListByName(persionId);
    }

    /**
     * 查询组织名称是否存在
     * @param orgName
     * @return
     */
    @Override
    public SysCompany findByName(String orgName) {
        return dao.findByName(orgName);
    }

    /**
     * 保存方法（返回id）
     *
     * @param sysCompany
     */
    @Override
    public String insertReturnId(SysCompany sysCompany) {
        sysCompany.preInsert();//添加日期
        int i = dao.insertReturnId(sysCompany);
        String id = sysCompany.getId();
        return id;
    }

    /**
     * 组织机构通过审核
     * @param sysCompany
     * @return
     * @author fengyuguang
     */
    public int update(SysCompany sysCompany) {
        return bo.update(sysCompany);
    }

    /**
     * 驳回组织机构审核
     * @param sysCompany
     * @return
     */
    public int failPass(SysCompany sysCompany) {
        return bo.failPass(sysCompany);
    }

    /**
     * 保存注册信息以及选择的服务
     * @param company
     * @return 1 成功 ,0 失败
     */
    public String saveCompanyAndService(SysCompany company){
        String result = "0";
        try{
            bo.saveCompanyAndService(company);
            result = "1";
        } catch (Exception e){
        }
        return result;
    }

    public List<SysCompany> findList(SysCompany company){
        return bo.findList(company);
    }
}
