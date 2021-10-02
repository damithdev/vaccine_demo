package main;

import java.util.Scanner;

public class Demo{
    public static void main(String[] args) {

        int entry;
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        do {
            System.out.println("\n\n\n\n\n\n\n");
            System.out.flush();
            System.out.println("============================");
            System.out.println("|   MENU SELECTION");
            System.out.println("============================");
            System.out.println("| Options:");
            System.out.println("|        1. Top 10 Highest Completely vaccinated nations as a % of Population");
            System.out.println("|        2. Top 10 Highest Disparity Between Fully/Partially Vaccinated per 1000");
            System.out.println("|        3. Difference between the highest/lowest vaccinated continent");
            System.out.println("|        4. Global total ratios");
            System.out.println("|---------------------------");
            System.out.println("|        8. Refresh Data");
            System.out.println("|        9. Exit");
            System.out.println("============================");
            System.out.print(" Select option: ");
            System.out.flush();
            entry = scanner.nextInt();

            switch (entry)
            {
                case 1:
                    VaccineDataHandlerSingleton.getInstance().topTenCompletelyVaccinated();
                    break;
                case 2:
                    VaccineDataHandlerSingleton.getInstance().topTenFullPartialVaccinationDisparity();
                    break;
                case 3:
                    VaccineDataHandlerSingleton.getInstance().vaccinationDifferencePerPopulationOfContinents();
                    break;
                case 4:
                    VaccineDataHandlerSingleton.getInstance().globalVaccinationRatiosPerK();
                    break;
                case 8:
                    VaccineDataHandlerSingleton.getInstance().update();
                    break;
                case 9:
                    System.out.println("Exiting now...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Input.");
            }



            System.out.println(" Press Enter To Continue... ");
            System.out.flush();
            try
            {
                System.in.read();
            }
            catch(Exception e)
            {}
        } while (!exit);
        System.out.println("Thank you!");
    }
}
