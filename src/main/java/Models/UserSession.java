package Models;

public class UserSession {
    private static UserSession instance;
    private String username;
    private int userId;  // Assuming user ID is an integer

    private UserSession(String username, int userId) {
        this.username = username;
        this.userId = userId;
    }

    public static UserSession getInstance(String username, int userId) {
        if (instance == null) {
            instance = new UserSession(username, userId);
        }
        return instance;
    }

    public static UserSession getInstance() {
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }

    public static void clearSession() {
        instance = null;
    }
}
