package com.pluralsight;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private static DataSource dataSource;

    public VehicleDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public int vehicleAdd() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO vehicles (VIN, Sold, color, make, model, price, year, mileage, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setInt(1, VehicleInput.addVehicle1());
            preparedStatement.setBoolean(2, VehicleInput.addVehicle2());
            preparedStatement.setString(3, VehicleInput.addVehicle3());
            preparedStatement.setString(4, VehicleInput.addVehicle4());
            preparedStatement.setString(5, VehicleInput.addVehicle5());
            preparedStatement.setDouble(6, VehicleInput.addVehicle6());
            preparedStatement.setInt(7, VehicleInput.addVehicle7());
            preparedStatement.setInt(8, VehicleInput.addVehicle8());
            preparedStatement.setString(9, VehicleInput.addVehicle9());

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

    public int vehicleDelete() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vehicles WHERE VIN = ?;")) {

            preparedStatement.setInt(1, VehicleInput.deleteVehicle());

            int rows = preparedStatement.executeUpdate();

            System.out.println("Rows updated: " + rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public List<Vehicle> searchByPriceRange() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE price BETWEEN ? AND ?;");
             ) {

            preparedStatement.setDouble(1, VehicleInput.searchbyPrice());
            preparedStatement.setDouble(2, VehicleInput.searchbyPrice2());

            try(ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    int vin = resultSet.getInt("VIN");
                    boolean sold = resultSet.getBoolean("Sold");
                    String color = resultSet.getString("color");
                    String make = resultSet.getString("make");
                    String model = resultSet.getString("model");
                    double price = resultSet.getDouble("price");
                    int year = resultSet.getInt("year");
                    int mileage = resultSet.getInt("mileage");
                    String type = resultSet.getString("type");

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, sold);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }
    public List<Vehicle> searchByMakeModel() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE make = ? AND model = ?;");) {

            preparedStatement.setString(1, VehicleInput.searchbyMake());
            preparedStatement.setString(2, VehicleInput.searchbyModel());

            try (ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    int vin = resultSet.getInt("VIN");
                    boolean sold = resultSet.getBoolean("Sold");
                    String color = resultSet.getString("color");
                    String make = resultSet.getString("make");
                    String model = resultSet.getString("model");
                    double price = resultSet.getDouble("price");
                    int year = resultSet.getInt("year");
                    int mileage = resultSet.getInt("mileage");
                    String type = resultSet.getString("type");

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, sold);
                    vehicles.add(vehicle);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }
    public List<Vehicle> searchByYearRange() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE year BETWEEN ? AND ?;");) {

            preparedStatement.setInt(1, VehicleInput.searchbyYear());
            preparedStatement.setInt(2, VehicleInput.searchbyYear2());

            try(ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    int vin = resultSet.getInt("VIN");
                    boolean sold = resultSet.getBoolean("Sold");
                    String color = resultSet.getString("color");
                    String make = resultSet.getString("make");
                    String model = resultSet.getString("model");
                    double price = resultSet.getDouble("price");
                    int year = resultSet.getInt("year");
                    int mileage = resultSet.getInt("mileage");
                    String type = resultSet.getString("type");

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, sold);
                    vehicles.add(vehicle);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }
    public List<Vehicle> searchByColor() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE color = ?;");) {

            preparedStatement.setString(1, VehicleInput.searchbyColor());

            try(ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    int vin = resultSet.getInt("VIN");
                    boolean sold = resultSet.getBoolean("Sold");
                    String color = resultSet.getString("color");
                    String make = resultSet.getString("make");
                    String model = resultSet.getString("model");
                    double price = resultSet.getDouble("price");
                    int year = resultSet.getInt("year");
                    int mileage = resultSet.getInt("mileage");
                    String type = resultSet.getString("type");

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, sold);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }
    public List<Vehicle> searchByMileageRange() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE mileage BETWEEN ? AND ?;");) {

            preparedStatement.setInt(1, VehicleInput.searchbyMileage());
            preparedStatement.setInt(2, VehicleInput.searchbyMileage2());

            try(ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    int vin = resultSet.getInt("VIN");
                    boolean sold = resultSet.getBoolean("Sold");
                    String color = resultSet.getString("color");
                    String make = resultSet.getString("make");
                    String model = resultSet.getString("model");
                    double price = resultSet.getDouble("price");
                    int year = resultSet.getInt("year");
                    int mileage = resultSet.getInt("mileage");
                    String type = resultSet.getString("type");

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, sold);
                    vehicles.add(vehicle);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }
    public List<Vehicle> searchByType() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE type = ?;");) {

            preparedStatement.setString(1, VehicleInput.searchbyType());

            try(ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    int vin = resultSet.getInt("VIN");
                    boolean sold = resultSet.getBoolean("Sold");
                    String color = resultSet.getString("color");
                    String make = resultSet.getString("make");
                    String model = resultSet.getString("model");
                    double price = resultSet.getDouble("price");
                    int year = resultSet.getInt("year");
                    int mileage = resultSet.getInt("mileage");
                    String type = resultSet.getString("type");

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, sold);
                    vehicles.add(vehicle);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }
}
