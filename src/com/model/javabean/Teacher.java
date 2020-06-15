
package com.model.javabean;

public class Teacher {
	private String Name;
	private String sex;
	private String Birthday;
	private String Idcard;
	private long PID;
	private long DID;
	private String status;
	
	public Teacher() {
		super();
	}
	public Teacher(String name, String sex, String birthday, String idcard, long pID, long dID, String status) {
		super();
		Name = name;
		this.sex = sex;
		Birthday = birthday;
		Idcard = idcard;
		PID = pID;
		DID = dID;
		this.status = status;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}
	public String getIdcard() {
		return Idcard;
	}
	public void setIdcard(String idcard) {
		Idcard = idcard;
	}
	public long getPID() {
		return PID;
	}
	public void setPID(long pID) {
		PID = pID;
	}
	public long getDID() {
		return DID;
	}
	public void setDID(long dID) {
		DID = dID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

