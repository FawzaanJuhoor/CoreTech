package db;
import Models.Admin;
import Models.NewAdmin;

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











}
