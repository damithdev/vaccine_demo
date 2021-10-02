package main;

import main.model.Vaccination;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    // Fetch vaccination data from api and map to List<Vaccination>
    public static List<Vaccination> getVaccineData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-api.mmediagroup.fr/v1/vaccines"))
                .GET()
                .build();

        try {
            System.out.println("Loading data...");
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JSONObject data = new JSONObject(response.body());

            List<Vaccination> list = new ArrayList<>();
            data.remove("Global");
            data.keys().forEachRemaining(key -> {
                try {
                    JSONObject item = data.getJSONObject(key).getJSONObject("All");
                    Vaccination object = new Vaccination();
                    object.setContinent(item.has("continent")?item.getString("continent"):null);
                    object.setCountry(key);
                    object.setPopulation(item.getLong("population"));
                    object.setPeopleVaccinated(item.getLong("people_vaccinated"));
                    object.setPartialVaccinated(item.getLong("people_partially_vaccinated"));
                    object.setFullyVaccinatedRatio(getPercentage(object.getPeopleVaccinated(), object.getPopulation()));
                    object.setFullyVaccinatedPerK(getPerK(object.getPeopleVaccinated(), object.getPopulation()));
                    object.setPartiallyVaccinatedPerK(getPerK(object.getPartialVaccinated(), object.getPopulation()));
                    object.setDisparity(getDifference(object.getFullyVaccinatedPerK(), object.getPartiallyVaccinatedPerK()));
                    list.add(object);
//                    System.out.println(object);
                } catch (Exception e) {
                    System.out.println(key + " Skipped ! Reason:" + e.getMessage());
                }


            });
            System.out.println("============================Complete==============================");
            return list;
        } catch (Exception e) {
            System.out.println("============================FAILED==============================");
            e.printStackTrace();
            return null;
        }

    }

    public static final BigDecimal HUNDRED = new BigDecimal(100);
    public static final BigDecimal THOUSAND = new BigDecimal(1000);

    //    Rounding mode half_even is used which is considered unbiased

    //Returns percentage as a decimal for provided fraction and total
    public static BigDecimal getPercentage(long part, long total) {
        return new MyBigDecimal(part).multiply(HUNDRED).divide(new MyBigDecimal(total), RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN);
    }

    //Returns per 1000 ratio for provided fraction and total
    public static BigDecimal getPerK(long part, long total) {
        return new MyBigDecimal(part).divide(new MyBigDecimal(total).divide(THOUSAND, RoundingMode.HALF_EVEN), RoundingMode.HALF_EVEN);
    }

    //Returns Difference as a positive decimal for provided two decimal values
    public static BigDecimal getDifference(BigDecimal part1, BigDecimal part2) {
        return part1.subtract(part2).abs();
    }

}
