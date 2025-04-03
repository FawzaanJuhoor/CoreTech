package db;
import Models.Admin;
import Models.NewAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    public static void insertAdmin(Admin admin) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    Display All admins in table
public static List<NewAdmin> getAllAdminsForDisplay() {
    List<NewAdmin> adminList = new ArrayList<>();
    String sql = "SELECT USERID, USERNAME, PHONENO, ROLE FROM SYSTEMUSER";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            NewAdmin admin = new NewAdmin(
                    rs.getInt("USERID"),
                    rs.getString("USERNAME"),
                    rs.getString("PHONENO"),
                    rs.getString("ROLE")
            );
            adminList.add(admin);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return adminList;
}










}
