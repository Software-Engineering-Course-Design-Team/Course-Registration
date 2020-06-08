
package com.model.javabean;

public class Course {
	private long CID;
	private int term;
	private long DID;
	private int fee;
	private String name;
	private int person;
	private int Beginweek;
	private int EndWeek;
	private long PID;
	
	
	public Course() {
		super();
	}
	public Course(long cID, int term, long dID, int fee, String name, int person, int beginweek, int endWeek,
			long pID) {
		super();
		CID = cID;
		this.term = term;
		DID = dID;
		this.fee = fee;
		this.name = name;
		this.person = person;
		Beginweek = beginweek;
		EndWeek = endWeek;
		PID = pID;
	}
	public long getCID() {
		return CID;
	}
	public void setCID(long cID) {
		CID = cID;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public long getDID() {
		return DID;
	}
	public void setDID(long dID) {
		DID = dID;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPerson() {
		return person;
	}
	public void setPerson(int person) {
		this.person = person;
	}
	public int getBeginweek() {
		return Beginweek;
	}
	public void setBeginweek(int beginweek) {
		Beginweek = beginweek;
	}
	public int getEndWeek() {
		return EndWeek;
	}
	public void setEndWeek(int endWeek) {
		EndWeek = endWeek;
	}
	public long getPID() {
		return PID;
	}
	public void setPID(long pID) {
		PID = pID;
	}
	
	
	
}

