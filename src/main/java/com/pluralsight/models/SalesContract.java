package com.pluralsight.models;

import java.sql.Date;

public class SalesContract {
    private int salesID;
    private int VIN;
    private Date date;

    public SalesContract(int salesID, int VIN, Date date) {
        this.salesID = salesID;
        this.VIN = VIN;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Sales Contract: " + "\n" +
                "salesID=" + salesID + "\n" +
                ", VIN=" + VIN + "\n" +
                ", date=" + date + "\n" + "-------\n";
    }

    public int getSalesID() {
        return salesID;
    }

    public int getVIN() {
        return VIN;
    }

    public Date getDate() {
        return date;
    }
}
