package com.pluralsight.daos;

import com.pluralsight.models.Vehicle;

import javax.sql.DataSource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleDao {
    private static DataSource dataSource;

    @Autowired
    public VehicleDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    // --- CRUD methods for Controllers ---
    public int createVehicle(Vehicle v) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO vehicles (VIN, Sold, color, make, model, price, year, mileage, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);",
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, v.getVin());
            ps.setBoolean(2, false);
            ps.setString(3, v.getColor());
            ps.setString(4, v.getMake());
            ps.setString(5, v.getModel());
            ps.setDouble(6, v.getPrice());
            ps.setInt(7, v.getYear());
            ps.setInt(8, v.getOdometer());
            ps.setString(9, v.getVehicleType());
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateVehicle(Vehicle v) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE vehicles SET Sold = ?, color = ?, make = ?, model = ?, price = ?, year = ?, mileage = ?, type = ? WHERE VIN = ?")) {
            ps.setBoolean(1, v.getPrice() == 0 ? false : false); // Sold not exposed in model setters; keep false
            ps.setString(2, v.getColor());
            ps.setString(3, v.getMake());
            ps.setString(4, v.getModel());
            ps.setDouble(5, v.getPrice());
            ps.setInt(6, v.getYear());
            ps.setInt(7, v.getOdometer());
            ps.setString(8, v.getVehicleType());
            ps.setInt(9, v.getVin());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByVin(int vin) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM vehicles WHERE VIN = ?")) {
            ps.setInt(1, vin);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> searchByFilters(Double minPrice, Double maxPrice,
                                         String make, String model,
                                         Integer minYear, Integer maxYear,
                                         String color,
                                         Integer minMiles, Integer maxMiles,
                                         String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM vehicles WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (minPrice != null) { sql.append(" AND price >= ?"); params.add(minPrice); }
        if (maxPrice != null) { sql.append(" AND price <= ?"); params.add(maxPrice); }
        if (make != null && !make.isBlank()) { sql.append(" AND make = ?"); params.add(make); }
        if (model != null && !model.isBlank()) { sql.append(" AND model = ?"); params.add(model); }
        if (minYear != null) { sql.append(" AND year >= ?"); params.add(minYear); }
        if (maxYear != null) { sql.append(" AND year <= ?"); params.add(maxYear); }
        if (color != null && !color.isBlank()) { sql.append(" AND color = ?"); params.add(color); }
        if (minMiles != null) { sql.append(" AND mileage >= ?"); params.add(minMiles); }
        if (maxMiles != null) { sql.append(" AND mileage <= ?"); params.add(maxMiles); }
        if (type != null && !type.isBlank()) { sql.append(" AND type = ?"); params.add(type); }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int vin = resultSet.getInt("VIN");
                    boolean sold = resultSet.getBoolean("Sold");
                    String c = resultSet.getString("color");
                    String mk = resultSet.getString("make");
                    String mdl = resultSet.getString("model");
                    double price = resultSet.getDouble("price");
                    int year = resultSet.getInt("year");
                    int mileage = resultSet.getInt("mileage");
                    String tp = resultSet.getString("type");
                    Vehicle vehicle = new Vehicle(vin, year, mk, mdl, tp, c, mileage, price, sold);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    // CLI-specific search and add/delete methods removed; use REST filter method below.
}
