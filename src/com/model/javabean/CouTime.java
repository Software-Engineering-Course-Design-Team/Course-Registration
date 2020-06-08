
package com.model.javabean;


public class CouTime {
	private long CID;
	private int WeekDay;
	private int BeginC;
	private int EndC;
	private String Address;
	public CouTime() {
		super();
	}
	
	public CouTime(long cID, int weekDay, int beginC, int endC, String address) {
		super();
		CID = cID;
		WeekDay = weekDay;
		BeginC = beginC;
		EndC = endC;
		Address = address;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public long getCID() {
		return CID;
	}
	public void setCID(long cID) {
		CID = cID;
	}
	public int getWeekDay() {
		return WeekDay;
	}
	public void setWeekDay(int weekDay) {
		WeekDay = weekDay;
	}
	public int getBeginC() {
		return BeginC;
	}
	public void setBeginC(int beginC) {
		BeginC = beginC;
	}
	public int getEndC() {
		return EndC;
	}
	public void setEndC(int endC) {
		EndC = endC;
	}
	
}
