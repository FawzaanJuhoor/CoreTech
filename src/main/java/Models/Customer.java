
package Models;

/**
 * Represents a customer in the system with personal and contact information.
 */
public class Customer {

    /** The unique identifier for the customer. */
    private int customerID;

    /** The name of the customer. */
    private String customerName;

    /** The phone number of the customer. */
    private String phoneNo;

    /** The email address of the customer. */
    private String emailID;

    /** The physical address of the customer. */
    private String address;

    /**
     * Constructs a new Customer object with the specified details.
     *
     * @param customerName the name of the customer
     * @param phoneNo the phone number of the customer
     * @param emailID the email address of the customer
     * @param address the physical address of the customer
     */
    public Customer(String customerName, String phoneNo, String emailID, String address) {
        this.customerName = customerName;
        this.phoneNo = phoneNo;
        this.emailID = emailID;
        this.address = address;
    }
    public Customer(int customerID,String customerName, String phoneNo, String emailID, String address) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.phoneNo = phoneNo;
        this.emailID = emailID;
        this.address = address;
    }


    /**
     * Returns the customer ID.
     *
     * @return the customer ID
     */
    public int getCustomerID() { return customerID; }

    /**
     * Sets the customer ID.
     *
     * @param customerID the new customer ID to set
     */
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    /**
     * Returns the name of the customer.
     *
     * @return the customer name
     */
    public String getCustomerName() { return customerName; }

    /**
     * Sets the name of the customer.
     *
     * @param customerName the new customer name to set
     */
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    /**
     * Returns the phone number of the customer.
     *
     * @return the phone number
     */
    public String getPhoneNo() { return phoneNo; }

    /**
     * Sets the phone number of the customer.
     *
     * @param phoneNo the new phone number to set
     */
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    /**
     * Returns the email address of the customer.
     *
     * @return the email address
     */
    public String getEmailID() { return emailID; }

    /**
     * Sets the email address of the customer.
     *
     * @param emailID the new email address to set
     */
    public void setEmailID(String emailID) { this.emailID = emailID; }

    /**
     * Returns the address of the customer.
     *
     * @return the address
     */
    public String getAddress() { return address; }

    /**
     * Sets the address of the customer.
     *
     * @param address the new address to set
     */
    public void setAddress(String address) { this.address = address; }
}
