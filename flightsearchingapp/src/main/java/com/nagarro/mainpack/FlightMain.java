package com.nagarro.mainpack;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import com.nagarro.controller.FlightController;
import com.nagarro.csvreader.ReadCSVThread;
import com.nagarro.validation.Validate;

public class FlightMain {
	public static void main(String args[]) throws FileNotFoundException, ParseException, InterruptedException {

		ReadCSVThread thread = new ReadCSVThread();
		thread.start();

		Thread.sleep(3000);

		Scanner scan = new Scanner(System.in);

		String deptLoc;
		String arrivalLoc;
		String date;
		String flightClass;
		int choiceCode;

		List<String> csvData = ReadCSVThread.getCSVData();
		Validate validate = new Validate();
		do {
			System.out.print("Enter Departure Location  : ");
			deptLoc = scan.nextLine().toUpperCase();
		} while (!validate.departLoc(deptLoc));

		do {
			System.out.print("Enter Arrival Location  : ");
			arrivalLoc = scan.nextLine().toUpperCase();
		} while (!validate.arrivalLoc(arrivalLoc));

		do {
			System.out.print("Enter Date  (DD/MM/YYYY) : ");
			date = scan.nextLine().toUpperCase();
		} while (!validate.flightDate(date));

		do {
			System.out.print("Enter Class: (E or B or EB) : ");
			flightClass = scan.nextLine().toUpperCase();
		} while (!validate.flightClass(flightClass));

		System.out.print("Enter Output Preference :   1. Fare \t 2. Flight Duration \nEnter Preference Choice Code : ");
		choiceCode = scan.nextInt();

		csvData = ReadCSVThread.getCSVData();
		FlightController flightcontroller = new FlightController();

		flightcontroller.searchFlight(csvData, deptLoc, arrivalLoc, date, flightClass);
		flightcontroller.updateView(choiceCode);

		WatcherService watcher = new WatcherService();
		Thread thread1 = new Thread(watcher);
		thread1.start();

	}

}
