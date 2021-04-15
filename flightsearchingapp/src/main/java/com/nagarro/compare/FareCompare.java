package com.nagarro.compare;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.nagarro.model.CSVData;
import com.nagarro.view.FlightView;

public class FareCompare {

	public void compareFare(List<CSVData> flightList) {
		FlightView view = new FlightView();
		Collections.sort(flightList, new Comparator<CSVData>() {
			public int compare(CSVData o1, CSVData o2) {
				return o1.getFare() - o2.getFare();
			}
		});
		view.viewFlights(flightList);
	}
}
