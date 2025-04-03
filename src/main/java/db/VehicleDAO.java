package db;

import Models.Vehicle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class VehicleDAO {

    public static int getCustomerIDByEmail(String email) {
        String sql = "{CALL GetCustomerIDByEmail(?, ?)}";
        int customerID = -1; // Default value if not found

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, email);
            stmt.registerOutParameter(2, java.sql.Types.INTEGER);
            stmt.execute();

            customerID = stmt.getInt(2);
            if (stmt.wasNull()) {
                customerID = -1; // Set to -1 if no data found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerID;
    }


    public static boolean insertVehicle(Vehicle vehicle) {
        String sql = "{CALL InsertVehicle(?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, vehicle.getCustomerID());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setString(5, vehicle.getVIN());
            stmt.setString(6, vehicle.getServiceHistory());

            stmt.execute();
            System.out.println("Vehicle added successfully!");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateVehicle(Vehicle vehicle) {
        String sql = "{CALL UpdateVehicle(?, ?, ?, ?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, vehicle.getVIN());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setString(5, vehicle.getServiceHistory());

            stmt.execute();
            System.out.println("Vehicle updated successfully!");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteVehicle(String vin) {
        String sql = "{CALL DeleteVehicle(?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, vin);
            stmt.execute();
            System.out.println("Vehicle deleted successfully!");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Vehicle searchVehicle(String vin) {
        String sql = "{CALL SearchVehicle(?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, vin);
            stmt.registerOutParameter(2, java.sql.Types.INTEGER);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.INTEGER);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);

            stmt.execute();

            int customerID = stmt.getInt(2);
            String make = stmt.getString(3);
            String model = stmt.getString(4);
            int year = stmt.getInt(5);
            String serviceHistory = stmt.getString(6);

            if (make != null && model != null) {
                return new Vehicle(vin, customerID, make, model, year, serviceHistory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
