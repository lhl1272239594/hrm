package com.jims.template.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
* 药品价格模板Entity
* @author lgx
* @version 2016-08-12
*/
public class DrugPriceListTemplate extends DataEntity<DrugPriceListTemplate> {

    private static final long serialVersionUID = 1L;
    private String drugCode;  // 药品代码
    private String drugSpec;  // 规格
    private String firmId;  // 厂家标识
    private String units;  // 单位
    private Double tradePrice;  // 市场批发价
    private Double retailPrice;  // 市场零售价
    private Integer amountPerPackage;  // 包装数量
    private String minSpec;  // 最小单位规格
    private String minUnits;  // 最小单位
    private Date startDate;  // 起用日期
    private Date stopDate;  // 停止日期
    private String memos;  // 备注
    private String classOnInpRcpt;  // 
    private String classOnOutpRcpt;  // 
    private String classOnReckoning;  // 
    private String subjCode;  // 
    private String classOnMr;  // 
    private Double hlimitPrice;  // 
    private String priceClass;  // 
    private String passNo;  // 
    private Integer gmp;  //
    private String masterId;  // 模板信息表ID

    private String area;
    private String areaName;

    public DrugPriceListTemplate() {
        super();
    }

    public DrugPriceListTemplate(String id) {
        super(id);
    }

    public  String getDrugCode() {
        return this.drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public  String getDrugSpec() {
        return this.drugSpec;
    }

    public void setDrugSpec(String drugSpec) {
        this.drugSpec = drugSpec;
    }

    public  String getFirmId() {
        return this.firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public  String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public  Double getTradePrice() {
        return this.tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public  Double getRetailPrice() {
        return this.retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public  Integer getAmountPerPackage() {
        return this.amountPerPackage;
    }

    public void setAmountPerPackage(Integer amountPerPackage) {
        this.amountPerPackage = amountPerPackage;
    }

    public  String getMinSpec() {
        return this.minSpec;
    }

    public void setMinSpec(String minSpec) {
        this.minSpec = minSpec;
    }

    public  String getMinUnits() {
        return this.minUnits;
    }

    public void setMinUnits(String minUnits) {
        this.minUnits = minUnits;
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

    public  String getMemos() {
        return this.memos;
    }

    public void setMemos(String memos) {
        this.memos = memos;
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

    public  Double getHlimitPrice() {
        return this.hlimitPrice;
    }

    public void setHlimitPrice(Double hlimitPrice) {
        this.hlimitPrice = hlimitPrice;
    }

    public  String getPriceClass() {
        return this.priceClass;
    }

    public void setPriceClass(String priceClass) {
        this.priceClass = priceClass;
    }

    public  String getPassNo() {
        return this.passNo;
    }

    public void setPassNo(String passNo) {
        this.passNo = passNo;
    }

    public  Integer getGmp() {
        return this.gmp;
    }

    public void setGmp(Integer gmp) {
        this.gmp = gmp;
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