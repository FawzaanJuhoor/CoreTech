package Models;

public class Customer {
    private int customerID;
    private String customerName;
    private String phoneNo;
    private String emailID;
    private String address;

    public Customer(String customerName, String phoneNo, String emailID, String address) {
        this.customerName = customerName;
        this.phoneNo = phoneNo;
        this.emailID = emailID;
        this.address = address;
    }

    // Getters and Setters
    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
    public String getEmailID() { return emailID; }
    public void setEmailID(String emailID) { this.emailID = emailID; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
