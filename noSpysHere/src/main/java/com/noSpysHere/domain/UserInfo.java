package com.noSpysHere.domain;

public class UserInfo {

	private String username;
	private String password;
	private String knock;
	
	public UserInfo(){
		
	}
	
	public UserInfo(String username, String password, String knock){
		this.password = password;
		this.username = username;
		this.knock = knock;
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
	public String getKnock() {
		return knock;
	}
	public void setKnock(String knock) {
		this.knock = knock;
	}
}
