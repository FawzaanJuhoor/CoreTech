package db;

import Models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public static int getCustomerIdByEmail(String email) {
        String sql = "{ ? = call GetCustomerIdByEmail(?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Register output parameter
            stmt.registerOutParameter(1, java.sql.Types.INTEGER);
            // Set input parameter
            stmt.setString(2, email);

            stmt.execute();

            return stmt.getInt(1); // Get the returned CustomerID

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getEmailByCustomerId(int customerId) {
        String sql = "{? = CALL GetEmailByCustomerId(?)}"; // Calling the function

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Register return type (first parameter)
            stmt.registerOutParameter(1, Types.VARCHAR);

            // Set input parameter (customerId)
            stmt.setInt(2, customerId);

            // Execute the function
            stmt.execute();

            // Retrieve the result
            return stmt.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ""; // Return empty string if email is not found
    }


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
    public static boolean deleteCustomer(String emailID) {
        String sql = "{CALL DeleteCustomer(?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, emailID);  // Now using email instead of phone number

            stmt.execute();
            System.out.println("Customer deleted successfully!");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //search Customer
    public static Customer searchCustomer(String emailID) {
        String sql = "{CALL SearchCustomer(?, ?, ?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, java.sql.Types.VARCHAR); // Output parameter for customer name
            stmt.setString(2, emailID);  // Search by email instead of phone number
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR); // Output parameter for PhoneNo
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR); // Output parameter for Address

            stmt.execute();

            String customerName = stmt.getString(1);
            String phoneNo = stmt.getString(3);
            String address = stmt.getString(4);

            if (customerName != null && phoneNo != null && address != null) {
                return new Customer(customerName, phoneNo, emailID, address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String procedureCall = "{ call GetAllCustomers(?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(procedureCall)) {

            stmt.registerOutParameter(1, Types.REF_CURSOR);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    customers.add(new Customer(
                            rs.getInt("CustomerID"),
                            rs.getString("CustomerName"),
                            rs.getString("PhoneNo"),
                            rs.getString("EmailID"),
                            rs.getString("Address")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public static Customer searchCustomerView(String email) {
        String sql = "{call SearchCustomerByEmail(?, ?, ?, ?, ?)}"; // Assuming this stored procedure

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, email);

            // Register OUT parameters
            stmt.registerOutParameter(2, Types.INTEGER); // CustomerID
            stmt.registerOutParameter(3, Types.VARCHAR); // CustomerName
            stmt.registerOutParameter(4, Types.VARCHAR); // PhoneNo
            stmt.registerOutParameter(5, Types.VARCHAR); // Address

            stmt.execute();

            int customerID = stmt.getInt(2);
            String name = stmt.getString(3);
            String phone = stmt.getString(4);
            String address = stmt.getString(5);

            if (customerID != 0) {
                return new Customer(customerID, name, phone, email, address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
