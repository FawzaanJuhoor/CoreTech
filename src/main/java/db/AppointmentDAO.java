package db;
import Models.Appointment;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class AppointmentDAO {

    public final Connection connection;

    public AppointmentDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addAppointment(Appointment appointment) {
        String query = "INSERT INTO ServiceAppointment  (vin, customer_id, service_type, service_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, appointment.getVin());
            stmt.setInt(2, appointment.getCustomerId());
            stmt.setString(3, appointment.getServiceType());
            stmt.setDate(4, java.sql.Date.valueOf(appointment.getServiceDate()));

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}