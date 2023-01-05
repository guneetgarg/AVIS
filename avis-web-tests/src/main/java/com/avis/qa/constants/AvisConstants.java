package com.avis.qa.constants;

import com.avis.qa.core.Configuration;

public class AvisConstants {

    public static final String REGRESSION = "regression";
    public static final String SANITY = "sanity";
    public static final String SMOKE = "smoke";

    public static final String PAYLESSCAR = "Paylesscar";
    public static final String AVIS = "Avis";
    public static final String BUDGET = "Budget";
    public static final String TEST_DATA = "testData";
    public static final String HOMEPAGE = "Homepage";
    public static final String RESERVATION = "Reservation";
    public static final String CreditCardNumber = "343710684134463";
    public static final String CARDEXPIRATIONDATE = "0630"; //mmyy
    public static final String CVV = "123";

    public static final String Default_PickUpTime_Noon = "12:00 PM";
    public static final String Default_DropTime_Noon = "12:00 PM";
    public static final String ResidentLoc = "U S A";
    public static final int FLIGHT_NAME = 3; //dropdown index value

    public static String getAvisUrl(String path) {
        return Configuration.URL.replace("/en/home", path);
    }

}
