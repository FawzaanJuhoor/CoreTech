package db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SalesDashboardDAO {
    public static int getTodayCustomerCount() {
        int count = 0;

        String sql = "{ call GetTodayCustomerCount(?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, java.sql.Types.INTEGER);
            stmt.execute();
            count = stmt.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public static Map<String, String> getUpcomingCustomerToday() {
        Map<String, String> data = new HashMap<>();
        String sql = "{ call GetUpcomingCustomerToday(?, ?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
            stmt.execute();

            data.put("name", stmt.getString(1));
            data.put("time", stmt.getString(2));

        } catch (SQLException e) {
            e.printStackTrace();
            data.put("name", "Error");
            data.put("time", "--");
        }

        return data;
    }

}
