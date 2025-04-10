package com.coretech.coretech;

import Models.Admin;
import Models.Customer;
import db.AdminDAO;
import db.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Controller class for managing customer-related operations in the CoreTech application.
 * Handles adding, updating, and deleting customer records through a JavaFX user interface.
 */
public class MainCustomerController extends BaseController{
    public Button viewCustomerButton;

    // FXML elements for buttons
    /** Button to trigger the add customer form display. */
    @FXML
    private Button addCustomerButton;
    /** Button to trigger the update customer form display. */
    @FXML
    private Button updateCustomerButton;
    /** Button to trigger the delete customer form display. */
    @FXML
    private Button deleteCustomerButton;

    // FXML elements for Add Customer Form
    /** VBox containing the add customer form elements. */
    @FXML
    private VBox addCustomerForm;
    /** TextField for entering the customer's name in the add form. */
    @FXML
    private TextField addCustomerNameField;
    /** TextField for entering the customer's phone number in the add form. */
    @FXML
    private TextField addPhoneNoField;
    /** TextField for entering the customer's email in the add form. */
    @FXML
    private TextField addEmailField;
    /** TextArea for entering the customer's address in the add form. */
    @FXML
    private TextArea addAddressField;
    /** Button to submit the add customer form. */
    @FXML
    private Button addButton;
    /** Button to cancel the add customer operation. */
    @FXML
    private Button cancelAddButton;

    // FXML elements for Update Customer Form
    /** VBox containing the update customer form elements. */
    @FXML
    private VBox updateCustomerForm;
    /** TextField for searching a customer by email in the update form. */
    @FXML
    private TextField updateSearchField;
    /** Button to search for a customer in the update form. */
    @FXML
    private Button updateSearchButton;
    /** TextField for updating the customer's name. */
    @FXML
    private TextField updateCustomerNameField;
    /** TextField for updating the customer's phone number. */
    @FXML
    private TextField updatePhoneNoField;
    /** TextField for updating the customer's email. */
    @FXML
    private TextField updateEmailField;
    /** TextArea for updating the customer's address. */
    @FXML
    private TextArea updateAddressField;
    /** Button to submit the update customer form. */
    @FXML
    private Button updateButton;
    /** Button to cancel the update customer operation. */
    @FXML
    private Button cancelUpdateButton;

    // FXML elements for Delete Customer Form
    /** VBox containing the delete customer form elements. */
    @FXML
    private VBox deleteCustomerForm;
    /** TextField for searching a customer by email in the delete form. */
    @FXML
    private TextField deleteSearchField;
    /** Button to search for a customer in the delete form. */
    @FXML
    private Button deleteSearchButton;
    /** TextField displaying the customer's name in the delete form. */
    @FXML
    private TextField deleteCustomerNameField;
    /** TextField displaying the customer's phone number in the delete form. */
    @FXML
    private TextField deletePhoneNoField;
    /** TextField displaying the customer's email in the delete form. */
    @FXML
    private TextField deleteEmailField;
    /** TextArea displaying the customer's address in the delete form. */
    @FXML
    private TextArea deleteAddressField;
    /** Button to confirm customer deletion. */
    @FXML
    private Button deleteButton;
    /** Button to cancel the delete customer operation. */
    @FXML
    private Button cancelDeleteButton;

    /** Main content pane for displaying forms. */
    @FXML
    private StackPane contentPane;
    @FXML private VBox viewForm;

    @FXML private TextField viewCustomerSearchField;
    @FXML private Button viewCustomerSearchButton;

    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Integer> idColumn;
    @FXML private TableColumn<Customer, String> nameColumn;
    @FXML private TableColumn<Customer, String> emailColumn;
    @FXML private TableColumn<Customer, String> phoneColumn;
    @FXML private TableColumn<Customer, String> addressColumn;
    @FXML private TableColumn<Customer, Void> actionColumn;

    /**
     * Initializes the controller after the FXML file has been loaded.
     * Hides all forms and clears active button styles on startup.
     */

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
        // Hide all forms initially
        hideAllForms();
        // Set the default active button (e.g., none)
        clearActiveButtons();
    }

    /**
     * Sets the specified button as active by applying a highlighted style.
     *
     * @param activeButton the button to set as active, or null to clear all highlights
     */

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

    /**
     * Clears the active style from all navigation buttons, resetting them to default.
     */
    // Helper method to clear the active class from all buttons
    private void clearActiveButtons() {
        addCustomerButton.setStyle("-fx-background-color: white; " + "-fx-text-fill: #2293C3; " + "-fx-border-color: #2293C3"); // Reset to default style
        updateCustomerButton.setStyle("-fx-background-color: white; " + "-fx-text-fill: #2293C3; " + "-fx-border-color: #2293C3"); // Reset to default style
        deleteCustomerButton.setStyle("-fx-background-color: white; " + "-fx-text-fill: #2293C3; " + "-fx-border-color: #2293C3"); // Reset to default style
        viewCustomerButton.setStyle("-fx-background-color: white; -fx-text-fill: #2293C3; -fx-border-color: #2293C3;");

    }

    /**
     * Hides all customer management forms and removes them from layout management.
     */
    // Hide all forms
    private void hideAllForms() {
        addCustomerForm.setVisible(false);
        addCustomerForm.setManaged(false);
        updateCustomerForm.setVisible(false);
        updateCustomerForm.setManaged(false);
        deleteCustomerForm.setVisible(false);
        deleteCustomerForm.setManaged(false);
        viewForm.setVisible(false);
        viewForm.setManaged(false);
    }


    /**
     * Displays the add customer form and highlights the add customer button.
     */
    // Show Add Customer Form
    @FXML
    private void showAddCustomerForm() {
        hideAllForms();
        addCustomerForm.setVisible(true);
        addCustomerForm.setManaged(true);
        setActiveButton(addCustomerButton); // Highlight the Add button
    }

    /**
     * Displays the update customer form and highlights the update customer button.
     */
    // Show Update Customer Form
    @FXML
    private void showUpdateCustomerForm() {
        hideAllForms();
        updateCustomerForm.setVisible(true);
        updateCustomerForm.setManaged(true);
        setActiveButton(updateCustomerButton); // Highlight the Update button
    }

    /**
     * Displays the delete customer form and highlights the delete customer button.
     */
    // Show Delete Customer Form
    @FXML
    private void showDeleteCustomerForm() {
        hideAllForms();
        deleteCustomerForm.setVisible(true);
        deleteCustomerForm.setManaged(true);
        setActiveButton(deleteCustomerButton); // Highlight the Delete button
    }

    /**
     * Handles the addition of a new customer by collecting form data and saving it to the database.
     */
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

    /**
     * Searches for a customer by email and populates the update form with their details.
     */
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

    /**
     * Handles the update of an existing customer's details in the database.
     */
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

    /**
     * Searches for a customer by email and populates the delete form with their details.
     */
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

    /**
     * Disables all fields in the delete form after a customer is found or deleted.
     */
    private void disableDeleteFields() {
        deleteCustomerNameField.setDisable(true);
        deletePhoneNoField.setDisable(true);
        deleteEmailField.setDisable(true);
        deleteAddressField.setDisable(true);

    }

    /**
     * Handles the deletion of a customer from the database using their email.
     */
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

    /**
     * Displays an alert dialog with the specified type, title, and message.
     *
     * @param alertType the type of alert (e.g., INFORMATION, ERROR)
     * @param title the title of the alert dialog
     * @param message the message to display in the alert
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Cancels the add operation and clears the add form fields.
     */
    @FXML
    private void handleCancelAdd() {
        clearAddForm();
//        hideAllForms();
    }

    /**
     * Cancels the update operation and clears the update form fields.
     */
    @FXML
    private void handleCancelUpdate() {
        clearUpdateForm();
//        hideAllForms();
    }

    /**
     * Cancels the delete operation and clears the delete form fields.
     */
    @FXML
    private void handleCancelDelete() {
        clearDeleteForm();

//        hideAllForms();
    }


    /**
     * Clears all fields in the add customer form.
     */
    private void clearAddForm() {
        addCustomerNameField.clear();
        addPhoneNoField.clear();
        addEmailField.clear();
        addAddressField.clear();
    }

    /**
     * Clears all fields in the update customer form and disables the email field.
     */
    private void clearUpdateForm() {
        updateSearchField.clear();
        updateCustomerNameField.clear();
        updatePhoneNoField.clear();
        updateEmailField.clear();
        updateAddressField.clear();
        updateEmailField.setDisable(true); // Keep email field disabled

    }

    /**
     * Clears all fields in the delete customer form and disables them.
     */
    private void clearDeleteForm() {
        deleteSearchField.clear();
        deleteCustomerNameField.clear();
        deletePhoneNoField.clear();
        deleteEmailField.clear();
        deleteAddressField.clear();

        disableDeleteFields(); // Disable fields after deletion
    }

    /**
     * Handles navigation to the home section (placeholder implementation).
     */
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

    @FXML
    private void handleViewCustomer() {
        // Hide other forms
        hideAllForms();
        viewForm.setVisible(true);
        viewForm.setManaged(true);
        setActiveButton(viewCustomerButton); // if you have a button highlighter

        loadCustomers(); // Optional: Load table data
    }
    private void loadCustomers() {
        customerTable.getItems().clear();
//        List<Customer> customers = CustomerDAO.getAllCustomers(); // if you have DAO
//        customerTable.getItems().addAll(customers);
    }

    public void handleViewCustomerSearch(ActionEvent actionEvent) {
    }
}