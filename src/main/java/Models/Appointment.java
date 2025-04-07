package Models;

import java.time.LocalDate;

public class Appointment {
    private int appointmentId;
    private String vin;
    private String serviceType;
    private LocalDate serviceDate;
    private String status;
    private int mechanicId;

    // Default constructor
    public Appointment() {}

    // Constructor with parameters
    public Appointment(int appointmentId, String vin, String serviceType, LocalDate serviceDate, String status, int mechanicId) {
        this.appointmentId = appointmentId;
        this.vin = vin;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.status = status;
        this.mechanicId = mechanicId;
    }

    // Getters and setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(int mechanicId) {
        this.mechanicId = mechanicId;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", vin='" + vin + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", serviceDate=" + serviceDate +
                ", status='" + status + '\'' +
                ", mechanicId=" + mechanicId +
                '}';
    }
}
