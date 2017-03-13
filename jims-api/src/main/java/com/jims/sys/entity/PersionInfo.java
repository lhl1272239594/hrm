/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 人员信息Entity
 * @author yangruidong
 * @version 2016-04-13
 */
@XmlRootElement
public class PersionInfo extends DataEntity<PersionInfo> {
    private static final long serialVersionUID = 1L;
    private String name; // 姓名
    private String sex;// 性别
    private String nation; // 民族
    private String cardNo; // 身份证号
    private String phoneNum; // 联系电话
    private String email; // 邮箱
    private String nickName; // 昵称
    private String inputCode; //拼音码
    private String type;
    private String classify;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getTitle_level() {
        return title_level;
    }

    public void setTitle_level(String title_level) {
        this.title_level = title_level;
    }
    private String title_level;
    private String education;
    private String politic;
    private String educationFinal;
    private String educationTime;
    private String workTime;
    private String comeTime;
    private String skill;
    private String skillLevel;
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getComeTime() {
        return comeTime;
    }

    public void setComeTime(String comeTime) {
        this.comeTime = comeTime;
    }

    public String getPolitic() {
        return politic;
    }

    public void setPolitic(String politic) {
        this.politic = politic;
    }

    public String getEducationFinal() {
        return educationFinal;
    }

    public void setEducationFinal(String educationFinal) {
        this.educationFinal = educationFinal;
    }

    public String getEducationTime() {
        return educationTime;
    }

    public void setEducationTime(String educationTime) {
        this.educationTime = educationTime;
    }

    public String getEducationFinalTime() {
        return educationFinalTime;
    }

    public void setEducationFinalTime(String educationFinalTime) {
        this.educationFinalTime = educationFinalTime;
    }

    private String educationFinalTime;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSelectCardNo() {
        return selectCardNo;
    }

    public void setSelectCardNo(String selectCardNo) {
        this.selectCardNo = selectCardNo;
    }

    public String[] getRole() {
        return role;
    }

    public void setRole(String[] role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getAge1() {
        return age1;
    }

    public void setAge1(String age1) {
        this.age1 = age1;
    }

    public String getAge2() {
        return age2;
    }

    public void setAge2(String age2) {
        this.age2 = age2;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge3() {
        return age3;
    }

    public void setAge3(String age3) {
        this.age3 = age3;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    //private String nation; // 民族
    //private String cardNo; // 身份证号
    //private String phoneNum; // 联系电话
    //private String email; // 邮箱
    //private String nickName; // 昵称
    private String title;    //职称
    private String titleLevel;

    public String getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(String titleLevel) {
        this.titleLevel = titleLevel;
    }

    private String password;  //密码
    private String deptId;      //所属科室ID
    private String orgId;      //组织机构Id
    private String selectCardNo;   //查询身份证号的字段
    private String role[];      //角色id
    private String roleName;    //角色名称
    private String staffId;    //人员id
    //private String inputCode;
    private String age1;
    private String age2;
    private String age;
    private String age3;
    private String dept;
    private String marry;
    private String pic;
    private String address;
    private String exp;
    private String remark;
    private String education_final;

    public String getEducation_final() {
        return education_final;
    }

    public void setEducation_final(String education_final) {
        this.education_final = education_final;
    }

    public PersionInfo() {
        super();
    }

    public PersionInfo(String id){
        super(id);
    }

    @Length(min=0, max=100, message="姓名长度必须介于 0 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min=0, max=2, message="性别长度必须介于 0 和 2 之间")
    public String getSex() {
        return sex;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Length(min=0, max=64, message="民族长度必须介于 0 和 64 之间")
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Length(min=0, max=20, message="身份证号长度必须介于 0 和 20 之间")
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Length(min=0, max=11, message="联系电话长度必须介于 0 和 11 之间")
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Length(min=0, max=100, message="邮箱长度必须介于 0 和 100 之间")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min=0, max=100, message="昵称长度必须介于 0 和 100 之间")
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}