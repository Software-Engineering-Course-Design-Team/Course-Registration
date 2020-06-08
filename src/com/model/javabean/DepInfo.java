
package com.model.javabean;

public class DepInfo {
	private String Name;
	private long DID;
	
	public DepInfo() {
		super();
	}
	public DepInfo(String name, long dID) {
		super();
		Name = name;
		DID = dID;
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
	
}
