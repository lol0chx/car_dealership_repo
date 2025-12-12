package com.pluralsight.daos;

import com.pluralsight.models.SalesContract;

import javax.sql.DataSource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SalesContractDao {
    private static DataSource dataSource;

    @Autowired
    public SalesContractDao(DataSource dataSource){
        this.dataSource = dataSource;
    }
    // --- CRUD support for Controllers ---
    public SalesContract getById(int id){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT salesID, VIN, date FROM salescontracts WHERE salesID = ?")){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    int sales_id = rs.getInt("salesID");
                    int vin = rs.getInt("VIN");
                    Date date = rs.getDate("date");
                    return new SalesContract(sales_id, vin, date);
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public int addSalesContract(int vin, Date date){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO salescontracts (VIN, Date) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, vin);
            ps.setDate(2, date);
            return ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<SalesContract> displaySalesContract() {
        List<SalesContract> sContract = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT salesID, VIN, date FROM salescontracts;");

             ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                int sales_id = resultSet.getInt("salesID");
                int vin = resultSet.getInt("vin");
                Date date = resultSet.getDate("date");

                SalesContract salesContract = new SalesContract(sales_id, vin, date);
                sContract.add(salesContract);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sContract;
    }

    
}
