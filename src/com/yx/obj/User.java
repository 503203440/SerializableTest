package com.yx.obj;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	
	private static final long serialVersionUID=666666;
	
	private String username="default name";
	private Integer age=18;
	private Date birthday=new Date();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	//÷ÿ–¥toString
	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + ", birthday=" + birthday + "]";
	}
	
	
	
	
	
}
