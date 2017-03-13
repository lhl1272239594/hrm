package com.jims.fbd.hrm.exam.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.exam.entity.*;
import com.jims.fbd.hrm.exam.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface ExamDao extends CrudDao<QuestionItem>{

    public int insertItem(@Param("QuestionItem") QuestionItem QuestionItem) ;
    /* *
  * 删除
  * @param ids
  * @return
  **/
    public void delItem(@Param("ids") String ids,@Param("updateBy") String updateBy) ;
    /**
     * 根据组织机构查询试题分类
     * @param orgId
     * @return
     */
    public List<QuestionItemVo> itemList(@Param("orgId") String orgId);
    /**
     * 手工选题查询试题分类
     * @param orgId
     * @return
     */
    public List<QuestionItemVo> examItemList(@Param("orgId") String orgId,@Param("itemId") String itemId);
    /**
     *
     *根据选中的试题分类显示其子节点
     * @return
     */
    public List<QuestionItem> findItemByParent( QuestionItem questionItem);

    /**
     *
     *根据组织机构id查询组织机构下的人员
     * @param orgId
     * @return
     */
    public List<QuestionItemVo> findOrgStaff(@Param("orgId") String orgId);

    /**
     *
     *根据组id查询组类名称
     * @param groupId
     * @return
     */
    public List<QuestionItemVo> findGroupClass(@Param("groupId") String groupId);


    /**
     * 查询全部试题
     * @param QuestionVo
     * @return
     */

    public List<QuestionVo> questionList(QuestionVo QuestionVo);
    /**
     * 查询题型
     *
     * @return
     */
    public List<QuestionType> questionType();
    /**
     * 修改试题
     *
     * @param Question
     * @return
     */
    public void updateQuestion(@Param("Question") Question Question);
    /**
     * 新增试题
     *
     * @param Question
     * @return
     */
    public void insertQuestion(@Param("Question" )Question Question);
    /**
     * 停用启用试题
     *
     * @param Question
     * @return
     */
    public void questionStatus(@Param("Question" ) Question Question);

    /**
     * 查询全部试题
     * @param examVo
     * @return
     */
    public List<ExamVo> examList(ExamVo examVo);
    /**
     * 查询出题方式
     *
     * @return
     */
    public List<ExamMethod> examMethod();

    /**
     * 修改试卷
     *
     * @param Exam
     * @return
     */
    public void updateExam(@Param("Exam") Exam Exam);
    /**
     * 新增试卷
     *
     * @param Exam
     * @return
     */
    public void insertExam(@Param("Exam" )Exam Exam);
    /**
     * 新增试卷题型
     *
     * @param ExamQuestionType
     * @return
     */
    public void insertExamQueType(@Param("ExamQuestionType" )ExamQuestionType ExamQuestionType);
    /**
     * 修改试卷题型
     *
     * @param ExamQuestionType
     * @return
     */
    public void updateExamQueType(@Param("ExamQuestionType" )ExamQuestionType ExamQuestionType);
    /**
     * 删除试卷题型
     *
     * @param examQueId
     * @return
     * @author wangzhiming
     */
    public void delExamQueType(String examQueId);
    /**
     * 查询试题是否存在该题型
     *
     * @return num
     */
    public int queryQueType(@Param("examId") String examId, @Param("typeId") String typeId);
    /**
     * 发布试卷
     * @return
     * @param Exam
     */
    public void examPublish(@Param("Exam") Exam Exam);
    /**
     * 查询考试计划
     * @param planVo
     * @return
     */
    public List<PlanVo> planList(PlanVo planVo);

    /**
     * 校验试卷名称是否存在
     *
     * @param exam
     * @return
     */
    public int getExamName(Exam exam);
    /**
     * 校验试卷名称是否存在
     *
     * @param plan
     * @return
     */
    public int getPlanName(Plan plan);
    /**
     * 修改考试计划
     *
     * @param plan
     * @return
     */
    public void updatePlan(@Param("Plan") Plan plan);
    /**
     * 新增考试计划
     *
     * @param plan
     * @return
     */
    public void insertPlan(@Param("Plan" )Plan plan);
    /**
     * 发布考试计划
     * @return
     * @param plan
     */
    public void planPublish(@Param("Plan") Plan plan);

    /**
     * 题目设置-查询题型
     * @return
     */
    public List<ExamQuestionTypeVo> examQuestionType(ExamQuestionTypeVo examQuestionTypeVo);

    /**
     * 删除题型后将该试卷该题型试题都删除
     * @return
     */
    public void delExamQuestionByType(String examQueId);
    /**
     * 随机出题
     * @return
     */
    public List<ExamQuestion> randomQuestion(@Param("orgId") String orgId, @Param("itemId") String itemId, @Param("typeId") String typeId, @Param("num1") String num1);
    /**
     * 随机出题并保存
     * @return
     */
    public void insertExamQuestion(@Param("q" )ExamQuestion q);
    /**
     * 查询已选试题
     *
     * @param examQuestionVo
     * @return
     */
    public List<ExamQuestionVo> examQuestion(ExamQuestionVo examQuestionVo);
    /**
     * 查询未选试题
     *
     * @param examQuestionVo
     * @return
     */
    public List<ExamQuestionVo> examQuestionByItem(ExamQuestionVo examQuestionVo);
    /**
     * 手工选题
     *
     * @param queId
     * @return
     */
    public List<ExamQuestion> selectQuestion(@Param("queId" )  String queId);
    /**
     * 更新题型中试题数量
     *
     *
     * @param examQueId
     * @param num
     * @return
     */
    public void updateExamQuestionNum(@Param("examQueId") String examQueId,@Param("type") String type,@Param("num" )  String num);
    /**
     * 考试查询
     * @return
     */
    public List<TestVo> testList(TestVo TestVo);
    /**
     * 删除手工选题
     * @return
     */
    public void deleteExamQuestion(@Param("que") String que);
    /**
     * 按照试卷Id查询题型ID
     * @return
     */
    public List<ExamQuestionTypeVo> getExamType(String examId);
    /**
     * 删除试卷
     * @return
     */
    public void examDelete(@Param("Exam" )Exam Exam);
    /**
     * 查看试卷是否有主观题
     * @return
     */
    public int getByHand(@Param("Exam" )Exam Exam);
    /**
     * 删除考试计划授权人员
     * @return
     */
    public void deletePerson(@Param("planId" ) String planId);
    /**
     * 添加考试计划授权人员
     * @return
     */
    public void insertPerson(@Param("PersonVo" ) PersonVo PersonVo);
    /**
     * 查看授权人员
     *
     * @return
     */
    public List<PersonVo> getPersonById(String id);
    /**
     * 更新试题类型
     * @return
     */
    public void updateItem(QuestionItem questionItem);
    /**
     * 查询试题名称
     * @return
     */
    public int getItemName(QuestionItem questionItem);
    /**
     * 删除试题分类
     *
     * @return
     * @author wangzhiming
     */
    public void delItem(QuestionItem q);
    /**
     * 启用停用试题类型
     * @param q
     * @return
     */
    public void itemState(QuestionItem q);
    /**
     * 查看试卷分数
     * @return
     */
    public String getExamScore(@Param("examId" ) String examId);
    /**
     * 查看该机构是否有父节点
     * @return
     */
    public QuestionItem getParent(QuestionItem questionItem);
    /**
     * 查看试题类型是否被占用
     * @return
     */
    public int checkItemIsUsed(QuestionItem q);
    /**
     * 查看试题是否被占用
     * @param q
     * @return
     */
    public int checkQuestionIsUsed(Question q);
    /**
     * 获取考试人员
     * @param planId
     * @return
     */
    public List<PersonVo> getPlanPerson(@Param("planId" )String planId);
    /**
     * 插入考试头表
     * @param examScore
     * @return
     */
    public void insertExamScore(@Param("ExamScore" ) ExamScore examScore);
    /**
     * 删除试题
     * @param Question
     * @return
     * @author wangzhiming
     */
    public void questionRemove(@Param("Question") Question Question);
    /**
     * 删除考试计划
     * @param plan
     * @return
     */
    public void planRemove(@Param("Plan") Plan plan);
    /**
     * 校验试题名称是否存在
     *
     * @param question
     * @return
     */
    public int getQueName(Question question);
    /**
     * 查询试卷试题
     * @param id
     * @return
     */
    public List<ExamDetail> getExamQuestion(@Param("id") String id);
    /**
     * 查询试卷题型
     * @param id
     * @return
     */
    public List<ExamQuestionType> getExamQuestionType(@Param("id") String id);
    /**
     * 获取试题分类ID
     * @param orgId
     * @return
     */
    public QuestionItem getItem(@Param("orgId")String orgId,@Param("name") String name);
    /**
     * 试题批量导入
     * @param questions
     * @return
     */
    public void insertQuestions(@Param("questions") List<Question> questions);
}
