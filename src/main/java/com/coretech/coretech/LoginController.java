package com.coretech.coretech;

import Models.UserSession;
import db.DBConnection;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Objects;

public class LoginController extends BaseController {
    @FXML
    public Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setOnCloseRequest(this::handleWindowClose); // Set close event
    }

    private void handleWindowClose(WindowEvent event) {
        System.out.println("Application closing...");
        Platform.exit(); // Stops JavaFX threads
        System.exit(0); // Ensures JVM terminates
    }
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Fields cannot be empty!");
            messageLabel.setTextFill(Color.RED);
            return;
        }

        String[] result = validateLogin(username, password);

        if (result != null) {
            String role = result[0];

            messageLabel.setText("Login Successful!");
            messageLabel.setTextFill(Color.GREEN);
            loadDashboard(role); // Load the appropriate dashboard
        } else {
            messageLabel.setText("Invalid credentials!");
            messageLabel.setTextFill(Color.RED);
        }
    }

    private void loadDashboard(String role) {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();

            // Determine which FXML file to load based on role
            String fxmlFile = role.equalsIgnoreCase("Admin")
                    ? "AdminView.fxml"
                    : "SalesRepDashboard.fxml";

            // Set the correct title for the dashboard
            String title = role.equalsIgnoreCase("Admin")
                    ? "Admin Dashboard"
                    : "Sales Representative Dashboard";

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Get the controller and pass the stage reference
            Object controller = loader.getController();
            if (controller instanceof SalesRepDashboardController) {
                ((SalesRepDashboardController) controller).setStage(stage);
            } else if (controller instanceof AdminController) {
                ((AdminController) controller).setStage(stage);
            }

            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String[] validateLogin(String username, String password) {
        String storedPassword = null;
        String role = null;
        int userId = -1;
        String sessionStatus = "OFFLINE";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{call GET_USER_PASSWORD_ROLE(?, ?, ?, ?, ?)}")) {

            cstmt.setString(1, username);
            cstmt.registerOutParameter(2, Types.VARCHAR); // Password
            cstmt.registerOutParameter(3, Types.VARCHAR); // Role
            cstmt.registerOutParameter(4, Types.INTEGER); // User ID
            cstmt.registerOutParameter(5, Types.VARCHAR); // Session status

            cstmt.execute();

            storedPassword = cstmt.getString(2);
            role = cstmt.getString(3);
            userId = cstmt.getInt(4);
            sessionStatus = cstmt.getString(5);

            // 🔹 Print out values for debugging
            System.out.println("Entered Password: " + password);
            System.out.println("Stored Hash: " + storedPassword);
            System.out.println("BCrypt Check: " + BCrypt.checkpw(password, storedPassword));
            System.out.println("Role: " + role);
            System.out.println("User ID: " + userId);
            System.out.println("Session Status: " + sessionStatus);

            // Check if credentials exist
            if (storedPassword == null) {
                System.out.println("User not found!");
                messageLabel.setText("Invalid credentials!");
                messageLabel.setTextFill(Color.RED);
                return null;
            }

            // Check if user is already logged in
            if ("ONLINE".equalsIgnoreCase(sessionStatus)) {
                messageLabel.setText("User already logged in!");
                messageLabel.setTextFill(Color.RED);
                return null;
            }

            // Verify password using BCrypt
            if (BCrypt.checkpw(password, storedPassword)) {
                // Update session status
                updateUserSessionStatus(userId, "ONLINE");

                // Store user session
                UserSession.getInstance(username, userId);
                return new String[]{role};
            } else {
                System.out.println("Password mismatch!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        messageLabel.setText("Invalid credentials!");
        messageLabel.setTextFill(Color.RED);
        return null;
    }


}
