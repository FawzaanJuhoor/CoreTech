package Models;

import java.time.LocalDate;
import java.util.List;

public class AppointmentInvoiceInfo {
    private int appointmentId;
    private String customerName, email, phone, address;
    private String make, model, vin;
    private int year;
    private String serviceType, status, mechanicName;
    private LocalDate serviceDate;
    private List<ServiceItem> serviceItems;

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMechanicName() { return mechanicName; }
    public void setMechanicName(String mechanicName) { this.mechanicName = mechanicName; }

    public LocalDate getServiceDate() { return serviceDate; }
    public void setServiceDate(LocalDate serviceDate) { this.serviceDate = serviceDate; }

    public List<ServiceItem> getServiceItems() { return serviceItems; }
    public void setServiceItems(List<ServiceItem> serviceItems) { this.serviceItems = serviceItems; }
}
