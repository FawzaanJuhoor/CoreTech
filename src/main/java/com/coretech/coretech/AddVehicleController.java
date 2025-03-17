package com.coretech.coretech;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddVehicleController {

    // Form Fields
    @FXML private TextField emailField;
    @FXML private TextField makeField;
    @FXML private TextField modelField;
    @FXML private TextField yearField;
    @FXML private TextField vinField;
    @FXML private TextField serviceHistoryField;

    // Buttons
    @FXML private Button addButton;
    @FXML private Button cancelButton;
    @FXML private Button addVehicleButton;
    @FXML private Button removeVehicleButton;
    @FXML private Button updateVehicleButton;
    @FXML private Button servicingDetailsButton;

    // Initialize method (optional)
    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    // Handle Add Vehicle Button Action
    @FXML
    private void handleAddVehicle() {
        String email = emailField.getText();
        String make = makeField.getText();
        String model = modelField.getText();
        String year = yearField.getText();
        String vin = vinField.getText();
        String serviceHistory = serviceHistoryField.getText();

        // Add logic to save the vehicle details
        System.out.println("Adding Vehicle with Email: " + email);
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("VIN: " + vin);
        System.out.println("Service History: " + serviceHistory);
    }

    // Handle Cancel Button Action
    @FXML
    private void handleCancel() {
        // Clear all form fields
        emailField.clear();
        makeField.clear();
        modelField.clear();
        yearField.clear();
        vinField.clear();
        serviceHistoryField.clear();
    }

    // Handle Remove Vehicle Button Action
    @FXML
    private void handleRemoveVehicle() {
        // Add logic to remove a vehicle
        System.out.println("Remove Vehicle button clicked");
    }

    // Handle Update Vehicle Button Action
    @FXML
    private void handleUpdateVehicle() {
        // Add logic to update vehicle information
        System.out.println("Update Vehicle Info button clicked");
    }

    // Handle Servicing Details Button Action
    @FXML
    private void handleServicingDetails() {
        // Add logic to view servicing details
        System.out.println("Servicing Details button clicked");
    }

    // Handle Home Button Action
    @FXML
    private void handleHome() {
        // Add logic to navigate to the home page
        System.out.println("Home button clicked");
    }

    // Handle Customer Management Button Action
    @FXML
    private void handleCustomerManagement() {
        // Add logic to navigate to the customer management page
        System.out.println("Customer Management button clicked");
    }

    // Handle Vehicle Management Button Action
    @FXML
    private void handleVehicleManagement() {
        // Add logic to navigate to the vehicle management page
        System.out.println("Vehicle Management button clicked");
    }

    // Handle Appointments Button Action
    @FXML
    private void handleAppointments() {
        // Add logic to navigate to the appointments page
        System.out.println("Appointments button clicked");
    }

    // Handle Servicing Button Action
    @FXML
    private void handleServicing() {
        // Add logic to navigate to the servicing page
        System.out.println("Servicing button clicked");
    }

    // Handle Logout Button Action
    @FXML
    private void handleLogout() {
        // Add logic to handle user logout
        System.out.println("Log Out button clicked");
    }
}