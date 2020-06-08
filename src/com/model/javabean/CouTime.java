
package com.model.javabean;


public class CouTime {
	private long CID;
	private int WeekDay;
	private int BeginC;
	private int EndC;
	
	public CouTime() {
		super();
	}
	public CouTime(long cID, int weekDay, int beginC, int endC) {
		super();
		CID = cID;
		WeekDay = weekDay;
		BeginC = beginC;
		EndC = endC;
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
