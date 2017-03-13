package com.jims.template.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
* 价表模板Entity
* @author lgx
* @version 2016-08-09
*/
public class PriceListTemplate extends DataEntity<PriceListTemplate> {

    private static final long serialVersionUID = 1L;
    private String itemClass;  // 项目类别
    private String itemCode;  // 项目代码
    private String itemName;  // 项目名称
    private String itemSpec;  // 规格
    private String units;  // 单位
    private Double price;  // 基本价格
    private Double preferPrice;  // 优惠价格
    private Double foreignerPrice;  // 外宾价格
    private String performedBy;  // 执行科室
    private Integer feeTypeMask;  // 是否自费
    private String classOnInpRcpt;  // 住院收据分类
    private String classOnOutpRcpt;  // 门诊收据分类
    private String classOnReckoning;  // 核算科目
    private String subjCode;  // 会计科目
    private String classOnMr;  // 病案首页分类
    private String memo;  // 备注信息
    private Date startDate;  // 启用日期
    private Date stopDate;  // 停止日期
    private String operator;  // 维护者
    private Date enterDate;  // 输入日期
    private Double highPrice;  // 最高价格
    private String materialCode;  // 物价码
    private Double score1;  // 
    private Double score2;  // 
    private String priceNameCode;  // 
    private String controlFlag;  // 
    private String inputCode;  // 
    private String inputCodeWb;  // 
    private String stdCode1;  // 
    private String changedMemo;  // 价格变更原因包括调价和停用等都可以录入保存原因
    private String classOnInsurMr;  // 
    private String cwtjCode;  // 
    private String xmWy;  // 未央项目对照
    private String lbWy;  // 未央类别对照
    private String mzsjWy;  // 门诊收据对照
    private String zysjWy;  // 住院收据对照
    private String groupFlag;  // 
    private String stopOperator;  // 
    private String masterId;  // 模板信息表ID

    private String area;
    private String areaName;

    public PriceListTemplate() {
        super();
    }

    public PriceListTemplate(String id) {
        super(id);
    }

    public  String getItemClass() {
        return this.itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public  String getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public  String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public  String getItemSpec() {
        return this.itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public  String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public  Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public  Double getPreferPrice() {
        return this.preferPrice;
    }

    public void setPreferPrice(Double preferPrice) {
        this.preferPrice = preferPrice;
    }

    public  Double getForeignerPrice() {
        return this.foreignerPrice;
    }

    public void setForeignerPrice(Double foreignerPrice) {
        this.foreignerPrice = foreignerPrice;
    }

    public  String getPerformedBy() {
        return this.performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public  Integer getFeeTypeMask() {
        return this.feeTypeMask;
    }

    public void setFeeTypeMask(Integer feeTypeMask) {
        this.feeTypeMask = feeTypeMask;
    }

    public  String getClassOnInpRcpt() {
        return this.classOnInpRcpt;
    }

    public void setClassOnInpRcpt(String classOnInpRcpt) {
        this.classOnInpRcpt = classOnInpRcpt;
    }

    public  String getClassOnOutpRcpt() {
        return this.classOnOutpRcpt;
    }

    public void setClassOnOutpRcpt(String classOnOutpRcpt) {
        this.classOnOutpRcpt = classOnOutpRcpt;
    }

    public  String getClassOnReckoning() {
        return this.classOnReckoning;
    }

    public void setClassOnReckoning(String classOnReckoning) {
        this.classOnReckoning = classOnReckoning;
    }

    public  String getSubjCode() {
        return this.subjCode;
    }

    public void setSubjCode(String subjCode) {
        this.subjCode = subjCode;
    }

    public  String getClassOnMr() {
        return this.classOnMr;
    }

    public void setClassOnMr(String classOnMr) {
        this.classOnMr = classOnMr;
    }

    public  String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public  Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public  Date getStopDate() {
        return this.stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public  String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public  Date getEnterDate() {
        return this.enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public  Double getHighPrice() {
        return this.highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public  String getMaterialCode() {
        return this.materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public  Double getScore1() {
        return this.score1;
    }

    public void setScore1(Double score1) {
        this.score1 = score1;
    }

    public  Double getScore2() {
        return this.score2;
    }

    public void setScore2(Double score2) {
        this.score2 = score2;
    }

    public  String getPriceNameCode() {
        return this.priceNameCode;
    }

    public void setPriceNameCode(String priceNameCode) {
        this.priceNameCode = priceNameCode;
    }

    public  String getControlFlag() {
        return this.controlFlag;
    }

    public void setControlFlag(String controlFlag) {
        this.controlFlag = controlFlag;
    }

    public  String getInputCode() {
        return this.inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public  String getInputCodeWb() {
        return this.inputCodeWb;
    }

    public void setInputCodeWb(String inputCodeWb) {
        this.inputCodeWb = inputCodeWb;
    }

    public  String getStdCode1() {
        return this.stdCode1;
    }

    public void setStdCode1(String stdCode1) {
        this.stdCode1 = stdCode1;
    }

    public  String getChangedMemo() {
        return this.changedMemo;
    }

    public void setChangedMemo(String changedMemo) {
        this.changedMemo = changedMemo;
    }

    public  String getClassOnInsurMr() {
        return this.classOnInsurMr;
    }

    public void setClassOnInsurMr(String classOnInsurMr) {
        this.classOnInsurMr = classOnInsurMr;
    }

    public  String getCwtjCode() {
        return this.cwtjCode;
    }

    public void setCwtjCode(String cwtjCode) {
        this.cwtjCode = cwtjCode;
    }

    public  String getXmWy() {
        return this.xmWy;
    }

    public void setXmWy(String xmWy) {
        this.xmWy = xmWy;
    }

    public  String getLbWy() {
        return this.lbWy;
    }

    public void setLbWy(String lbWy) {
        this.lbWy = lbWy;
    }

    public  String getMzsjWy() {
        return this.mzsjWy;
    }

    public void setMzsjWy(String mzsjWy) {
        this.mzsjWy = mzsjWy;
    }

    public  String getZysjWy() {
        return this.zysjWy;
    }

    public void setZysjWy(String zysjWy) {
        this.zysjWy = zysjWy;
    }

    public  String getGroupFlag() {
        return this.groupFlag;
    }

    public void setGroupFlag(String groupFlag) {
        this.groupFlag = groupFlag;
    }

    public  String getStopOperator() {
        return this.stopOperator;
    }

    public void setStopOperator(String stopOperator) {
        this.stopOperator = stopOperator;
    }

    public  String getMasterId() {
        return this.masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}