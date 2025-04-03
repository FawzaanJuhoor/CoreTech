package com.coretech.coretech;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Objects;

public class SalesRepDashboardController {
    @FXML
    private Button homeButton, customerButton, vehicleButton, appointmentButton, serviceButton, logoutButton;

    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        // Set welcome text dynamically (if needed)
//        welcomeLabel.setText("Welcome, Admin");

        // Event handlers
        homeButton.setOnAction(e -> handleHome());
        customerButton.setOnAction(e -> handleCustomerManagement());
        vehicleButton.setOnAction(e -> handleVehicleManagement());
        appointmentButton.setOnAction(e -> handleAppointments());
        serviceButton.setOnAction(e -> handleServicing());
        logoutButton.setOnAction(e -> handleLogout(e));

    }

    @FXML
    private void handleHome() {
        System.out.println("Home Clicked");
    }

    @FXML
    private void handleCustomerManagement() {
        System.out.println("Customer Management Clicked");
    }

    @FXML
    private void handleVehicleManagement() {
        System.out.println("Vehicle Management Clicked");
    }

    @FXML
    private void handleAppointments() {
        System.out.println("Appointments Clicked");
    }

    @FXML
    private void handleServicing() {
        System.out.println("Servicing Clicked");
    }



    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            // Get current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Load the login page
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
