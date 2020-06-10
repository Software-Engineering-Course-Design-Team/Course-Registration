
package com.model.javabean;

public class DepInfo {
	private String Name;
	private long DID;
	private int status;
	private String Date;
	
	
	public DepInfo() {
		super();
	}
	public DepInfo(String name, long dID, int status, String date) {
		super();
		Name = name;
		DID = dID;
		this.status = status;
		Date = date;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public long getDID() {
		return DID;
	}
	public void setDID(long dID) {
		DID = dID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
	
	
}
