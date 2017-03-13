package com.jims.fbd.hrm.employ.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.employ.api.EmployApi;
import com.jims.fbd.hrm.employ.bo.EmployBo;
import com.jims.fbd.hrm.employ.dao.EmployDao;
import com.jims.fbd.hrm.employ.entity.Employ;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 招聘管理imp层
 *
 * @author 
 * @version 2016-08-31
 */
@Service(version = "1.0.0")
public class EmployImpl extends CrudImplService<EmployDao,Employ> implements EmployApi {
    @Autowired
    private EmployBo employBo;

    /**
     * 查询是否重复
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<Employ> findEmploysame(String orgId, String name) {
        return employBo.findEmploysame(orgId,name);
    }

    /**
     * 保存修改
     * @return
     * @author 
     * @version 2016-05-31
     */
    @Override
    public String merge(Employ employ, String createDept) {
        return employBo.merge(employ,createDept);
    }
    /**
     * 删除
     * @param employs
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String del_employ(List<Employ> employs) {
        return employBo.delete(employs);
    }
    /**
     * 发布
     * @param employs
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String publish_employ(List<Employ> employs, String userName) {
        return employBo.publish_employ(employs,userName);
    }
    /**
     * 结束
     * @param employs
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String end_employ(List<Employ> employs) {
        return employBo.end_employ(employs);
    }
    /**
     * 重新发布
     * @param employs
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String redeal(List<Employ> employs, String userName) {
        return employBo.redeal(employs,userName);
    }
    /**
     * 查询发布招聘
     * @return
     * @author 
     */
    @Override
    public Page<Employ> employList(Page<Employ> page, Employ employ) {
        return employBo.employList(page,employ);
    }

    /**
     * 查询招聘信息
     * @return
     * @author 
     */
    @Override
    public Page<Employ> employSearch(Page<Employ> page, Employ employ) {
        return employBo.employSearch(page,employ);
    }

}
