package Models;

public class Vehicle {
    private String VIN;
    private int customerId;
    private String make;
    private String model;
    private int year;
    private String serviceHistory;

    // Constructor
    public Vehicle(String VIN, int customerId, String make, String model, int year, String serviceHistory) {
        this.VIN = VIN;
        this.customerId = customerId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.serviceHistory = serviceHistory;
    }

    public Vehicle(int customerId, String make, String model, int year, String vin, String serviceHistory) {
        this.customerId = customerId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.VIN = vin;
        this.serviceHistory = serviceHistory;

    }

    // Getters and Setters
    public String getVIN() { return VIN; }
    public void setVIN(String VIN) { this.VIN = VIN; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getServiceHistory() { return serviceHistory; }
    public void setServiceHistory(String serviceHistory) { this.serviceHistory = serviceHistory; }
}
