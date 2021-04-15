package com.nagarro.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nagarro.compare.DurationCompare;
import com.nagarro.compare.FareCompare;
import com.nagarro.database.ControllerDao;
import com.nagarro.model.CSVData;

public class FlightController {
	List<CSVData> flightList = new ArrayList<CSVData>();
	ArrayList<String> arr;

	public void searchFlight(List<String> csvData, String deptLoc, String arrivalLoc, String date, String flightClass)
			throws ParseException {

		List<CSVData> list = ControllerDao.getList();

		for (CSVData data : list) {

			Date requestedDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			Date ValidTill = new SimpleDateFormat("dd-MM-yyyy").parse(data.getValidTill());

			if (data.getDeptLoc().equals(deptLoc) && data.getArrLoc().equals(arrivalLoc)
					&& (ValidTill.compareTo(requestedDate) > 0) && (data.getSeatAvailable().equals("Y"))
					&& ("EB".contains(data.getClassAvailable()))) {
				CSVData model = new CSVData(data.getFlightNo(), data.getDeptLoc(), data.getArrLoc(),
						data.getValidTill(), data.getFare(), data.getClassAvailable(), (float) data.getFlightDur());
				flightList.add(model);

			}
		}
	}

	public void updateView(int choiceCode) {

		if (choiceCode == 1) {
			FareCompare fare = new FareCompare();
			fare.compareFare(flightList);

		} else if (choiceCode == 2) {

			DurationCompare duration = new DurationCompare();
			duration.durationCompare(flightList);

		} else {
			System.out.println("Wrong Choice.");
			return;
		}
	}
}