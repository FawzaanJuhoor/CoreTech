package db;

import Models.Vehicle;

import java.sql.*;

public class VehicleDAO {

    public static boolean insertVehicle(Vehicle vehicle) {
        String sql = "{call InsertVehicle(?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, vehicle.getCustomerId());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setString(5, vehicle.getVIN());
            stmt.setString(6, vehicle.getServiceHistory());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateVehicle(Vehicle vehicle) {
        String sql = "{call UpdateVehicle(?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, vehicle.getVIN());
            stmt.setInt(2, vehicle.getCustomerId());
            stmt.setString(3, vehicle.getMake());
            stmt.setString(4, vehicle.getModel());
            stmt.setInt(5, vehicle.getYear());
            stmt.setString(6, vehicle.getServiceHistory());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static Vehicle searchVehicleByVIN(String vin) {
        String sql = "{CALL GetVehicleByVIN(?, ?, ?, ?, ?, ?)}"; // Call to stored procedure

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Set input parameter (VIN)
            stmt.setString(1, vin);

            // Register output parameters
            stmt.registerOutParameter(2, Types.INTEGER); // CustomerID
            stmt.registerOutParameter(3, Types.VARCHAR); // Make
            stmt.registerOutParameter(4, Types.VARCHAR); // Model
            stmt.registerOutParameter(5, Types.INTEGER); // Year
            stmt.registerOutParameter(6, Types.VARCHAR); // ServiceHistory

            // Execute the procedure
            stmt.execute();

            // Retrieve output values
            int customerId = stmt.getInt(2);
            String make = stmt.getString(3);
            String model = stmt.getString(4);
            int year = stmt.getInt(5);
            String serviceHistory = stmt.getString(6);

            // Check if a record was found
            if (customerId != 0) {
                return new Vehicle(customerId, make, model, year, vin, serviceHistory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if vehicle is not found
    }

    public static boolean deleteVehicleByVIN(String vin) {
        String sql = "{CALL DeleteVehicleByVIN(?)}"; // Calling the stored procedure

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Set input parameter (VIN)
            stmt.setString(1, vin);

            // Execute the stored procedure
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if deletion fails
    }

    public static int getVehicleIdByVIN(String vin) {
        String sql = "{CALL GetVehicleIdByVIN(?, ?)}"; // Calling the stored procedure

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, vin); // Set the VIN parameter
            stmt.registerOutParameter(2, java.sql.Types.INTEGER); // Register output parameter

            stmt.execute();  // Execute the procedure

            return stmt.getInt(2);  // Get the result (VehicleID) from the output parameter

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;  // If something goes wrong, return -1
    }


}
