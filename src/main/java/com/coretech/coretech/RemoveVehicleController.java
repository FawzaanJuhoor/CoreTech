package com.coretech.coretech;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RemoveVehicleController {

    // Form Fields
    @FXML private TextField vinSearchField; // New field for VIN search
    @FXML private TextField emailField;     // New field for Email
    @FXML private TextField makeField;      // New field for Make
    @FXML private TextField modelField;     // New field for Model
    @FXML private TextField yearField;      // New field for Year
    @FXML private TextField vinField;       // Existing field for VIN
    @FXML private TextField serviceHistoryField; // New field for Service History

    // Buttons
    @FXML private Button searchButton;  // New button for Search
    @FXML private Button deleteButton;  // Renamed from removeButton
    @FXML private Button cancelButton;  // Existing button for Cancel

    // Initialize method (optional)
    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    // Handle Search Button Action
    @FXML
    private void handleSearch() {
        String vin = vinSearchField.getText();

        // Add logic to search for the vehicle using the VIN
        System.out.println("Searching for Vehicle with VIN: " + vin);

        // Populate the form fields with the retrieved data (mock example)
        emailField.setText("example@example.com");
        makeField.setText("Toyota");
        modelField.setText("Camry");
        yearField.setText("2020");
        vinField.setText(vin);
        serviceHistoryField.setText("Regular maintenance");
    }

    // Handle Delete Button Action
    @FXML
    private void handleDelete() {
        String vin = vinField.getText();

        // Add logic to delete the vehicle using the VIN
        System.out.println("Deleting Vehicle with VIN: " + vin);

        // Clear the form fields after deletion
        clearForm();
    }

    // Handle Cancel Button Action
    @FXML
    private void handleCancel() {
        clearForm();
    }

    // Helper method to clear the form fields
    private void clearForm() {
        vinSearchField.clear();
        emailField.clear();
        makeField.clear();
        modelField.clear();
        yearField.clear();
        vinField.clear();
        serviceHistoryField.clear();
    }

    // Other button handlers (same as referenced controller)
    @FXML
    private void handleHome() {
        System.out.println("Home button clicked");
    }

    @FXML
    private void handleCustomerManagement() {
        System.out.println("Customer Management button clicked");
    }

    @FXML
    private void handleVehicleManagement() {
        System.out.println("Vehicle Management button clicked");
    }

    @FXML
    private void handleAppointments() {
        System.out.println("Appointments button clicked");
    }

    @FXML
    private void handleServicing() {
        System.out.println("Servicing button clicked");
    }

    @FXML
    private void handleLogout() {
        System.out.println("Log Out button clicked");
    }

    @FXML
    private void handleAddVehicle() {
        System.out.println("Add Vehicle button clicked");
    }

    @FXML
    private void handleUpdateVehicle() {
        System.out.println("Update Vehicle Info button clicked");
    }

    @FXML
    private void handleServicingDetails() {
        System.out.println("Servicing Details button clicked");
    }

    public void handleRemoveVehicle(ActionEvent actionEvent) {

    }
}