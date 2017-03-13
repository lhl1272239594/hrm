package com.jims.fbd.hrm.exam.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.exam.dao.ExamDao;
import com.jims.fbd.hrm.exam.entity.*;
import com.jims.fbd.hrm.exam.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class ExamBo extends CrudImplService<ExamDao,QuestionItem> {

    /**
     * 新增修改试题分类
     *
     * @return
     * @author wangzhiming
     */
    public List<QuestionItem> saveItem(QuestionItemVo<QuestionItem> QuestionItemVo) {
        List<QuestionItem> newUpdateDict = new ArrayList<QuestionItem>();
        List<QuestionItem> inserted = QuestionItemVo.getInserted();
        List<QuestionItem> updated = QuestionItemVo.getUpdated();
        //插入
        for (QuestionItem QuestionItem : inserted) {
            QuestionItem.preInsert();
            QuestionItem.setItemId(IdGen.uuid());
            //int num = dao.insert(QuestionItem);
            int num1=dao.insertItem(QuestionItem);
        }
        //更新
        for (QuestionItem QuestionItem : updated) {
            QuestionItem.preUpdate();
            QuestionItem.setUpdateBy(QuestionItem.getCreateBy());
            int num = dao.update(QuestionItem);
        }
        return newUpdateDict;
    }
    /**
     * 删除试题分类
     *
     * @return
     * @author wangzhiming
     */

    public String delItem(List<QuestionItem> questionItem) {
        for (QuestionItem q : questionItem) {
            dao.delItem(q);
        }
        return "sucsess";
    }

    /**
     * 根据组织机构查询试题分类
     *
     * @param orgId
     * @return
     * @author wangzhiming
     */

    public List<QuestionItemVo> itemList(String orgId) {

        return dao.itemList(orgId);
    }
    /**
     * 手工选题查询试题分类
     *
     * @param orgId
     * @return
     * @author wangzhiming
     */

    public List<QuestionItemVo> examItemList(String orgId, String item) {
        String[] sourceStrArray = item.split(",");
        String itemId="";
        for (int i = 0; i < sourceStrArray.length; i++) {
            if(i==sourceStrArray.length-1){
                itemId+="'"+sourceStrArray[i]+"'";
            }
            else{
                itemId+="'"+sourceStrArray[i]+"',";
            }
        }
        return dao.examItemList(orgId,itemId);
    }
    /**
     * 查询全部试题
     * @param page
     * @param QuestionVo
     * @return
     * @author wei
     */
    public Page<QuestionVo> questionList(String orgId, Page<QuestionVo> page, QuestionVo QuestionVo ) {
        QuestionVo.setPage(page);
        page.setList(dao.questionList(QuestionVo));
        return page;
    }
    /**
     * 查询题型
     *
     * @return
     */
    public List<QuestionType> questionType() {

        return dao.questionType();
    }
    /**
     * 新增修改题型
     * @param  question
     * @return
     */
    public String questionMerge(Question question) {
        int num=dao.getQueName(question);
        if(num>0){
            return "hasName";
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(question.getQueId())){
            question.preUpdate();
            question.setUpdateBy(question.getCreateBy());
            dao.updateQuestion(question);
            return "success";
        }
        else{
            question.preInsert();
            question.setQueId(question.getId());
            dao.insertQuestion(question);
            return "success";
        }
    }
    /**
     * 停用启用试题
     * @param  question
     * @return
     */
    public String questionStatus(List<Question> question) {
        for (Question q : question) {
            q.preUpdate();
            dao.questionStatus(q);
        }
        return "sucsess";
    }
    /**
     * 查询考试结果
     * @param page
     * @param examVo
     * @return
     */
    public Page<ExamVo> examList(Page<ExamVo> page, ExamVo examVo) {
        examVo.setPage(page);
        page.setList(dao.examList(examVo));
        return page;
    }
    /**
     * 查询出题方式
     *
     * @return
     */
    public List<ExamMethod> examMethod() {

        return dao.examMethod();
    }
    /**
     * 新增修改试卷
     * @param  exam
     * @return
     */
    public String examMerge(Exam exam) {
        int num=dao.getExamName(exam);
        if(num>0){
            return "hasName";
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(exam.getExamId())){
            exam.preUpdate();
            exam.setUpdateBy(exam.getCreateBy());
            dao.updateExam(exam);
            return "success";
        }
        else{
            exam.preInsert();
            exam.setExamId(exam.getId());
            dao.insertExam(exam);
            return "success";
        }
    }
    /**
     * 删除试卷
     * @param  exam
     * @return
     */
    public String examDelete(Exam exam) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(exam.getExamId())){
            exam.preUpdate();
            dao.examDelete(exam);
            List<ExamQuestionTypeVo> l=dao.getExamType(exam.getExamId());
            for (ExamQuestionTypeVo e : l) {
                dao.delExamQueType(e.getExamQueId());
                dao.delExamQuestionByType(e.getExamQueId());
            }
            return "success";
        }
        return "error";
    }
    /**
     * 发布试卷
     * @return
     * @param exam
     */
    public String examPublish(Exam exam) {
        int num=dao.getByHand(exam);
        String byHand="0";
        if(num>0){
            byHand="1";
        }
        exam.preUpdate();
        exam.setByHand(byHand);
        dao.examPublish(exam);
        return "success";
    }
    /**
     * 题目设置-查询题型
     * @return
     */
    public Page<ExamQuestionTypeVo> examQuestionType(Page<ExamQuestionTypeVo> page,ExamQuestionTypeVo examQuestionTypeVo) {
        examQuestionTypeVo.setPage(page);
        page.setList(dao.examQuestionType(examQuestionTypeVo));
        return page;
    }
    /**
     * 新增修改试卷题型
     * @param  examQuestionType
     * @return
     */
    public String examQueTypeMerge(ExamQuestionType examQuestionType) {

            String examId=examQuestionType.getExamId();
            String typeId=examQuestionType.getTypeId();
            String totalScore=examQuestionType.getTotalScore();
            //查询试题是否存在该题型
            int num =dao.queryQueType(examId,typeId);
            if(num==0){
                examQuestionType.preInsert();
                examQuestionType.setExamQueId(examQuestionType.getId());
                if(examQuestionType.getMethodId().equals("1")){
                    String orgId=examQuestionType.getOrgId();
                    String item=examQuestionType.getItemId();
                    String type=examQuestionType.getTypeId();
                    String num1=examQuestionType.getNum();
                    int queNum=Integer.valueOf(num1);
                    String score=examQuestionType.getScore();
                    int queScore=Integer.valueOf(score);
                    String[] sourceStrArray = item.split(",");
                    String itemId="";
                    for (int i = 0; i < sourceStrArray.length; i++) {
                        if(i==sourceStrArray.length-1){
                            itemId+="'"+sourceStrArray[i]+"'";
                        }
                        else{
                            itemId+="'"+sourceStrArray[i]+"',";
                        }
                    }
                    String isOver=dao.getExamScore(examId);
                    if(Integer.valueOf(isOver)+queNum*queScore>Integer.valueOf(totalScore)){
                        return "over";
                    }
                    List<ExamQuestion> question=dao.randomQuestion(orgId,itemId,type,num1);
                    if(question.size()<Integer.valueOf(num1)){
                        return "num";
                    }
                    else{

                        for(ExamQuestion q:question){
                            q.setExamId(examId);
                            q.setExamQueTypeId(examQuestionType.getId());
                            q.preInsert();
                            q.setScore(score);
                            q.setExamQueId(q.getId());
                            dao.insertExamQuestion(q);
                        }
                        dao.insertExamQueType(examQuestionType);
                    }
                }
                else{
                    dao.insertExamQueType(examQuestionType);
                }
                return "add";
            }
            else{
                return "echo";
            }

    }
    /**
     * 删除试卷试题题型
     *
     * @param examQueId
     * @return
     * @author wangzhiming
     */
    public void delExamQueType(String examQueId) {
        dao.delExamQueType(examQueId);
    }
    /**
     * 按题型删除试卷试题
     *
     * @param examQueId
     * @return
     * @author wangzhiming
     */
    public void delExamQuestionByType(String examQueId) {
        dao.delExamQueType(examQueId);
        dao.delExamQuestionByType(examQueId);
    }
    /**
     * 查询考试计划
     * @param page
     * @param planVo
     * @return
     */
    public Page<PlanVo> planList(Page<PlanVo> page, PlanVo planVo) {
        planVo.setPage(page);
        page.setList(dao.planList(planVo));
        return page;
    }

    /**
     * 新增修改考试计划
     * @param  plan
     * @return
     */
    public String planMerge(Plan plan) {
        int num=dao.getPlanName(plan);
        if(num>0){
            return "hasName";
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(plan.getPlanId())){
            plan.setUpdateBy(plan.getCreateBy());
            dao.updatePlan(plan);
            dao.deletePerson(plan.getPlanId());
            List<PersonVo> personVos=plan.getPersonVos();
            for(PersonVo o:personVos){
                o.preInsert();
                o.setDataId(plan.getPlanId());
                o.setOrgId(plan.getOrgId());
                dao.insertPerson(o);
            }
        }
        else{

            plan.preInsert();
            plan.setPlanId(plan.getId());
            dao.insertPlan(plan);
            List<PersonVo> personVos=plan.getPersonVos();
            for(PersonVo o:personVos){
                o.preInsert();
                o.setDataId(plan.getPlanId());
                o.setOrgId(plan.getOrgId());
                dao.insertPerson(o);
            }
        }

        return "success";
    }
    /**
     * 发布考试计划
     * @return
     * @param plan
     * @param loginInfo
     */
    public String planPublish(Plan plan, LoginInfo loginInfo) {
        plan.preUpdate();
        dao.planPublish(plan);
        List<PersonVo> personVos=dao.getPlanPerson(plan.getPlanId());
        for(PersonVo p:personVos){
            ExamScore examScore = new ExamScore();
            examScore.preInsert();
            examScore.setExamId(plan.getExamId());
            examScore.setPlanId(plan.getPlanId());
            examScore.setOrgId(plan.getOrgId());
            examScore.setUserId(p.getUserId());
            examScore.setCreateDept(loginInfo.getDeptId());
            examScore.setCreateBy(loginInfo.getPersionId());
            dao.insertExamScore(examScore);
        }
        return "success";
    }

    /**
     * 随机出题
     *
     * @param examQuestionType
     * @return
     */
    public String randomQuestion(ExamQuestionType examQuestionType) {
        dao.delExamQuestionByType(examQuestionType.getExamQueId());
        String orgId=examQuestionType.getOrgId();
        String examId=examQuestionType.getExamId();
        String item=examQuestionType.getItemId();
        String type=examQuestionType.getTypeId();
        String num1=examQuestionType.getNum();
        String[] sourceStrArray = item.split(",");
        String itemId="";
        for (int i = 0; i < sourceStrArray.length; i++) {
            if(i==sourceStrArray.length-1){
                itemId+="'"+sourceStrArray[i]+"'";
            }
            else{
                itemId+="'"+sourceStrArray[i]+"',";
            }
        }
        List<ExamQuestion> question=dao.randomQuestion(orgId,itemId,type,num1);
        if(question.size()<Integer.valueOf(num1)){
            return "num";
        }
        else{
            for(ExamQuestion q:question){
                q.setExamId(examId);
                q.setExamQueTypeId(examQuestionType.getExamQueId());
                q.preInsert();
                q.setScore(examQuestionType.getScore());
                q.setExamQueId(q.getId());
                dao.insertExamQuestion(q);
            }
            return "success";
        }
    }
    /**
     * 查询已选试题
     *
     *
     * @param examQuestionVo
     * @return
     */
    public Page<ExamQuestionVo> examQuestion(Page<ExamQuestionVo> page, ExamQuestionVo examQuestionVo) {
        examQuestionVo.setPage(page);
        page.setList(dao.examQuestion(examQuestionVo));
        return page;
    }
    /**
     * 查询未选试题
     *
     * @return
     */
    public Page<ExamQuestionVo> examQuestionByItem(Page<ExamQuestionVo> page,ExamQuestionVo examQuestionVo) {
        examQuestionVo.setPage(page);
        page.setList(dao.examQuestionByItem(examQuestionVo));
        return page;
    }
    /**
     * 手工选题
     *
     * @param examQuestionType
     * @return
     */
    public String selectQuestion(ExamQuestionType examQuestionType) {
        String queId=examQuestionType.getQueId();
        String examId=examQuestionType.getExamId();
        String totalScore=examQuestionType.getTotalScore();
        String num=examQuestionType.getNum();
        int queNum=Integer.valueOf(num);
        String score=examQuestionType.getScore();
        int queScore=Integer.valueOf(score);
        String examQueId=examQuestionType.getExamQueId();
        String[] sourceStrArray = queId.split(",");
        String type="add";
        String que="";
        for (int i = 0; i < sourceStrArray.length; i++) {
            if(i==sourceStrArray.length-1){
                que+="'"+sourceStrArray[i]+"'";
            }
            else{
                que+="'"+sourceStrArray[i]+"',";
            }
        }
        List<ExamQuestion> question=dao.selectQuestion(que);
        if(question.size()<Integer.valueOf(num)){
            return "num";
        }
        else{
            String isOver=dao.getExamScore(examId);
            if(Integer.valueOf(isOver)+queNum*queScore>Integer.valueOf(totalScore)){
                return "over";
            }
            for(ExamQuestion q:question){
                q.setExamId(examId);
                q.setExamQueTypeId(examQueId);
                q.preInsert();
                q.setScore(examQuestionType.getScore());
                q.setExamQueId(q.getId());
                dao.insertExamQuestion(q);
            }
            dao.updateExamQuestionNum(examQueId,type,num);
            return "success";
        }

    }
    /**
     * 删除手工选题
     *
     * @param examQuestionType
     * @return
     */
    public String delSelectQuestion(ExamQuestionType examQuestionType) {
        String queId=examQuestionType.getQueId();
        String examId=examQuestionType.getExamId();
        String num=examQuestionType.getNum();
        String examQueId=examQuestionType.getExamQueId();
        String[] sourceStrArray = queId.split(",");
        String type="del";
        String que="";
        for (int i = 0; i < sourceStrArray.length; i++) {
            if(i==sourceStrArray.length-1){
                que+="'"+sourceStrArray[i]+"'";
            }
            else{
                que+="'"+sourceStrArray[i]+"',";
            }
        }
        dao.deleteExamQuestion(que);
        dao.updateExamQuestionNum(examQueId,type,num);
        return "success";

    }
    /**
     * 考试查询
     * @return
     */
    public Page<TestVo> testList(Page<TestVo> page, TestVo testVo) {
        testVo.setPage(page);
        page.setList(dao.testList(testVo));
        return page;
    }
    /**
     * 查看授权人员
     *
     * @return
     */
    public List<PersonVo> getPersonById(String id) {
        return dao.getPersonById(id);
    }
    /**
     * 查询试题类型
     *
     * @return
     */
    public Page<QuestionItem> findItemByParent(Page<QuestionItem> page, QuestionItem questionItem) {
        questionItem.setPage(page);
        page.setList(dao.findItemByParent(questionItem));
        return page;
    }
    /**
     * 新增修改试题类型
     *
     * @param questionItem
     * @return
     */
    public String itemMerge(QuestionItem questionItem) {
        //QuestionItem q =dao.getParent(questionItem);
        int num1=dao.getItemName(questionItem);
        if(num1>0){
            return "hasName";
        }
        if (!questionItem.getItemId().equals("")){
            questionItem.preUpdate();
            questionItem.setUpdateBy(questionItem.getCreateBy());
            dao.updateItem(questionItem);
            return "success";
        }
        else{

            questionItem.preInsert();
            dao.insertItem(questionItem);
            return "success";
        }
    }
    /**
     * 启用停用试题类型
     * @param questionItems
     * @return
     */
    public String itemState(List<QuestionItem> questionItems) {
        for (QuestionItem q : questionItems) {
            dao.itemState(q);
        }
        return "sucsess";
    }

    public String checkItemIsUsed(List<QuestionItem> questionItems) {
        for (QuestionItem q : questionItems) {
            int num=dao.checkItemIsUsed(q);
            if(num>0){
                return "isUsed";
            }
        }
        return "success";
    }
    /**
     * 查看试题是否被占用
     * @param questions
     * @return
     */
    public String checkQuestionIsUsed(List<Question> questions) {
        for (Question q : questions) {
            int num=dao.checkQuestionIsUsed(q);
            if(num>0){
                return "isUsed";
            }
        }
        return "success";
    }
    /**
     * 删除试题
     * @param question
     * @return
     * @author wangzhiming
     */
    public String questionRemove(List<Question> question) {
        for (Question q : question) {
            q.preUpdate();
            dao.questionRemove(q);
        }
        return "sucsess";
    }
    /**
     * 删除考试计划
     * @param plan
     * @return
     */
    public String planRemove(Plan plan, LoginInfo loginInfo) {
        plan.preUpdate();
        plan.setUpdateBy(loginInfo.getPersionId());
        dao.planRemove(plan);
        return "success";
    }
    /**
     * 查询试卷试题
     * @param id
     * @return
     */
    public List<ExamDetail> getExamQuestion(String id) {
        return dao.getExamQuestion(id);
    }
    /**
     * 查询试卷题型
     * @param id
     * @return
     */
    public List<ExamQuestionType> getExamQuestionType(String id) {
        return dao.getExamQuestionType(id);
    }
    /**
     * 获取试题分类
     * @param orgId
     * @return
     */
    public List<QuestionItem> getItems(String orgId) {
        QuestionItem questionItem=new QuestionItem();
        if(!orgId.equals("")&&orgId!=null){
            questionItem.setOrgId(orgId);
        }
        return dao.findItemByParent(questionItem);
    }
    /**
     * 获取试题分类ID
     * @param orgId
     * @return
     */
    public QuestionItem getItem(String orgId, String name) {
        QuestionItem q=dao.getItem(orgId,name);
        return q;
    }
    /**
     * 校验试题名称是否存在
     * @param question
     * @return
     */
    public int getQueName(Question question) {
        return dao.getQueName(question);
    }
    /**
     * 试题批量导入
     * @param questions
     * @return
     */
    public String insertByExcel(List<Question> questions) {
        for(Question q:questions){
            dao.insertQuestion(q);
        }
        return questions.size()+"";
    }
}
