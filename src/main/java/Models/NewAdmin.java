package Models;
public class NewAdmin {
    private final int userId;
    private final String username;
    private final String phone;
    private final String role;

    public NewAdmin(int userId, String username, String phone, String role) {
        this.userId = userId;
        this.username = username;
        this.phone = phone;
        this.role = role;
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPhone() { return phone; }
    public String getRole() { return role; }
}

