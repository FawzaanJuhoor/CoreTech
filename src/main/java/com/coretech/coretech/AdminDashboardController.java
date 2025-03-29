package com.coretech.coretech;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

public class AdminDashboardController {

    @FXML
    private Button homeButton, customerButton, vehicleButton, appointmentButton, serviceButton, logoutButton;
    @FXML
    private Button addSalesRepButton, removeSalesRepButton, updateSalesRepButton, viewSalesRepButton, inventoryButton, reportButton, revenueButton;
    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        // Set welcome text dynamically (if needed)
//        welcomeLabel.setText("Welcome, Admin");

        // Event handlers
//        homeButton.setOnAction(e -> handleHome());
//        customerButton.setOnAction(e -> handleCustomerManagement());
//        vehicleButton.setOnAction(e -> handleVehicleManagement());
//        appointmentButton.setOnAction(e -> handleAppointments());
//        serviceButton.setOnAction(e -> handleServicing());
//        logoutButton.setOnAction(e -> handleLogout());

//        addSalesRepButton.setOnAction(e -> handleAddSalesRep());
//        removeSalesRepButton.setOnAction(e -> handleRemoveSalesRep());
//        updateSalesRepButton.setOnAction(e -> handleUpdateSalesRep());
//        viewSalesRepButton.setOnAction(e -> handleViewSalesRep());
//        inventoryButton.setOnAction(e -> handleInventory());
//        reportButton.setOnAction(e -> handleReport());
//        revenueButton.setOnAction(e -> handleRevenueTracking());
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
    private void handleAddSalesRep() {
        System.out.println("Add Sales Rep Clicked");
    }

    @FXML
    private void handleRemoveSalesRep() {
        System.out.println("Remove Sales Rep Clicked");
    }

    @FXML
    private void handleUpdateSalesRep() {
        System.out.println("Update Sales Rep Info Clicked");
    }

    @FXML
    private void handleViewSalesRep() {
        System.out.println("View All Sales Reps Clicked");
    }

    @FXML
    private void handleInventory() {
        System.out.println("Inventory Management Clicked");
    }

    @FXML
    private void handleReport() {
        System.out.println("Monthly Report Clicked");
    }

    @FXML
    private void handleRevenueTracking() {
        System.out.println("Revenue Tracking Clicked");
    }
}
