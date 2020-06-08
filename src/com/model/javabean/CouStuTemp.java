
package com.model.javabean;

public class CouStuTemp {
	private long SID;
	private long CID;
	private int order;
	
	public CouStuTemp() {
		super();
	}
	public CouStuTemp(long sID, long cID, int order) {
		super();
		SID = sID;
		CID = cID;
		this.order = order;
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
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
}
