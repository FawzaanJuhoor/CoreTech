package com.coretech.coretech;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ServicingFormController {

    // Main Content Fields
    @FXML private TextField vinTextField;
    @FXML private TextField customerEmailTextField;
    @FXML private TextField serviceTypeTextField;
    @FXML private DatePicker serviceDatePicker;
    @FXML private TextField descriptionTextField;

    // Buttons
    @FXML private Button addServiceButton;
    @FXML private Button cancelButton;

    // Sidebar Buttons
    @FXML private Button homeButton;
    @FXML private Button customerManagementButton;
    @FXML private Button vehicleManagementButton;
    @FXML private Button appointmentsButton;
    @FXML private Button servicingButton;
    @FXML private Button logoutButton;

    // Event Handlers
    @FXML
    private void handleAddService() {
        // Logic to add a new service
        String vin = vinTextField.getText();
        String customerEmail = customerEmailTextField.getText();
        String serviceType = serviceTypeTextField.getText();
        String serviceDate = serviceDatePicker.getValue().toString();
        String description = descriptionTextField.getText();

        System.out.println("Adding new service:");
        System.out.println("VIN: " + vin);
        System.out.println("Customer Email: " + customerEmail);
        System.out.println("Service Type: " + serviceType);
        System.out.println("Service Date: " + serviceDate);
        System.out.println("Description: " + description);
    }

    @FXML
    private void handleCancel() {
        // Logic to clear the form
        vinTextField.clear();
        customerEmailTextField.clear();
        serviceTypeTextField.clear();
        serviceDatePicker.setValue(null);
        descriptionTextField.clear();
        System.out.println("Form cleared.");
    }

    @FXML
    private void handleHome() {
        // Logic to navigate to the home page
        System.out.println("Navigating to Home...");
    }

    @FXML
    private void handleCustomerManagement() {
        // Logic to navigate to customer management
        System.out.println("Navigating to Customer Management...");
    }

    @FXML
    private void handleVehicleManagement() {
        // Logic to navigate to vehicle management
        System.out.println("Navigating to Vehicle Management...");
    }

    @FXML
    private void handleAppointments() {
        // Logic to navigate to appointments
        System.out.println("Navigating to Appointments...");
    }

    @FXML
    private void handleServicing() {
        // Logic to navigate to servicing
        System.out.println("Navigating to Servicing...");
    }

    @FXML
    private void handleLogout() {
        // Logic to handle logout
        System.out.println("Logging out...");
    }
}