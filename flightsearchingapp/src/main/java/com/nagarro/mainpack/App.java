package com.nagarro.mainpack;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import com.nagarro.controller.FlightController;
import com.nagarro.services.ReadCSV;
import com.nagarro.services.WatcherService;
import com.nagarro.validation.Validate;

public class App {
	public static void main(String args[]) throws FileNotFoundException, ParseException, InterruptedException {
		Scanner scan = new Scanner(System.in);
		int repeat = 0;
		do {
			ReadCSV thread = new ReadCSV();
			thread.start();

			Thread.sleep(3000);

			String deptLoc;
			String arrivalLoc;
			String date;
			String flightClass;
			int choiceCode;

			List<String> csvData;
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
				System.out.print("Enter Date  (DD-MM-YYYY) : ");
				date = scan.nextLine().toUpperCase();
			} while (!validate.flightDate(date));

			do {
				System.out.print("Enter Class: (E or B or EB) : ");
				flightClass = scan.nextLine().toUpperCase();
			} while (!validate.flightClass(flightClass));

			do {
				System.out.print(
						"Enter Output Preference :   1. Fare \t 2. Flight Duration \nEnter Preference Choice Code : ");
				choiceCode = scan.nextInt();

			} while (choiceCode != 1 && choiceCode != 2);
			
			csvData = ReadCSV.getCSVData();
			
			FlightController flightcontroller = new FlightController();

			flightcontroller.searchFlight(csvData, deptLoc, arrivalLoc, date, flightClass);
			flightcontroller.updateView(choiceCode);

			WatcherService watcher = new WatcherService();
			Thread thread1 = new Thread(watcher);
			thread1.start();

			thread.sleep(3000);

			System.out.println("Please type 1 if you want to search more flights: ");
			repeat = scan.nextInt();

		} while (repeat == 1);

	}
}
