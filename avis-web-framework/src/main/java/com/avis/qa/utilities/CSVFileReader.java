package com.avis.qa.utilities;

import static com.avis.qa.core.Configuration.BRAND;
import static com.avis.qa.core.Configuration.DOMAIN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVFileReader {
	static String filepath = null;

	/**
	 * loads the data parameter file path
	 */
	public static void loadDataParameter() {

		if (BRAND != null && DOMAIN != null) {
			filepath = "./testdata/" + BRAND + "_DataParameter" + "_" + DOMAIN + ".csv";
		} else {
			System.exit(0);
		}

	}

	@DataProvider(name = "dataAsMap")
	public static Map<String, String>[][] readDataLineBymethodName(Method method) throws IOException, CsvException {
		loadDataParameter();

		BufferedReader filereader = new BufferedReader(new FileReader(filepath));
		// file reader as a parameter
		CSVReader csvReader = new CSVReader(filereader);

		List<String[]> nextRecord = csvReader.readAll();

		Map<String, String>[][] twoDHashMap = new HashMap[nextRecord.size() - 1][1];

		for (int i = 1; i < nextRecord.size(); i++) {
			HashMap<String, String> map = new HashMap<>();
			if (nextRecord.get(i)[0].equalsIgnoreCase(method.getName())) {

				// rows or lines
				for (int j = 0; j < nextRecord.get(i).length; j++) {
					if (nextRecord.get(i)[0].equalsIgnoreCase(method.getName())) {
						map.put(nextRecord.get(0)[j].trim(), nextRecord.get(i)[j].trim());
					}
				}
				twoDHashMap[i - 1][0] = map;

			}
		}
		return twoDHashMap;

	}
}
