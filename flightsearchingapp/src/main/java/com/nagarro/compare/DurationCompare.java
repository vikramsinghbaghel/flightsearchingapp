package com.nagarro.compare;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.nagarro.model.CSVData;
import com.nagarro.view.FlightView;

public class DurationCompare {

	FlightView view = new FlightView();

	public void durationCompare(List<CSVData> flightList) {
		Collections.sort(flightList, new Comparator<CSVData>() {
			public int compare(CSVData o1, CSVData o2) {
				return (int) (o1.getFlightDur() - o2.getFlightDur());
			}
		});

		view.viewFlights(flightList);

	}

}
