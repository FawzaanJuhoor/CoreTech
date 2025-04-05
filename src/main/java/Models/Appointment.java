package Models;
import java.time.LocalDate;
public class Appointment {
    private String vin;
    private int customerId;
    private String serviceType;
    private LocalDate serviceDate;

    public Appointment(String vin, int customerId, String serviceType, LocalDate serviceDate) {
        this.vin = vin;
        this.customerId = customerId;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }
}