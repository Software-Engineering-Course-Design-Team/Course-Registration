
package com.model.javabean;


public class Student {
	private String Name;
	private String sex;
	private String GradDate;
	private String Birthday;
	private long Idcard;
	private long SID;
	private long DID;
	private String status;
	
	
	public Student() {
		super();
	}
	public Student(String name, String sex, String gradDate, String birthday, long idcard, long sID, long dID,
			String status) {
		super();
		Name = name;
		this.sex = sex;
		GradDate = gradDate;
		Birthday = birthday;
		Idcard = idcard;
		SID = sID;
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
	public String getGradDate() {
		return GradDate;
	}
	public void setGradDate(String gradDate) {
		GradDate = gradDate;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}
	public long getIdcard() {
		return Idcard;
	}
	public void setIdcard(long idcard) {
		Idcard = idcard;
	}
	public long getSID() {
		return SID;
	}
	public void setSID(long sID) {
		SID = sID;
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

