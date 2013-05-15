package com.wang.yin.hessian.bean;

import java.io.Serializable;

public class User  implements Serializable {
	private String username;
	private String password;
	private String id;
	private String str;

	public User() {
		username = "vv";
		password = "123456";
		id = "2";

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

}
