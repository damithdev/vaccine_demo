package main;

import main.model.Vaccination;
import main.sort.SortByVaccinatedRatio;
import main.sort.SortByVaccinationDisparity;
import org.apache.commons.collections4.map.LinkedMap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class VaccineDataHandlerSingleton {
    private static VaccineDataHandlerSingleton instance = null;

    List<Vaccination> data;

    private VaccineDataHandlerSingleton() {
        if(!update()){
            System.out.println("Data Not Ready :(");
        }
    }

    public static VaccineDataHandlerSingleton getInstance() {
        if(instance == null)instance = new VaccineDataHandlerSingleton();

        return instance;
    }

    // Updates existing locally stored vaccination data
    public boolean update(){
        try{
            data = Utility.getVaccineData();
            return data != null;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // Returns top 10 completely vaccinated countries for fully vaccinated ratio
    public void topTenCompletelyVaccinated(){
        data.sort(new SortByVaccinatedRatio());
        List<Vaccination> top = data.subList(0,Math.min(data.size(), 10));
        top.forEach(x-> System.out.println(x.getCountry() + ": Ratio - "+ x.getFullyVaccinatedRatio() + "%"));
    }

    // Returns top 10 countries with the highest disparity between full vaccination and partial vaccination in per 1000 ratio
    public void topTenFullPartialVaccinationDisparity(){
        data.sort(new SortByVaccinationDisparity());
        List<Vaccination> top = data.subList(0, Math.min(data.size(), 10));
        top.forEach(x-> System.out.println(x.getCountry() + ": Disparity - "+ x.getDisparity().setScale(0,RoundingMode.HALF_EVEN)));
    }

    // Returns the difference between most vaccinated and least vaccinated as a per 1000 ratio
    public void vaccinationDifferencePerPopulationOfContinents(){
        Map<String, Long> vaccinatedMap = new HashMap<>();
        Map<String, Long> populationMap = new HashMap<>();
        data.forEach(value -> {
            if(value.getContinent() != null){
                vaccinatedMap.put(value.getContinent(),value.getPeopleVaccinated() + Optional.ofNullable(vaccinatedMap.get(value.getContinent())).orElse(0L));
                populationMap.put(value.getContinent(),value.getPopulation() + Optional.ofNullable(populationMap.get(value.getContinent())).orElse(0L));
            }

        });

        Map<String, BigDecimal> normalizedMap = new HashMap<>();
        populationMap.forEach( (k,v) -> normalizedMap.put(k,Utility.getPerK(vaccinatedMap.get(k),populationMap.get(k))));

        LinkedMap<String,BigDecimal> sortedMap = normalizedMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(ov,nv)-> ov,LinkedMap::new));

        BigDecimal difference = Utility.getDifference(normalizedMap.get(sortedMap.firstKey()),normalizedMap.get(sortedMap.lastKey()));
        System.out.println("Difference between the highest vaccinated ("+sortedMap.lastKey()+") and lowest vaccinated ("+sortedMap.firstKey()+") continent is "+ difference.setScale(0, RoundingMode.HALF_EVEN));


    }

    // Returns globally vaccinated ratios in per 1000 units for,
    // Fully Vaccinated
    // Partially Vaccinated
    // Un-Vaccinated
    public void globalVaccinationRatiosPerK(){

        long vaccinated = 0L;
        long partialVaccinated= 0L;
        long unVaccinated = 0L;
        long population = 0L;

        for (Vaccination vaccination : data) {
            vaccinated += vaccination.getPeopleVaccinated();
            partialVaccinated += vaccination.getPartialVaccinated();
            unVaccinated += (vaccination.getPopulation()-vaccination.getPartialVaccinated());
            population += vaccination.getPopulation();
        }

        System.out.println("Global total vaccinations Per 1000 is " + Utility.getPerK(vaccinated,population).setScale(0,RoundingMode.HALF_EVEN));
        System.out.println("Global total partial vaccinations Per 1000 is " + Utility.getPerK(partialVaccinated,population).setScale(0,RoundingMode.HALF_EVEN));
        System.out.println("Global total not vaccinated Per 1000 is " + Utility.getPerK(unVaccinated,population).setScale(0,RoundingMode.HALF_EVEN));
    }

}
