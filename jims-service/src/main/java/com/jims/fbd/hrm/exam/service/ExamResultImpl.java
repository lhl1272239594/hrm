package com.jims.fbd.hrm.exam.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.exam.api.ExamResultApi;
import com.jims.fbd.hrm.exam.bo.ExamResultBo;
import com.jims.fbd.hrm.exam.dao.ExamResultDao;
import com.jims.fbd.hrm.exam.vo.ExamResultVo;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")

public class ExamResultImpl extends CrudImplService<ExamResultDao, ExamResultVo> implements ExamResultApi {

    @Autowired
    private ExamResultBo examResultBo;
        /**
     * 查询考试结果
     * @param page
     * @param ExamResultVo
     * @return
     * @author wei
     */
    @Override
    public Page<ExamResultVo> resultList(Page<ExamResultVo> page, ExamResultVo ExamResultVo) {
        return examResultBo.resultList(page,ExamResultVo);
    }
    /**
     * 我的考试
     * @param examResultVo
     * @return
     */
    @Override
    public Page<ExamResultVo> getmyexam(Page<ExamResultVo> page, ExamResultVo ExamResultVo) {
        return examResultBo.getmyexam(page,ExamResultVo);
    }

}
