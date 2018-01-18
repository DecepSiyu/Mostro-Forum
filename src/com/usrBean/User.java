package com.usrBean;

import java.sql.Date;

public class User {
	private String usrname;
	private String password;
	private Date birthday;
	private String sex;
	private String Email;
	private boolean isAdmin;

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public void setAdmin(boolean flag) {
		isAdmin = flag;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

}
