package com.pluralsight.contracts;

import com.pluralsight.fileManager.ContractsFileManager;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;

public class LeaseContractDao {
    private static DataSource dataSource;

    public LeaseContractDao(DataSource dataSource){
        this.dataSource = dataSource;
    }


    public static int addLeaseContract() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO leaseContracts (VIN, Date) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setString(1, ContractsFileManager.addLeaseContract());
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));

            int rows = preparedStatement.executeUpdate();

            System.out.println("Rows updated: " + rows);

            try (ResultSet keys = preparedStatement.getGeneratedKeys();) {

                boolean results = false;
                while (keys.next()) {
                    results = true;
                    System.out.println("Keys added: " + keys.getInt(1));
                    return keys.getInt(1);
                }if (!results){
                    System.out.println("No results were found");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

}
