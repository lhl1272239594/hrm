package com.jims.fbd.hrm.notice.entity;

import com.jims.common.persistence.DataEntity;
import com.jims.fbd.hrm.tool.vo.PersonVo;

import java.util.List;

/**
 * 公告管理Entity
 * @author 
 * @version 2016-09-27
 */
public class Notice extends DataEntity<Notice> {

	private static final long serialVersionUID = 1L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

    private String noticeId;
	private String state;
	private String start;
	private String end;
	private String orgName;
	private String name;
	private String personId;
    private String dataId;
	private String createDept;
	private String createDeptname;
	private String deptIds;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String getCreateDeptname() {
		return createDeptname;
	}

	public void setCreateDeptname(String createDeptname) {
		this.createDeptname = createDeptname;
	}

	@Override
	public String getCreateDept() {
		return createDept;
	}

	@Override
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    //
    private String depId;             //部门ID
    private String depName;             //部门名称

    private String userId;
	private List<PersonVo> personVos;

	public List<PersonVo> getPersonVos() {
		return personVos;
	}

	public void setPersonVos(List<PersonVo> personVos) {
		this.personVos = personVos;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getMan() {
		return man;
	}

	public void setMan(String man) {
		this.man = man;
	}

	private String man;
	private byte[] content;

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	private String orgId;
	private String enableFlag;
	private String num;
    private String publishBy;
    private String publishDate;
	private String delFlag;


	@Override
	public String getDelFlag() {
		return delFlag;
	}

	@Override
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}




	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublishBy() {
		return publishBy;
	}

	public void setPublishBy(String publishBy) {
		this.publishBy = publishBy;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public Notice() {
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}










}