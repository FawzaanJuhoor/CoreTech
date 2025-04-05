package com.coretech.coretech;

import Models.Admin;
import Models.Customer;
import db.AdminDAO;
import db.CustomerDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainCustomerController {

    // FXML elements for buttons
    @FXML
    private Button addCustomerButton;
    @FXML
    private Button updateCustomerButton;
    @FXML
    private Button deleteCustomerButton;

    // FXML elements for Add Customer Form
    @FXML
    private VBox addCustomerForm;
    @FXML
    private TextField addCustomerNameField;
    @FXML
    private TextField addPhoneNoField;
    @FXML
    private TextField addEmailField;
    @FXML
    private TextArea addAddressField;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelAddButton;

    // FXML elements for Update Customer Form
    @FXML
    private VBox updateCustomerForm;
    @FXML
    private TextField updateSearchField;
    @FXML
    private Button updateSearchButton;
    @FXML
    private TextField updateCustomerNameField;
    @FXML
    private TextField updatePhoneNoField;
    @FXML
    private TextField updateEmailField;
    @FXML
    private TextArea updateAddressField;
    @FXML
    private Button updateButton;
    @FXML
    private Button cancelUpdateButton;

    // FXML elements for Delete Customer Form
    @FXML
    private VBox deleteCustomerForm;
    @FXML
    private TextField deleteSearchField;
    @FXML
    private Button deleteSearchButton;
    @FXML
    private TextField deleteCustomerNameField;
    @FXML
    private TextField deletePhoneNoField;
    @FXML
    private TextField deleteEmailField;
    @FXML
    private TextArea deleteAddressField;
    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelDeleteButton;

    // Main content pane
    @FXML
    private StackPane contentPane;

    // Initialize method (called when the FXML is loaded)
    @FXML
    public void initialize() {
        // Hide all forms initially
        hideAllForms();
        // Set the default active button (e.g., none)
        clearActiveButtons();
    }

    // Helper method to set the active button
    private void setActiveButton(Button activeButton) {
        // Remove the active class from all buttons
        clearActiveButtons();

        // Add the active class to the selected button
        if (activeButton != null) {
            activeButton.setStyle(
                    "-fx-background-color: #2293C3; " + // Highlight color
                            "-fx-text-fill: white; " +         // Text color
                            "-fx-font-weight: bold;"           // Bold text
            );
        }
    }

    // Helper method to clear the active class from all buttons
    private void clearActiveButtons() {
        addCustomerButton.setStyle("-fx-background-color: white; " + "-fx-text-fill: #2293C3; " + "-fx-border-color: #2293C3"); // Reset to default style
        updateCustomerButton.setStyle("-fx-background-color: white; " + "-fx-text-fill: #2293C3; " + "-fx-border-color: #2293C3"); // Reset to default style
        deleteCustomerButton.setStyle("-fx-background-color: white; " + "-fx-text-fill: #2293C3; " + "-fx-border-color: #2293C3"); // Reset to default style
    }
    // Hide all forms
    private void hideAllForms() {
        addCustomerForm.setVisible(false);
        addCustomerForm.setManaged(false);
        updateCustomerForm.setVisible(false);
        updateCustomerForm.setManaged(false);
        deleteCustomerForm.setVisible(false);
        deleteCustomerForm.setManaged(false);
    }

    // Show Add Customer Form
    @FXML
    private void showAddCustomerForm() {
        hideAllForms();
        addCustomerForm.setVisible(true);
        addCustomerForm.setManaged(true);
        setActiveButton(addCustomerButton); // Highlight the Add button
    }

    // Show Update Customer Form
    @FXML
    private void showUpdateCustomerForm() {
        hideAllForms();
        updateCustomerForm.setVisible(true);
        updateCustomerForm.setManaged(true);
        setActiveButton(updateCustomerButton); // Highlight the Update button
    }

    // Show Delete Customer Form
    @FXML
    private void showDeleteCustomerForm() {
        hideAllForms();
        deleteCustomerForm.setVisible(true);
        deleteCustomerForm.setManaged(true);
        setActiveButton(deleteCustomerButton); // Highlight the Delete button
    }

    // Handle Add Button Click
    @FXML
    private void handleAdd() {
        // Get input values
        String name = addCustomerNameField.getText().trim();
        String phoneNo = addPhoneNoField.getText().trim();
        String email = addEmailField.getText().trim();
        String address = addAddressField.getText().trim();

        // Validate inputs
        if (name.isEmpty() || phoneNo.isEmpty() || email.isEmpty() || address.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Add customer logic (e.g., save to database)
          Customer customer = new Customer(name, phoneNo, email, address);
        boolean isInserted = CustomerDAO.insertCustomer(customer);

        if (isInserted) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Customer added successfully!");
            clearAddForm();
        } else {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to add customer. Please try again.");
        }
    }

    @FXML
    private void handleUpdateSearch() {
        String email = updateSearchField.getText().trim();

        if (email.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please enter Email ID.");
            return;
        }

        Customer customer = CustomerDAO.searchCustomer(email); // Search by email

        if (customer != null) {
            updateCustomerNameField.setText(customer.getCustomerName());
            updatePhoneNoField.setText(customer.getPhoneNo());
            updateEmailField.setText(customer.getEmailID());
            updateAddressField.setText(customer.getAddress());
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Not Found", "No customer found with the given details.");
        }
    }

    /**
     * Disables the email field so it cannot be modified.
     */
    private void disableEmailField() {
        updateEmailField.setDisable(true);
    }

    /**
     * Enables the email field when a new customer search is performed.
     */
    @FXML
    private void enableEmailField() {
        updateEmailField.setDisable(false);
    }
    @FXML
    private void handleUpdate() {
        String name = updateCustomerNameField.getText().trim();
        String phone = updatePhoneNoField.getText().trim();
        String email = updateEmailField.getText().trim();
        String address = updateAddressField.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please fill in all fields.");
            return;
        }

        Customer customer = new Customer(name, phone, email, address);
        boolean isUpdated = CustomerDAO.updateCustomer(customer);

        if (isUpdated) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Customer updated successfully!");
        } else {
            clearUpdateForm();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update customer.");
        }
        // Keep email field disabled after updating
        disableEmailField();

        // Clear fields after updating
        clearUpdateForm();
    }

    @FXML
    private void handleDeleteSearch() {
        String email = deleteSearchField.getText().trim();

        if (email.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please enter Email ID.");
            return;
        }

        Customer customer = CustomerDAO.searchCustomer(email); // Search by email instead of phone number

        if (customer != null) {
            deleteCustomerNameField.setText(customer.getCustomerName());
            deletePhoneNoField.setText(customer.getPhoneNo());
            deleteEmailField.setText(customer.getEmailID());
            deleteAddressField.setText(customer.getAddress());
            disableDeleteFields(); // Disable fields after deletion

        } else {
            showAlert(Alert.AlertType.INFORMATION, "Not Found", "No customer found with the given details.");

        }
    }

    private void disableDeleteFields() {
        deleteCustomerNameField.setDisable(true);
        deletePhoneNoField.setDisable(true);
        deleteEmailField.setDisable(true);
        deleteAddressField.setDisable(true);

    }


    @FXML
    private void handleDelete() {
        String email = deleteEmailField.getText().trim(); // Get email instead of phone number

        if (email.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please enter Email ID.");
            return;
        }

        boolean isDeleted = CustomerDAO.deleteCustomer(email); // Delete by email

        if (isDeleted) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Customer deleted successfully!");
            clearDeleteForm();
            disableDeleteFields(); // Disable fields after deletion

        } else {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to delete customer.");
            clearDeleteForm();
            disableDeleteFields(); // Disable fields after deletion

        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Handle Cancel Button Click (Add Form)
    @FXML
    private void handleCancelAdd() {
        clearAddForm();
//        hideAllForms();
    }

    // Handle Cancel Button Click (Update Form)
    @FXML
    private void handleCancelUpdate() {
        clearUpdateForm();
//        hideAllForms();
    }

    // Handle Cancel Button Click (Delete Form)
    @FXML
    private void handleCancelDelete() {
        clearDeleteForm();

//        hideAllForms();
    }


    // Clear Add Form fields
    private void clearAddForm() {
        addCustomerNameField.clear();
        addPhoneNoField.clear();
        addEmailField.clear();
        addAddressField.clear();
    }

    // Clear Update Form fields
    private void clearUpdateForm() {
        updateSearchField.clear();
        updateCustomerNameField.clear();
        updatePhoneNoField.clear();
        updateEmailField.clear();
        updateAddressField.clear();
        updateEmailField.setDisable(true); // Keep email field disabled

    }

    // Clear Delete Form fields
    private void clearDeleteForm() {
        deleteSearchField.clear();
        deleteCustomerNameField.clear();
        deletePhoneNoField.clear();
        deleteEmailField.clear();
        deleteAddressField.clear();

        disableDeleteFields(); // Disable fields after deletion
    }

    // Navigation methods (example implementations)
    @FXML
    private void handleHome() {
        System.out.println("Navigating to Home...");
        // Add navigation logic here
    }

    @FXML
    private void handleCustomerManagement() {
        System.out.println("Navigating to Customer Management...");
        // Add navigation logic here
    }

    @FXML
    private void handleVehicleManagement() {
        System.out.println("Navigating to Vehicle Management...");
        // Add navigation logic here
    }

    @FXML
    private void handleAppointments() {
        System.out.println("Navigating to Appointments...");
        // Add navigation logic here
    }

    @FXML
    private void handleServicing() {
        System.out.println("Navigating to Servicing...");
        // Add navigation logic here
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logging out...");
        // Add logout logic here
    }
}