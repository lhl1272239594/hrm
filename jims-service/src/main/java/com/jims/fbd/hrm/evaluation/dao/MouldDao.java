package com.jims.fbd.hrm.evaluation.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.evaluation.api.MouldApi;
import com.jims.fbd.hrm.evaluation.vo.DeptConfig;
import com.jims.fbd.hrm.evaluation.vo.Mould;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface MouldDao extends CrudDao<Mould> {
    /**
     * 查询模板
     * @return
     * @param id
     */
    public List<Mould> getTemplet(String id);
    /**
     * 查询模板名称
     * @return
     */
    public int getName(Mould mould);
    /**
     * 查询模板标准
     * @return
     */
    public List<StandardVo> findListByid(String id);
    /**
     * 按照模板ID删除模板标准
     * @return
     */
    public void delStandard(Mould m);
    /**
     * 查询模板类型
     * @return
     */
    public List<Mould> getType();
    /**
     * 保存考评标准
     * @param s
     * @return
     */
    public void saveStandard(@Param("StandardVo" ) StandardVo s);
    /**
     * 删除模板标准
     *
     * @param s
     * @return
     */
    public void delStandardById(StandardVo s);
    /**
     * 根据项目查询标准
     * @return
     */
    public List<StandardVo> standardByProject(StandardVo standardVo);
}
