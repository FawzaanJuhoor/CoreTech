package db;

import Models.Mechanic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MechanicDAO {

    public static List<Mechanic> getAllMechanics() {
        List<Mechanic> mechanics = new ArrayList<>();
        String sql = "{ call get_all_mechanics(?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, Types.REF_CURSOR);
//            stmt.registerOutParameter(1, java.sql.Types.OTHER);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    int id = rs.getInt("MechanicID");
                    String name = rs.getString("MechanicName");
                    String expertise = rs.getString("Expertise");

                    mechanics.add(new Mechanic(id, name, expertise));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mechanics;
    }

}
