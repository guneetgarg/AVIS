package com.avis.qa.utilities;

import com.avis.qa.core.Configuration;
import com.avis.qa.utilities.DataConverter;
import com.avis.qa.utilities.JacksonUtils;
import com.avis.qa.utilities.ExcelUtils;
import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;



/**
 * Class contains all generic excel data providers
 *
 * @author ikumar
 */
public class ExcelDataProvider {

    public static final String EXCEL_DATA_AS_POJO = "dataAsPojo";
    public static final String EXCEL_DATA_AS_MAP = "dataAsMap";
    private static String[][] excelData;
    public static String Domain =Configuration.getValue("domain");
    public static String Brand =Configuration.getValue("brand");
    public static final String EXCEL_PATH = Brand+"_Anonymous_"+Domain;
    


    static {
    	System.out.println(EXCEL_PATH);
        excelData = ExcelUtils.getInstance().getAllSheetsData(EXCEL_PATH);
    }

    @SneakyThrows
    @DataProvider(name = EXCEL_DATA_AS_MAP)
    public Object[][] dataAsMap(Method method) {
        List<String[]> testMethodList = DataConverter.getTestMethodDataAsList(excelData,method);
        Map<String, String>[][] testMethodMap = DataConverter.convertListToTwoDHashMap(testMethodList);
        return testMethodMap;
    }



}