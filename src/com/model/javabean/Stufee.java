
package com.model.javabean;

public class Stufee {
	private String status;
	private int fee;
	private int term;
	private long SID;
	
	public Stufee() {
		super();
	}
	public Stufee(String status, int fee, int term, long sID) {
		super();
		this.status = status;
		this.fee = fee;
		this.term = term;
		SID = sID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public long getSID() {
		return SID;
	}
	public void setSID(long sID) {
		SID = sID;
	}
	
}
