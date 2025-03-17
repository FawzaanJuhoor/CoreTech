package com.coretech.coretech;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SalesRepDashboardController {
    @FXML
    private Button homeButton, customerButton, vehicleButton, appointmentButton, serviceButton, logoutButton;
    @FXML
    private Button addSalesRepButton, removeSalesRepButton, updateSalesRepButton, viewSalesRepButton, inventoryButton, reportButton, revenueButton;
    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        // Set welcome text dynamically (if needed)
        welcomeLabel.setText("Welcome, Admin");

        // Event handlers
        homeButton.setOnAction(e -> handleHome());
        customerButton.setOnAction(e -> handleCustomerManagement());
        vehicleButton.setOnAction(e -> handleVehicleManagement());
        appointmentButton.setOnAction(e -> handleAppointments());
        serviceButton.setOnAction(e -> handleServicing());
        logoutButton.setOnAction(e -> handleLogout());

        addSalesRepButton.setOnAction(e -> handleAddCustomer());
        removeSalesRepButton.setOnAction(e -> handleUpdateCustomer());
        updateSalesRepButton.setOnAction(e -> handleRemoveCustomer());

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
    private void handleLogout() {
        System.out.println("Logging Out...");
    }

    @FXML
    private void handleAddCustomer() {
        System.out.println("Add Sales Rep Clicked");
    }

    @FXML
    private void handleRemoveCustomer() {
        System.out.println("Remove Sales Rep Clicked");
    }

    @FXML
    private void handleUpdateCustomer() {
        System.out.println("Update Sales Rep Info Clicked");
    }

}
