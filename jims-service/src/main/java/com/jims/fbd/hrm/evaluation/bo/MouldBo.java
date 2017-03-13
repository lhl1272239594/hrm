package com.jims.fbd.hrm.evaluation.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.dao.DeptConfigDao;
import com.jims.fbd.hrm.evaluation.dao.EvaluationDao;
import com.jims.fbd.hrm.evaluation.dao.MouldDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class MouldBo extends CrudImplService<MouldDao,Mould> {
    /**
     * 查询模板
     * @return
     * @param id
     */
    public List<Mould> getTemplet(String id) {
        return dao.getTemplet(id);
    }
    /**
     * 新增修改模板
     *
     * @param mould
     * @return
     */
    public String Merge(Mould mould) {
        int num=dao.getName(mould);
        if(num>0){
            return "hasName";
        }
        if (!mould.getId().equals("")){
            mould.preUpdate();
            dao.update(mould);
            return "success";
        }
        else{
            mould.preInsert();
            dao.insert(mould);
            return "success";
        }
    }
    /**
     * 删除模板
     * @param moulds
     * @return
     */
    public String delMould(List<Mould> moulds) {
        for(Mould m:moulds){
            dao.delStandard(m);
            dao.delete(m);
        }
        return "success";
    }
    /**
     * 查询模板标准
     * @return
     */
    public List<StandardVo> findListByid(String id) {
        return dao.findListByid(id);
    }
    /**
     * 查询模板类型
     * @return
     */
    public List<Mould> getType() {
        return dao.getType();
    }
    /**
     * 保存考评标准
     * @param t
     * @return
     */
    public String saveStandard(TempletStandardVo t) {
        String templetId=t.getId();
        for(StandardVo s:t.getStandardVos()){
            s.setCode(s.getId());
            s.setTempletId(templetId);
            s.preInsert();
            dao.saveStandard(s);
        }
        return "success";
    }
    /**
     * 删除考评标准
     *
     * @param t
     * @return
     */
    public String delStandardById(TempletStandardVo t) {
        String templetId=t.getId();
        for(StandardVo s:t.getStandardVos()){
            s.setCode(s.getId());
            s.setTempletId(templetId);
            dao.delStandardById(s);
        }
        return "success";
    }
    /**
     * 根据项目查询标准
     * @return
     */
    public List<StandardVo> standardByProject(StandardVo standardVo) {
        return dao.standardByProject(standardVo);
    }
}
