package com.jims.fbd.hrm.evaluation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.api.DeptStandardApi;
import com.jims.fbd.hrm.evaluation.bo.DeptStandardBo;
import com.jims.fbd.hrm.evaluation.dao.DeptStandardDao;
import com.jims.fbd.hrm.evaluation.vo.DeptConfig;
import com.jims.fbd.hrm.evaluation.vo.Mould;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import com.jims.fbd.hrm.evaluation.vo.TempletStandardVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")

public class DeptStandardImpl extends CrudImplService<DeptStandardDao, DeptConfig> implements DeptStandardApi {

    @Autowired
    private DeptStandardBo deptStandardBo;
    /**
     * 查询模板分类
     * @return
     */
    @Override
    public List<Mould> getMouldType() {
        return deptStandardBo.getMouldType();
    }
    /**
     * 查询模板名称
     * @return
     */
    @Override
    public List<Mould> getMouldName(String id) {
        return deptStandardBo.getMouldName(id);
    }
    /**
     * 导入模板
     */
    @Override
    public String importMould(Mould Mould) {
        return deptStandardBo.importMould(Mould);
    }
    /**
     * 查询标准
     * @return
     */
    @Override
    public List<StandardVo> findListByid(String id) {
        return deptStandardBo.findListByid(id);
    }
    /**
     * 删除已有标准
     * @return
     */
    @Override
    public void delStandard(String id, String deptId) {
        deptStandardBo.delStandard(id,deptId);
    }
    /**
     * 根据科室查询标准
     * @return
     */
    @Override
    public List<StandardVo> standardByProject(StandardVo standardVo) {
        return deptStandardBo.standardByProject(standardVo);
    }
    /**
     * 保存考评标准
     */
    @Override
    public String saveStandard(TempletStandardVo t) {
        return deptStandardBo.saveStandard(t);
    }
    /**
     * 修改标准
     */
    @Override
    public String editStandard(StandardVo standardVo) {
        return deptStandardBo.editStandard(standardVo);
    }
}
