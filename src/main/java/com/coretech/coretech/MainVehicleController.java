package com.coretech.coretech;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainVehicleController {

    // References to forms in the center pane
    @FXML
    private VBox addForm;
    @FXML
    private VBox updateForm;
    @FXML
    private VBox removeForm;
    @FXML
    private VBox servicingForm; // Added reference to the Servicing Details form

    // References to buttons in the right panel
    @FXML
    private Button addVehicleButton;
    @FXML
    private Button removeVehicleButton;
    @FXML
    private Button updateVehicleButton;
    @FXML
    private Button servicingDetailsButton;

    // Reference to the VIN search field in the Servicing Details form
    @FXML
    private TextField vinTextField;
    // Handle Add Vehicle Button Action
    @FXML
    private void handleAddVehicle() {
        // Show the "Add Vehicle" form
        addForm.setVisible(true);
        addForm.setManaged(true);
        updateForm.setVisible(false);
        updateForm.setManaged(false);
        removeForm.setVisible(false);
        removeForm.setManaged(false);
        servicingForm.setVisible(false); // Hide Servicing Details form
        servicingForm.setManaged(false);

        // Highlight the "Add Vehicle" button
        highlightButton(addVehicleButton);
    }

    // Handle Update Vehicle Button Action
    @FXML
    private void handleUpdateVehicle() {
        // Show the "Update Vehicle" form
        addForm.setVisible(false);
        addForm.setManaged(false);
        updateForm.setVisible(true);
        updateForm.setManaged(true);
        removeForm.setVisible(false);
        removeForm.setManaged(false);
        servicingForm.setVisible(false); // Hide Servicing Details form
        servicingForm.setManaged(false);

        // Highlight the "Update Vehicle Info" button
        highlightButton(updateVehicleButton);
    }

    // Handle Remove Vehicle Button Action
    @FXML
    private void handleRemoveVehicle() {
        // Show the "Remove Vehicle" form
        addForm.setVisible(false);
        addForm.setManaged(false);
        updateForm.setVisible(false);
        updateForm.setManaged(false);
        removeForm.setVisible(true);
        removeForm.setManaged(true);
        servicingForm.setVisible(false); // Hide Servicing Details form
        servicingForm.setManaged(false);

        // Highlight the "Remove Vehicle" button
        highlightButton(removeVehicleButton);
    }

    // Handle Servicing Details Button Action
    @FXML
    private void handleServicingDetails() {
        // Show the "Servicing Details" form
        addForm.setVisible(false);
        addForm.setManaged(false);
        updateForm.setVisible(false);
        updateForm.setManaged(false);
        removeForm.setVisible(false);
        removeForm.setManaged(false);
        servicingForm.setVisible(true); // Show Servicing Details form
        servicingForm.setManaged(true);

        // Highlight the "Servicing Details" button
        highlightButton(servicingDetailsButton);
    }
    // Handle Search Button Action (for Servicing Details)
    @FXML
    private void handleSearch() {
        String vin = vinTextField.getText().trim(); // Get the VIN from the text field
        if (!vin.isEmpty()) {
            // Add logic to search for service history based on the VIN
            System.out.println("Searching for service history with VIN: " + vin);
            // Example: Call a method to fetch and display service history
            // fetchAndDisplayServiceHistory(vin);
        } else {
            System.out.println("Please enter a valid VIN.");
        }
    }

    // Helper method to highlight the clicked button and reset others
    private void highlightButton(Button clickedButton) {
        // Reset all buttons to default style
        resetButtonStyles();

        // Highlight the clicked button
        clickedButton.setStyle("-fx-background-color: #2293C3; -fx-text-fill: white; -fx-border-color: #2293C3;");
    }

    // Helper method to reset all buttons to default style
    private void resetButtonStyles() {
        addVehicleButton.setStyle("-fx-background-color: white; -fx-text-fill: #2293C3; -fx-border-color: #2293C3;");
        removeVehicleButton.setStyle("-fx-background-color: white; -fx-text-fill: #2293C3; -fx-border-color: #2293C3;");
        updateVehicleButton.setStyle("-fx-background-color: white; -fx-text-fill: #2293C3; -fx-border-color: #2293C3;");
        servicingDetailsButton.setStyle("-fx-background-color: white; -fx-text-fill: #2293C3; -fx-border-color: #2293C3;");
    }

    // Other button handlers (same as before)
    @FXML
    private void handleHome() {
        System.out.println("Navigating to Home.");
    }

    @FXML
    private void handleCustomerManagement() {
        System.out.println("Navigating to Customer Management.");
    }

    @FXML
    private void handleVehicleManagement() {
        System.out.println("Navigating to Vehicle Management.");
    }

    @FXML
    private void handleAppointments() {
        System.out.println("Navigating to Appointments.");
    }

    @FXML
    private void handleServicing() {
        System.out.println("Navigating to Servicing.");
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logging out...");
    }

}