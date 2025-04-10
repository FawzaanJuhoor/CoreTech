package db;
import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    public static boolean insertAdmin(Admin admin) {
        String sql = "{ call ADD_SYSTEM_USER(?, ?, ?, ?, ?) }";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPhone());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPassword());
            stmt.setString(5, admin.getRole());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Admin added successfully to SystemUser!");
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //    Display All admins in table
    public static List<NewAdmin> getAllAdminsForDisplay() {
        List<NewAdmin> admins = new ArrayList<>();
        String sql = "{call GET_ALL_SYSTEM_USERS(?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, java.sql.Types.REF_CURSOR); // ✅ Avoid OracleTypes
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    int userId = rs.getInt("USERID");
                    String username = rs.getString("USERNAME");
                    String phone = rs.getString("PHONENO");
                    String role = rs.getString("ROLE");

                    NewAdmin admin = new NewAdmin(userId, username, phone, role);
                    admins.add(admin);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public static boolean updateAdminByUsername(Admin admin) {
        String sql = "{call UPDATE_USER_BY_USERNAME(?, ?, ?, ?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPhone());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPassword());
            stmt.setString(5, admin.getRole());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }

    public static Admin getAdminByUsername(String username) {
        String sql = "SELECT UserName, PhoneNo, EmailID, Password, Role FROM SystemUser WHERE LOWER(UserName) = LOWER(?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Admin(
                        rs.getString("UserName"),
                        rs.getString("PhoneNo"),
                        rs.getString("EmailID"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteAdminByUsername(String username) {
        String query = "DELETE FROM SystemUser WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Monthly report data
    public static ObservableList<MonthlyServiceReport> getMonthlyServiceReport() {
        ObservableList<MonthlyServiceReport> reportList = FXCollections.observableArrayList();

        String procedureCall = "{call Get_Monthly_Service_Report(?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(procedureCall)) {

            // Register the OUT parameter (SYS_REFCURSOR)
            cstmt.registerOutParameter(1, java.sql.Types.REF_CURSOR);

            // Execute the procedure
            cstmt.execute();

            // Retrieve the cursor
            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    String customerName = rs.getString("CustomerName");
                    String vehicleName = rs.getString("VehicleName");
                    String serviceType = rs.getString("ServiceType");

                    // Set cost to 0 for now (can be updated later if needed)
                    MonthlyServiceReport report = new MonthlyServiceReport(customerName, vehicleName, serviceType, 0.0);
                    reportList.add(report);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL Error: " + e.getMessage());
        }

        return reportList;
    }

    public static ObservableList<MonthlyInventoryReport> getMonthlyInventoryReport() {
        ObservableList<MonthlyInventoryReport> reportList = FXCollections.observableArrayList();

        String procedureCall = "{call Get_Inventory_Summary(?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(procedureCall)) {

            // Register the OUT parameter (SYS_REFCURSOR)
            cstmt.registerOutParameter(1, java.sql.Types.REF_CURSOR);

            // Execute the procedure
            cstmt.execute();

            // Retrieve the cursor
            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    String itemName = rs.getString("ItemName");
                    int quantityUsed = rs.getInt("Quantity");
                    int remainingStock = rs.getInt("MinStockLevel");

                    reportList.add(new MonthlyInventoryReport(itemName, quantityUsed, remainingStock));

                    // Temporary Debugging Line (to verify data)
                    System.out.println(itemName + " | " + quantityUsed + " | " + remainingStock);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL Error: " + e.getMessage());
        }

        // Temporary Debugging Line (Check size of returned data)
        System.out.println("Total rows fetched: " + reportList.size());

        return reportList;
    }

    public static ObservableList<RevenueSummary> getRevenueSummaryReport() {
        ObservableList<RevenueSummary> reportList = FXCollections.observableArrayList();

        String procedureCall = "{call Get_Revenue_Summary(?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(procedureCall)) {

            // Register the OUT parameter as SYS_REFCURSOR
            cstmt.registerOutParameter(1, java.sql.Types.REF_CURSOR);

            // Execute the stored procedure
            cstmt.execute();

            // Process the returned cursor
            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    double totalRevenue = rs.getDouble("TotalRevenue");
                    double totalInventoryCost = rs.getDouble("TotalInventoryCost");
                    double netProfit = rs.getDouble("NetProfit");

                    reportList.add(new RevenueSummary(totalRevenue, totalInventoryCost, netProfit));

                    // Debugging
                    System.out.println(totalRevenue + " | " + totalInventoryCost + " | " + netProfit);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error fetching revenue summary: " + e.getMessage());
        }

        return reportList;
    }




}










