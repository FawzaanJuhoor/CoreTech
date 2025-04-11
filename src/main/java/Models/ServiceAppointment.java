package Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class ServiceAppointment {
    private final IntegerProperty appointmentID;
    private final IntegerProperty vehicleID;
    private final IntegerProperty mechanicID;
    private final IntegerProperty userID;
    private final StringProperty serviceType;
    private final ObjectProperty<LocalDate> serviceDate;
    private final StringProperty serviceStatus;

    public ServiceAppointment(int appointmentID, int vehicleID, int mechanicID, int userID,
                              String serviceType, LocalDate serviceDate, String serviceStatus) {
        this.appointmentID = new SimpleIntegerProperty(appointmentID);
        this.vehicleID = new SimpleIntegerProperty(vehicleID);
        this.mechanicID = new SimpleIntegerProperty(mechanicID);
        this.userID = new SimpleIntegerProperty(userID);
        this.serviceType = new SimpleStringProperty(serviceType);
        this.serviceDate = new SimpleObjectProperty<>(serviceDate);
        this.serviceStatus = new SimpleStringProperty(serviceStatus);
    }

    public int getAppointmentID() { return appointmentID.get(); }
    public int getVehicleID() { return vehicleID.get(); }
    public int getMechanicID() { return mechanicID.get(); }
    public int getUserID() { return userID.get(); }
    public String getServiceType() { return serviceType.get(); }
    public LocalDate getServiceDate() { return serviceDate.get(); }
    public String getServiceStatus() { return serviceStatus.get(); }

    // Add Property getters if you need bindings
    public IntegerProperty appointmentIDProperty() { return appointmentID; }
    public IntegerProperty vehicleIDProperty() { return vehicleID; }
    public IntegerProperty mechanicIDProperty() { return mechanicID; }
    public IntegerProperty userIDProperty() { return userID; }
    public StringProperty serviceTypeProperty() { return serviceType; }
    public ObjectProperty<LocalDate> serviceDateProperty() { return serviceDate; }
    public StringProperty serviceStatusProperty() { return serviceStatus; }
}
