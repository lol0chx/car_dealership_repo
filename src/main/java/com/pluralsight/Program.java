package com.pluralsight;

import com.pluralsight.contracts.LeaseContractDao;
import com.pluralsight.contracts.SalesContractDao;
import org.apache.commons.dbcp2.BasicDataSource;

import static com.pluralsight.UserInterface.homeScreen;

public class Program {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/cardealershipdatabase");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup");

        VehicleDao dataManager = new VehicleDao(dataSource);
        SalesContractDao dataManager1 = new SalesContractDao(dataSource);
        LeaseContractDao dataManager2 = new LeaseContractDao(dataSource);


        boolean endProgram = false;
        while (!endProgram) {
            endProgram = homeScreen(dataManager, dataManager1, dataManager2);
        }
    }
}
