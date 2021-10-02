package main.sort;

import main.model.Vaccination;

import java.util.Comparator;

// Reusable Comparator for Vaccinated Ratio
public class SortByVaccinatedRatio implements Comparator<Vaccination> {
    @Override
    public int compare(Vaccination o1, Vaccination o2) {
//        Descending Order
        return o2.getFullyVaccinatedRatio().compareTo(o1.getFullyVaccinatedRatio());
    }
}
