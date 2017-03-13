package com.jims.fbd.hrm.evaluation.api;

import com.jims.fbd.hrm.evaluation.vo.Mould;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import com.jims.fbd.hrm.evaluation.vo.TempletStandardVo;

import java.util.List;

public interface DeptStandardApi {
    /**
     * 查询模板分类
     * @return
     */
    public List<Mould> getMouldType();
    /**
     * 查询模板名称
     * @return
     */
    public List<Mould> getMouldName(String id);
    /**
     * 导入模板
     */
    public String importMould(Mould Mould);
    /**
     * 查询标准
     * @return
     */
    public List<StandardVo> findListByid(String id);
    /**
     * 删除已有标准
     * @return
     */
    public void delStandard(String id, String deptId);
    /**
     * 根据科室查询标准
     * @return
     */
    public List<StandardVo> standardByProject(StandardVo standardVo);
    /**
     * 保存考评标准
     */
    public String saveStandard(TempletStandardVo t);
    /**
     * 修改标准
     */
    public String editStandard(StandardVo standardVo);
}
