package db;
import Models.Appointment;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for managing appointment-related operations in the database.
 * Provides methods to insert, retrieve, update, and delete appointment records using stored procedures.
 */
public class AppointmentDAO {

    /**
     * Inserts a new appointment into the database using a stored procedure.
     *
     * @param vehicleId the ID of the vehicle associated with the appointment
     * @param mechanicId the ID of the mechanic assigned to the appointment
     * @param userId the ID of the user who created the appointment
     * @param serviceType the type of service scheduled (e.g., oil change, tire rotation)
     * @param serviceDate the date of the scheduled service
     * @param status the current status of the appointment (e.g., pending, completed)
     * @return true if the insertion is successful, false otherwise
     */
    public static boolean insertAppointment(int vehicleId, int mechanicId, int userId, String serviceType, LocalDate serviceDate, String status) {
        String sql = "{CALL InsertAppointment(?, ?, ?, ?, ?, ?)}";  // Calling the stored procedure

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, vehicleId);        // Set VehicleID
            stmt.setInt(2, mechanicId);       // Set MechanicID
            stmt.setInt(3, userId);           // Set UserID
            stmt.setString(4, serviceType);   // Set ServiceType
            stmt.setDate(5, Date.valueOf(serviceDate));  // Set ServiceDate
            stmt.setString(6, status);        // Set ServiceStatus

            // Execute the procedure and check if the insertion was successful
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves an appointment from the database by its ID using a stored procedure.
     *
     * @param appointmentId the ID of the appointment to retrieve
     * @return an Appointment object if found, null otherwise
     */
    public static Appointment getAppointmentById(int appointmentId) {
        String procedureCall = "{ CALL get_appointment_by_id(?, ?, ?, ?, ?, ?) }";
        Appointment appointment = null;

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(procedureCall)) {

            stmt.setInt(1, appointmentId);
            stmt.registerOutParameter(2, Types.VARCHAR);   // VIN
            stmt.registerOutParameter(3, Types.VARCHAR);   // ServiceType
            stmt.registerOutParameter(4, Types.DATE);      // ServiceDate
            stmt.registerOutParameter(5, Types.VARCHAR);   // ServiceStatus
            stmt.registerOutParameter(6, Types.INTEGER);   // MechanicId

            stmt.execute();

            String vin = stmt.getString(2);
            String serviceType = stmt.getString(3);
            java.sql.Date serviceDateSQL = stmt.getDate(4);
            String status = stmt.getString(5);
            int mechanicId = stmt.getInt(6);

            if (vin != null) {
                appointment = new Appointment();
                appointment.setVin(vin);
                appointment.setServiceType(serviceType);
                appointment.setServiceDate(serviceDateSQL != null ? serviceDateSQL.toLocalDate() : null);
                appointment.setStatus(status);
                appointment.setMechanicId(mechanicId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    /**
     * Updates an existing appointment in the database by its ID using a stored procedure.
     *
     * @param appointmentId the ID of the appointment to update
     * @param serviceType the updated type of service
     * @param mechanicId the updated ID of the assigned mechanic
     * @param serviceDate the updated date of the service
     * @param status the updated status of the appointment
     * @return true if the update is successful, false otherwise
     */
    public static boolean updateAppointmentById(int appointmentId, String serviceType, int mechanicId, LocalDate serviceDate, String status) {
        String callProcedure = "{ call update_appointment_by_id(?, ?, ?, ?, ?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(callProcedure)) {

            stmt.setInt(1, appointmentId);
            stmt.setString(2, serviceType);
            stmt.setDate(3, Date.valueOf(serviceDate));
            stmt.setString(4, status);
            stmt.setInt(5, mechanicId);

            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes an appointment from the database by its ID using a stored procedure.
     *
     * @param appointmentId the ID of the appointment to delete
     * @return true if the deletion is successful, false otherwise
     */
    public static boolean deleteAppointment(int appointmentId) {
        String sql = "{ call delete_appointment_by_id(?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, appointmentId);
            stmt.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "{ call GetAllAppointments(?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, Types.REF_CURSOR);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    Appointment a = new Appointment();
                    a.setAppointmentId(rs.getInt("AppointmentID"));
                    a.setVin(rs.getString("VIN"));
                    a.setServiceType(rs.getString("ServiceType"));
                    a.setServiceDate(rs.getDate("ServiceDate").toLocalDate());
                    a.setStatus(rs.getString("ServiceStatus"));
                    a.setMechanicName(rs.getString("MechanicName")); // new field in model
                    appointments.add(a);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }


}