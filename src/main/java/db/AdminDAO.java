package db;
import Models.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDAO {

    public static void insertAdmin(Admin admin) {
        String sql = "INSERT INTO SystemUser (USERNAME, PHONENO, EMAILID, PASSWORD, ROLE) VALUES (?, ?, ?, ?, ?)";

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


}
