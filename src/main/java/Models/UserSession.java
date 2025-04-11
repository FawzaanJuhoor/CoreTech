package Models;

/**
 * Represents a singleton user session in the system, storing the current user's username and ID.
 * Only one instance of this class can exist at a time, following the singleton design pattern.
 */
public class UserSession {
    /** The single instance of the UserSession class. */
    private static UserSession instance;

    /** The username of the currently logged-in user. */
    private String username;

    /** The unique identifier of the currently logged-in user. */
    private int userId;  // Assuming user ID is an integer

    /**
     * Private constructor to prevent direct instantiation and enforce the singleton pattern.
     *
     * @param username the username of the user
     * @param userId the unique identifier of the user
     */
    private UserSession(String username, int userId) {
        this.username = username;
        this.userId = userId;
    }

    /**
     * Returns the singleton instance of UserSession, creating it if it doesn't exist.
     *
     * @param username the username of the user to initialize the session with
     * @param userId the unique identifier of the user to initialize the session with
     * @return the singleton UserSession instance
     */
    public static UserSession getInstance(String username, int userId) {
        if (instance == null) {
            instance = new UserSession(username, userId);
        }
        return instance;
    }

    /**
     * Returns the existing singleton instance of UserSession, or null if it hasn't been initialized.
     *
     * @return the singleton UserSession instance, or null if not yet created
     */
    public static UserSession getInstance() {
        return instance;
    }

    /**
     * Returns the username of the currently logged-in user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the user ID of the currently logged-in user.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Clears the current session by setting the singleton instance to null.
     * This effectively logs out the user and allows a new session to be created.
     */
    public static void clearSession() {
        instance = null;
    }
}
