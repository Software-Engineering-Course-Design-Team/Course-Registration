
package com.model.javabean;


public class CouTea {
	private long PID;
	private String Name; 
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public CouTea() {
		super();
	}
	public CouTea(long pID,String name) {
		super();
		PID = pID;
		Name=name;
	}

	public long getPID() {
		return PID;
	}
	public void setPID(long pID) {
		PID = pID;
	}
	
}

