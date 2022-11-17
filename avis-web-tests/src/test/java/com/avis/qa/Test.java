package com.avis.qa;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
    public static void main(String args[]) {

String text="All Vehicles (15)";
        System.out.println(text.substring(14,16));
        ArrayList<Double> s = new ArrayList<>();
           s.add (Double.parseDouble("$1,085.24".replaceAll(",", "").substring(1).trim()));
        s.add(12.22);
        s.add(15.25);
        s.add(18.20);
        s.add(1025.29);
        s.add(12.22);
        s.add(17.22);
        System.out.println("obtainedList "+s);
        ArrayList<Double> sortedList1 = new ArrayList<>();
        for(Double s1:s){
            sortedList1.add(s1);
        }
        Collections.sort(sortedList1);
        System.out.println("sorted list "+sortedList1);


    }

}
