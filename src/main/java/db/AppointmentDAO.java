package db;
import Models.Appointment;


import java.sql.*;
import java.time.LocalDate;

public class AppointmentDAO {

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

    // Get appointment by ID
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

    // Update appointment by ID
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




}