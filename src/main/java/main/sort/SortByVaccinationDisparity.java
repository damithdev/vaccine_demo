package main.sort;

import main.model.Vaccination;

import java.util.Comparator;

// Reusable Comparator for Vaccination Disparity
public class SortByVaccinationDisparity implements Comparator<Vaccination> {
    @Override
    public int compare(Vaccination o1, Vaccination o2) {
        //        Descending Order
        return o2.getDisparity().compareTo(o1.getDisparity());
    }
}
