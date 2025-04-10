package db;

import Models.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for managing vehicle-related operations in the database.
 * Provides methods to insert, update, delete, and retrieve vehicle records using stored procedures.
 */
public class VehicleDAO {

    /**
     * Inserts a new vehicle into the database using a stored procedure.
     *
     * @param vehicle the Vehicle object containing the details to insert
     * @return true if the insertion is successful, false otherwise
     */
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

    /**
     * Updates an existing vehicle in the database using a stored procedure.
     *
     * @param vehicle the Vehicle object containing the updated details
     * @return true if the update is successful, false otherwise
     */
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


//    public static Vehicle searchVehicleByVIN(String vin) {
//        String sql = "{CALL GetVehicleByVIN(?, ?, ?, ?, ?, ?)}"; // Call to stored procedure
//
//        try (Connection conn = DBConnection.getConnection();
//             CallableStatement stmt = conn.prepareCall(sql)) {
//
//            // Set input parameter (VIN)
//            stmt.setString(1, vin);
//
//            // Register output parameters
//            stmt.registerOutParameter(2, Types.INTEGER); // CustomerID
//            stmt.registerOutParameter(3, Types.VARCHAR); // Make
//            stmt.registerOutParameter(4, Types.VARCHAR); // Model
//            stmt.registerOutParameter(5, Types.INTEGER); // Year
//            stmt.registerOutParameter(6, Types.VARCHAR); // ServiceHistory
//
//            // Execute the procedure
//            stmt.execute();
//
//            // Retrieve output values
//            int customerId = stmt.getInt(2);
//            String make = stmt.getString(3);
//            String model = stmt.getString(4);
//            int year = stmt.getInt(5);
//            String serviceHistory = stmt.getString(6);
//
//            // Check if a record was found
//            if (customerId != 0) {
//                return new Vehicle(customerId, make, model, year, vin, serviceHistory);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null; // Return null if vehicle is not found
//    }

    /**
     * Retrieves a list of vehicle descriptions associated with a customer's email using a stored procedure.
     * Each description is formatted as "VehicleID - Make - Model - Year".
     *
     * @param email the email address of the customer
     * @return a List of Strings representing vehicle descriptions
     */
    public static List<String> getVehiclesByEmail(String email) {
        String sql = "{CALL GetVehiclesByEmail(?, ?)}";
        List<String> vehicleList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, email);
            stmt.registerOutParameter(2, Types.REF_CURSOR);

            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(2)) {
                while (rs.next()) {
                    int vehicleId = rs.getInt("VehicleID");
                    String make = rs.getString("Make");
                    String model = rs.getString("Model");
                    int year = rs.getInt("Year");
                    vehicleList.add(vehicleId + " - " + make + " - " + model + " - " + year);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleList;
    }

    /**
     * Retrieves a vehicle from the database by its ID using a stored procedure.
     *
     * @param vehicleId the ID of the vehicle to retrieve
     * @return a Vehicle object if found, null otherwise
     */
    public static Vehicle getVehicleByID(int vehicleId) {
        String sql = "{CALL GetVehicleByID(?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, vehicleId);
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.registerOutParameter(3, Types.VARCHAR);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.INTEGER);
            stmt.registerOutParameter(6, Types.VARCHAR);
            stmt.registerOutParameter(7, Types.VARCHAR);

            stmt.execute();

            int customerId = stmt.getInt(2);
            String make = stmt.getString(3);
            String model = stmt.getString(4);
            int year = stmt.getInt(5);
            String vin = stmt.getString(6);
            String serviceHistory = stmt.getString(7);

            return new Vehicle(customerId, make, model, year, vin, serviceHistory);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes a vehicle from the database by its VIN using a stored procedure.
     *
     * @param vin the Vehicle Identification Number (VIN) of the vehicle to delete
     * @return true if the deletion is successful, false otherwise
     */
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

    /**
     * Retrieves the vehicle ID associated with a given VIN using a stored procedure.
     *
     * @param vin the Vehicle Identification Number (VIN) of the vehicle
     * @return the vehicle ID if found, -1 if not found or on error
     */
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

    /**
     * Retrieves a list of vehicles associated with a customer's email using a stored procedure.
     *
     * @param email the email address of the customer
     * @return a List of Vehicle objects containing all vehicles linked to the email
     */
    public static List<Vehicle> searchVehiclesByEmail(String email) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "{CALL GetVehiclesByEmail(?, ?)}"; // Calling the stored procedure

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Set input parameter (email)
            stmt.setString(1, email);
            // Register output parameter (cursor)
            stmt.registerOutParameter(2, Types.REF_CURSOR);

            // Execute the stored procedure
            stmt.execute();

            // Retrieve the result set
            try (ResultSet rs = (ResultSet) stmt.getObject(2)) {
                while (rs.next()) {
                    Vehicle vehicle = new Vehicle(
                            rs.getInt("CustomerId"),
                            rs.getString("Make"),
                            rs.getString("Model"),
                            rs.getInt("Year"),
                            rs.getString("VIN"),
                            rs.getString("ServiceHistory")
                    );
                    vehicles.add(vehicle);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }


}
