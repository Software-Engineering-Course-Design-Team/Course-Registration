
package com.model.javabean;

public class Count {
	private int Identity;
	private String password;
	private long ID;
	
	
	public Count() {
		super();
	}
	public Count(int identity, String password, long iD) {
		super();
		Identity = identity;
		this.password = password;
		ID = iD;
	}
	public int getIdentity() {
		return Identity;
	}
	public void setIdentity(int identity) {
		Identity = identity;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	
}
