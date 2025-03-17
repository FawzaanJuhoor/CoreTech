package com.coretech.coretech;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class AddSalesRepController {

    @FXML
    private TextField userNameField;

    @FXML
    private TextField phoneNoField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        // Set welcome text dynamically (if needed)
        welcomeLabel.setText("Welcome, Admin");

        // Populate the role combo box (example roles)
        roleComboBox.getItems().addAll("Sales Rep", "Manager", "Admin");

        // Event handlers
        addButton.setOnAction(e -> handleAdd());
        cancelButton.setOnAction(e -> handleCancel());
    }

    @FXML
    private void handleAdd() {
        // Get input values
        String userName = userNameField.getText();
        String phoneNo = phoneNoField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();

        // Validate input (basic validation)
        if (userName.isEmpty() || phoneNo.isEmpty() || email.isEmpty() || password.isEmpty() || role == null) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Process the data (e.g., save to database or print to console)
        System.out.println("Adding Sales Rep:");
        System.out.println("User Name: " + userName);
        System.out.println("Phone No: " + phoneNo);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);

        // Clear the form after submission
        clearForm();
    }

    @FXML
    private void handleCancel() {
        // Clear the form
        clearForm();
        System.out.println("Form cleared.");
    }

    private void clearForm() {
        userNameField.clear();
        phoneNoField.clear();
        emailField.clear();
        passwordField.clear();
        roleComboBox.getSelectionModel().clearSelection();
    }

    public void handleLogout(ActionEvent actionEvent) {
    }

    public void handleServicing(ActionEvent actionEvent) {
    }

    public void handleAppointments(ActionEvent actionEvent) {
    }

    public void handleVehicleManagement(ActionEvent actionEvent) {
    }

    public void handleCustomerManagement(ActionEvent actionEvent) {
    }

    public void handleHome(ActionEvent actionEvent) {
    }

    public void handleRemoveSalesRep(ActionEvent actionEvent) {
    }

    public void handleUpdateSalesRep(ActionEvent actionEvent) {
    }

    public void handleViewSalesRep(ActionEvent actionEvent) {
    }

    public void handleInventory(ActionEvent actionEvent) {
    }

    public void handleReport(ActionEvent actionEvent) {
    }

    public void handleRevenueTracking(ActionEvent actionEvent) {
    }

    public void handleAddSalesRep(ActionEvent actionEvent) {
    }
}