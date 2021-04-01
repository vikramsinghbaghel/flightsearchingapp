package com.nagarro.controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nagarro.HibernateUtil;
import com.nagarro.csvreader.CSVData;
import com.nagarro.display.FlightView;
import com.nagarro.flightdetails.FlightModel;

public class FlightController {
	ArrayList<FlightModel> flightList = new ArrayList<FlightModel>();
	ArrayList<String> arr;
	FlightView view = new FlightView();

	public void searchFlight(List<String> csvData, String deptLoc, String arrivalLoc, String date, String flightClass)
			throws FileNotFoundException, ParseException {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tnx = session.beginTransaction();

		Query query = session.createQuery("from CSVData ");
		List<CSVData> list = query.list();
		List<CSVData> res = new ArrayList<CSVData>();
		for (CSVData l : list) {

			Date requestedDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			Date ValidTill = new SimpleDateFormat("dd-MM-yyyy").parse(l.getValidTill());

			if (l.getDeptLoc().equals(deptLoc) && l.getArrLoc().equals(arrivalLoc)
					&& (ValidTill.compareTo(requestedDate) == 0) && (l.getSeatAvailable().equals("Y"))
					&& ("EB".contains(l.getClassAvailable()))) {
				FlightModel model = new FlightModel(l.getFlightNo(), l.getDeptLoc(), l.getArrLoc(), l.getValidTill(),
						l.getFare(), l.getSeatAvailable(), (float) l.getFlightDur());
				flightList.add(model);

			}
		}
		tnx.commit();
	}

	public void updateView(int choiceCode) {
		if (choiceCode == 1) {
			Collections.sort(flightList, new Comparator<FlightModel>() {
				public int compare(FlightModel o1, FlightModel o2) {
					return o1.getFare() - o2.getFare();
				}
			});
		} else if (choiceCode == 2) {
			Collections.sort(flightList, new Comparator<FlightModel>() {
				public int compare(FlightModel o1, FlightModel o2) {
					int a = ((o1.getDuration() - o2.getDuration()) > 0) ? 1
							: ((o1.getDuration() - o2.getDuration()) < 0) ? -1 : 0;
					return a;
				}
			});
		} else {
			System.out.println("Wrong Choice.");
			return;
		}
		view.viewFlights(flightList);

	}
}