package db;

import Models.ServiceInventory;

import java.sql.CallableStatement;
import java.sql.Connection;

public class ServiceInventoryDAO {

        public static boolean addServiceInventory(ServiceInventory item) {
            String procedureCall = "{call Add_Service_Inventory(?, ?, ?)}";

            try (Connection conn = DBConnection.getConnection();
                 CallableStatement stmt = conn.prepareCall(procedureCall)) {

                stmt.setInt(1, item.getAppointmentId());
                stmt.setInt(2, item.getItemId());
                stmt.setInt(3, item.getQuantity());

                stmt.execute();
                return true;

            } catch (Exception e) {
                System.err.println("Error in addServiceInventory (procedure): " + e.getMessage());
                return false;
            }
        }
}
