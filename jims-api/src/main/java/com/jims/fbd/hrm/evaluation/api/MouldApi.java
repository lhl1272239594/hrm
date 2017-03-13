package com.jims.fbd.hrm.evaluation.api;

import com.jims.fbd.hrm.evaluation.vo.Mould;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import com.jims.fbd.hrm.evaluation.vo.TempletStandardVo;

import java.util.List;

public interface MouldApi {
    /**
     * 查询模板
     * @return
     * @param id
     */
    public List<Mould> getTemplet(String id);
    /**
     * 新增修改模板
     * @param mould
     * @return
     */
    public String Merge(Mould mould);
    /**
     * 删除模板
     * @param moulds
     * @return
     */
    public String delMould(List<Mould> moulds);
    /**
     * 查询模板标准
     * @return
     */
    public List<StandardVo> findListByid(String id);
    /**
     * 查询模板类型
     * @return
     */
    public List<Mould> getType();
    /**
     * 保存考评标准
     * @param t
     * @return
     */
    public String saveStandard(TempletStandardVo t);
    /**
     * 删除考评标准
     *
     * @param t
     * @return
     */
    public String delStandardById(TempletStandardVo t);
    /**
     * 根据项目查询标准
     * @return
     */
    public List<StandardVo> standardByProject(StandardVo standardVo);
}
