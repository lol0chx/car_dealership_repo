package com.pluralsight.daos;

import com.pluralsight.models.LeaseContract;

import javax.sql.DataSource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class LeaseContractDao {
    private static DataSource dataSource;

    @Autowired
    public LeaseContractDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    // CLI-dependent constructor and static add method removed.
    // --- CRUD support for Controllers ---
    public LeaseContract getById(int id){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT leaseID, VIN, date FROM leasecontracts WHERE leaseID = ?")){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    int lease_id = rs.getInt("leaseID");
                    int vin = rs.getInt("VIN");
                    Date date = rs.getDate("date");
                    return new LeaseContract(lease_id, vin, date);
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public int addLeaseContract(int vin, Date date){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO leasecontracts (VIN, Date) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, vin);
            ps.setDate(2, date);
            return ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<LeaseContract> displayLeaseContract() {
        List<LeaseContract> lContract = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT leaseID, VIN, date FROM leasecontracts;");

             ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                int lease_id = resultSet.getInt("leaseID");
                int vin = resultSet.getInt("vin");
                Date date = resultSet.getDate("date");

                LeaseContract leaseContract = new LeaseContract(lease_id, vin, date);
                lContract.add(leaseContract);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lContract;
    }
}
