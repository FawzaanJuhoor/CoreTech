package com.coretech.coretech;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddCustomerController {

    @FXML
    private TextField userNameField;

    @FXML
    private TextField phoneNoField;

    @FXML
    private TextField emailField;

    @FXML
    private TextArea textarea;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        // Set welcome text dynamically (if needed)
        welcomeLabel.setText("Welcome, Sales Rep");


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
        String address = textarea.getText();

        // Validate input (basic validation)
        if (userName.isEmpty() || phoneNo.isEmpty() || email.isEmpty() || address.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Process the data (e.g., save to database or print to console)
        System.out.println("Adding Sales Rep:");
        System.out.println("User Name: " + userName);
        System.out.println("Phone No: " + phoneNo);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);

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
        textarea.clear();
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

    public void handleAddCustomer(ActionEvent actionEvent) {
    }

    public void handleUpdateCustomer(ActionEvent actionEvent) {
    }

    public void handleRemoveCustomer(ActionEvent actionEvent) {
    }

}