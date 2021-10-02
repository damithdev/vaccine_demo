package main.model;

import java.math.BigDecimal;

public class Vaccination {

    //    Original Data Points
    String country;
    String continent;
    long peopleVaccinated;
    long partialVaccinated;
    long population;

    //    Generated Data points
    BigDecimal fullyVaccinatedRatio;
    BigDecimal fullyVaccinatedPerK;
    BigDecimal partiallyVaccinatedPerK;
    BigDecimal disparity;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public long getPeopleVaccinated() {
        return peopleVaccinated;
    }

    public void setPeopleVaccinated(long peopleVaccinated) {
        this.peopleVaccinated = peopleVaccinated;
    }

    public long getPartialVaccinated() {
        return partialVaccinated;
    }

    public void setPartialVaccinated(long partialVaccinated) {
        this.partialVaccinated = partialVaccinated;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public BigDecimal getFullyVaccinatedRatio() {
        return fullyVaccinatedRatio;
    }

    public void setFullyVaccinatedRatio(BigDecimal fullyVaccinatedRatio) {
        this.fullyVaccinatedRatio = fullyVaccinatedRatio;
    }

    public BigDecimal getFullyVaccinatedPerK() {
        return fullyVaccinatedPerK;
    }

    public void setFullyVaccinatedPerK(BigDecimal fullyVaccinatedPerK) {
        this.fullyVaccinatedPerK = fullyVaccinatedPerK;
    }

    public BigDecimal getPartiallyVaccinatedPerK() {
        return partiallyVaccinatedPerK;
    }

    public void setPartiallyVaccinatedPerK(BigDecimal partiallyVaccinatedPerK) {
        this.partiallyVaccinatedPerK = partiallyVaccinatedPerK;
    }

    public BigDecimal getDisparity() {
        return disparity;
    }

    public void setDisparity(BigDecimal disparity) {
        this.disparity = disparity;
    }


    @Override
    public String toString() {
        return
                "\nCountry='" + country + '\'' +
                        "\nContinent='" + continent + '\'' +
                        "\nPeople Vaccinated=" + peopleVaccinated +
                        "\nPartial Vaccinated=" + partialVaccinated +
                        "\nPopulation=" + population +
                        "\nFully Vaccinated Ratio=" + fullyVaccinatedRatio +
                        "\nFully Vaccinated Per K=" + fullyVaccinatedPerK +
                        "\nPartially Vaccinated Per K=" + partiallyVaccinatedPerK +
                        "\nDisparity=" + disparity + "\n";
    }
}
