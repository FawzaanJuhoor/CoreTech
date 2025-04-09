package Models;

/**
 * Represents an administrator user in the system with basic authentication and role information.
 */
public class Admin {

    /** The username of the admin. */
    private String username;

    /** The phone number of the admin. */
    private String phone;

    /** The email address of the admin. */
    private String email;

    /** The password for the admin's account. */
    private String password;

    /** The role assigned to the admin (e.g., manager, support). */
    private String role;

    /**
     * Constructs a new Admin object with the specified details.
     *
     * @param username the username of the admin
     * @param phone the phone number of the admin
     * @param email the email address of the admin
     * @param password the password for the admin's account
     * @param role the role assigned to the admin
     */
    public Admin(String username, String phone, String email, String password, String role) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Returns the username of the admin.
     *
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Sets the username of the admin.
     *
     * @param username the new username to set
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Returns the phone number of the admin.
     *
     * @return the phone number
     */
    public String getPhone() { return phone; }

    /**
     * Sets the phone number of the admin.
     *
     * @param phone the new phone number to set
     */
    public void setPhone(String phone) { this.phone = phone; }

    /**
     * Returns the email address of the admin.
     *
     * @return the email address
     */
    public String getEmail() { return email; }

    /**
     * Sets the email address of the admin.
     *
     * @param email the new email address to set
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Returns the password of the admin.
     *
     * @return the password
     */
    public String getPassword() { return password; }

    /**
     * Sets the password of the admin.
     *
     * @param password the new password to set
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Returns the role of the admin.
     *
     * @return the role
     */
    public String getRole() { return role; }

    /**
     * Sets the role of the admin.
     *
     * @param role the new role to set
     */
    public void setRole(String role) { this.role = role; }
}
