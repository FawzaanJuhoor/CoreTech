package Models;

import java.time.LocalDate;

/**
 * Represents an appointment for vehicle servicing in the system.
 * Contains details such as appointment ID, vehicle VIN, service type, date, status, and assigned mechanic.
 */
public class Appointment {

    /** The unique identifier for the appointment. */
    private int appointmentId;

    /** The Vehicle Identification Number (VIN) of the vehicle associated with the appointment. */
    private String vin;

    /** The type of service scheduled for the appointment (e.g., oil change, tire rotation). */
    private String serviceType;

    /** The date of the scheduled service. */
    private LocalDate serviceDate;

    /** The current status of the appointment (e.g., pending, completed). */
    private String status;

    /** The identifier of the mechanic assigned to the appointment. */
    private int mechanicId;

    /**
     * Default constructor that creates an empty Appointment object.
     */
    public Appointment() {}

    /**
     * Constructs a new Appointment object with the specified details.
     *
     * @param appointmentId the unique identifier for the appointment
     * @param vin the Vehicle Identification Number (VIN) of the vehicle
     * @param serviceType the type of service scheduled
     * @param serviceDate the date of the scheduled service
     * @param status the current status of the appointment
     * @param mechanicId the identifier of the assigned mechanic
     */
    public Appointment(int appointmentId, String vin, String serviceType, LocalDate serviceDate, String status, int mechanicId) {
        this.appointmentId = appointmentId;
        this.vin = vin;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.status = status;
        this.mechanicId = mechanicId;
    }

    /**
     * Returns the appointment ID.
     *
     * @return the appointment ID
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets the appointment ID.
     *
     * @param appointmentId the new appointment ID to set
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Returns the Vehicle Identification Number (VIN).
     *
     * @return the VIN
     */
    public String getVin() {
        return vin;
    }

    /**
     * Sets the Vehicle Identification Number (VIN).
     *
     * @param vin the new VIN to set
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Returns the type of service scheduled for the appointment.
     *
     * @return the service type
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the type of service scheduled for the appointment.
     *
     * @param serviceType the new service type to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Returns the date of the scheduled service.
     *
     * @return the service date
     */
    public LocalDate getServiceDate() {
        return serviceDate;
    }

    /**
     * Sets the date of the scheduled service.
     *
     * @param serviceDate the new service date to set
     */
    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    /**
     * Returns the current status of the appointment.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the appointment.
     *
     * @param status the new status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the ID of the mechanic assigned to the appointment.
     *
     * @return the mechanic ID
     */
    public int getMechanicId() {
        return mechanicId;
    }

    /**
     * Sets the ID of the mechanic assigned to the appointment.
     *
     * @param mechanicId the new mechanic ID to set
     */
    public void setMechanicId(int mechanicId) {
        this.mechanicId = mechanicId;
    }

    /**
     * Returns a string representation of the Appointment object.
     *
     * @return a string containing the appointment details
     */
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
