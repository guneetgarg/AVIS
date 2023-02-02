package com.avis.qa.utilities;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVFileReader {
	static String filepath = null;
	
	@SuppressWarnings("unchecked")
	@DataProvider(name = "dataAsMap")
	public static Map<String, String>[][] readDataLineBymethodName(Method method) throws IOException, CsvException {
		int rowCount;
		System.out.println("======= "+System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.dir")+"\\testData\\Paylesscar_DataParameter_US.csv");
		String filepath = System.getProperty("user.dir")+"\\testData\\Paylesscar_DataParameter_US.csv";
		System.out.println("=====filePath");
//		CSVFileReader csvFileReader = new CSVFileReader();

		// Create an object of filereader
		// class with CSV file as a parameter.
		InputStreamReader filereader = new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8);
//		FileReader filereader = new FileReader(filepath);
			System.out.println("FileReader");
		// create csvReader object passing
		// file reader as a parameter
		CSVReader csvReader = new CSVReader(filereader);

		List<String[]> nextRecord = csvReader.readAll();

		Map<String, String>[][] twoDHashMap = new HashMap[1][1];
		HashMap<String, String> map = new HashMap<>();

		for (int i = 1; i < nextRecord.size(); i++) {
			if (nextRecord.get(i)[0].equalsIgnoreCase(method.getName())) {

				// rows or lines
				for (int j = 0; j < nextRecord.get(i).length; j++) {
					if (nextRecord.get(i)[0].equalsIgnoreCase(method.getName())) {
						map.put(nextRecord.get(0)[j].trim(), nextRecord.get(i)[j].trim());
						twoDHashMap[0][0] = map;
					}
				}
			}
		}
		return twoDHashMap;
	}
}
