package com.pluralsight.models;

import java.sql.Date;

public class LeaseContract {
    private int leaseID;
    private int VIN;
    private Date date;

    public LeaseContract(int leaseID, int VIN, Date date) {
        this.leaseID = leaseID;
        this.VIN = VIN;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Lease Contract: " + "\n" +
                "leaseID=" + leaseID + "\n" +
                ", VIN=" + VIN + "\n" +
                ", date=" + date + "\n" + "-------\n";
    }

    public int getLeaseID() {
        return leaseID;
    }

    public int getVIN() {
        return VIN;
    }

    public Date getDate() {
        return date;
    }
}
