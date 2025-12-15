package com.pluralsight.daos;

import com.pluralsight.models.Cart;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    private static DataSource dataSource;

    public CartDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    // REST-friendly methods (parameterized; no CLI prompts)
    public int addCart(int vin){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cart (VIN) VALUES (?);", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, vin);
            int rows = preparedStatement.executeUpdate();
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            return rows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteCart(int vin){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cart WHERE VIN = ?")) {
            preparedStatement.setInt(1, vin);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cart> viewCart(){
        List<Cart> cart = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT c.VIN, v.make, v.model, v.price, v.mileage, v.color, v.type, v.year, v.sold FROM cart AS c JOIN vehicles AS v ON c.VIN = v.VIN");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int vin = resultSet.getInt("vin");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                int mileage = resultSet.getInt("mileage");
                String color = resultSet.getString("color");
                double price = resultSet.getDouble("price");
                String type = resultSet.getString("type");
                int year = resultSet.getInt("year");
                boolean sold = resultSet.getBoolean("sold");
                Cart viewCart = new Cart(vin, make, model, price, color, type, mileage, year, sold);
                cart.add(viewCart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }
}
