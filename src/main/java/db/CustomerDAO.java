package db;

import Models.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {

    public static boolean insertCustomer(Customer customer) {
        String sql = "{CALL InsertCustomer(?, ?, ?, ?)}"; // Calling the stored procedure

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getPhoneNo());
            stmt.setString(3, customer.getEmailID());
            stmt.setString(4, customer.getAddress());

            stmt.execute(); // Execute the stored procedure

            System.out.println("Customer added successfully via stored procedure!");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update Customer
    public static boolean updateCustomer(Customer customer) {
        String sql = "{CALL UpdateCustomer(?, ?, ?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getPhoneNo());
            stmt.setString(3, customer.getEmailID());
            stmt.setString(4, customer.getAddress());

            stmt.execute();
            System.out.println("Customer updated successfully!");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    // Delete Customer
    public static boolean deleteCustomer(String phoneNo) {
        String sql = "{CALL DeleteCustomer(?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, phoneNo);

            stmt.execute();
            System.out.println("Customer deleted successfully!");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //search Customer
    public static Customer searchCustomer(String phoneNo) {
        String sql = "{CALL SearchCustomer(?, ?, ?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, java.sql.Types.VARCHAR); // Output parameter for customer name
            stmt.setString(2, phoneNo);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR); // Output parameter for EmailID
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR); // Output parameter for Address

            stmt.execute();

            String customerName = stmt.getString(3);
            String emailID = stmt.getString(3);
            String address = stmt.getString(4);

            if (customerName != null && emailID != null && address != null) {
                return new Customer(customerName, phoneNo, emailID, address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


//    // View Customers
//    public List<Customer> getAllCustomers() {
//        List<Customer> customers = new ArrayList<>();
//        String sql = "SELECT * FROM Customer";
//        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                Customer customer = new Customer();
//                customer.setCustomerID(rs.getInt("CustomerID"));
//                customer.setCustomerName(rs.getString("CustomerName"));
//                customer.setPhoneNo(rs.getString("PhoneNo"));
//                customer.setEmailID(rs.getString("EmailID"));
//                customer.setAddress(rs.getString("Address"));
//                customers.add(customer);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return customers;
//    }

}
