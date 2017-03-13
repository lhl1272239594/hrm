package com.jims.fbd.hrm.exam.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.exam.vo.*;

import java.util.List;

@MyBatisHrmDao
public interface ExamResultDao extends CrudDao<ExamResultVo>{



    /**
     * 查询考试结果
     * @param examResultVo
     * @return
     */
    public  List<ExamResultVo> resultList(ExamResultVo examResultVo);

    /**
     * 我的考试
     * @param examResultVo
     * @return
     */
    public  List<ExamResultVo> getmyexam(ExamResultVo examResultVo);
}
