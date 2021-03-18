package com.tweetapp.business;

public class ForgotPassword {

	private String password;
	private String Newpassword;
	private String ConfirmNewpassword;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewpassword() {
		return Newpassword;
	}
	public void setNewpassword(String newpassword) {
		Newpassword = newpassword;
	}
	public String getConfirmNewpassword() {
		return ConfirmNewpassword;
	}
	public void setConfirmNewpassword(String confirmNewpassword) {
		ConfirmNewpassword = confirmNewpassword;
	}
	public ForgotPassword(String password, String newpassword, String confirmNewpassword) {
		super();
		this.password = password;
		Newpassword = newpassword;
		ConfirmNewpassword = confirmNewpassword;
	}
	
}
