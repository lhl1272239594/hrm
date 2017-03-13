package com.jims.fbd.hrm.exam.api;

import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.exam.entity.*;
import com.jims.fbd.hrm.exam.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;

import java.util.List;

public interface ExamApi {
    /**
     * 删除方法试题分类
     * @param questionItem
     */
    public String delItem(List<QuestionItem> questionItem);
    /**
     * 根据根据组织机构查询试题分类
     * @param orgId
     * @return
     * @author yangruidong
     */
    public List<QuestionItemVo> itemList(String orgId);
    /**
     * 手工选题查询试题分类
     * @param orgId
     * @param itemId
     * @return
     * @author yangruidong
     */
    public List<QuestionItemVo> examItemList(String orgId, String itemId);
    /**
     * 查询试题
     * @param page
     * @param orgId
     * @return
     */
    public Page<QuestionVo> questionList(String orgId, Page<QuestionVo> page, QuestionVo QuestionVo);
    /**
     * 查询题型
     *
     * @return
     */
    public List<QuestionType> questionType();
    /**
     * 新增修改试题
     *
     * @return
     */
    public String questionMerge(Question question);
    /**
     * 启用停用试题
     * @param question
     * @return
     * @author yangruidong
     */
    public String questionStatus(List<Question> question);

    /**
     * 查询试卷
     * @param page
     * @param ExamVo
     * @return
     */
    public Page<ExamVo> examList(Page<ExamVo> page, ExamVo ExamVo);
    /**
     * 查询出题方式
     *
     * @return
     */
    public List<ExamMethod> examMethod();
    /**
     * 新增修改试卷
     *
     * @return
     */
    public String examMerge(Exam exam);
    /**
     * 删除试卷
     *
     * @return
     * @param exam
     */
    public String examDelete(Exam exam);
    /**
     * 新增修改试卷题型
     *
     * @return
     */
    public String examQueTypeMerge(ExamQuestionType examQuestionType);
    /**
     * 删除试卷题型
     *
     * @return
     */
    public String delExamQueType(String examQueId);
    /**
     * 发布试卷
     *
     * @return
     * @param exam
     */
    public String examPublish(Exam exam);
    /**
     * 题目设置-查询题型
     * @return
     */
    public Page<ExamQuestionTypeVo> examQuestionType(Page<ExamQuestionTypeVo> examVoPage, ExamQuestionTypeVo examQuestionTypeVo);



    /**
     * 查询考试计划
     *
     * @return
     */
    public Page<PlanVo> planList(Page<PlanVo> examVoPage, PlanVo planVo);
    /**
     * 新增修改计划
     *
     * @param plan
     * @return
     */
    public String planMerge(Plan plan);

    /**
     * 发布考试计划
     *
     * @return
     * @param plan
     * @param loginInfo
     */
    public String planPublish(Plan plan, LoginInfo loginInfo);

    /**
     * 随机出题
     *
     * @param ExamQuestionType
     * @return
     */
    public String randomQuestion(ExamQuestionType ExamQuestionType);
    /**
     * 查询已选试题
     *
     *
     * @param examQuestionVo
     * @return
     */
    public Page<ExamQuestionVo> examQuestion(Page<ExamQuestionVo> page, ExamQuestionVo examQuestionVo);

    /**
     * 查询未选试题
     *
     * @return
     */
    public Page<ExamQuestionVo> examQuestionByItem(Page<ExamQuestionVo> page, ExamQuestionVo examQuestionVo);
    /**
     * 手工选题
     *
     * @param examQuestionType
     * @return
     */
    public String selectQuestion(ExamQuestionType examQuestionType);
    /**
     * 删除手工选题
     *
     * @param examQuestionType
     * @return
     */
    public String delSelectQuestion(ExamQuestionType examQuestionType);
    /**
     * 考试查询
     *
     * @param testVo
     * @return
     */
    public Page<TestVo> testList(Page<TestVo> testVoPage, TestVo testVo);
    /**
     * 查看授权人员
     *
     * @return
     */
    public List<PersonVo> getPersonById(String id);
    /**
     * 查询试题类型
     *
     * @return
     */
    public Page<QuestionItem> findItemByParent(Page<QuestionItem> page, QuestionItem questionItem);
    /**
     * 新增修改试题类型
     *
     * @param questionItem
     * @return
     */
    public String itemMerge(QuestionItem questionItem);
    /**
     * 启用停用考评类型
     * @param questionItems
     * @return
     */
    public String itemState(List<QuestionItem> questionItems);
    /**
     * 查看试题类型是否被占用
     * @param questionItems
     * @return
     */
    public String checkItemIsUsed(List<QuestionItem> questionItems);
    /**
     * 查看试题是否被占用
     * @param questions
     * @return
     */
    public String checkQuestionIsUsed(List<Question> questions);
    /**
     * 删除试题
     * @param question
     * @return
     * @author wangzhiming
     */
    public String questionRemove(List<Question> question);
    /**
     * 删除考试计划
     * @param plan
     * @return
     */
    public String planRemove(Plan plan, LoginInfo loginInfo);
    /**
     * 查询试卷试题
     * @param id
     * @return
     */
    public List<ExamDetail> getExamQuestion(String id);
    /**
     * 查询试卷题型
     * @param id
     * @return
     */
    public List<ExamQuestionType> getExamQuestionType(String id);
    /**
     * 获取试题分类
     * @param orgId
     * @return
     */
    public List<QuestionItem> getItems(String orgId);
    /**
     * 获取试题分类ID
     * @param orgId
     * @return
     */
    public QuestionItem getItem(String orgId, String name);
    /**
     * 校验试题名称是否存在
     * @param question
     * @return
     */
    public int getQueName(Question question);
    /**
     * 试题导入
     * @param questions
     * @return
     */
    public String insertByExcel(List<Question> questions);
}
