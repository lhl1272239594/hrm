/**
 * Copyright &copy; 2012-2014 <a href="https://github.com.jims.emr">JeeSite</a> All rights reserved.
 */
package com.jims.common.persistence;


import com.jims.common.supcan.annotation.treelist.SupTreeList;
import com.jims.common.supcan.annotation.treelist.cols.SupCol;
import com.jims.common.utils.StringUtils;
import com.jims.sys.entity.User;
import com.jims.common.utils.UserUtils;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Map;

/**
 * Entity支持类
 * @author ThinkGem
 * @version 2014-05-16
 */
@SupTreeList
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;
	
	/**
	 * 当前用户
	 */
	protected User currentUser;
	
	/**
	 * 当前实体分页对象
	 */
	protected Page<T> page;

	
	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	protected boolean isNewRecord = false;

	public BaseEntity() {
		
	}
	
	public BaseEntity(String id) {
		this();
		this.id = id;
	}

	@SupCol(isUnique="true", isHide="true")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	@XmlTransient
	public User getCurrentUser() {
		if(currentUser == null){
			currentUser = UserUtils.getUser();
		}
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}


	@XmlTransient
	public Page<T> getPage() {
		if (page == null){
			page = new Page<T>();
		}
		return page;
	}
	
	public Page<T> setPage(Page<T> page) {
		this.page = page;
		return page;
	}


	
	/**
	 * 插入之前执行方法，子类实现
	 */
	public abstract void preInsert();
	
	/**
	 * 更新之前执行方法，子类实现
	 */
	public abstract void preUpdate();
	
    /**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     * @return
     */
	public boolean getIsNewRecord() {
        return isNewRecord || StringUtils.isBlank(getId());
    }

	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}



	
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
    
//    @Override
//    public String toString() {
//        return ReflectionToStringBuilder.toString(this);
//    }
    
	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";

    /**
     *签名
     */
    public static final String SIGNATURE_TYPE_DEFAULT = "0";

    /**
     * 检验申请状态（0：申请； 1：完成）
     */
    public static final String LAB_STATUS_APPLY = "0";
    public static final String LAB_STATUS_COMPLETE = "1";
    /**
     * 检验结果状态（0：申请； 1：完成）
     */
    public static final String LAB_RESULTSTATUS_APPLY = "0";
    public static final String LAB_RESULTSTATUS_COMPLETE = "1";

    /***
     * 打印标记(0:未打印；1：打印 )
     */
    public static final Integer PRINTINDICATOR_NOT = 0;
    public static final Integer PRINTINDICATOR_COMPLETE = 1;

    /**
     * 计价标记(0:未计价；1：已计价)
     */
    public static final Integer LAB_BILLINGINDICATOR_APPLY = 0;
    public static final Integer LAB_BILLINGINDICATOR_COMPLETE = 1;

}
