package db;
import Models.Admin;
import Models.MonthlyServiceReport;
import Models.NewAdmin;
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

        String query = """
        SELECT c.CustomerName,
               v.Make || ' ' || v.Model AS VehicleName,
               s.ServiceType
        FROM ServiceAppointment s
        JOIN Vehicle v ON s.VehicleID = v.VehicleID
        JOIN Customer c ON v.CustomerID = c.CustomerID
        ORDER BY s.ServiceDate
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String customerName = rs.getString("CustomerName");
                String vehicleName = rs.getString("VehicleName");
                String serviceType = rs.getString("ServiceType");

                // Set cost to 0 for now (can be updated later if needed)
                MonthlyServiceReport report = new MonthlyServiceReport(customerName, vehicleName, serviceType, 0.0);
                reportList.add(report);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportList;
    }

}










