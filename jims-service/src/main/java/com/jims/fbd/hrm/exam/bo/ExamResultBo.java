package com.jims.fbd.hrm.exam.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.exam.dao.ExamDao;
import com.jims.fbd.hrm.exam.dao.ExamResultDao;
import com.jims.fbd.hrm.exam.entity.*;
import com.jims.fbd.hrm.exam.vo.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class ExamResultBo extends CrudImplService<ExamResultDao,ExamResultVo> {

    /**
     * 查询试卷
     * @param page
     * @param examResultVo
     * @return
     */
    public Page<ExamResultVo> resultList(Page<ExamResultVo> page, ExamResultVo examResultVo) {
        examResultVo.setPage(page);
        page.setList(dao.resultList(examResultVo));
        return page;
    }
    /**
     * 我的考试
     * @param examResultVo
     * @return
     */
    public Page<ExamResultVo> getmyexam(Page<ExamResultVo> page, ExamResultVo examResultVo) {
        examResultVo.setPage(page);
        page.setList(dao.getmyexam(examResultVo));
        return page;
    }
}
