package com.coretech.coretech;

import javafx.fxml.FXML;
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
        System.out.println("Adding Customer:");
        System.out.println("Name: " + name);
        System.out.println("Phone No: " + phoneNo);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);

        // Clear fields after adding
        clearAddForm();
    }

    // Handle Update Button Click
    @FXML
    private void handleUpdate() {
        // Get input values
        String name = updateCustomerNameField.getText().trim();
        String phoneNo = updatePhoneNoField.getText().trim();
        String email = updateEmailField.getText().trim();
        String address = updateAddressField.getText().trim();

        // Validate inputs
        if (name.isEmpty() || phoneNo.isEmpty() || email.isEmpty() || address.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Update customer logic (e.g., save to database)
        System.out.println("Updating Customer:");
        System.out.println("Name: " + name);
        System.out.println("Phone No: " + phoneNo);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);

        // Clear fields after updating
        clearUpdateForm();
    }

    // Handle Delete Button Click
    @FXML
    private void handleDelete() {
        // Get input values
        String name = deleteCustomerNameField.getText().trim();
        String phoneNo = deletePhoneNoField.getText().trim();
        String email = deleteEmailField.getText().trim();
        String address = deleteAddressField.getText().trim();

        // Validate inputs
        if (name.isEmpty() || phoneNo.isEmpty() || email.isEmpty() || address.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Delete customer logic (e.g., remove from database)
        System.out.println("Deleting Customer:");
        System.out.println("Name: " + name);
        System.out.println("Phone No: " + phoneNo);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);

        // Clear fields after deleting
        clearDeleteForm();
    }

    // Handle Cancel Button Click (Add Form)
    @FXML
    private void handleCancelAdd() {
        clearAddForm();
        hideAllForms();
    }

    // Handle Cancel Button Click (Update Form)
    @FXML
    private void handleCancelUpdate() {
        clearUpdateForm();
        hideAllForms();
    }

    // Handle Cancel Button Click (Delete Form)
    @FXML
    private void handleCancelDelete() {
        clearDeleteForm();
        hideAllForms();
    }

    // Handle Search Button Click (Update Form)
    @FXML
    private void handleUpdateSearch() {
        String searchText = updateSearchField.getText().trim();
        if (searchText.isEmpty()) {
            System.out.println("Please enter a search term.");
            return;
        }

        // Search logic (e.g., fetch customer details from database)
        System.out.println("Searching for customer: " + searchText);
        // Example: Populate fields with fetched data
        updateCustomerNameField.setText("Fetched Name");
        updatePhoneNoField.setText("Fetched Phone No");
        updateEmailField.setText("Fetched Email");
        updateAddressField.setText("Fetched Address");
    }

    // Handle Search Button Click (Delete Form)
    @FXML
    private void handleDeleteSearch() {
        String searchText = deleteSearchField.getText().trim();
        if (searchText.isEmpty()) {
            System.out.println("Please enter a search term.");
            return;
        }

        // Search logic (e.g., fetch customer details from database)
        System.out.println("Searching for customer: " + searchText);
        // Example: Populate fields with fetched data
        deleteCustomerNameField.setText("Fetched Name");
        deletePhoneNoField.setText("Fetched Phone No");
        deleteEmailField.setText("Fetched Email");
        deleteAddressField.setText("Fetched Address");
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
    }

    // Clear Delete Form fields
    private void clearDeleteForm() {
        deleteSearchField.clear();
        deleteCustomerNameField.clear();
        deletePhoneNoField.clear();
        deleteEmailField.clear();
        deleteAddressField.clear();
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