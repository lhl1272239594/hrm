package com.jims.fbd.hrm.evaluation.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.dao.DeptConfigDao;
import com.jims.fbd.hrm.evaluation.dao.DeptStandardDao;
import com.jims.fbd.hrm.evaluation.vo.DeptConfig;
import com.jims.fbd.hrm.evaluation.vo.Mould;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import com.jims.fbd.hrm.evaluation.vo.TempletStandardVo;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class DeptStandardBo extends CrudImplService<DeptStandardDao,DeptConfig> {
    /**
     * 查询模板分类
     * @return
     */
    public List<Mould> getMouldType() {
        return dao.getMouldType();
    }
    /**
     * 查询模板名称
     * @return
     */
    public List<Mould> getMouldName(String id) {
        return dao.getMouldName(id);
    }
    /**
     * 导入模板
     */
    public String importMould(Mould Mould) {
        List<PersonVo> dept=Mould.getDept();
        String id=Mould.getId();
        for(PersonVo p:dept){
            String deptId=p.getId();
            dao.delStandard("",deptId);
            dao.importMould(id,deptId);
        }
        return "success";
    }
    /**
     * 查询标准
     * @return
     */
    public List<StandardVo> findListByid(String id) {
        return dao.findListByid(id);
    }
    /**
     * 删除已有标准
     * @return
     */
    public void delStandard(String id, String deptId) {
        dao.delStandard(id,deptId);
    }
    /**
     * 根据科室查询标准
     * @return
     */
    public List<StandardVo> standardByProject(StandardVo standardVo) {
        return dao.standardByProject(standardVo);
    }
    /**
     * 保存考评标准
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
     * 修改标准
     */
    public String editStandard(StandardVo standardVo) {
        dao.editStandard(standardVo);
        return "success";
    }
}
