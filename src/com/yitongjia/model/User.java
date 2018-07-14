package com.yitongjia.model;
/**
 * 
 * @author bym @date 2018年7月4日
 *
 */
public class User {
	private int id;
	private String openID;
	private String userName;
	private int status;
	
	
	public User(String openID, int status) {
		super();
		this.openID = openID;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
