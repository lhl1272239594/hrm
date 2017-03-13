/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.lab.entity;

import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateDeSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 检验主记录Entity
 * @author xueyx
 * @version 2016-05-04
 */
public class LabTestMaster extends DataEntity<LabTestMaster> {
	
	private static final long serialVersionUID = 1L;
	private String testNo;		// 申请序号
	private Integer priorityIndicator;		// 优先标志
	private String patientId;		// 病人标识号
	private String visitId;		// 本次住院标识
	private String workingId;		// 工作单号
	private Date executeDate;		// 执行日期
	private String name;		// 姓名
	private String namePhonetic;		// 姓名拼音
	private String chargeType;		// 费别
	private String sex;		// 性别
	private Integer age;		// 年龄
	private String testCause;		// 检验目的
	private String relevantClinicDiag;		// 临床诊断
	private String specimen;		// 标本
	private String notesForSpcm;		// 标本说明
	private Date spcmReceivedDateTime;		// 标本采样日期及时间
	private Date spcmSampleDateTime;		// 标本收到日期及时间
	private Date requestedDateTime;		// 申请日期及时间
	private String orderingDept;		// 申请科室
	private String orderingProvider;		// 申请医生
	private String performedBy;		// 执行科室
	private String resultStatus;		// 结果状态
	private Date resultsRptDateTime;		// 报告日期及时间
	private String transcriptionist;		// 报告者
	private String verifiedBy;		// 校对者
	private Double costs;		// 费用
	private Double charges;		// 应收费用
	private Integer billingIndicator;		// 计价标志
	private String printIndicator;		// 打印标志
	private String subject;		// subject
	private String containerCarrier;		// container_carrier
	private String status;		// 申请状态
	private String memo;		// memo
	private String wardCode;		// 护理单元
	private String phyexamFlag;		// phyexam_flag
	private String nayaotishi;		// 耐药细菌提示
	private String jianyi;		// LIS建议
	private String wsw;		// wsw
	private String sampleid;		// LIS接口主键
	private String labItemClass;  //检验项目类别
	private String  clinicId;//就诊id
	private String  orgId;
	private String inOrOutFlag;//是否住院标识

	//增加
	private LabTestItems labTestItems;

	public LabTestMaster() {
		super();
	}

	private List<LabTestItems> list;
	public LabTestMaster(String id){
		super(id);
	}

	public LabTestItems getLabTestItems() {
		return labTestItems;
	}

	public void setLabTestItems(LabTestItems labTestItems) {
		this.labTestItems = labTestItems;
	}

	@Length(min=1, max=40, message="申请序号长度必须介于 1 和 40 之间")
	public String getTestNo() {
		return testNo;
	}

	public void setTestNo(String testNo) {
		this.testNo = testNo;
	}
	
	public Integer getPriorityIndicator() {
		return priorityIndicator;
	}

	public void setPriorityIndicator(Integer priorityIndicator) {
		this.priorityIndicator = priorityIndicator;
	}
	
	@Length(min=0, max=20, message="病人标识号长度必须介于 0 和 20 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	@Length(min=0, max=12, message="工作单号长度必须介于 0 和 12 之间")
	public String getWorkingId() {
		return workingId;
	}

	public void setWorkingId(String workingId) {
		this.workingId = workingId;
	}

	public Date getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}
	
	@Length(min=0, max=40, message="姓名长度必须介于 0 和 40 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="姓名拼音长度必须介于 0 和 32 之间")
	public String getNamePhonetic() {
		return namePhonetic;
	}

	public void setNamePhonetic(String namePhonetic) {
		this.namePhonetic = namePhonetic;
	}
	
	@Length(min=0, max=16, message="费别长度必须介于 0 和 16 之间")
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	
	@Length(min=0, max=8, message="性别长度必须介于 0 和 8 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Length(min=0, max=16, message="检验目的长度必须介于 0 和 16 之间")
	public String getTestCause() {
		return testCause;
	}

	public void setTestCause(String testCause) {
		this.testCause = testCause;
	}
	
	@Length(min=0, max=160, message="临床诊断长度必须介于 0 和 160 之间")
	public String getRelevantClinicDiag() {
		return relevantClinicDiag;
	}

	public void setRelevantClinicDiag(String relevantClinicDiag) {
		this.relevantClinicDiag = relevantClinicDiag;
	}
	
	@Length(min=0, max=16, message="标本长度必须介于 0 和 16 之间")
	public String getSpecimen() {
		return specimen;
	}

	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}
	
	@Length(min=0, max=200, message="标本说明长度必须介于 0 和 200 之间")
	public String getNotesForSpcm() {
		return notesForSpcm;
	}

	public void setNotesForSpcm(String notesForSpcm) {
		this.notesForSpcm = notesForSpcm;
	}
	

	public Date getSpcmReceivedDateTime() {
		return spcmReceivedDateTime;
	}

	public void setSpcmReceivedDateTime(Date spcmReceivedDateTime) {
		this.spcmReceivedDateTime = spcmReceivedDateTime;
	}
	

	public Date getSpcmSampleDateTime() {
		return spcmSampleDateTime;
	}

	public void setSpcmSampleDateTime(Date spcmSampleDateTime) {
		this.spcmSampleDateTime = spcmSampleDateTime;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getRequestedDateTime() {
		return requestedDateTime;
	}

	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setRequestedDateTime(Date requestedDateTime) {
		this.requestedDateTime = requestedDateTime;
	}
	
	@Length(min=0, max=16, message="申请科室长度必须介于 0 和 16 之间")
	public String getOrderingDept() {
		return orderingDept;
	}

	public void setOrderingDept(String orderingDept) {
		this.orderingDept = orderingDept;
	}
	
	@Length(min=0, max=40, message="申请医生长度必须介于 0 和 40 之间")
	public String getOrderingProvider() {
		return orderingProvider;
	}

	public void setOrderingProvider(String orderingProvider) {
		this.orderingProvider = orderingProvider;
	}
	
	@Length(min=0, max=16, message="执行科室长度必须介于 0 和 16 之间")
	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}
	
	@Length(min=0, max=2, message="结果状态长度必须介于 0 和 2 之间")
	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getResultsRptDateTime() {
		return resultsRptDateTime;
	}

	public void setResultsRptDateTime(Date resultsRptDateTime) {
		this.resultsRptDateTime = resultsRptDateTime;
	}
	
	@Length(min=0, max=40, message="报告者长度必须介于 0 和 40 之间")
	public String getTranscriptionist() {
		return transcriptionist;
	}

	public void setTranscriptionist(String transcriptionist) {
		this.transcriptionist = transcriptionist;
	}
	
	@Length(min=0, max=16, message="校对者长度必须介于 0 和 16 之间")
	public String getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
	
	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}
	
	public Double getCharges() {
		return charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}
	
	public Integer getBillingIndicator() {
		return billingIndicator;
	}

	public void setBillingIndicator(Integer billingIndicator) {
		this.billingIndicator = billingIndicator;
	}

	public String getPrintIndicator() {
		return printIndicator;
	}

	public void setPrintIndicator(String printIndicator) {
		this.printIndicator = printIndicator;
	}

	@Length(min=0, max=80, message="subject长度必须介于 0 和 80 之间")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Length(min=0, max=40, message="container_carrier长度必须介于 0 和 40 之间")
	public String getContainerCarrier() {
		return containerCarrier;
	}

	public void setContainerCarrier(String containerCarrier) {
		this.containerCarrier = containerCarrier;
	}
	
	@Length(min=0, max=320, message="status长度必须介于 0 和 320 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=320, message="memo长度必须介于 0 和 320 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=40, message="护理单元长度必须介于 0 和 40 之间")
	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	
	@Length(min=0, max=40, message="phyexam_flag长度必须介于 0 和 40 之间")
	public String getPhyexamFlag() {
		return phyexamFlag;
	}

	public void setPhyexamFlag(String phyexamFlag) {
		this.phyexamFlag = phyexamFlag;
	}
	
	@Length(min=0, max=200, message="耐药细菌提示长度必须介于 0 和 200 之间")
	public String getNayaotishi() {
		return nayaotishi;
	}

	public void setNayaotishi(String nayaotishi) {
		this.nayaotishi = nayaotishi;
	}
	
	@Length(min=0, max=1000, message="LIS建议长度必须介于 0 和 1000 之间")
	public String getJianyi() {
		return jianyi;
	}

	public void setJianyi(String jianyi) {
		this.jianyi = jianyi;
	}
	
	@Length(min=0, max=2, message="wsw长度必须介于 0 和 2 之间")
	public String getWsw() {
		return wsw;
	}

	public void setWsw(String wsw) {
		this.wsw = wsw;
	}
	
	@Length(min=0, max=80, message="LIS接口主键长度必须介于 0 和 80 之间")
	public String getSampleid() {
		return sampleid;
	}

	public void setSampleid(String sampleid) {
		this.sampleid = sampleid;
	}

	public String getLabItemClass() {
		return labItemClass;
	}

	public void setLabItemClass(String labItemClass) {
		this.labItemClass = labItemClass;
	}

	public List<LabTestItems> getList() {
		return list;
	}

	public void setList(List<LabTestItems> list) {
		this.list = list;
	}

	public String getClinicId() {
		return clinicId;
	}

	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}

	private List<LabTestItems> children;

	public List<LabTestItems> getChildren() {
		return children;
	}

	public void setChildren(List<LabTestItems> children) {
		this.children = children;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getInOrOutFlag() {
		return inOrOutFlag;
	}

	public void setInOrOutFlag(String inOrOutFlag) {
		this.inOrOutFlag = inOrOutFlag;
	}
}