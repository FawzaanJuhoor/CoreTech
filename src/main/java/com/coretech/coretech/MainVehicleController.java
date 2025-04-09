package com.coretech.coretech;

import Models.Vehicle;
import db.CustomerDAO;
import db.VehicleDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MainVehicleController extends BaseController{

    @FXML
    private Button homeButton, customerButton, vehicleButton, appointmentButton, serviceButton, logoutButton;

    @FXML
    protected Label welcomeLabel; // Must be protected or public if accessed by subclass

    @FXML
    public void initialize() {
        setWelcomeMessage(welcomeLabel); // Set welcome message from BaseController

        // Event handlers
        homeButton.setOnAction(this::handleHome);
        customerButton.setOnAction(this::handleCustomerManagement);
        vehicleButton.setOnAction(this::handleVehicleManagement);
        appointmentButton.setOnAction(this::handleAppointments);
        serviceButton.setOnAction(this::handleServicing);
        logoutButton.setOnAction(e -> handleLogout());

    }
    public TextField addEmailField;
    public VBox centerPane;
    public TextField addMake;
    public TextField addModel;
    public TextField addYear;
    public TextField addVin;
    public TextField addServiceHistory;
    public Button addButton;
    public Button cancelAddButton;
    public TextField updateVinSearchField;
    public Button updateSearchButton;
    public TextField updateEmailField;
    public TextField updateMakeField;
    public TextField updateModelField;
    public TextField updateYearField;
    public TextField updateVinField;
    public TextField updateServiceHistoryField;
    public Button updateButton;
    public Button updateCancelButton;
    public TextField removeVinSearchField;
    public Button removeSearchButton;
    public TextField removeEmailField;
    public TextField removeMakeField;
    public TextField removeModelField;
    public TextField removeYearField;
    public TextField removeVinField;
    public TextField removeServiceHistoryField;
    public Button removeButton;
    public Button removeCancelButton;
    public TableView serviceHistoryTable;
    public TableColumn dateColumn;
    public TableColumn descriptionColumn;
    public VBox spacer;
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

    @FXML
    private void handleHome(ActionEvent event) {
        System.out.println("Home Clicked");
        switchScene("SalesRepDashboard.fxml", "Home", (Node) event.getSource());

    }

    @FXML
    private void handleCustomerManagement(ActionEvent event) {
        System.out.println("Customer Management Clicked");
        switchScene("MainCustomerManagement.fxml", "Home", (Node) event.getSource());

    }

    @FXML
    private void handleVehicleManagement(ActionEvent event) {
        System.out.println("Vehicle Management Clicked");
        switchScene("MainVehicleManagement.fxml", "Home", (Node) event.getSource());

    }

    @FXML
    private void handleAppointments(ActionEvent event) {
        System.out.println("Appointments Clicked");
        switchScene("MainAppointmentManagement.fxml", "Home", (Node) event.getSource());

    }

    @FXML
    private void handleServicing(ActionEvent event) {
        System.out.println("Servicing Clicked");
    }

    @FXML
    private void handleLogout() {
        logout(welcomeLabel); // Use common logout method from BaseController
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void AddVehicle() {
        String email = addEmailField.getText().trim();
        String make = addMake.getText().trim();
        String model = addModel.getText().trim();
        String yearStr = addYear.getText().trim();
        String vin = addVin.getText().trim();
        String serviceHistory = addServiceHistory.getText().trim();

        // Basic validation
        if (email.isEmpty() || make.isEmpty() || model.isEmpty() || yearStr.isEmpty() || vin.isEmpty() || serviceHistory.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill in all fields.");
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Year", "Year must be a valid number.");
            return;
        }

        // Get CustomerID from email
        int customerId = CustomerDAO.getCustomerIdByEmail(email);
        if (customerId == -1) {
            showAlert(Alert.AlertType.ERROR, "Customer Not Found", "No customer found with the provided email.");
            return;
        }

        // Create vehicle and insert into DB
        Vehicle vehicle = new Vehicle(vin, customerId, make, model, year, serviceHistory);
        boolean inserted = VehicleDAO.insertVehicle(vehicle);

        if (inserted) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Vehicle added successfully.");
            clearAddVehicleForm();
        } else {
            showAlert(Alert.AlertType.ERROR, "Insert Failed", "Failed to add vehicle. Please try again.");
        }
    }

    @FXML
    private void clearAddVehicleForm() {
        addEmailField.clear();
        addMake.clear();
        addModel.clear();
        addYear.clear();
        addVin.clear();
        addServiceHistory.clear();
    }

    @FXML
    private void clearUpdateVehicleForm() {
        updateVinSearchField.clear();
        updateEmailField.clear();
        updateMakeField.clear();
        updateModelField.clear();
        updateYearField.clear();
        updateVinField.clear();
        updateServiceHistoryField.clear();
    }

    @FXML
    private void SearchVehicle() {
        String vin = updateVinSearchField.getText().trim();

        if (vin.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please enter a VIN to search.");
            return;
        }

        Vehicle vehicle = VehicleDAO.searchVehicleByVIN(vin);
        if (vehicle != null) {
            updateEmailField.setText(CustomerDAO.getEmailByCustomerId(vehicle.getCustomerId())); // If available
            updateMakeField.setText(vehicle.getMake());
            updateModelField.setText(vehicle.getModel());
            updateYearField.setText(String.valueOf(vehicle.getYear()));
            updateVinField.setText(vehicle.getVIN());
            updateServiceHistoryField.setText(vehicle.getServiceHistory());

            showAlert(Alert.AlertType.INFORMATION, "Success", "Vehicle found and loaded.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Not Found", "No vehicle found with VIN: " + vin);
        }
    }

    @FXML
    private void UpdateVehicle() {
        String email = updateEmailField.getText().trim();
        String make = updateMakeField.getText().trim();
        String model = updateModelField.getText().trim();
        String yearStr = updateYearField.getText().trim();
        String vin = updateVinField.getText().trim();
        String serviceHistory = updateServiceHistoryField.getText().trim();

        if (email.isEmpty() || make.isEmpty() || model.isEmpty() || yearStr.isEmpty() || vin.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please fill all required fields.");
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Year must be a number.");
            return;
        }

        int customerId = CustomerDAO.getCustomerIdByEmail(email);
        if (customerId == -1) {
            showAlert(Alert.AlertType.ERROR, "Invalid Email", "No customer found with email: " + email);
            return;
        }

        Vehicle updatedVehicle = new Vehicle(customerId, make, model, year, vin, serviceHistory);

        boolean success = VehicleDAO.updateVehicle(updatedVehicle);
        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Vehicle updated successfully!");
            clearUpdateVehicleForm();
        } else {
            showAlert(Alert.AlertType.ERROR, "Failure", "Failed to update vehicle. Please try again.");
        }
    }

    public void searchVehicleForRemoval() {
        String vin = removeVinSearchField.getText().trim();

        if (vin.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a VIN.");
            return;
        }

        // Call DAO method to search for the vehicle
        Vehicle vehicle = VehicleDAO.searchVehicleByVIN(vin);

        if (vehicle != null) {
            // Populate fields
            removeEmailField.setText(CustomerDAO.getEmailByCustomerId(vehicle.getCustomerId()));
            removeMakeField.setText(vehicle.getMake());
            removeModelField.setText(vehicle.getModel());
            removeYearField.setText(String.valueOf(vehicle.getYear()));
            removeVinField.setText(vehicle.getVIN());
            removeServiceHistoryField.setText(vehicle.getServiceHistory());

            // Disable all fields
            setFieldsDisabled(true);
        } else {
            showAlert(Alert.AlertType.ERROR, "Not Found", "No vehicle found with the given VIN.");
        }
    }

    public void removeVehicle() {
        String vin = removeVinField.getText().trim();

        if (vin.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "VIN is required to remove a vehicle.");
            return;
        }

        boolean success = VehicleDAO.deleteVehicleByVIN(vin);

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Vehicle removed successfully!");
            clearFields();
            setFieldsDisabled(false); // Enable fields for next search
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to remove vehicle. VIN not found.");
        }
    }

    @FXML
    private void setFieldsDisabled(boolean disabled) {
        removeEmailField.setDisable(disabled);
        removeMakeField.setDisable(disabled);
        removeModelField.setDisable(disabled);
        removeYearField.setDisable(disabled);
        removeVinField.setDisable(disabled);
        removeServiceHistoryField.setDisable(disabled);
    }

    @FXML
    private void clearFields() {
        removeEmailField.clear();
        removeMakeField.clear();
        removeModelField.clear();
        removeYearField.clear();
        removeVinField.clear();
        removeServiceHistoryField.clear();
    }



}