package com.jims.fbd.hrm.exam.api;

import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.exam.entity.*;
import com.jims.fbd.hrm.exam.vo.*;

import java.util.List;

public interface ExamResultApi {
    /**
     * 查询考试结果
     *
     * @return
     */
    public Page<ExamResultVo> resultList(Page<ExamResultVo> examResultVoPage, ExamResultVo examResultVo);
    public Page<ExamResultVo> getmyexam(Page<ExamResultVo> examResultVoPage, ExamResultVo examResultVo);
}
