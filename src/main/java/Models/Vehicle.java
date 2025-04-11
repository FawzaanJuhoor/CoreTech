package Models;

/**
 * Represents a vehicle in the system with its identification, ownership, and service details.
 */
public class Vehicle {
    /** The Vehicle Identification Number (VIN), a unique identifier for the vehicle. */
    private String VIN;

    /** The ID of the customer who owns the vehicle. */
    private int customerId;

    /** The manufacturer or brand of the vehicle (e.g., Toyota, Ford). */
    private String make;

    /** The model name of the vehicle */
    private String model;

    /** The manufacturing year of the vehicle. */
    private int year;

    /** A record of the vehicle's service history. */
    private String serviceHistory;

    /**
     * Constructs a new Vehicle object with the specified details, with VIN as the first parameter.
     *
     * @param VIN the Vehicle Identification Number
     * @param customerId the ID of the customer who owns the vehicle
     * @param make the manufacturer or brand of the vehicle
     * @param model the model name of the vehicle
     * @param year the manufacturing year of the vehicle
     * @param serviceHistory a record of the vehicle's service history
     */
    public Vehicle(String VIN, int customerId, String make, String model, int year, String serviceHistory) {
        this.VIN = VIN;
        this.customerId = customerId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.serviceHistory = serviceHistory;
    }

    private String email;

    public Vehicle(String vin, int customerId, String email, String make, String model, int year, String serviceHistory) {
        this.VIN = vin;
        this.customerId = customerId;
        this.email = email;
        this.make = make;
        this.model = model;
        this.year = year;
        this.serviceHistory = serviceHistory;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    /**
     * Constructs a new Vehicle object with the specified details, with customerId as the first parameter.
     *
     * @param customerId the ID of the customer who owns the vehicle
     * @param make the manufacturer or brand of the vehicle
     * @param model the model name of the vehicle
     * @param year the manufacturing year of the vehicle
     * @param vin the Vehicle Identification Number (named 'vin' in this constructor)
     * @param serviceHistory a record of the vehicle's service history
     */
    public Vehicle(int customerId, String make, String model, int year, String vin, String serviceHistory) {
        this.customerId = customerId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.VIN = vin;
        this.serviceHistory = serviceHistory;

    }

    /**
     * Returns the Vehicle Identification Number (VIN).
     *
     * @return the VIN
     */
    public String getVIN() { return VIN; }

    /**
     * Sets the Vehicle Identification Number (VIN).
     *
     * @param VIN the new VIN to set
     */
    public void setVIN(String VIN) { this.VIN = VIN; }

    /**
     * Returns the ID of the customer who owns the vehicle.
     *
     * @return the customer ID
     */
    public int getCustomerId() { return customerId; }

    /**
     * Sets the ID of the customer who owns the vehicle.
     *
     * @param customerId the new customer ID to set
     */
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    /**
     * Returns the manufacturer or brand of the vehicle.
     *
     * @return the make
     */
    public String getMake() { return make; }

    /**
     * Sets the manufacturer or brand of the vehicle.
     *
     * @param make the new make to set
     */
    public void setMake(String make) { this.make = make; }

    /**
     * Returns the model name of the vehicle.
     *
     * @return the model
     */
    public String getModel() { return model; }

    /**
     * Sets the model name of the vehicle.
     *
     * @param model the new model to set
     */
    public void setModel(String model) { this.model = model; }

    /**
     * Returns the manufacturing year of the vehicle.
     *
     * @return the year
     */
    public int getYear() { return year; }

    /**
     * Sets the manufacturing year of the vehicle.
     *
     * @param year the new year to set
     */
    public void setYear(int year) { this.year = year; }

    /**
     * Returns the service history of the vehicle.
     *
     * @return the service history
     */
    public String getServiceHistory() { return serviceHistory; }

    /**
     * Sets the service history of the vehicle.
     *
     * @param serviceHistory the new service history to set
     */
    public void setServiceHistory(String serviceHistory) { this.serviceHistory = serviceHistory; }
}
