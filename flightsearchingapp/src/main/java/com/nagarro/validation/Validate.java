package com.nagarro.validation;

public class Validate {
	public boolean departLoc(String deptLoc) {
		if (deptLoc.length() != 3) {
			System.out.println("The depart Location should have 3 characters");
			return false;
		} else if (deptLoc.matches("[A-Z][A-Z][A-Z]")) {
			return true;
		} else {
			System.out.println("Should contain only Alphabets");
			return false;
		}
	}

	public boolean arrivalLoc(String arrivalLoc) {
		if (arrivalLoc.length() != 3) {
			System.out.println("The arrival Location should have 3 characters");
			return false;
		} else if (arrivalLoc.matches("[A-Z][A-Z][A-Z]")) {
			return true;
		} else {
			System.out.println("Should contain only Alphabets");
			return false;
		}
	}

	public boolean flightDate(String date) {
		if (date.matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
			return true;
		} else {
			System.out.println("DD/MM/YYYY should be the date format");
			return false;
		}
	}

	public boolean flightClass(String flightClass) {
		if (flightClass.equals("E") || flightClass.equals("B") || flightClass.equals("EB")) {
			return true;
		} else {
			System.out.println("The flight Class Should be either E or B or EB ");
			return false;
		}

	}

}
