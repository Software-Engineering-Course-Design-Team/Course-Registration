
package com.model.javabean;

public class CouStu {
	private long SID;
	private long CID;
	private String Grade;
	
	public CouStu() {
		super();
	}
	public CouStu(long sID, long cID, String grade) {
		super();
		SID = sID;
		CID = cID;
		Grade = grade;
	}
	public long getSID() {
		return SID;
	}
	public void setSID(long sID) {
		SID = sID;
	}
	public long getCID() {
		return CID;
	}
	public void setCID(long cID) {
		CID = cID;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	
	
	
}

