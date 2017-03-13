package com.jims.fbd.hrm.exam.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.exam.api.ExamApi;
import com.jims.fbd.hrm.exam.bo.ExamBo;
import com.jims.fbd.hrm.exam.dao.ExamDao;
import com.jims.fbd.hrm.exam.entity.*;
import com.jims.fbd.hrm.exam.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")

public class ExamImpl extends CrudImplService<ExamDao, QuestionItem> implements ExamApi {

    @Autowired
    private ExamBo examBo;

    @Override
    public String delItem(List<QuestionItem> questionItem) {
        return examBo.delItem(questionItem);
    }

    /**
     * 根据组织机构查询试题分类
     *
     * @param orgId
     * @return
     * @author wangzhiming
     */
    @Override
    public List<QuestionItemVo> itemList(String orgId) {
        return examBo.itemList(orgId);
    }
    /**
     * 手工选题查询试题分类
     *
     * @param orgId
     * @param itemId
     * @return
     * @author wangzhiming
     */
    @Override
    public List<QuestionItemVo> examItemList(String orgId, String itemId) {
        return examBo.examItemList(orgId,itemId);
    }
    /**
     * 查询试题
     * @param orgId
     * @param page
     * @param QuestionVo
     * @return
     * @author wei
     */
    @Override
    public Page<QuestionVo> questionList(String orgId, Page<QuestionVo> page, QuestionVo QuestionVo) {
        return examBo.questionList(orgId,page,QuestionVo);
    }

    /**
     * 查询题型
     *
     * @return
     */
    public List<QuestionType> questionType() {
        return examBo.questionType();
    }

    /**
     * 新增修改试题
     *
     * @param question
     * @return
     */
    @Override
    public String questionMerge(Question question) {
        return examBo.questionMerge(question);
    }
    /**
     * 启用停用试题
     * @param question
     * @return
     * @author wangzhiming
     */
    @Override
    public String questionStatus(List<Question> question) {
        return examBo.questionStatus(question);
    }
    /**
     * 查询试卷
     * @param page
     * @param ExamVo
     * @return
     * @author wei
     */
    @Override
    public Page<ExamVo> examList(Page<ExamVo> page, ExamVo ExamVo) {
        return examBo.examList(page,ExamVo);
    }
    /**
     * 查询出题方式
     *
     * @return
     */
    public List<ExamMethod> examMethod() {
        return examBo.examMethod();
    }

    /**
     * 新增修改试卷
     *
     * @param exam
     * @return
     */
    @Override
    public String examMerge(Exam exam) {
        return examBo.examMerge(exam);
    }
    /**
     * 删除试卷
     *
     * @param exam
     * @return
     */
    @Override
    public String examDelete(Exam exam) {
        return examBo.examDelete(exam);
    }

    /**
     * 新增修改试卷题型
     *
     * @param ExamQuestionType
     * @return
     */
    @Override
    public String examQueTypeMerge(ExamQuestionType ExamQuestionType) {
        return examBo.examQueTypeMerge(ExamQuestionType);
    }
    /**
     * 删除试卷题型
     *
     * @param examQueId
     * @return
     * @author wangzhiming
     */
    @Override
    public String delExamQueType(String examQueId) {
        try {

            examBo.delExamQuestionByType(examQueId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 发布试卷
     *
     * @return
     * @param exam
     */
    @Override
    public String examPublish(Exam exam) {
        return examBo.examPublish(exam);
    }
    /**
     * 题目设置-查询题型
     * @return
     */
    @Override
    public Page<ExamQuestionTypeVo> examQuestionType(Page<ExamQuestionTypeVo> page, ExamQuestionTypeVo examQuestionTypeVo) {
        return examBo.examQuestionType(page,examQuestionTypeVo);
    }

    /**
     * 查询考试计划
     * @param page
     * @param PlanVo
     * @return
     */
    @Override
    public Page<PlanVo> planList(Page<PlanVo> page, PlanVo PlanVo) {
        return examBo.planList(page,PlanVo);
    }
    /**
     * 新增修改考试计划
     *
     * @param plan
     * @return
     */
    @Override
    public String planMerge(Plan plan) {
        return examBo.planMerge(plan);
    }
    /**
     * 发布考试计划
     *
     * @return
     * @param plan
     * @param loginInfo
     */
    @Override
    public String planPublish(Plan plan, LoginInfo loginInfo) {
        return examBo.planPublish(plan,loginInfo);
    }
    /**
     * 随机出题
     *
     * @param examQuestion
     * @return
     */
    @Override
    public String randomQuestion(ExamQuestionType examQuestion) {
        return examBo.randomQuestion(examQuestion);
    }
    /**
     * 查询已选试题
     *
     *
     * @param examQuestionVo
     * @return
     */
    @Override
    public Page<ExamQuestionVo> examQuestion(Page<ExamQuestionVo> page,  ExamQuestionVo examQuestionVo) {
        return examBo.examQuestion(page,examQuestionVo);
    }
    /**
     * 查询未选试题
     *
     * @return
     */
    @Override
    public Page<ExamQuestionVo> examQuestionByItem(Page<ExamQuestionVo> page, ExamQuestionVo examQuestionVo) {
        return examBo.examQuestionByItem(page,examQuestionVo);
    }
    /**
     * 手工选题
     *
     * @param examQuestionType
     * @return
     */
    @Override
    public String selectQuestion(ExamQuestionType examQuestionType) {
        return examBo.selectQuestion(examQuestionType);
    }
    /**
     * 删除手工选题
     *
     * @param examQuestionType
     * @return
     */
    @Override
    public String delSelectQuestion(ExamQuestionType examQuestionType) {
        return examBo.delSelectQuestion(examQuestionType);
    }

    /**
     * 考试查询
     * @return
     */
    @Override
    public Page<TestVo> testList(Page<TestVo> page, TestVo testVo) {
        return examBo.testList(page,testVo);
    }
    /**
     * 查看授权人员
     *
     * @return
     */
    @Override
    public List<PersonVo> getPersonById(String id) {
        return examBo.getPersonById(id);
    }
    /**
     * 查询试题类型
     *
     * @return
     */
    @Override
    public Page<QuestionItem> findItemByParent(Page<QuestionItem> page, QuestionItem questionItem) {
        return examBo.findItemByParent(page,questionItem);
    }
    /**
     * 新增修改试题类型
     *
     * @param questionItem
     * @return
     */
    @Override
    public String itemMerge(QuestionItem questionItem) {
        return examBo.itemMerge(questionItem);
    }
    /**
     * 启用停用考评类型
     * @param questionItems
     * @return
     */
    @Override
    public String itemState(List<QuestionItem> questionItems) {
        return examBo.itemState(questionItems);
    }
    /**
     * 查看试题类型是否被占用
     * @param questionItems
     * @return
     */
    @Override
    public String checkItemIsUsed(List<QuestionItem> questionItems) {
        return examBo.checkItemIsUsed(questionItems);
    }
    /**
     * 查看试题是否被占用
     * @param questions
     * @return
     */
    @Override
    public String checkQuestionIsUsed(List<Question> questions) {
        return examBo.checkQuestionIsUsed(questions);
    }
    /**
     * 删除试题
     * @param question
     * @return
     * @author wangzhiming
     */
    @Override
    public String questionRemove(List<Question> question) {
        return examBo.questionRemove(question);
    }
    /**
     * 删除考试计划
     * @param plan
     * @return
     */
    @Override
    public String planRemove(Plan plan, LoginInfo loginInfo) {
        return examBo.planRemove(plan,loginInfo);
    }
    /**
     * 查询试卷试题
     * @param id
     * @return
     */
    @Override
    public List<ExamDetail> getExamQuestion(String id) {
        return examBo.getExamQuestion(id);
    }
    /**
     * 查询试卷题型
     * @param id
     * @return
     */
    @Override
    public List<ExamQuestionType> getExamQuestionType(String id) {
        return examBo.getExamQuestionType(id);
    }
    /**
     * 获取试题分类
     * @param orgId
     * @return
     */
    @Override
    public List<QuestionItem> getItems(String orgId) {
        return examBo.getItems(orgId);
    }
    /**
     * 获取试题分类ID
     * @param orgId
     * @return
     */
    @Override
    public QuestionItem getItem(String orgId, String name) {
        return examBo.getItem(orgId,name);
    }
    /**
     * 校验试题名称是否存在
     * @param question
     * @return
     */
    @Override
    public int getQueName(Question question) {
        return examBo.getQueName(question);
    }
    /**
     * 试题导入
     * @param questions
     * @return
     */
    @Override
    public String insertByExcel(List<Question> questions) {
        return examBo.insertByExcel(questions);
    }
}
