package Models;

public class Vehicle {
    private String vin;
    private int customerID;
    private String make;
    private String model;
    private int year;
    private String serviceHistory;

    // Constructor
    public Vehicle(String vin, int customerID, String make, String model, int year, String serviceHistory) {
        this.vin = vin;
        this.customerID = customerID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.serviceHistory = serviceHistory;
    }

    // Default Constructor
    public Vehicle() {
    }

    // Getters and Setters
    public String getVIN() {
        return vin;
    }

    public void setVIN(String vin) {
        this.vin = vin;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(String serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Vehicle{" +
                "VIN='" + vin + '\'' +
                ", CustomerID=" + customerID +
                ", Make='" + make + '\'' +
                ", Model='" + model + '\'' +
                ", Year=" + year +
                ", ServiceHistory='" + serviceHistory + '\'' +
                '}';
    }
}
