package com.amazon.alexa.comms.async.utilities;

import java.util.ArrayList;
import java.util.List;

public class AddressUtil {

    static List<String> addressList = new ArrayList<String>();

    public static List<String> getAddress(String Country) {
        addressList.clear();
        switch (Country) {
            case "US":
                addressList.add("75");
                addressList.add("Griffin St.");
                addressList.add("Parkville");
                addressList.add("MD");
                addressList.add("21234");
                addressList.add("United States");
                break;
            case "GB":
                addressList.add("353");
                addressList.add("The Crescent");
                addressList.add("CAMBRIDGE");
                addressList.add("Essex");
                addressList.add("CB97 6QH");
                addressList.add("United Kingdom");
                break;
            case "DE":
                addressList.add("4");
                addressList.add("Ocean Street");
                addressList.add("Winona");
                addressList.add("MN");
                addressList.add("55987");
                addressList.add("Germany");
                break;
            case "FR":
                addressList.add("68");
                addressList.add("rue Léon Dierx");
                addressList.add("LIMOGES");
                addressList.add("Limousin");
                addressList.add("87280");
                addressList.add("France");
                break;
            case "ES":
                addressList.add("13");
                addressList.add("Arlington Dr.");
                addressList.add("Lawrenceville");
                addressList.add("GA");
                addressList.add("30043");
                addressList.add("Spain");
                break;
            case "IN":
                addressList.add("Shop No 7, Gr Flr, Kenwood Apts");
                addressList.add("Lokhandwala Complex Rd, Opp High Point Resturant, Andheri (west)");
                addressList.add("Mumbai");
                addressList.add("Maharashtra");
                addressList.add("400053");
                addressList.add("India");
                break;
            case "IT":
                addressList.add("85");
                addressList.add("Via Scala");
                addressList.add("Plodio");
                addressList.add("Savona");
                addressList.add("17043");
                addressList.add("Italy");
                break;
            case "JP":
                addressList.add("093");
                addressList.add("0022");
                addressList.add("448-1195");
                addressList.add("Minami 12-jonishi, Abashiri-shi");
                break;
            case "CA":
                addressList.add("4498");
                addressList.add("4498  nd Avenue");
                addressList.add("Kelowna");
                addressList.add("British Columbia");
                addressList.add("V1Y 1P5");
                addressList.add("Canada");
                break;
            case "BR":
                addressList.add("1883");
                addressList.add("Rua Angélica");
                addressList.add("Ananindeua");
                addressList.add("Pará");
                addressList.add("67020-310");
                addressList.add("Brazil");
                break;
            case "MX":
                addressList.add("2832");
                addressList.add("Joaquín Angulo");
                addressList.add("Guadalajara");
                addressList.add("Jalisco");
                addressList.add("23844");
                addressList.add("Mexico");
                break;
            case "AU":
                addressList.add("10");
                addressList.add("Bayley Street");
                addressList.add("YARRA GLEN");
                addressList.add("Victoria");
                addressList.add("3775");
                addressList.add("Australia");
                break;
            case "PL":
                addressList.add("ul. Mila 34");
                addressList.add("Jastrzebie-Zdroj");
                addressList.add("Guadamur");
                addressList.add("Mazowieckie");
                addressList.add("18-300");
                addressList.add("Poland");
                break;
            case "NL":
                addressList.add("55");
                addressList.add("Hofakker");
                addressList.add("Koog aan de Zaan");
                addressList.add("Noord-Holland");
                addressList.add("1541 TK");
                addressList.add("Netherlands");
                break;
            case "AE":
                addressList.add("89610");
                addressList.add("Box No. 89610");
                addressList.add("Dubai");
                addressList.add("Dubai");
                addressList.add("89610");
                addressList.add("United Arab Emirates");
                break;
        }
        return addressList;
    }
}
