package Models;

import javafx.beans.property.*;

public class MonthlyServiceReport {

    private final StringProperty customerName = new SimpleStringProperty();
    private final StringProperty vehicle = new SimpleStringProperty();
    private final StringProperty serviceType = new SimpleStringProperty();
    private final DoubleProperty cost = new SimpleDoubleProperty();

    // ✅ Required no-arg constructor
    public MonthlyServiceReport() {}

    // ✅ Main constructor
    public MonthlyServiceReport(String customerName, String vehicle, String serviceType, double cost) {
        setCustomerName(customerName);
        setVehicle(vehicle);
        setServiceType(serviceType);
        setCost(cost);
    }

    public String getCustomerName() { return customerName.get(); }
    public void setCustomerName(String value) { customerName.set(value); }
    public StringProperty customerNameProperty() { return customerName; }

    public String getVehicle() { return vehicle.get(); }
    public void setVehicle(String value) { vehicle.set(value); }
    public StringProperty vehicleProperty() { return vehicle; }

    public String getServiceType() { return serviceType.get(); }
    public void setServiceType(String value) { serviceType.set(value); }
    public StringProperty serviceTypeProperty() { return serviceType; }

    public double getCost() { return cost.get(); }
    public void setCost(double value) { cost.set(value); }
    public DoubleProperty costProperty() { return cost; }

    @Override
    public String toString() {
        return "MonthlyServiceReport{" +
                "customerName=" + getCustomerName() +
                ", vehicle=" + getVehicle() +
                ", serviceType=" + getServiceType() +
                ", cost=" + getCost() +
                '}';
    }
}
