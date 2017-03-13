package com.jims.fbd.hrm.evaluation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.api.MouldApi;
import com.jims.fbd.hrm.evaluation.bo.MouldBo;
import com.jims.fbd.hrm.evaluation.dao.MouldDao;
import com.jims.fbd.hrm.evaluation.vo.Mould;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import com.jims.fbd.hrm.evaluation.vo.TempletStandardVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")

public class MouldImpl extends CrudImplService<MouldDao, Mould> implements MouldApi {

    @Autowired
    private MouldBo mouldBo;
    /**
     * 查询模板
     * @return
     * @param id
     */
    @Override
    public List<Mould> getTemplet(String id) {
        return mouldBo.getTemplet(id);
    }
    /**
     * 新增修改模板
     * @param mould
     * @return
     */
    @Override
    public String Merge(Mould mould) {
        return mouldBo.Merge(mould);
    }
    /**
     * 删除模板
     * @param moulds
     * @return
     */
    @Override
    public String delMould(List<Mould> moulds) {
        return mouldBo.delMould(moulds);
    }
    /**
     * 查询模板标准
     * @return
     */
    @Override
    public List<StandardVo> findListByid(String id) {
        return mouldBo.findListByid(id);
    }
    /**
     * 查询模板类型
     * @return
     */
    @Override
    public List<Mould> getType() {
        return mouldBo.getType();
    }
    /**
     * 保存考评标准
     * @param t
     * @return
     */
    @Override
    public String saveStandard(TempletStandardVo t) {
        return mouldBo.saveStandard(t);
    }
    /**
     * 删除考评标准
     *
     * @param t
     * @return
     */
    @Override
    public String delStandardById(TempletStandardVo t) {
        return mouldBo.delStandardById(t);
    }
    /**
     * 根据项目查询标准
     * @return
     */
    @Override
    public List<StandardVo> standardByProject(StandardVo standardVo) {
        return mouldBo.standardByProject(standardVo);
    }
}
