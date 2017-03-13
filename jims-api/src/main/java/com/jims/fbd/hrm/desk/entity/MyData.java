/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.fbd.hrm.desk.entity;

import com.jims.common.persistence.DataEntity;


public class MyData extends DataEntity<MyData> {

	private static final long serialVersionUID = 1L;
	private String leave_time;		    // 请假时长
	private String leave_nums;		    // 请假次数
	private String overtime_time;	    // 加班时长
    private String overtime_nums;   	// 加班次数
    private String lieu_time;   		// 调休时长
    private String lieu_nums;       	// 调休次数
    private String trip_time;      		// 公出时长
    private String trip_nums; 			// 公出次数

	public String getLeave_time() {
		return leave_time;
	}

	public void setLeave_time(String leave_time) {
		this.leave_time = leave_time;
	}

	public String getLeave_nums() {
		return leave_nums;
	}

	public void setLeave_nums(String leave_nums) {
		this.leave_nums = leave_nums;
	}

	public String getOvertime_time() {
		return overtime_time;
	}

	public void setOvertime_time(String overtime_time) {
		this.overtime_time = overtime_time;
	}

	public String getOvertime_nums() {
		return overtime_nums;
	}

	public void setOvertime_nums(String overtime_nums) {
		this.overtime_nums = overtime_nums;
	}

	public String getLieu_time() {
		return lieu_time;
	}

	public void setLieu_time(String lieu_time) {
		this.lieu_time = lieu_time;
	}

	public String getLieu_nums() {
		return lieu_nums;
	}

	public void setLieu_nums(String lieu_nums) {
		this.lieu_nums = lieu_nums;
	}

	public String getTrip_time() {
		return trip_time;
	}

	public void setTrip_time(String trip_time) {
		this.trip_time = trip_time;
	}

	public String getTrip_nums() {
		return trip_nums;
	}

	public void setTrip_nums(String trip_nums) {
		this.trip_nums = trip_nums;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


}