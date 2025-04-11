package Models;

/**
 * Represents an immutable administrator user in the system with basic identification and role information.
 * This class does not allow modification of its fields after instantiation.
 */
public class NewAdmin {

    /** The unique identifier for the admin user. */
    private final int userId;

    /** The username of the admin. */
    private final String username;

    /** The phone number of the admin. */
    private final String phone;

    /** The role assigned to the admin (e.g., manager, support). */
    private final String role;

    /**
     * Constructs a new NewAdmin object with the specified details.
     * Once created, the object's fields cannot be modified.
     *
     * @param userId the unique identifier for the admin user
     * @param username the username of the admin
     * @param phone the phone number of the admin
     * @param role the role assigned to the admin
     */
    public NewAdmin(int userId, String username, String phone, String role) {
        this.userId = userId;
        this.username = username;
        this.phone = phone;
        this.role = role;
    }

    /**
     * Returns the user ID of the admin.
     *
     * @return the user ID
     */
    public int getUserId() { return userId; }

    /**
     * Returns the username of the admin.
     *
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Returns the phone number of the admin.
     *
     * @return the phone number
     */
    public String getPhone() { return phone; }

    /**
     * Returns the role of the admin.
     *
     * @return the role
     */
    public String getRole() { return role; }
}

