package com.coretech.coretech;

import Models.UserSession;
import db.DBConnection;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import javafx.scene.control.Label;
import javafx.stage.WindowEvent;

public class BaseController {

    // Method to set the close request handler
    public void setStage(Stage stage) {
        stage.setOnCloseRequest(this::handleWindowClose);
    }

    // Window close event handler to log out and exit the application
    private void handleWindowClose(WindowEvent event) {
        System.out.println("Application closing... Logging out user.");

        UserSession session = UserSession.getInstance();
        if (session != null) {
            updateUserSessionStatus(session.getUserId(), "OFFLINE"); // Mark user as offline
            UserSession.clearSession();
        }

        Platform.exit(); // Ensures JavaFX exits properly
        System.exit(0); // Ensure JVM terminates
    }

    protected void setWelcomeMessage(Label welcomeLabel) {
        UserSession session = UserSession.getInstance();
        if (session != null) {
            welcomeLabel.setText("Welcome, " + session.getUsername());
        } else {
            welcomeLabel.setText("Welcome!");
        }
    }

    protected void switchScene(String fxmlFile, String title, Node sourceNode) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Stage stage = (Stage) sourceNode.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void logout(Node sourceNode) {
        UserSession session = UserSession.getInstance();
        if (session != null) {
            updateUserSessionStatus(session.getUserId(), "OFFLINE"); // Mark as offline
            UserSession.clearSession();
        }

        // Only switch scene if the application is still running
        if (sourceNode.getScene() != null) {
            switchScene("Login.fxml", "Login", sourceNode);
        }
    }




    void updateUserSessionStatus(int userId, String status) {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{call UPDATE_SESSION_STATUS(?, ?)}")) {

            cstmt.setInt(1, userId);
            cstmt.setString(2, status);

            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
