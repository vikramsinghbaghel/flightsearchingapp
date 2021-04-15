package com.nagarro.view;

import java.util.List;

import com.nagarro.model.CSVData;

public class FlightView {

	public void viewFlights(List<CSVData> flightList) {
		System.out.println("\n \t\t\t ***** FLIGHT INFORMATION *****");
		System.out
				.println("FLIGHT_NO|DEP_LOC        |ARR_LOC        |VALID_TILL | FARE     |FlightClass    |DURATION|");
		for (CSVData flight : flightList) {
			System.out.print(" " + flight.getFlightNo());
			System.out.print("\t |\t" + flight.getDeptLoc());
			System.out.print("\t |\t" + flight.getArrLoc());
			System.out.print("\t |" + flight.getValidTill());
			System.out.print(" | " + flight.getFare());
			System.out.print("\t|\t" + flight.getClassAvailable());
			System.out.println("\t|  " + flight.getFlightDur() + "\t |");
		}
		if (flightList.isEmpty()) {
			System.out.println("Flights Not Available.");
		}
	}
}
