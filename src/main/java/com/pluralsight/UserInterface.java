package com.pluralsight;

import com.pluralsight.contracts.LeaseContractDao;
import com.pluralsight.contracts.SalesContractDao;

import java.util.List;
import java.util.Scanner;

import static com.pluralsight.VehicleInput.getNumberChoice;
import static java.lang.System.out;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);

    public UserInterface() {
    }

    public static boolean homeScreen(VehicleDao dataManager, SalesContractDao dataManager2, LeaseContractDao dataManager3) {
        String options = ("""
                What do you want to do?
                 1) Add a vehicle
                 2) Delete a vehicle
                 3) Search by price range
                 4) Search by make & model
                 5) Search by year range
                 6) Search by color
                 7) Search by mileage range
                 8) Search by type
                 9) Sell a vehicle
                 10) Lease a vehicle
                 0) Exit
                Select an option:
                """);
        switch (getNumberChoice(options)) {
            case 1:
                int vehiclesAdd = dataManager.vehicleAdd();
                System.out.println(vehiclesAdd);
                break;
            case 2:
                int vehiclesDelete = dataManager.vehicleDelete();
                System.out.println(vehiclesDelete);
                break;
            case 3:
                List<Vehicle> vehiclePrice = dataManager.searchByPriceRange();
                System.out.println(vehiclePrice);
                break;
            case 4:
                List<Vehicle> vehicleMakeModel = dataManager.searchByMakeModel();
                System.out.println(vehicleMakeModel);
                break;
            case 5:
                List<Vehicle> vehicleYear = dataManager.searchByYearRange();
                System.out.println(vehicleYear);
                break;
            case 6:
                List<Vehicle> vehicleColor = dataManager.searchByColor();
                System.out.println(vehicleColor);
                break;
            case 7:
                List<Vehicle> vehicleMileage = dataManager.searchByMileageRange();
                System.out.println(vehicleMileage);
                break;
            case 8:
                List<Vehicle> vehicleType = dataManager.searchByType();
                System.out.println(vehicleType);
                break;
            case 9:
                int salesContract = SalesContractDao.addSalesContract();
                out.println(salesContract);
                break;
            case 10:
                int leaseContract = LeaseContractDao.addLeaseContract();
                out.println(leaseContract);
                break;
            case 0:
                System.exit(0);
                break;
            default:
                out.println("that's not an option");
                break;
        }
        return false;
    }
}
