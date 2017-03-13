package com.jims.demo.entity;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * HospitalDict entity. @author MyEclipse Persistence Tools
 */

public class HospitalDict implements java.io.Serializable {

	// Fields

	private String id;
	private String hospitalName;
    private String parentHospital ;
	private String unitCode;
	private String location;
	private String zipCode;
	private String organizationFullCode;
    private String inputCode ;

	// Constructors

	/** default constructor */
	public HospitalDict() {
	}


    public void prePersist(){
    }

	/** full constructor */
	public HospitalDict(String hospitalName,
                        String parentHospital, String unitCode, String location, String zipCode,
                        String organizationFullCode, String inputCode) {
		this.hospitalName = hospitalName;
        this.parentHospital = parentHospital;
        this.unitCode = unitCode;
		this.location = location;
		this.zipCode = zipCode;
		this.organizationFullCode = organizationFullCode;
        this.inputCode = inputCode;
    }


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


    public String getParentHospital() {
        return parentHospital;
    }

    public void setParentHospital(String parentHospital) {
        this.parentHospital = parentHospital;
    }

	public String getHospitalName() {
		return this.hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getUnitCode() {
		return this.unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getOrganizationFullCode() {
		return this.organizationFullCode;
	}

	public void setOrganizationFullCode(String organizationFullCode) {
		this.organizationFullCode = organizationFullCode;
	}

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }




}