package com.coretech.coretech;

import javafx.event.ActionEvent;
import db.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import util.PasswordUtil;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.Objects;

public class LoginController {
    @FXML
    public Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

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

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
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

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{call GET_USER_PASSWORD_ROLE(?, ?, ?)}")) {

            cstmt.setString(1, username);
            cstmt.registerOutParameter(2, Types.VARCHAR); // For password
            cstmt.registerOutParameter(3, Types.VARCHAR); // For role

            cstmt.execute();

            storedPassword = cstmt.getString(2);
            role = cstmt.getString(3);

            if (storedPassword != null && BCrypt.checkpw(password, storedPassword)) {
                return new String[]{role}; // Return role if authentication succeeds
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if authentication fails
    }

}
