package com.nagarro.csvreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nagarro.HibernateUtil;

public class ReadCSVThread extends Thread {
	private static final String srcDir = "src/main/resources";
	private static ArrayList<String> listOfFile = new ArrayList<String>();

	static public ArrayList<String> getAllFiles() {
		try {
			File folder = new File(srcDir);
			for (File file : folder.listFiles()) {
				String name = file.getName();
				if (!listOfFile.contains(name)) {
					listOfFile.add(name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfFile;
	}

	static List<String> csvData = new ArrayList<String>();
	static List<String> arr;

	static public List<String> readCSV() {
		if (listOfFile.size() > 0) {
			for (int i = 0; i < listOfFile.size(); i++) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(srcDir + "/" + listOfFile.get(i)));

					String str_line = "";
					br.readLine();
					while ((str_line = br.readLine()) != null) {

						if (!csvData.contains(str_line)) {
							StringTokenizer token = new StringTokenizer(str_line, "|");
							arr = new ArrayList(str_line.length());
							while (token.hasMoreTokens()) {
								arr.add(token.nextToken());
							}
							Object[] objArr = arr.toArray();

							// convert Object array to String array
							String[] str = Arrays.copyOf(objArr, objArr.length, String[].class);

							CSVData row = new CSVData();
							row.setFlightNo(str[0]);
							row.setDeptLoc(str[1]);
							row.setArrLoc(str[2]);
							row.setValidTill(str[3]);
							row.setFlightTime(Integer.parseInt(str[4]));
							row.setFlightDur(Double.parseDouble(str[5]));
							row.setFare(Integer.parseInt(str[6]));
							row.setSeatAvailable(str[7]);
							row.setClassAvailable(str[8]);

							SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

							Session session = sessionFactory.openSession();
							Transaction tnx = session.beginTransaction();

							session.save(row);
							tnx.commit();

							csvData.add(str_line);
						}
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("Error occurs");
				}
			}
		}
		return csvData;
	}

	public static List<String> getCSVData() {
		return csvData;
	}

	@Override
	public void run() {
		while (true) {
			getAllFiles();
			readCSV();
			try {
				Thread.sleep(10000);// sleep for 10 second
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}