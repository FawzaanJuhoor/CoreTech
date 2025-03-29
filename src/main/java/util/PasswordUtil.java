package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // Hash the password before storing
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    // Verify user-entered password against stored hashed password
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
