package com.tweetapp.business;

public class User {
	private String first_name;
	private String last_name;
	private String gender;
	private String dob;
	private String email;
	private String password;
	private String numTwits;
	private String lastLogin;
	private String salt;
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNumTwits() {
		return numTwits;
	}
	public void setNumTwits(String numTwits) {
		this.numTwits = numTwits;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "User [first_name=" + first_name + ", last_name=" + last_name + ", gender=" + gender + ", dob=" + dob
				+ ", email=" + email + ", password=" + password + ", numTwits=" + numTwits + ", lastLogin=" + lastLogin
				+ ", salt=" + salt + "]";
	}
	public User(String first_name, String last_name, String gender, String dob, String email, String password,
			String numTwits, String lastLogin, String salt) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.numTwits = numTwits;
		this.lastLogin = lastLogin;
		this.salt = salt;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	
}