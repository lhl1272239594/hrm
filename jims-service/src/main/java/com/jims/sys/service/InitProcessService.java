package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.InitProcessApi;
import com.jims.sys.bo.InitProcessBo;
import com.jims.sys.dao.InitProcessDao;
import com.jims.sys.entity.InitProcess;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 药品库存Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")
public class InitProcessService extends CrudImplService<InitProcessDao, InitProcess> implements InitProcessApi {

    @Autowired
    private InitProcessBo bo;

    @Override
    public String saveList(List<InitProcess> initProcesses) {
        return bo.saveList(initProcesses);
    }

    /**
     * 根据orgId查询
     * @param orgId
     * @return
     * @author  zq
     */
    @Override
    public List<InitProcess> findByOrgId(String orgId) {
        return bo.findByOrgId(orgId);
    }

    /**
     * 根据菜单表数据生成initProcess表数据
     * @author zq
     */
    @Override
    public List<InitProcess> findMenuList() {
        return bo.findMenuList();
    }

    /**
     * 删除
     * @author zq
     */
    @Override
    public String delete(String id) {
        return bo.delete(id);
    }
}