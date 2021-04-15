package com.nagarro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CSVData {
	@Id
	@GeneratedValue
	private int serialNo;

	private String flightNo;
	private String deptLoc;
	private String arrLoc;
	private String validTill;
	private int flightTime;
	private float flightDur;
	private int fare;
	private String seatAvailable;
	private String classAvailable;

	public CSVData(String flightNo, String deptLoc, String arrLoc, String validTill, int fare, String classAvailable,
			float flightDur) {
		super();
		this.flightNo = flightNo;
		this.deptLoc = deptLoc;
		this.arrLoc = arrLoc;
		this.validTill = validTill;

		this.flightDur = flightDur;
		this.fare = fare;
		this.classAvailable = classAvailable;

	}

	public int getSerialNo() {
		return serialNo;
	}

	public CSVData() {
		super();

	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDeptLoc() {
		return deptLoc;
	}

	public void setDeptLoc(String deptLoc) {
		this.deptLoc = deptLoc;
	}

	public String getArrLoc() {
		return arrLoc;
	}

	public void setArrLoc(String arrLoc) {
		this.arrLoc = arrLoc;
	}

	public String getValidTill() {
		return validTill;
	}

	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}

	public int getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}

	public float getFlightDur() {
		return flightDur;
	}

	public void setFlightDur(float flightDur) {
		this.flightDur = flightDur;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public String getSeatAvailable() {
		return seatAvailable;
	}

	public void setSeatAvailable(String seatAvailable) {
		this.seatAvailable = seatAvailable;
	}

	public String getClassAvailable() {
		return classAvailable;
	}

	public void setClassAvailable(String classAvailable) {
		this.classAvailable = classAvailable;
	}

}
