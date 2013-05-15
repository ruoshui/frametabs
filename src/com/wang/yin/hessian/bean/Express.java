package com.wang.yin.hessian.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.wang.yin.utils.PersonStringUtils;

public class Express implements java.io.Serializable {
	private String message;
	private String nu;
	private String ischeck;
	private String com;
	private String updatetime;
	private String status;
	private String condition;
	private String expressName;
	private List<ExpressData> data;

	public Express() {
		message = "单号不存在或者已经过期";
		nu = "";
		ischeck = "";
		com = "";
		updatetime = PersonStringUtils.pareDateToString(new Date());
		status = "300";
		condition = "";
		expressName = "未知";
		data = new ArrayList<ExpressData>();

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNu() {
		return nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}

	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<ExpressData> getData() {
		return data;
	}

	public void setData(List<ExpressData> data) {
		this.data = data;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

}
